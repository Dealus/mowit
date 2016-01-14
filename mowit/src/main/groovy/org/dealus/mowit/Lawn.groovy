package org.dealus.mowit

import groovy.transform.TypeChecked

import org.dealus.mowit.exceptions.MowerOutOfLawnBoundariesException

/**
 * @author Nicolas Carlot
 * The Lawn object has the responsibility to run the mowers across it
 * A mower can not go outside the lawn, thus it will not move if facing 
 * an edge of the lawn and trying to move forward.
 *
 */

@TypeChecked
class Lawn {
	int width
	int height

	Collection<Mower> allMowers = []

	/**
	 * Add the given mower to the field
	 * @param mower
	 */
	void addMower(Mower mower) {
		if(mower.posX < 0 || mower.posX >= width || mower.posY < 0 || mower.posY >= height) {
			throw new MowerOutOfLawnBoundariesException(mower, this)
		}
		allMowers << mower
	}

	/**
	 * Removes all mowers from the lawn (quite useful for tests)
	 */
	void clear() {
		allMowers = []
	}

	/**
	 * Run the mowers on the lawn regarding their instructions
	 */
	void runMowers() {
		allMowers.each { Mower mower ->
			mower.instructions.each { String singleInstruction ->
				switch(singleInstruction) {
					case 'A':
						advanceMowerIfPossible(mower)
						break
					case 'G':
						mower.rotateLeft()
						break
					case 'D':
						mower.rotateRight()
						break
					default:
						break
				}
			}
		}
	}

	/**
	 * Retrieve all mowers position
	 * @return a list of string representing the position of a all mowers
	 */
	List<String> getAllMowersPositions() {
		allMowers.collect { it.position }
	}

	/**
	 * Will make the mower go forward regarding its orientation.
	 * If the mower should get out the lawn, its position won't change.
	 * @param mower The mower to advance
	 */
	private advanceMowerIfPossible(Mower mower) {
		int newPosX = mower.orientation.orientationVector[0] + mower.posX
		int newPosY = mower.orientation.orientationVector[1] + mower.posY
		if(newPosX >= 0 && newPosX < width && newPosY >= 0 && newPosY < height) {
			mower.advance()
		}
	}
}
