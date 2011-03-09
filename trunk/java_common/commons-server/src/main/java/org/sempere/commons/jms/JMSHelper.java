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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import javax.jms.JMSException;
import java.io.Serializable;

import static javax.jms.Session.*;

/**
 * Helper class that provides several methods to work with JMS.
 *
 * @author bsempere
 */
public final class JMSHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(JMSHelper.class);

    // *************************************************************************
    //
    // Constructors
    //
    // *************************************************************************

    private JMSHelper() {
    }

    // ********************************************************************************
    //
    // Methods for JMS messages
    //
    // ********************************************************************************

    public static Message createTextMessage(Session session, String textMessage) {
        try {
            LOGGER.debug("About to create text message [" + textMessage + "].");
            return session.createTextMessage(textMessage);
        } catch (JMSException e) {
            throw new org.sempere.commons.jms.JMSException(e);
        }
    }

    public static <T extends Serializable> Message createObjectMessage(Session session, T objectMessage) {
        try {
            LOGGER.debug("About to create object message  [" + objectMessage + "].");
            return session.createObjectMessage(objectMessage);
        } catch (JMSException e) {
            throw new org.sempere.commons.jms.JMSException(e);
        }
    }

    // ********************************************************************************
    //
    // Methods for JMS producers
    //
    // ********************************************************************************

    public static void send(MessageProducer producer, MessageCreator messageCreator) {
        send(producer, messageCreator.createMessage());
    }

    public static void send(MessageProducer producer, Message message) {
        try {
            producer.send(message);
        } catch (JMSException e) {
            throw new org.sempere.commons.jms.JMSException(e);
        }
    }

    public static MessageProducer createMessageProducer(Session session, Destination destination) {
        try {
            return session.createProducer(destination);
        } catch (JMSException e) {
            throw new org.sempere.commons.jms.JMSException(e);
        }
    }

    public static void closeProducer(MessageProducer producer) {
        try {
            if (producer != null) {
                producer.close();
            }
        } catch (JMSException e) {
            LOGGER.warn("Cannot close producer.", e);
        }
    }

    // ********************************************************************************
    //
    // Methods for JMS consumers
    //
    // ********************************************************************************

    public static MessageConsumer createMessageConsumer(Session session, Destination destination) {
        return createMessageConsumer(session, destination, null);
    }

    public static MessageConsumer createMessageConsumer(Session session, Destination destination, String messageSelector) {
        try {
            return session.createConsumer(destination, messageSelector);
        } catch (JMSException e) {
            throw new org.sempere.commons.jms.JMSException(e);
        }
    }

    public static void closeConsumer(MessageConsumer consumer) {
        try {
            if (consumer != null) {
                consumer.close();
            }
        } catch (JMSException e) {
            LOGGER.warn("Cannot close consumer.", e);
        }
    }

    // ********************************************************************************
    //
    // Methods for JMS sessions
    //
    // ********************************************************************************

    public static Session createNonTransactedSession(Connection connection) {
        return createSession(connection, false, AUTO_ACKNOWLEDGE);
    }

    public static Session createNonTransactedSession(Connection connection, int acknowledgeMode) {
        return createSession(connection, false, acknowledgeMode);
    }

    public static Session createTransactedSession(Connection connection) {
        return createSession(connection, true, SESSION_TRANSACTED);
    }

    public static Session createSession(Connection connection, boolean isTransacted, int acknowledgeMode) {
        try {
            return connection.createSession(isTransacted, acknowledgeMode);
        } catch (JMSException e) {
            throw new org.sempere.commons.jms.JMSException(e);
        }
    }

    public static void closeSession(Session session) {
        try {
            if (session != null) {
                session.close();
            }
        } catch (JMSException e) {
            LOGGER.warn("Cannot close session.", e);
        }
    }

    // ********************************************************************************
    //
    // Methods for JMS connections
    //
    // ********************************************************************************

    public static Connection createConnection(ConnectionFactory connectionFactory) {
        return createConnection(connectionFactory, null, null);
    }

    public static Connection createConnection(ConnectionFactory connectionFactory, String user, String password) {
        try {
            return connectionFactory.createConnection(user, password);
        } catch (JMSException e) {
            throw new org.sempere.commons.jms.JMSException(e);
        }
    }

    public static void startConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.start();
            }
        } catch (JMSException e) {
            LOGGER.warn("Cannot start connection.", e);
        }
    }

    public static void stopConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.stop();
            }
        } catch (JMSException e) {
            LOGGER.warn("Cannot stop connection.", e);
        }
    }

    public static void closeConnection(Connection connection) {
        closeConnection(connection, false);
    }

    public static void closeConnection(Connection connection, boolean stopBeforeClose) {
        try {
            if (connection != null) {
                if (stopBeforeClose) {
                    stopConnection(connection);
                }
                connection.close();
            }
        } catch (JMSException e) {
            LOGGER.warn("Cannot close connection.", e);
        }
    }
}
