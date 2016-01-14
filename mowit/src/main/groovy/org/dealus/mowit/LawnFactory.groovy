package org.dealus.mowit

import groovy.util.logging.Slf4j

import java.util.regex.PatternSyntaxException

import org.dealus.mowit.exceptions.LawnFactoryParsingException
import org.dealus.mowit.exceptions.OrientationException


/**
 * A factory for the {@link Lawn} object
 * @author Nicolas Carlot
 */
@Slf4j
public class LawnFactory {


	/**
	 * Parse the given content string to produce a {@link Lawn} object
	 * Blank spaces at beginning and end of lines are automatically removed
	 * @param content The string that describe the lawn and mowers initialization
	 * @param debugMode Set to true to display error stack traces
	 * @return A {@link Lawn} object
	 * @see {@link src/main/resources/FileSample.txt} for a sample of input string
	 */
	public static Lawn parse(String content, boolean debugMode = false) {
		Lawn lawn
		int countLines = -1
		try {
			Mower mower
			content.readLines().each { String line ->
				line = line.trim()
				if(line) {
					int lineType = countLines % 2
					switch(lineType) {
						case -1: // Lawn dimensions
							lawn = new Lawn(width: 1 + (line.split(" ")[0] as int), height: 1 + (line.split(" ")[1] as int))
							break
						case 0: // Mower position/orientation
							mower = new Mower(posX: line.split(" ")[0] as int, posY: line.split(" ")[1] as int, orientation: Orientation.getOrientation(line.split(" ")[2]))
							break
						case 1: // Mower instructionsS
							mower.instructions = line
							lawn.addMower(mower)
							break
					}
					countLines++
				}
			}
		}
		catch(PatternSyntaxException | NumberFormatException | OrientationException | ArrayIndexOutOfBoundsException e) {
			log.info("Wrong file format. Use option -s to view a sample, option -d for error details")
			if(debugMode) {
				log.debug("File could not be read at line ${countLines+2}", e)
			}
			throw new LawnFactoryParsingException()
		}
		return lawn;
	}

}
