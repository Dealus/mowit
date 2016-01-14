package org.dealus.mowit

import groovy.transform.TypeChecked

import org.dealus.mowit.exceptions.OrientationException
/**
 * An enumeration for the 4 cardinal points, North, East, South and West which is able to
 * change its value clockwise and counter clockwise using next() and previous() methods
 * @author Nicolas Carlot
 */
@TypeChecked
public enum Orientation {
	NORTH('N'), EAST('E'), SOUTH('S'), WEST('W')
	private String representation

	private Orientation(String representation) {
		if(!representation ==~ "[NESW]{1}") {
			throw new OrientationException(representation)
		}
		this.representation = representation
	}

	/**
	 * Change the orientation value to the next possible, looping through if necessary
	 * <br>Values are ORTH('N'), EAST('E'), SOUTH('S'), WEST('W')
	 * @return this Orientation
	 */
	Orientation next() {
		Orientation.values()[(this.ordinal() + 1) % Orientation.values().size()]
	}

	/**
	 * Change the orientation value to the previous possible, looping through if necessary
	 * <br>Values are ORTH('N'), EAST('E'), SOUTH('S'), WEST('W')
	 * @return this Orientation
	 */
	Orientation previous() {
		Orientation.values()[(this.ordinal() - 1) % Orientation.values().size()]
	}

	/**
	 * @return this orientation value as a 2 dimensional vector X,Y 
	 */
	int[] getOrientationVector() {
		int[] vector
		switch(this) {
			case NORTH:
				vector = [0, 1]
				break
			case EAST:
				vector = [1, 0]
				break
			case SOUTH:
				vector = [0, -1]
				break
			case WEST:
				vector = [-1, 0]
				break
			default:
				vector = [0, 0]
		}
		return vector
	}

	String toString() {
		representation
	}
	
	/**
	 * Constructs an Orientation enumeration object based on the given representation
	 * Allowed representations are 
	 * <ul>
	 * 	<li>N for North</li>
	 * 	<li>E for East</li>
	 * 	<li>S for South</li>
	 * 	<li>W for West</li>
	 * </ul>
	 * @param representation The letter that represent the Orientation enumeration value
	 */
	static Orientation getOrientation(String value) {
		Orientation o =
		this.values().find {
			it.representation == value
		}
		if(!o) {
			throw new OrientationException(value)
		}
		return o
	}

}
