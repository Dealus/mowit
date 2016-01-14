package org.dealus.mowit.exceptions

class OrientationException extends RuntimeException {

	OrientationException(String orientationValue) {
		super("Orientation should be one of N, E, S or W. Value passed was ${orientationValue}")
	}
	
}
