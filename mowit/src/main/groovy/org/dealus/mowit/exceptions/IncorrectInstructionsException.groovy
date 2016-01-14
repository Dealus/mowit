package org.dealus.mowit.exceptions

import org.dealus.mowit.Mower

class IncorrectInstructionsException extends RuntimeException {
	
	IncorrectInstructionsException(String instructions) {
		super("Given instructions '${instructions}' arn't respecting the expected format '${Mower.INSTRUCTIONS_PATTERN}'")
	}
}
