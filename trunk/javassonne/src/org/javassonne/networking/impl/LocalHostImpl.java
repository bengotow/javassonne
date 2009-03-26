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

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import org.javassonne.messaging.Notification;
import org.javassonne.messaging.NotificationManager;
import org.javassonne.networking.HostMonitor;
import org.springframework.remoting.RemoteLookupFailureException;

import com.thoughtworks.xstream.XStream;

/**
 * The implementation of our local host. Note that the LocalHost class is an
 * easy way to call through to all of these classes
 * 
 * Internally this keeps a small local cache of client URI's, which allows us to
 * prevent many redundant network calls
 * 
 * @author Hamilton Turner
 */
public class LocalHostImpl implements RemoteHost {

	private List<RemoteClient> connectedClients_;
	private List<String> connectedClientURIs_;
	private RemoteHost.MODE currentMode_;
	private static LocalHostImpl instance_ = null;

	protected String URI_;
	private String realName_;
	protected String rmiSafeName_;
	// A flag that indicates whether or not
	// this host can be connected to
	private boolean clientsCanConnect_;

	protected boolean isLocalHostStarted_;
	private XStream xStream_;

	public static LocalHostImpl getInstance() {
		if (instance_ == null)
			instance_ = new LocalHostImpl();
		return instance_;
	}

	// TODO - this needs to get a hostName from the preferences manager
	private LocalHostImpl() {
		connectedClients_ = new ArrayList<RemoteClient>();
		clientsCanConnect_ = false;
		URI_ = null;
		currentMode_ = RemoteHost.MODE.IN_LOBBY;
		isLocalHostStarted_ = false;
		xStream_ = new XStream();
		connectedClientURIs_ = new ArrayList<String>();

		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		realName_ = addr.getHostName();
		rmiSafeName_ = realName_.replace(' ', '_');

		Timer t = new Timer("Host Starter");
		t.schedule(new HostStarter(), 0);
	}

	/**
	 * @see org.javassonne.networking.impl.RemoteHost
	 */
	public void addClient(String clientURI) {
		if (clientsCanConnect_ == false) {

			String info = "LocalHostImpl: Client " + clientURI
					+ " attempted  to "
					+ "connect to our host while our host was not "
					+ "accepting connections";

			NotificationManager.getInstance().sendNotification(
					Notification.LOG_ERROR, info);
		}

		try {
			RemoteClient c = (RemoteClient) RemotingUtils.lookupRMIService(
					clientURI, RemoteClient.class);
			connectedClients_.add(c);
			connectedClientURIs_.add(clientURI);

			String info = "LocalHostImpl: Client '" + c.getName()
					+ "' connected";

			NotificationManager.getInstance().sendNotification(
					Notification.LOG_INFO, info);

		} catch (RemoteLookupFailureException e) {
			String info = "LocalHostImpl could not resolve client at uri: "
					+ clientURI;

			NotificationManager.getInstance().sendNotification(
					Notification.LOG_INFO, info);
			return;
		} catch (RuntimeException e) {
			String err = "LocalHostImpl - A RuntimeException occurred while adding "
					+ "a client at URI: " + clientURI;
			err += "\n" + e.getMessage();
			err += "\nStack Trace: \n";
			for (int i = 0; i < e.getStackTrace().length; i++)
				err += e.getStackTrace()[i] + "\n";

			NotificationManager.getInstance().sendNotification(
					Notification.LOG_ERROR, err);
		}

	}

	// TODO - insure this works
	/**
	 * @see org.javassonne.networking.impl.RemoteHost
	 */
	public void removeClient(String clientURI) {
		if (connectedClientURIs_.contains(clientURI))
			connectedClientURIs_.remove(clientURI);
	}

	/**
	 * @see org.javassonne.networking.impl.RemoteHost
	 */
	public boolean canClientsConnect() {
		return clientsCanConnect_;
	}

	/**
	 * @see org.javassonne.networking.impl.RemoteHost
	 */
	public String getName() {
		return realName_;
	}

	/**
	 * @see org.javassonne.networking.impl.RemoteHost
	 */
	public String getURI() {
		if (URI_ == null) {
			HostStarter hs = new HostStarter();
			hs.run();
		}
		return URI_;
	}

	/**
	 * Useful for other classes to know if the localhost is ready to go
	 * 
	 * @return true if the localhost is started, false otherwise
	 */
	public boolean isLocalHostStarted() {
		return isLocalHostStarted_;
	}

	private boolean isClientConnected(String clientURI) {
		if (connectedClientURIs_.contains(clientURI) == false)
			return false;
		return true;
	}

	/**
	 * @see org.javassonne.networking.impl.RemoteHost
	 */
	public void receiveNotificationFromClient(String serializedNotification,
			String clientURI) {

		// If the client is not connected to us, we don't care
		if (isClientConnected(clientURI) == false) {
			String info = "LocalHostImpl: Client '" + clientURI
					+ "' tried to send us a notification, "
					+ "but was not connected to us. Notification ignored";

			NotificationManager.getInstance().sendNotification(
					Notification.LOG_INFO, info);

			return;
		}

		// Send the message out to all other clients
		for (Iterator<RemoteClient> i = connectedClients_.iterator(); i
				.hasNext();) {
			RemoteClient curClient = i.next();

			// Do not send the message back out to the client that sent it to us
			if (curClient.getURI().equals(clientURI))
				continue;

			curClient.receiveNotificationFromHost(serializedNotification);
		}
	}

	/**
	 * @see org.javassonne.networking.impl.RemoteHost
	 */
	// TODO - make this work
	public MODE getStatus() {
		return this.currentMode_;
	}

	/**
	 * @see org.javassonne.networking.impl.RemoteHost
	 */
	// TODO - list the not allowed notifications
	public void receiveNotification(String serializedNotification) {
		Notification n = (Notification) xStream_
				.fromXML(serializedNotification);
		String id = n.identifier();
		if ((id != Notification.SEND_GLOBAL_CHAT)
				&& (id != Notification.SEND_PRIVATE_CHAT))
			NotificationManager.getInstance().sendNotification(n.identifier(),
					n.argument());
	}

	/**
	 * @see org.javassonne.networking.impl.RemoteHost
	 */	
	public void addHost(String hostURI) {
		HostMonitor.getInstance().addHost(hostURI);
		
	}

	/**
	 * @see org.javassonne.networking.impl.RemoteHost
	 */	
	public void addHostNoConfirmation(String hostURI) {
		HostMonitor.getInstance().addHostNoConfirmation(hostURI);
	}

	/**
	 * @see org.javassonne.networking.impl.RemoteHost
	 */	
	public void addHostNoPropagation(String hostURI) {
		HostMonitor.getInstance().addHostNoPropagation(hostURI);
	}

}