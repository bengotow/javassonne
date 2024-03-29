package org.javassonne.model;

/**
 * The TileFeature class is used to represent foreground items in the game.
 * These include roads, forts, cloisters, etc... It is a relatively simple
 * container class and leaves the user responsible for interpreting it's
 * properties. Each tile has five regions for tile features, and the TileSet
 * maintains an array of possible tile features.
 * 
 * @author bengotow
 */
public class TileFeature {
	public String name;
	public String identifier;
	
	public boolean actsAsWall;
	public boolean endsTraversal;

	public int farmPointValue;
	public int pointValue;
	
	public TileFeature() {
		this.name = "Untitled";
		this.identifier = "U";
		this.actsAsWall = false;
		this.pointValue = 1;
		this.farmPointValue = 0;
		this.endsTraversal = false;
	}

	public TileFeature(String name, String identifier, boolean actsAsWall,
			int pointValue) {
		this.name = name;
		this.identifier = identifier;
		this.actsAsWall = actsAsWall;
		this.pointValue = pointValue;
		this.endsTraversal = false;
	}

	/**
	 * @return A string description of the tile feature
	 */
	public String description() {
		return String.format("%s (Identifier: %s Wall: %b)", this.name,
				this.identifier, this.actsAsWall);
	}
}
