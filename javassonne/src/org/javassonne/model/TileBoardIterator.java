/**
 * Javassonne 
 *  http://code.google.com/p/javassonne/
 * 
 * @author Kyle Prete
 * @date Jan 25, 2009
 * 
 * Copyright 2009 Javassonne Team
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License. 
 *  You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software 
 *  distributed under the License is distributed on an "AS IS" BASIS, 
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or 
 *  implied. See the License for the specific language governing 
 *  permissions and limitations under the License. 
 */

package org.javassonne.model;

import java.awt.Point;

public interface TileBoardIterator {

	/**
	 * @return Tile this iterator currently points to
	 */
	public abstract Tile current();

	/**
	 * @return reference to itself moved right in board
	 */
	public abstract TileBoardIterator right();

	/**
	 * @return reference to itself moved to start of next row
	 */
	public abstract TileBoardIterator nextRow();

	/**
	 * @return Point denoting coordinates of current() in board
	 */
	public abstract Point getLocation();

	/**
	 * @return reference to TileBoard this iterator belongs to
	 */
	public abstract TileBoard getData();

	/**
	 * @return true if TileBoardIterator is out of the bounds of the TileBoard
	 */
	public abstract boolean outOfBounds();

}