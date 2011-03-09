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
package org.sempere.commons.mail;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base implementation of MailSender.
 *
 * @author bsempere
 */
public class MailSenderImpl implements MailSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailSenderImpl.class);

    private String smtpLogin;
    private String smtpPassword;

    private String smtpHost;
    private int smtpPort;

    // *************************************************************************
    //
    // Constructors
    //
    // *************************************************************************

    public MailSenderImpl() {
    }


    public MailSenderImpl(String smtpHost, int smtpPort) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
    }

    public MailSenderImpl(String smtpHost, Integer smtpPort, String smtpLogin, String smtpPassword) {
        this(smtpHost, smtpPort);
        this.smtpLogin = smtpLogin;
        this.smtpPassword = smtpPassword;
    }

    // ********************************************************************************
    //
    // Methods from MailSender interface
    //
    // ********************************************************************************

    public void sendHtmlEmail(MailData message) {
        this.onPreSend(message);

        LOGGER.debug("Message to send [" + message + "]");
        try {
            // Create the email message
            HtmlEmail email = new HtmlEmail();
            email.setHostName(this.smtpHost);
            email.setSmtpPort(this.smtpPort);

            // Set authentication informations
            if (!StringUtils.isEmpty(this.smtpLogin) && !StringUtils.isEmpty(this.smtpPassword)) {
                email.setAuthentication(this.smtpLogin, this.smtpPassword);
            }

            // Set message sender
            email.setFrom(message.getFrom());

            // Add message receivers
            if (message.getTo() != null) {
                for (String to : message.getTo()) {
                    email.addTo(to);
                }
            }
            if (message.getCc() != null) {
                for (String cc : message.getCc()) {
                    email.addCc(cc);
                }
            }
            if (message.getBcc() != null) {
                for (String bcc : message.getBcc()) {
                    email.addBcc(bcc);
                }
            }

            // Set message subject
            email.setSubject(message.getSubject());

            // Set HTML message
            email.setHtmlMsg(message.getContent());

            // Set alternative message
            email.setTextMsg("Your email client does not support HTML messages");

            // Sends email
            email.send();

            LOGGER.debug("Message sent successfully");
        } catch (EmailException e) {
            throw new MailException("Exception encountered when sending message.", e);
        }
    }

    public void sendTextEmail(MailData message) {
        this.onPreSend(message);

        LOGGER.debug("Message to send [" + message + "]");
        try {
            // Create the email message
            SimpleEmail email = new SimpleEmail();
            email.setHostName(this.smtpHost);
            email.setSmtpPort(this.smtpPort);

            // Set authentication informations
            if (!StringUtils.isEmpty(this.smtpLogin) && !StringUtils.isEmpty(this.smtpPassword)) {
                email.setAuthentication(this.smtpLogin, this.smtpPassword);
            }

            // Set message sender
            email.setFrom(message.getFrom());

            // Add message receivers
            if (message.getTo() != null) {
                for (String to : message.getTo()) {
                    email.addTo(to);
                }
            }
            if (message.getCc() != null) {
                for (String cc : message.getCc()) {
                    email.addCc(cc);
                }
            }
            if (message.getBcc() != null) {
                for (String bcc : message.getBcc()) {
                    email.addBcc(bcc);
                }
            }

            // Set message subject
            email.setSubject(message.getSubject());

            // Set HTML message
            email.setMsg(message.getContent());

            // Sends email
            email.send();

            LOGGER.debug("Message sent successfully");
        } catch (EmailException e) {
            throw new MailException("Exception encountered when sending message.", e);
        }
    }

    // *************************************************************************
    //
    // Convenience methods
    //
    // *************************************************************************

    /**
     * Method called before sending the email
     *
     * @param message the message to be sent
     */
    protected void onPreSend(MailData message) {
        this.checkConnectionInformations();
        this.checkMessage(message);
    }

    /**
     * Checks that connection informations are valid
     */
    protected void checkConnectionInformations() {
        if (StringUtils.isEmpty(this.smtpHost)) {
            throw new IllegalArgumentException("SMTP host cannot be empty.");
        }
    }

    /**
     * Checks that given email is valid
     *
     * @param message message to test
     */
    protected void checkMessage(MailData message) {
        if (message == null || message.isEmpty()) {
            throw new IllegalArgumentException("Cannot send an empty message.");
        }
    }

    // ********************************************************************************
    //
    // Setters for dependencies injection
    //
    // ********************************************************************************

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public void setSmtpPort(int smtpPort) {
        this.smtpPort = smtpPort;
    }

    public void setSmtpLogin(String smtpLogin) {
        this.smtpLogin = smtpLogin;
    }

    public void setSmtpPassword(String smtpPassword) {
        this.smtpPassword = smtpPassword;
    }
}
