/**
 * Javassonne 
 *  http://code.google.com/p/javassonne/
 * 
 * @author David Leinweber
 * @date Jan 14, 2009
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

package org.javassonne.ui.controllers;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import javax.imageio.ImageIO;

import org.javassonne.messaging.Notification;
import org.javassonne.messaging.NotificationManager;
import org.javassonne.model.BoardPositionFilledException;
import org.javassonne.model.NotValidPlacementException;
import org.javassonne.model.Tile;
import org.javassonne.model.TileBoard;
import org.javassonne.model.TileBoardGenIterator;
import org.javassonne.model.TileBoardIterator;
import org.javassonne.ui.DisplayHelper;
import org.javassonne.ui.map.TilePlacementSprite;
import org.javassonne.ui.panels.HUDConfirmPlacementPanel;

public class BoardController {

	private static final String tempTileImagesFolder_ = "images";
	private static final String litTileIdentifier_ = "background_tile_highlighted.jpg";

	Tile tempLitTile_;
	Tile tempPlacedTile_;
	TilePlacementSprite tempPlacementSprite_;
	TileBoardGenIterator tempLocationIter_;

	Tile tileInHand_;
	TileBoard board_;

	/**
	 * The BoardController will handle interaction between the board model and
	 * board views in the interface. For instance, clicking the board, placing
	 * meeple, zooming in and out will be handled here.
	 * 
	 * @param b
	 *            The TileBoard. This will never be changed once the game has
	 *            begun.
	 */
	public BoardController(TileBoard b) {

		board_ = b;
		tempLitTile_ = new Tile();
		try {
			tempLitTile_.setImage(ImageIO.read(new File(String.format("%s/%s",
					tempTileImagesFolder_, litTileIdentifier_))));
		} catch (IOException ex) {
			// TODO: Fix this
			ex.printStackTrace();
		}

		NotificationManager.getInstance().addObserver(Notification.PLACE_TILE,
				this, "placeTile");
		NotificationManager.getInstance().addObserver(
				Notification.UNDO_PLACE_TILE, this, "undoPlaceTile");
		NotificationManager.getInstance().addObserver(
				Notification.TILE_IN_HAND_CHANGED, this, "updateTileInHand");
		NotificationManager.getInstance().addObserver(Notification.END_GAME,
				this, "endGame");
		NotificationManager.getInstance().addObserver(Notification.END_TURN,
				this, "endTurn");

		// Now that we have a board object, we want to update the interface to
		// show the board. Share our board_ object in a notification so the
		// views can get it and display it.
		NotificationManager.getInstance().sendNotification(
				Notification.BOARD_SET, board_);

	}

	public void endGame(Notification n) {
		// Unsubscribe from notifications once the game has ended
		NotificationManager.getInstance().removeObserver(this);

		// let go of the board and the tileInhand. They should not be used
		// once this notification is received and setting to null allows
		// us to make sure this is followed.
		board_ = null;
		tileInHand_ = null;
	}

	public void endTurn(Notification n) {
		// update the tile's status so that it is now permanent
		try {
			board_.removeTempStatus(tempLocationIter_);
			board_.removeTemps();
		} catch (NotValidPlacementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// remove the placement sprite if it exists
		if (tempPlacementSprite_ != null)
			NotificationManager.getInstance().sendNotification(
					Notification.MAP_REMOVE_SPRITE, tempPlacementSprite_);

		tempPlacementSprite_ = null;
		tempPlacedTile_ = null;
		tempLocationIter_ = null;
	}

	public void placeTile(Notification n) {
		if (tileInHand_ != null) {
			Point here = (Point) (n.argument());
			tempLocationIter_ = new TileBoardGenIterator(board_, here);

			try {
				if (board_.isValidPlacement(tempLocationIter_, tileInHand_)) {

					// remove the tile from our hand
					tempPlacedTile_ = tileInHand_;
					NotificationManager.getInstance().sendNotification(
							Notification.TILE_IN_HAND_CHANGED, null);

					// add the tile to the board
					board_.removeTemps();
					board_.addTemp(tempLocationIter_, tempPlacedTile_);

					// show the confirm placement panel
					HUDConfirmPlacementPanel confirmPanel = new HUDConfirmPlacementPanel();
					DisplayHelper.getInstance().add(confirmPanel,
							DisplayHelper.Layer.PALETTE,
							DisplayHelper.Positioning.TOP_CENTER);
					confirmPanel.attachMeeplePanels();

					// trigger an update of the board so the board is
					// re-rendered with the new temp-tiles in place.
					NotificationManager.getInstance().sendNotification(
							Notification.BOARD_SET, board_);

					// highlight the tile on the map and show placement options
					tempPlacementSprite_ = new TilePlacementSprite(here);

					// TODO: Determine which spots on the tile are valid meeple
					// locations and populate the options arrays. This is dummy
					// code:
					ArrayList<Tile.Region> options = new ArrayList<Tile.Region>();
					options.add(Tile.Region.Left);
					options.add(Tile.Region.Top);
					options.add(Tile.Region.Right);
					options.add(Tile.Region.Bottom);
					options.add(Tile.Region.Center);
					tempPlacementSprite_.setRegionOptions(options);

					// Add the placement indicator to the map
					NotificationManager.getInstance().sendNotification(
							Notification.MAP_ADD_SPRITE, tempPlacementSprite_);
				}

			} catch (BoardPositionFilledException ex) {
				// Bury this exception?
				NotificationManager.getInstance().logError(
						new Notification("PositionFilled", here.toString()
								+ " is filled"));
				return;
			}
		}
	}

	public void undoPlaceTile(Notification n) {
		board_.removeTemps();
		NotificationManager.getInstance().sendNotification(
				Notification.TILE_IN_HAND_CHANGED, tempPlacedTile_);
		NotificationManager.getInstance().sendNotification(
				Notification.MAP_REMOVE_SPRITE, tempPlacementSprite_);

		tileInHand_ = null;
		tempPlacementSprite_ = null;
		tempPlacedTile_ = null;
		tempLocationIter_ = null;
	}

	public void updateTileInHand(Notification n) {
		Boolean tileHasChanged = false;
		Tile t = (Tile) n.argument();

		if ((tileInHand_ == null)
				|| ((t != null) && (t.getUniqueIdentifier() != tileInHand_
						.getUniqueIdentifier())))
			tileHasChanged = true;

		tileInHand_ = t;

		// Do we need to populate possible locations?
		if (tileHasChanged && t != null) {
			try {
				Set<TileBoardIterator> temp = board_.possiblePlacements(t);
				// If there are none, throw out TileInHand
				if (temp.isEmpty()) {
					NotificationManager.getInstance().sendNotification(
							Notification.LOG_WARNING,
							"Tile does not fit on board; drawing new Tile");
					NotificationManager.getInstance().sendNotification(
							Notification.DRAW_TILE);

					// Otherwise, add the possibles
				} else {
					board_.addTemps(temp, tempLitTile_);
					NotificationManager.getInstance().sendNotification(
							Notification.BOARD_SET, board_);
				}
			} catch (BoardPositionFilledException e) {
				// we really shouldn't get here if possible placements works
				// correctly
				e.printStackTrace();
			}
		}
	}

}
