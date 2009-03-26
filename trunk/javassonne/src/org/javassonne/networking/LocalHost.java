/**
 * Javassonne 
 *  http://code.google.com/p/javassonne/
 * 
 * @author [Add Name Here]
 * @date Mar 21, 2009
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

package org.javassonne.networking;

import org.javassonne.messaging.Notification;
import org.javassonne.networking.impl.LocalHostImpl;
import org.javassonne.networking.impl.RemoteHost.MODE;

/*
 * A Helper class that lets other developers very easily connect to the Local Host 
 * object
 */
public class LocalHost {

	public static void addClient(String clientURI) {
		LocalHostImpl.getInstance().addClient(clientURI);
	}

	public static boolean canClientsConnect() {
		return LocalHostImpl.getInstance().canClientsConnect();
	}

	public static String getName() {
		return LocalHostImpl.getInstance().getName();
	}

	public static MODE getStatus() {
		return LocalHostImpl.getInstance().getStatus();
	}

	public static String getURI() {
		return LocalHostImpl.getInstance().getURI();
	}

	public static void receiveNotification(String serializedNotification, String clientURI) {	
		LocalHostImpl.getInstance().receiveNotificationFromClient(serializedNotification, clientURI);
	}
	
	public static boolean isLocalHostStarted() {
		return LocalHostImpl.getInstance().isLocalHostStarted();
	}
	
}