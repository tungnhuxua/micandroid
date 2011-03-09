/*
 * =============================================================================
 * Copyright by Benjamin Sempere,
 * All rights reserved.
 * =============================================================================
 * Author  : Benjamin Sempere
 * E-mail  : benjamin@sempere.org
 * Homepage: www.sempere.org
 * =============================================================================
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.sempere.commons.jms;

import static javax.jms.Session.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;

import org.junit.Before;
import org.junit.Test;
import org.sempere.commons.naming.NamingManager;

/**
 * Unit tests class for JMSManagerImpl class.
 * 
 * @author bsempere
 */
public class JMSManagerImplTest {

	private static final String CONNECTION_FACTORY_JNDI_NAME = "jms/myConnectionFactory";
	private static final String DESTINATION_JNDI_NAME = "jms/MyDestination";

	private JMSManagerImpl manager;

	@Before
	public void before() throws Exception {
		this.manager = new JMSManagerImpl(CONNECTION_FACTORY_JNDI_NAME, DESTINATION_JNDI_NAME);
		this.manager.setConnectionUser("user");
		this.manager.setConnectionPassword("password");
	}

	// ********************************************************************************
	//
	// Constructors
	//
	// ********************************************************************************

	@Test
	public void newInstanceWithConnectionFactoryJndiAndDestinationJndi() throws Exception {
		this.manager = new JMSManagerImpl(CONNECTION_FACTORY_JNDI_NAME, DESTINATION_JNDI_NAME);
		this.manager.setConnectionUser("user");
		this.manager.setConnectionPassword("password");

		assertEquals(CONNECTION_FACTORY_JNDI_NAME, this.manager.connectionFactoryJndiName);
		assertEquals(DESTINATION_JNDI_NAME, this.manager.destinationJndiName);
		assertNotNull("NamingManager should not be null.", this.manager.namingManager);
		assertEquals("user", this.manager.connectionUser);
		assertEquals("password", this.manager.connectionPassword);
	}

	@Test
	public void newInstanceWithConnectionFactoryJndiAndDestinationJndiAndContext() throws Exception {
		this.manager = new JMSManagerImpl(CONNECTION_FACTORY_JNDI_NAME, DESTINATION_JNDI_NAME, mock(Context.class));
		this.manager.setConnectionUser("user");
		this.manager.setConnectionPassword("password");

		assertEquals(CONNECTION_FACTORY_JNDI_NAME, this.manager.connectionFactoryJndiName);
		assertEquals(DESTINATION_JNDI_NAME, this.manager.destinationJndiName);
		assertNotNull("NamingManager should not be null.", this.manager.namingManager);
		assertEquals("user", this.manager.connectionUser);
		assertEquals("password", this.manager.connectionPassword);
	}

	// ********************************************************************************
	//
	// Convenience methods for messages publication
	//
	// ********************************************************************************

	@Test
	public void sendMessageWithMessageCreatorShouldCallSendMessageWithMessage() throws Exception {
		Message message = mock(Message.class);
		Destination destination = mock(Destination.class);

		MessageCreator messageCreator = mock(MessageCreator.class);
		when(messageCreator.createMessage()).thenReturn(message);

		Session session = mock(Session.class);
		MessageProducer producer = mock(MessageProducer.class);
		when(session.createProducer(destination)).thenReturn(producer);

		Connection connection = mock(Connection.class);
		when(connection.createSession(false, AUTO_ACKNOWLEDGE)).thenReturn(session);

		ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
		when(connectionFactory.createConnection("user", "password")).thenReturn(connection);

		NamingManager namingManager = mock(NamingManager.class);
		when(namingManager.getLocalObject(CONNECTION_FACTORY_JNDI_NAME)).thenReturn(connectionFactory);
		when(namingManager.getLocalObject(DESTINATION_JNDI_NAME)).thenReturn(destination);

		this.manager.namingManager = namingManager;
		this.manager.sendMessage(messageCreator);
	}

	@Test
	public void sendMessageWithTextMessageShouldCallSendMessageWithMessage() throws Exception {
		TextMessage message = mock(TextMessage.class);
		Destination destination = mock(Destination.class);

		Session session = mock(Session.class);
		MessageProducer producer = mock(MessageProducer.class);
		when(session.createProducer(destination)).thenReturn(producer);
		when(session.createTextMessage("MyTextMessage")).thenReturn(message);

		Connection connection = mock(Connection.class);
		when(connection.createSession(false, AUTO_ACKNOWLEDGE)).thenReturn(session);

		ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
		when(connectionFactory.createConnection("user", "password")).thenReturn(connection);

		NamingManager namingManager = mock(NamingManager.class);
		when(namingManager.getLocalObject(CONNECTION_FACTORY_JNDI_NAME)).thenReturn(connectionFactory);
		when(namingManager.getLocalObject(DESTINATION_JNDI_NAME)).thenReturn(destination);

		this.manager.namingManager = namingManager;
		this.manager.sendMessage("MyTextMessage");
	}

	@Test
	public void sendMessageWithObjectMessageShouldCallSendMessageWithMessage() throws Exception {
		ObjectMessage message = mock(ObjectMessage.class);
		Destination destination = mock(Destination.class);

		Session session = mock(Session.class);
		MessageProducer producer = mock(MessageProducer.class);
		when(session.createProducer(destination)).thenReturn(producer);
		when(session.createObjectMessage("MyObjectMessage")).thenReturn(message);

		Connection connection = mock(Connection.class);
		when(connection.createSession(false, AUTO_ACKNOWLEDGE)).thenReturn(session);

		ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
		when(connectionFactory.createConnection("user", "password")).thenReturn(connection);

		NamingManager namingManager = mock(NamingManager.class);
		when(namingManager.getLocalObject(CONNECTION_FACTORY_JNDI_NAME)).thenReturn(connectionFactory);
		when(namingManager.getLocalObject(DESTINATION_JNDI_NAME)).thenReturn(destination);

		this.manager.namingManager = namingManager;
		this.manager.sendMessage("MyObjectMessage");
	}

	@Test
	public void sendMessage() throws Exception {
		Message message = mock(Message.class);
		Destination destination = mock(Destination.class);

		Session session = mock(Session.class);
		MessageProducer producer = mock(MessageProducer.class);
		when(session.createProducer(destination)).thenReturn(producer);

		Connection connection = mock(Connection.class);
		when(connection.createSession(false, AUTO_ACKNOWLEDGE)).thenReturn(session);

		ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
		when(connectionFactory.createConnection("user", "password")).thenReturn(connection);

		NamingManager namingManager = mock(NamingManager.class);
		when(namingManager.getLocalObject(CONNECTION_FACTORY_JNDI_NAME)).thenReturn(connectionFactory);
		when(namingManager.getLocalObject(DESTINATION_JNDI_NAME)).thenReturn(destination);

		this.manager.namingManager = namingManager;
		this.manager.sendMessage(message);
	}

	// ********************************************************************************
	//
	// Convenience methods for messages consumers
	//
	// ********************************************************************************

	@Test
	public void createMessageProducerShouldCallCreateMessageProducerOnJMSHelper() throws Exception {
		MessageProducer producer = mock(MessageProducer.class);
		Destination destination = mock(Destination.class);

		Session session = mock(Session.class);
		when(session.createProducer(destination)).thenReturn(producer);

		NamingManager namingManager = mock(NamingManager.class);
		when(namingManager.getLocalObject(DESTINATION_JNDI_NAME)).thenReturn(destination);

		this.manager.namingManager = namingManager;
		assertEquals(producer, this.manager.createMessageProducer(session));
	}

	// ********************************************************************************
	//
	// Convenience methods for JMS connections
	//
	// ********************************************************************************

	@Test
	public void getConnectionShouldCallCreateConnectionOnJMSHelper() throws Exception {
		Connection connection = mock(Connection.class);

		ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
		when(connectionFactory.createConnection("user", "password")).thenReturn(connection);

		NamingManager namingManager = mock(NamingManager.class);
		when(namingManager.getLocalObject(CONNECTION_FACTORY_JNDI_NAME)).thenReturn(connectionFactory);

		this.manager.namingManager = namingManager;
		assertEquals(connection, this.manager.createConnection("user", "password"));
	}

	@Test
	public void getConnectionFactoryShouldCallGetLocalObjectOnNamingManager() throws Exception {
		ConnectionFactory connectionFactory = mock(ConnectionFactory.class);

		NamingManager namingManager = mock(NamingManager.class);
		when(namingManager.getLocalObject(CONNECTION_FACTORY_JNDI_NAME)).thenReturn(connectionFactory);

		this.manager.namingManager = namingManager;
		assertEquals(connectionFactory, this.manager.getConnectionFactory());
	}

	// ********************************************************************************
	//
	// Convenience methods for JMS destinations
	//
	// ********************************************************************************

	@Test
	public void getDestinationShouldCallGetLocalObjectOnNamingManager() throws Exception {
		Destination destination = mock(Destination.class);

		NamingManager namingManager = mock(NamingManager.class);
		when(namingManager.getLocalObject(DESTINATION_JNDI_NAME)).thenReturn(destination);

		this.manager.namingManager = namingManager;
		assertEquals(destination, this.manager.getDestination());
	}
}