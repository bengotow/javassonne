/**
 * Javassonne 
 *  http://code.google.com/p/javassonne/
 * 
 * @author Hamilton Turner
 * @date Mar 5, 2009
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

package org.javassonne.networking.impl;

/**
 * This is the networking only interface of the client. Only put functions here
 * that a remote host or client should be able to call.
 * 
 * @author Hamilton Turner
 */
public interface RemoteClient {
	public static final String SERVICENAME = "JavassonneClient";

	/**
	 * Allows the host to send an arbitrary notification to the client. Note
	 * that internally the client chooses to allow or deny this notification.
	 * The host serializes the notification before sending.
	 * 
	 * @param n
	 *            The notification to send
	 */
	public void receiveNotificationFromHost(String serializedNotification);

	/**
	 * Allows an arbitrary host to get the clients current URI.
	 * 
	 * @return the RMI safe client URI
	 */
	public String getURI();

	/**
	 * Query for what name this player has chosen
	 * 
	 * @return the name of this player in the game
	 */
	public String getName();

	/**
	 * Used for a host to let a client know that it has been added to the list
	 * of connected clients
	 */
	public void addClientACK();

	/**
	 * Used for a host to let a client know that it has been refused entry into
	 * the list of connected clients
	 */
	public void addClientNAK();
}
