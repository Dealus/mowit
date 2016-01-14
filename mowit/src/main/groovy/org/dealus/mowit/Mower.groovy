package org.dealus.mowit

import groovy.transform.AutoClone
import groovy.transform.TypeChecked

import org.dealus.mowit.exceptions.IncorrectInstructionsException

/**
 * The Mower object keep track of its position and orientation regarding a 2 dimensional square grid
 * It is capable of advancing (i.e changing its coordinates depending on orientation at this moment)
 * and of turning left or right to orientate itself North, East, South or West
 * The Mower holds a set of instruction as a String respecting the following regex: [DGA]+
 * @author Nicolas Carlot
 */
@AutoClone
@TypeChecked
class Mower {
	def final static INSTRUCTIONS_PATTERN = "[AGD]+"
	int posX = 0
	int posY = 0
	Orientation orientation
	private String instructions
	
	/**
	 * Sets the Mower's instructions which must follow the INSTRUCTIONS_PATTERN
	 * @param instructions Given instructions for the mower
	 */
	public void setInstructions(String instructions) {
		if(!(instructions ==~ INSTRUCTIONS_PATTERN)) {
			throw new IncorrectInstructionsException(instructions)
		}
		this.instructions = instructions
	}
	
	/**
	 * Rotates the Mower to its left (4 directions orientation, North, East, South or West)
	 */
	void rotateLeft() {
		orientation = orientation.previous()
	}
	
	/**
	 * Rotates the Mower to its right (4 directions orientation, North, East, South or West)
	 */
	void rotateRight() {
		orientation = orientation.next()
	}
	
	/**
	 * Advance the mower forward (thus depending on its current orientation)
	 */
	void advance() {
		posX += orientation.orientationVector[0]
		posY += orientation.orientationVector[1]
	}
	
	/**
	 * 
	 * @return The current position and orientation of this Mower formatted as "X Y O"
	 */
	String getPosition() {
		"${posX} ${posY} ${orientation}"
	}
}
