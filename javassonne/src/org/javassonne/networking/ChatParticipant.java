/**
 * Javassonne 
 *  http://code.google.com/p/javassonne/
 * 
 * @author [Add Name Here]
 * @date Mar 20, 2009
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

/*
 * Needed by the ChatManager to contact anyone that wants to participate in chat
 * Also needed so certain users can see who a message came from
 * 
 * Note that although some of the participants are local classes, most of them
 * are in fact RemoteHosts or RemoteClients, so everything in ChatParticipant 
 * needs to be Serializable
 */
public interface ChatParticipant {
	public void receiveGlobalChat(String msg, String senderName);
	
	public void receivePrivateGameChat(String msg, String senderName);
	
	public String getChatParticipantName();
}