package org.dealus.mowit.exceptions

import org.dealus.mowit.Lawn
import org.dealus.mowit.Mower

class MowerOutOfLawnBoundariesException extends RuntimeException {
	
	MowerOutOfLawnBoundariesException(Mower mower, Lawn lawn) {
		super("Mower position (${mower?.posX}x${mower?.posY}) is incorect regarding the lawn's dimensions (${lawn?.width}x${lawn?.height})")
	}
}
