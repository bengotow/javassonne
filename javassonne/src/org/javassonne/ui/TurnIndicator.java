/**
 * Javassonne 
 *  http://code.google.com/p/javassonne/
 * 
 * @author David Leinweber
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

package org.javassonne.ui;

import javax.swing.JLabel;

import org.javassonne.model.Player;

public class TurnIndicator extends JLabel {
	private Player curPlayer_;
	private static int MAX_SIZE = 6;

	public TurnIndicator(String text) {
		super(text);
		curPlayer_ = new Player();
	}

	public void nextPlayer() {
		curPlayer_.setTurnNumber_(curPlayer_.getTurnNumber() % MAX_SIZE + 1);
	}

	public int getPlayerTurn() {
		return curPlayer_.getTurnNumber();
	}

}