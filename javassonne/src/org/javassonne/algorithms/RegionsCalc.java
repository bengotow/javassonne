/**
 * Javassonne 
 *  http://code.google.com/p/javassonne/
 * 
 * @author Kyle Prete
 * @date Mar 22, 2009
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

/**
 * 
 */

package org.javassonne.algorithms;

import java.awt.Point;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javassonne.messaging.Notification;
import org.javassonne.messaging.NotificationManager;
import org.javassonne.model.Meeple;
import org.javassonne.model.Tile;
import org.javassonne.model.TileBoardGenIterator;
import org.javassonne.model.TileBoardIterator;
import org.javassonne.model.TileFeatureBindings;
import org.javassonne.ui.GameState;

/**
 * @author Kyle Prete Note: If the board this RegionsCalc is indirectly attached
 *         to is changed, the RegionsCalc must be destroyed as all its data is
 *         corrupt.
 * 
 *         May enforce this by listening for board_changed events.
 */
public class RegionsCalc {

	public RegionsCalc() {
		marked_ = new HashMap<Point, EnumMap<Tile.Region, Integer>>();
		globalMeep_ = new HashMap<Point, EnumMap<Tile.Region, List<Meeple>>>();
		isComplete_ = new HashMap<Point, EnumMap<Tile.Region, Boolean>>();

	}

	public void traverseRegion(TileBoardIterator iter, Tile.Region reg) {
		HashMap<Point, ArrayList<Tile.Region>> list = new HashMap<Point, ArrayList<Tile.Region>>();
		ArrayList<Meeple> meeps = new ArrayList<Meeple>();
		boolean returnVal = traverseRegion(iter, reg, meeps, list, true);
		int total = list.keySet().size();
		for (Point p : list.keySet()) {

			if (marked_.get(p) == null)
				marked_.put(p, new EnumMap<Tile.Region, Integer>(Tile.Region.class));
			if (globalMeep_.get(p) == null)
				globalMeep_.put(p, new EnumMap<Tile.Region, List<Meeple>>(Tile.Region.class));
			if (isComplete_.get(p) == null)
				isComplete_.put(p, new EnumMap<Tile.Region, Boolean>(Tile.Region.class));

			for (Tile.Region r : list.get(p)) {
				marked_.get(p).put(r, total);
				globalMeep_.get(p).put(r, meeps);
				isComplete_.get(p).put(r, returnVal);
			}
		}
		return;

	}

	private boolean traverseRegion(TileBoardIterator iter, Tile.Region reg,
			List<Meeple> meeps, HashMap<Point, ArrayList<Tile.Region>> list,
			boolean returnVal) {
		if (iter.current() == null)
			return false;
		// else if current feature is null, quit
		if (iter.current().featureInRegion(reg) == null)
			return returnVal;
		// else
		if (getsizeOfRegion(iter.getLocation(), reg) != -1)
			return returnVal;
		// else
		if (reg.equals(Tile.Region.Center)) {

			// test for not null
			if (iter.current().featureInRegion(reg) != null) {
				// meeple?
				Meeple meep1 = iter.current().meepleInRegion(reg);
				if (meep1 != null)
					meeps.add(meep1);
				// addme to list
				if (list.get(iter.getLocation()) == null)
					list.put(iter.getLocation(), new ArrayList<Tile.Region>());
				list.get(iter.getLocation()).add(reg);
				// check for completion
				TileBoardGenIterator temp = new TileBoardGenIterator(iter);
				if (temp.right().current() == null) {
					return false;
				} else if (temp.down().current() == null) {
					return false;
				} else if (temp.left().current() == null) {
					return false;
				} else if (temp.left().current() == null) {
					return false;
				} else if (temp.up().current() == null) {
					return false;
				} else if (temp.up().current() == null) {
					return false;
				} else if (temp.right().current() == null) {
					return false;
				} else if (temp.right().current() == null) {
					return false;
				}
				// never found null tile, so monastery is complete
				if (marked_.get(iter.getLocation()) == null)
					marked_.put(iter.getLocation(),
							new EnumMap<Tile.Region, Integer>(Tile.Region.class));
				marked_.get(iter.getLocation()).put(reg, 9);
				return true;
			}
			// center feature is null - pass back returnVal
			return returnVal;

		}
		// else{
		if (marked_.get(iter.getLocation()) == null)
			marked_
					.put(iter.getLocation(),
							new EnumMap<Tile.Region, Integer>(Tile.Region.class));
		marked_.get(iter.getLocation()).put(reg, 0);
		if (list.get(iter.getLocation()) == null)
			list.put(iter.getLocation(), new ArrayList<Tile.Region>());
		list.get(iter.getLocation()).add(reg);
		Meeple current = iter.current().meepleInRegion(reg);
		if (current != null)
			meeps.add(current);

		Tile.Region newReg;
		// traverse to next tile
		if (reg.equals(Tile.Region.Left)) {
			newReg = Tile.Region.Right;
			returnVal = traverseRegion(
					((TileBoardGenIterator) iter).leftCopy(), newReg, meeps,
					list, returnVal)
					&& returnVal;
		} else if (reg.equals(Tile.Region.Right)) {
			newReg = Tile.Region.Left;
			returnVal = traverseRegion(((TileBoardGenIterator) iter)
					.rightCopy(), newReg, meeps, list, returnVal)
					&& returnVal;
		} else if (reg.equals(Tile.Region.Top)) {
			newReg = Tile.Region.Bottom;
			returnVal = traverseRegion(((TileBoardGenIterator) iter).upCopy(),
					newReg, meeps, list, returnVal)
					&& returnVal;
		} else if (reg.equals(Tile.Region.Bottom)) {
			newReg = Tile.Region.Top;
			returnVal = traverseRegion(
					((TileBoardGenIterator) iter).downCopy(), newReg, meeps,
					list, returnVal)
					&& returnVal;
		}
		// if feature does not end traversal
		// traverse to other regions in Tile except center
		TileFeatureBindings bindings_ = GameState.getInstance().getDeck()
				.tileFeatureBindings();

		if (!iter.current().featureInRegion(reg).endsTraversal) {
			for (Tile.Region r : Tile.Region.values()) {
				if (bindings_.featuresBind(iter.current()
						.featureIdentifierInRegion(r), iter.current()
						.featureIdentifierInRegion(reg))
						&& !r.equals(Tile.Region.Center)) {
					returnVal = traverseRegion(iter, r, meeps, list, returnVal)
							&& returnVal;
				}
			}
		}

		return returnVal;
		// }

	}

	// If traverseRegion has touched given region of Tile at given location
	// This function returns the size of the region, else, returns -1
	public Integer getsizeOfRegion(Point loc, Tile.Region reg) {
		Map<Tile.Region, Integer> tileRegions = marked_.get(loc);
		if (tileRegions == null)
			return -1;

		Integer temp = tileRegions.get(reg);
		if (temp == null)
			return -1;

		return temp;
	}

	public List<Meeple> getMeepleList(Point loc, Tile.Region reg) {
		ArrayList<Meeple> returnVal = new ArrayList<Meeple>();
		Map<Tile.Region, List<Meeple>> tileRegions = globalMeep_.get(loc);
		if (tileRegions == null)
			return returnVal;

		List<Meeple> temp = tileRegions.get(reg);
		if (temp == null)
			return returnVal;
		
		returnVal.addAll(temp);
		return returnVal;
	}

	public boolean getRegionCompletion(Point loc, Tile.Region reg) {
		Map<Tile.Region, Boolean> tileRegions = isComplete_.get(loc);
		if (tileRegions == null)
			return false;
		Boolean temp = tileRegions.get(reg);
		if (temp == null)
			return false;
		return temp;
	}

	// Keeps track of touched locations
	private HashMap<Point, EnumMap<Tile.Region, Integer>> marked_;
	private HashMap<Point, EnumMap<Tile.Region, List<Meeple>>> globalMeep_;
	private HashMap<Point, EnumMap<Tile.Region, Boolean>> isComplete_;

}