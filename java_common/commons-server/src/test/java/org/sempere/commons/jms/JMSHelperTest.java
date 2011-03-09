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
import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.junit.Test;

/**
 * Unit tests class for JMSHelper class.
 * 
 * @author bsempere
 */
public class JMSHelperTest {

	// ********************************************************************************
	//
	// Methods for JMS messages
	//
	// ********************************************************************************

	@Test(expected = org.sempere.commons.jms.JMSException.class)
	public void createTextMessageWhenJMSExceptionOccursShouldReturnRuntimeJMSException() throws Exception {
		String textMessage = "MyMessage";
		Session session = mock(Session.class);

		doThrow(new JMSException("Exception encountered.")).when(session).createTextMessage(textMessage);
		JMSHelper.createTextMessage(session, textMessage);
	}

	@Test
	public void createTextMessageWhenNoJMSExceptionOccursShouldReturnMessage() throws Exception {
		String textMessage = "MyMessage";

		Session session = mock(Session.class);
		TextMessage message = mock(TextMessage.class);
		when(session.createTextMessage(textMessage)).thenReturn(message);

		assertSame(message, JMSHelper.createTextMessage(session, textMessage));
	}

	@Test(expected = org.sempere.commons.jms.JMSException.class)
	public void createObjectMessageWhenJMSExceptionOccursShouldReturnRuntimeJMSException() throws Exception {
		String objectMessage = "MyMessage";
		Session session = mock(Session.class);

		doThrow(new JMSException("Exception encountered.")).when(session).createObjectMessage(objectMessage);
		JMSHelper.createObjectMessage(session, objectMessage);
	}

	@Test
	public void createObjectMessageWhenNoJMSExceptionOccursShouldReturnMessage() throws Exception {
		String textMessage = "MyMessage";

		Session session = mock(Session.class);
		ObjectMessage message = mock(ObjectMessage.class);
		when(session.createObjectMessage(textMessage)).thenReturn(message);

		assertSame(message, JMSHelper.createObjectMessage(session, textMessage));
	}

	// ********************************************************************************
	//
	// Methods for JMS producers
	//
	// ********************************************************************************

	@Test(expected = RuntimeException.class)
	public void sendMessageWithMessageCreatorShouldCallSendOnProducer() throws Exception {
		MessageProducer producer = mock(MessageProducer.class);
		MessageCreator creator = mock(MessageCreator.class);
		Message message = mock(Message.class);

		// creator.createMessage(); => Message
		doReturn(message).when(creator).createMessage();

		// A RuntimeException is thrown if send method is called on the producer
		doThrow(new RuntimeException()).when(producer).send(message);

		// In this call, a RuntimeException should be raise
		JMSHelper.send(producer, message);
	}

	@Test(expected = org.sempere.commons.jms.JMSException.class)
	public void sendMessageWhenJMSExceptionOccursShouldReturnRuntimeJMSException() throws Exception {
		MessageProducer producer = mock(MessageProducer.class);
		Message message = mock(Message.class);

		doThrow(new JMSException("Exception encountered.")).when(producer).send(message);
		JMSHelper.send(producer, message);
	}

	@Test(expected = RuntimeException.class)
	public void sendMessageShouldCallSendOnProducer() throws Exception {
		MessageProducer producer = mock(MessageProducer.class);
		Message message = mock(Message.class);

		// A RuntimeException is thrown if send method is called on the producer
		doThrow(new RuntimeException()).when(producer).send(message);

		// In this call, a RuntimeException should be raise
		JMSHelper.send(producer, message);
	}

	@Test(expected = org.sempere.commons.jms.JMSException.class)
	public void createMessageProducerWhenJMSExceptionOccursShouldReturnRuntimeJMSException() throws Exception {
		Session session = mock(Session.class);
		Destination destination = mock(Destination.class);

		doThrow(new JMSException("Exception encountered.")).when(session).createProducer(destination);
		JMSHelper.createMessageProducer(session, destination);
	}

	@Test(expected = RuntimeException.class)
	public void createProducerShouldCallCreateProducerOnSession() throws Exception {
		Session session = mock(Session.class);
		Destination destination = mock(Destination.class);

		// A RuntimeException is thrown if createProducer method is called on the session
		doThrow(new RuntimeException()).when(session).createProducer(destination);

		// In this call, a RuntimeException should be raise
		JMSHelper.createMessageProducer(session, destination);
	}

	@Test
	public void closeProducerWhenProducerIsNullShouldDoesNothing() throws Exception {
		JMSHelper.closeProducer(null);
	}

	@Test(expected = RuntimeException.class)
	public void closeProducerWhenProducerIsNotNullShouldCloseProducer() throws Exception {
		MessageProducer producer = mock(MessageProducer.class);

		// A RuntimeException is thrown if close method is called on the producer
		doThrow(new RuntimeException()).when(producer).close();

		// In this call, a RuntimeException should be raise
		JMSHelper.closeProducer(producer);
	}

	// ********************************************************************************
	//
	// Methods for JMS consumers
	//
	// ********************************************************************************

	@Test(expected = RuntimeException.class)
	public void createConsumerWithoutMessageSelectorShouldCallCreateConsumerOnSessionWithNullForMessageSelector() throws Exception {
		Session session = mock(Session.class);
		Destination destination = mock(Destination.class);

		// A RuntimeException is thrown if createConsumer method is called on the session
		doThrow(new RuntimeException()).when(session).createConsumer(destination, null);

		// In this call, a RuntimeException should be raise
		JMSHelper.createMessageConsumer(session, destination);
	}

	@Test(expected = org.sempere.commons.jms.JMSException.class)
	public void createMessageConsumerWhenJMSExceptionOccursShouldReturnRuntimeJMSException() throws Exception {
		Session session = mock(Session.class);
		Destination destination = mock(Destination.class);
		String messageSelector = "MyMessageSelector";

		doThrow(new JMSException("Exception encountered.")).when(session).createConsumer(destination, messageSelector);
		JMSHelper.createMessageConsumer(session, destination, messageSelector);
	}

	@Test(expected = RuntimeException.class)
	public void createConsumerShouldCallCreateConsumerOnSession() throws Exception {
		Session session = mock(Session.class);
		Destination destination = mock(Destination.class);
		String messageSelector = "MyMessageSelector";

		// A RuntimeException is thrown if createConsumer method is called on the session
		doThrow(new RuntimeException()).when(session).createConsumer(destination, messageSelector);

		// In this call, a RuntimeException should be raise
		JMSHelper.createMessageConsumer(session, destination, messageSelector);
	}

	@Test
	public void closeConsumerWhenConsumerIsNullShouldDoesNothing() throws Exception {
		JMSHelper.closeConsumer(null);
	}

	@Test(expected = RuntimeException.class)
	public void closeConsumerWhenConsumerIsNotNullShouldCloseConsumer() throws Exception {
		MessageConsumer consumer = mock(MessageConsumer.class);

		// A RuntimeException is thrown if close method is called on the consumer
		doThrow(new RuntimeException()).when(consumer).close();

		// In this call, a RuntimeException should be raise
		JMSHelper.closeConsumer(consumer);
	}

	// ********************************************************************************
	//
	// Methods for JMS sessions
	//
	// ********************************************************************************

	@Test(expected = RuntimeException.class)
	public void createNonTransactedSessionWithoutAcknowledgeModeShouldCallCreateSessionOnConnectionWithAutoAcknowledgeMode() throws Exception {
		Connection connection = mock(Connection.class);

		// A RuntimeException is thrown if createSession method is called on the connection
		doThrow(new RuntimeException()).when(connection).createSession(false, AUTO_ACKNOWLEDGE);

		// In this call, a RuntimeException should be raise
		JMSHelper.createNonTransactedSession(connection);
	}

	@Test(expected = RuntimeException.class)
	public void createNonTransactedSessionWithAcknowledgeModeShouldCallCreateSessionOnConnectionWithGivenAcknowledgeMode() throws Exception {
		Connection connection = mock(Connection.class);

		// A RuntimeException is thrown if createSession method is called on the connection
		doThrow(new RuntimeException()).when(connection).createSession(false, DUPS_OK_ACKNOWLEDGE);

		// In this call, a RuntimeException should be raise
		JMSHelper.createNonTransactedSession(connection, DUPS_OK_ACKNOWLEDGE);
	}

	@Test(expected = RuntimeException.class)
	public void createTransactedSessionShouldCallCreateSessionOnConnectionWithSessionTransactedAcknowledgeMode() throws Exception {
		Connection connection = mock(Connection.class);

		// A RuntimeException is thrown if createSession method is called on the connection
		doThrow(new RuntimeException()).when(connection).createSession(true, SESSION_TRANSACTED);

		// In this call, a RuntimeException should be raise
		JMSHelper.createTransactedSession(connection);
	}

	@Test(expected = RuntimeException.class)
	public void createSessionShouldCallCreateSessionOnConnection() throws Exception {
		Connection connection = mock(Connection.class);

		// A RuntimeException is thrown if createSession method is called on the connection
		doThrow(new RuntimeException()).when(connection).createSession(true, DUPS_OK_ACKNOWLEDGE);

		// In this call, a RuntimeException should be raise
		JMSHelper.createSession(connection, true, DUPS_OK_ACKNOWLEDGE);
	}

	@Test
	public void closeSessionWhenSessionIsNullShouldDoesNothing() throws Exception {
		JMSHelper.closeSession(null);
	}

	@Test(expected = RuntimeException.class)
	public void closeSessionWhenSessionIsNotNullShouldCloseSession() throws Exception {
		Session session = mock(Session.class);

		// A RuntimeException is thrown if close method is called on the session
		doThrow(new RuntimeException()).when(session).close();

		// In this call, a RuntimeException should be raise
		JMSHelper.closeSession(session);
	}

	// ********************************************************************************
	//
	// Methods for JMS connections
	//
	// ********************************************************************************

	@Test(expected = RuntimeException.class)
	public void createConnectionShouldCallCreateMethodOnConnectionFactory() throws Exception {
		ConnectionFactory connectionFactory = mock(ConnectionFactory.class);

		// A RuntimeException is thrown if createConnection method is called on the connectionFactory
		doThrow(new RuntimeException()).when(connectionFactory).createConnection(null, null);

		// In this call, a RuntimeException should be raise
		JMSHelper.createConnection(connectionFactory);
	}

	@Test(expected = RuntimeException.class)
	public void createConnectionWithUserAndPasswordShouldCallCreateMethodOnConnectionFactory() throws Exception {
		ConnectionFactory connectionFactory = mock(ConnectionFactory.class);

		// A RuntimeException is thrown if createConnection method is called on the connectionFactory
		doThrow(new RuntimeException()).when(connectionFactory).createConnection("user", "password");

		// In this call, a RuntimeException should be raise
		JMSHelper.createConnection(connectionFactory, "user", "password");
	}

	@Test
	public void startConnectionWhenConnectionIsNullShouldDoesNothing() throws Exception {
		JMSHelper.startConnection(null);
	}

	@Test(expected = RuntimeException.class)
	public void startConnectionWhenConnectionIsNotNullShouldStartConnection() throws Exception {
		Connection connection = mock(Connection.class);

		// A RuntimeException is thrown if startConnection method is called on the connection
		doThrow(new RuntimeException()).when(connection).start();

		// In this call, a RuntimeException should be raise
		JMSHelper.startConnection(connection);
	}

	@Test
	public void stopConnectionWhenConnectionIsNullShouldDoesNothing() throws Exception {
		JMSHelper.stopConnection(null);
	}

	@Test(expected = RuntimeException.class)
	public void stopConnectionWhenConnectionIsNotNullShouldStopConnection() throws Exception {
		Connection connection = mock(Connection.class);

		// A RuntimeException is thrown if stopConnection method is called on the connection
		doThrow(new RuntimeException()).when(connection).stop();

		// In this call, a RuntimeException should be raise
		JMSHelper.stopConnection(connection);
	}

	@Test
	public void closeConnectionWhenConnectionIsNullShouldDoesNothing() throws Exception {
		JMSHelper.closeConnection(null);
	}

	@Test
	public void closeConnectionWhenConnectionIsNotNullShouldCloseConnectionOnly() throws Exception {
		Connection connection = mock(Connection.class);

		// A RuntimeException is thrown if closeConnection method is called on the connection
		doThrow(new RuntimeException()).when(connection).stop();

		// In this call, no RuntimeException should be raise
		JMSHelper.closeConnection(connection);
	}

	@Test
	public void closeConnectionWithBooleanWhenConnectionIsNullShouldDoesNothing() throws Exception {
		JMSHelper.closeConnection(null, true);
	}

	@Test
	public void closeConnectionWithoutStoppingItWhenConnectionIsNotNullShouldCloseConnectionOnly() throws Exception {
		Connection connection = mock(Connection.class);

		// A RuntimeException is thrown if closeConnection method is called on the connection
		doThrow(new RuntimeException()).when(connection).stop();

		// In this call, no RuntimeException should be raise
		JMSHelper.closeConnection(connection, false);
	}

	@Test(expected = RuntimeException.class)
	public void closeConnectionWithStoppingItWhenConnectionIsNotNullShouldStopAndCloseConnection() throws Exception {
		Connection connection = mock(Connection.class);

		// A RuntimeException is thrown if closeConnection method is called on the connection
		doThrow(new RuntimeException()).when(connection).stop();

		// In this call, a RuntimeException should be raise
		JMSHelper.closeConnection(connection, true);
	}
}