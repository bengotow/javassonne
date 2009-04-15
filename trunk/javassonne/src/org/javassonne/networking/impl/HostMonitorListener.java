/**
 * Javassonne 
 *  http://code.google.com/p/javassonne/
 * 
 * @author [Add Name Here]
 * @date Mar 24, 2009
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

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

import org.javassonne.logger.LogSender;
import org.javassonne.messaging.Notification;
import org.javassonne.messaging.NotificationManager;
import org.javassonne.networking.HostMonitor;

public class HostMonitorListener implements ServiceListener {
	private static JmDNS jmdns_ = JmDNSSingleton.getJmDNS();
	
	public void serviceAdded(ServiceEvent e) {
		String info = "HostMonitorListener: Found service " + e.getName();

		NotificationManager.getInstance().sendNotification(
				Notification.LOG_INFO, info);

		// We only care if this is a Javassonne host
		if (e.getName().contains(HostImpl.SERVICENAME) == false)
			return;

		// Cut out instances of
		// rmi://some.ip.here:port/JavassonneHost_name (2)
		// These are 'old' instances of the current host that are still
		// alive in multicast, but will not be there when you try to make
		// calls on them
		if (e.getName().matches(".+\\(\\d+\\)") == true) {
			String info2 = "HostMonitorListener: Determined that service '"
					+ e.getName() + "' is a duplicate, ignoring";

			NotificationManager.getInstance().sendNotification(
					Notification.LOG_INFO, info2);

			return;
		}

		// because Service requestor calls on JmDNS, we need to 
		// ensure that the servicerequestor is run in a different thread
		// from the one jmdns is in. 
		// 
		
		Thread t = new Thread(new ServiceRequestor(e), "ServiceRequestor");
		t.run();
	}

	public void serviceRemoved(ServiceEvent e) {
		String info = "HostMonitorListener: Service '" + e.getName()
				+ "' removed";

		NotificationManager.getInstance().sendNotification(
				Notification.LOG_INFO, info);
		
		String name = e.getName();
		HostMonitor.removeHost(name);
	}

	public void serviceResolved(ServiceEvent e) {
		String rinfo = "HostMonitorListener: Service '" + e.getName()
				+ "' resolved";

		NotificationManager.getInstance().sendNotification(
				Notification.LOG_INFO, rinfo);

		// We only care if this is a Javassonne host
		if (e.getName().contains(HostImpl.SERVICENAME) == false)
			return;

		ServiceInfo info = e.getInfo();
		if (info == null) {
			String sinfo = "HostMonitorListener: Service '" + e.getName()
					+ "' failed to get info on";

			NotificationManager.getInstance().sendNotification(
					Notification.LOG_INFO, sinfo);
			return;
		}

		String hostURI = "rmi://" + info.getHostAddress() + ":"
				+ info.getPort() + "/" + info.getName();

		String hinfo = "HostMonitorListener: Service '" + e.getName()
				+ "' has URI of: " + hostURI;

		NotificationManager.getInstance().sendNotification(
				Notification.LOG_INFO, hinfo);
 
		HostMonitor.resolveNewHost(hostURI);
	}
	
	private class ServiceRequestor implements Runnable {
		private ServiceEvent event_;

		public ServiceRequestor(ServiceEvent e) {
			event_ = e;
		}

		public void run() {
			LogSender.sendInfo("ServiceRequstor - entered");
			jmdns_.requestServiceInfo(event_.getType(),
					event_.getName());
			LogSender.sendInfo("ServiceRequstor - exited");
		}
	}
}
