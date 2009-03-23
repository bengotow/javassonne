/**
 * Javassonne 
 *  http://code.google.com/p/javassonne/
 * 
 * @author Hamilton Turner
 * @date Mar 6, 2009
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
import org.javassonne.networking.impl.Client;
import org.javassonne.networking.impl.RemoteHost;
import org.javassonne.networking.impl.RemotingUtils;

/**
 * A bootstrapper used to very simply test the networking
 * 
 * @author Hamilton Turner
 */
public class TestLauncher {

	public static void main(String[] args) {

		String hostURI = "rmi://129.59.82.77:5099/JavassonneHost_demetri-d5042f7";
		HostMonitor.getInstance().addHost(hostURI);
		
		
		
		//Client cl = new Client("a");
		//cl.connectToHost(LocalHost.getURI());
		

		// TODO change code so that it makes sure the name has no spaces
		// or fix underlying imp
		//Client clb = new Client("b");
		//clb.connectToHost(LocalHost.getURI());
		

		// TODO works with a serializable object, but not with a non
		// Object boo = new Object();
		//String boo = new String();
		//Notification n = new Notification("test notification", boo);
		//cl.sendNotificationToHost(n);
	}
}
