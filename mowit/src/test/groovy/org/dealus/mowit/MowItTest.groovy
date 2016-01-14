package org.dealus.mowit

import org.jspresso.contrib.tmar.core.Tmar4JUnit
import org.junit.Test

class MowItTest extends Tmar4JUnit {

	@Test
	void test() {
		
		def testTmar = getData('test')
		Map<String, Mower> mowers = [:]
		
		Map lawnDimensionsMap = testTmar.getTable("lawnDimensions")
		Map mowersMap = testTmar.getTable("mowersInitialPositions")
		Lawn lawn = new Lawn(height: 1 + lawnDimensionsMap["height"][0], width: 1 + lawnDimensionsMap["width"][0])
		[
			mowersMap["mowerName"],
			mowersMap["positionX"],
			mowersMap["positionY"],
			mowersMap["orientation"],
			mowersMap["instructions"]
		].transpose().each {
			Mower mower = new Mower(posX: it[1], posY: it[2], orientation: Orientation.getOrientation(it[3]), instructions: it[4])
			mowers << [(it[0]) : mower]
		}

		eachIteration(testTmar) { tmar ->
			switch(tmar.run) {
				case "All mowers":
					lawn.clear()
					mowers.values().each {
						lawn.addMower(it.clone())
					}
					lawn.runMowers()
					tmar.finalPosition = lawn.getAllMowersPositions().join(", ")
					break
				default :
					lawn.clear()
					lawn.addMower(mowers[tmar.run].clone())
					lawn.runMowers()
					tmar.finalPosition = lawn.getAllMowersPositions().join(", ")
					break
			}
		}
	}

	@Test
	  void testLawnFactory() {
	    eachIteration('testLawnFactory') { tmar ->
			String configString = tmar.inputString
			try {
			Lawn resultingLawn = LawnFactory.parse(configString)
			tmar.resultingLawn = resultingLawn ? "Lawn of dimention ${resultingLawn.width}x${resultingLawn.height} with ${resultingLawn.allMowers.size()} Mowers" : null
			int mowercount = 1
			tmar.resultingMowers = resultingLawn ? resultingLawn.getAllMowersPositions().collect { "Mower${mowercount++}(${it})" }.join(", ") : null
			}
			catch(Exception e) {
				tmar.error = "${e.getClass().getName()}: ${e.message}"
			}
		}
	}
}
