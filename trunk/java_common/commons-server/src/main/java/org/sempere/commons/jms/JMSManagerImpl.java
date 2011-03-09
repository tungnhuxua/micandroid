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

import org.sempere.commons.naming.NamingManager;
import org.sempere.commons.naming.NamingManagerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import javax.naming.Context;
import java.io.Serializable;

/**
 * Base implementation of JMSManager.
 *
 * @author bsempere
 */
public class JMSManagerImpl implements JMSManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(JMSManagerImpl.class);

    // JEE context
    protected NamingManager namingManager;

    // Administered objects JNDI
    protected String connectionFactoryJndiName;
    protected String destinationJndiName;

    // Used to create JMS connection
    protected String connectionUser;
    protected String connectionPassword;

    /**
     * Constructor
     */
    public JMSManagerImpl() {
    }

    /**
     * Constructor
     *
     * @param connectionFactoryJndiName
     * @param destinationJndiName
     */
    public JMSManagerImpl(String connectionFactoryJndiName, String destinationJndiName) {
        this(connectionFactoryJndiName, destinationJndiName, null);
    }

    /**
     * Constructor
     *
     * @param connectionFactoryJndiName
     * @param destinationJndiName
     * @param context
     */
    public JMSManagerImpl(String connectionFactoryJndiName, String destinationJndiName, Context context) {
        // Initializes the NamingManager
        if (context != null) {
            this.namingManager = new NamingManagerImpl(context);
        } else {
            this.namingManager = new NamingManagerImpl();
        }

        this.connectionFactoryJndiName = connectionFactoryJndiName;
        LOGGER.debug("connectionFactoryJndiName set with value [" + connectionFactoryJndiName + "].");

        this.destinationJndiName = destinationJndiName;
        LOGGER.debug("destinationJndiName set with value [" + destinationJndiName + "].");
    }

    // ********************************************************************************
    //
    // Methods from JMSManager interface
    //
    // ********************************************************************************

    public void sendMessage(MessageCreator messageCreator) {
        this.sendMessage(messageCreator.createMessage());
    }

    public void sendMessage(String textMessage) {
        Message message = null;

        // Step 1 - constructs the message
        Connection connection = this.createConnection(this.connectionUser, this.connectionPassword);
        try {
            Session session = JMSHelper.createNonTransactedSession(connection);
            message = JMSHelper.createTextMessage(session, textMessage);
        } finally {
            // There is no need to close the sessions, producers, and consumers of a closed connection.
            JMSHelper.closeConnection(connection);
        }

        // Step 2 - sends the message
        this.sendMessage(message);
    }

    public <T extends Serializable> void sendMessage(T objectMessage) {
        Message message = null;

        // Step 1 - constructs the message
        Connection connection = this.createConnection(this.connectionUser, this.connectionPassword);
        try {
            Session session = JMSHelper.createNonTransactedSession(connection);
            message = JMSHelper.createObjectMessage(session, objectMessage);
        } finally {
            // There is no need to close the sessions, producers, and consumers of a closed connection.
            JMSHelper.closeConnection(connection);
        }

        // Step 2 - sends the message
        this.sendMessage(message);
    }

    public void sendMessage(Message message) {
        Connection connection = this.createConnection(this.connectionUser, this.connectionPassword);
        try {
            // Message and producer creation
            Session session = JMSHelper.createNonTransactedSession(connection);
            LOGGER.info("Session created successfully.");

            MessageProducer producer = this.createMessageProducer(session);
            LOGGER.info("Message producer created successfully.");

            // Message publication
            JMSHelper.send(producer, message);
            LOGGER.info("Message sent successfully by producer.");
        } finally {
            // There is no need to close the sessions, producers, and consumers of a closed connection.
            JMSHelper.closeConnection(connection);
        }
    }

    // ********************************************************************************
    //
    // Convenience methods for messages consumers
    //
    // ********************************************************************************

    protected MessageProducer createMessageProducer(Session session) {
        return JMSHelper.createMessageProducer(session, this.getDestination());
    }

    // ********************************************************************************
    //
    // Convenience methods for JMS connections
    //
    // ********************************************************************************

    protected Connection createConnection(String user, String password) {
        return JMSHelper.createConnection(this.getConnectionFactory(), user, password);
    }

    protected ConnectionFactory getConnectionFactory() {
        return this.namingManager.getLocalObject(this.connectionFactoryJndiName);
    }

    // ********************************************************************************
    //
    // Convenience methods for JMS destinations
    //
    // ********************************************************************************

    protected Destination getDestination() {
        return this.namingManager.getLocalObject(this.destinationJndiName);
    }

    // ********************************************************************************
    //
    // Setters for dependencies injection
    //
    // ********************************************************************************

    public void setNamingManager(NamingManager namingManager) {
        this.namingManager = namingManager;
    }

    public void setConnectionUser(String connectionUser) {
        this.connectionUser = connectionUser;
    }

    public void setConnectionPassword(String connectionPassword) {
        this.connectionPassword = connectionPassword;
    }
}
