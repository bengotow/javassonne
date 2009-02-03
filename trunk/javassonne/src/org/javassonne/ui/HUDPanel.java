/**
 * Javassonne 
 *  http://code.google.com/p/javassonne/
 * 
 * @author Brian Salisbury
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

package org.javassonne.ui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.javassonne.messaging.Notification;
import org.javassonne.messaging.NotificationManager;
import org.javassonne.model.Tile;
import org.javassonne.ui.control.JImagePanel;
import org.javassonne.ui.control.JKeyListener;

/**
 * The primary JPanel in the HUD
 * 
 * @author bengotow
 * 
 */
public class HUDPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static final String DRAW_NEXT_TILE = "Draw";
	private static final String ZOOM_OUT = "Zoom Out";
	private static final String ZOOM_IN = "Zoom In";
	private static final String EXIT_GAME = "Exit Game";
	private static final String LOAD_GAME = "Load Game";
	private static final String NEW_GAME = "New Game";

	private JButton newGameButton_;
	private JButton loadGameButton_;
	private JButton exitGameButton_;
	private JButton zoomInButton_;
	private JButton zoomOutButton_;
	private JButton drawTile_;
	private JButton rotateRight_;
	private JButton rotateLeft_;

	private JImagePanel curTileImage_;
	private TurnIndicator playerTurn_;
	private BufferedImage background_;
	private JKeyListener keyListener_;

	public HUDPanel() {
		setVisible(true);
		setSize(140, 400);

		keyListener_ = new JKeyListener();
		this.addKeyListener(keyListener_);

		// Create all of the components that will be shown

		newGameButton_ = new JButton(NEW_GAME);
		newGameButton_.setActionCommand(Notification.NEW_GAME);
		newGameButton_.addActionListener(this);

		loadGameButton_ = new JButton(LOAD_GAME);
		loadGameButton_.setActionCommand(Notification.LOAD_GAME);
		loadGameButton_.addActionListener(this);

		exitGameButton_ = new JButton(EXIT_GAME);
		exitGameButton_.setActionCommand(Notification.EXIT_GAME);
		exitGameButton_.addActionListener(this);

		zoomInButton_ = new JButton(ZOOM_IN);
		zoomInButton_.setActionCommand(Notification.ZOOM_IN);
		zoomOutButton_ = new JButton(ZOOM_OUT);
		zoomOutButton_.setActionCommand(Notification.ZOOM_OUT);

		drawTile_ = new JButton(DRAW_NEXT_TILE);

		rotateRight_ = new JButton("=>");
		rotateRight_.setActionCommand(Notification.TILE_ROTATE_RIGHT);
		rotateRight_.addActionListener(this);

		rotateLeft_ = new JButton("<=");
		rotateLeft_.setActionCommand(Notification.TILE_ROTATE_LEFT);
		rotateLeft_.addActionListener(this);

		playerTurn_ = new TurnIndicator("Player " + 1 + "'s Turn");

		curTileImage_ = new JImagePanel(null, 50, 50);

		// attach all of the components to the JFrame

		add(playerTurn_);
		add(newGameButton_);
		add(loadGameButton_);
		add(exitGameButton_);
		add(zoomInButton_);
		add(zoomOutButton_);
		add(rotateLeft_);
		add(curTileImage_);
		add(new JLabel("             "));
		add(rotateRight_);
		add(drawTile_);

		// Subscribe for notifications from the controller so we know when to
		// update ourselves!
		NotificationManager.getInstance().addObserver(
				Notification.TILE_IN_HAND_CHANGED, this, "updateTileInHand");
	}

	/*
	 * This function is responsible for painting the background image we have.
	 */
	public void paintComponent(Graphics g) {
		if (background_ == null) {
			try {
				background_ = ImageIO.read(new File(
						"images/bottom_bar_background.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(background_, 0, 0, this.getWidth(), this.getHeight(), 0,
				0, background_.getWidth(), background_.getHeight(), null);
	}

	/*
	 * Whenever a button is pressed in our panel, we want to send a notification
	 * so the button press can be handled by the HUDController (and possibly
	 * elsewhere - the BoardController handles zoom in and zoom out). We've
	 * wired things up so that each button sends a notification with the same
	 * name as it's actionCommand.
	 */
	public void actionPerformed(ActionEvent e) {
		NotificationManager.getInstance()
				.sendNotification(e.getActionCommand());
	}

	// Notification Handlers

	public void updateTileInHand(Notification n) {
		Tile t = (Tile) n.argument();
		curTileImage_.setImage(t.getImage());

		this.invalidate();
	}

	// Overloaded the add() method to bind a key listener to any elements placed
	// within the JPanel
	public Component add(Component comp) {
		super.add(comp);
		comp.addKeyListener(keyListener_);
		return comp;
	}
}