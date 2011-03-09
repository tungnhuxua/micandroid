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

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;

import static org.junit.Assert.*;

/**
 * Unit tests class for MailSenderImpl class.
 *
 * @author bsempere
 */
public class MailSenderImplTest {

    private static final String TEXT_EMAIL_RESOURCE_PATH = "/org/sempere/commons/mail/email.txt";
    private static final String HTML_EMAIL_RESOURCE_PATH = "/org/sempere/commons/mail/email.html";

    private static String textEmailContent;
    private static String htmlEmailContent;

    private Wiser wiser;
    private MailSenderImpl sender;

    @BeforeClass
    public static void beforeClass() throws Exception {
        textEmailContent = IOUtils.toString(MailSenderImpl.class.getResourceAsStream(TEXT_EMAIL_RESOURCE_PATH));
        htmlEmailContent = IOUtils.toString(MailSenderImpl.class.getResourceAsStream(HTML_EMAIL_RESOURCE_PATH));
    }

    @Before
    public void before() throws Exception {
        this.wiser = new Wiser();
        this.wiser.setHostname("localhost");
        this.wiser.setPort(6666);
        this.wiser.start();

        this.sender = new MailSenderImpl();
    }

    @After
    public void after() throws Exception {
        if (this.wiser != null) {
            this.wiser.stop();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkConnectionInformationsWhenHostIsNull() throws Exception {
        this.sender.checkConnectionInformations();
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkConnectionInformationsWhenHostIsEmpty() throws Exception {
        sender.setSmtpHost("");
        this.sender.checkConnectionInformations();
    }

    @Test
    public void checkConnectionInformationsWhenInformationsAreValid() throws Exception {
        sender.setSmtpHost("hostname");
        this.sender.checkConnectionInformations();
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkMessageWhenMessageIsNull() throws Exception {
        this.sender.checkMessage(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkMessageWhenMessageIsEmpty() throws Exception {
        this.sender.checkMessage(new MailData());
    }

    @Test
    public void checkMessageWhenMessageIsValid() throws Exception {
        this.sender.checkMessage(this.createTextEmailMessageData());
    }

    @Test(expected = IllegalArgumentException.class)
    public void onPreSendWhenHostIsNull() throws Exception {
        this.sender.onPreSend(this.createTextEmailMessageData());
    }

    @Test(expected = IllegalArgumentException.class)
    public void onPreSendWhenHostIsEmpty() throws Exception {
        this.sender.setSmtpHost("");
        this.sender.onPreSend(this.createTextEmailMessageData());
    }

    @Test(expected = IllegalArgumentException.class)
    public void onPreSendWhenMessageIsNull() throws Exception {
        this.sender.setSmtpHost("host");
        this.sender.onPreSend(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void onPreSendWhenMessageIsEmpty() throws Exception {
        this.sender.setSmtpHost("host");
        this.sender.onPreSend(new MailData());
    }

    @Test
    public void onPreSendWhenAllIsOK() throws Exception {
        this.sender.setSmtpHost("host");
        this.sender.onPreSend(this.createTextEmailMessageData());
    }

    @Test(expected = IllegalArgumentException.class)
    public void sendTextMessageWhenHostIsNull() throws Exception {
        this.sender.sendTextEmail(this.createTextEmailMessageData());
    }

    @Test(expected = IllegalArgumentException.class)
    public void sendTextMessageWhenHostIsEmpty() throws Exception {
        this.sender.setSmtpHost("");
        this.sender.sendTextEmail(this.createTextEmailMessageData());
    }

    @Test(expected = IllegalArgumentException.class)
    public void sendTextMessageWhenMessageIsNull() throws Exception {
        this.sender.setSmtpHost("hostname");
        this.sender.sendTextEmail(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void sendTextMessageWhenMessageIsEmpty() throws Exception {
        this.sender.setSmtpHost("hostname");
        this.sender.sendTextEmail(new MailData());
    }

    @Test
    public void sendTextMessageWhenAllIsOK() throws Exception {
        MailData message = this.createTextEmailMessageData();

        this.sender.setSmtpHost("localhost");
        this.sender.setSmtpPort(6666);
        this.sender.sendTextEmail(message);

        assertEquals(4, this.wiser.getMessages().size());

        WiserMessage wiserMessage = this.wiser.getMessages().get(0);
        assertEquals(message.getFrom(), wiserMessage.getEnvelopeSender());
        assertEquals(message.getTo()[0], wiserMessage.getEnvelopeReceiver());
        assertEquals(message.getSubject(), wiserMessage.getMimeMessage().getSubject());
        assertEquals(message.getContent(), wiserMessage.getMimeMessage().getContent());

        wiserMessage = this.wiser.getMessages().get(1);
        assertEquals(message.getFrom(), wiserMessage.getEnvelopeSender());
        assertEquals(message.getTo()[1], wiserMessage.getEnvelopeReceiver());
        assertEquals(message.getSubject(), wiserMessage.getMimeMessage().getSubject());
        assertEquals(message.getContent(), wiserMessage.getMimeMessage().getContent());

        wiserMessage = this.wiser.getMessages().get(2);
        assertEquals(message.getFrom(), wiserMessage.getEnvelopeSender());
        assertEquals(message.getCc()[0], wiserMessage.getEnvelopeReceiver());
        assertEquals(message.getSubject(), wiserMessage.getMimeMessage().getSubject());
        assertEquals(message.getContent(), wiserMessage.getMimeMessage().getContent());

        wiserMessage = this.wiser.getMessages().get(3);
        assertEquals(message.getFrom(), wiserMessage.getEnvelopeSender());
        assertEquals(message.getBcc()[0], wiserMessage.getEnvelopeReceiver());
        assertEquals(message.getSubject(), wiserMessage.getMimeMessage().getSubject());
        assertEquals(message.getContent(), wiserMessage.getMimeMessage().getContent());
    }

    @Test(expected = IllegalArgumentException.class)
    public void sendHtmlMessageWhenHostIsNull() throws Exception {
        this.sender.sendHtmlEmail(this.createHtmlEmailMessageData());
    }

    @Test(expected = IllegalArgumentException.class)
    public void sendHtmlMessageWhenHostIsEmpty() throws Exception {
        this.sender.setSmtpHost("");
        this.sender.sendHtmlEmail(this.createHtmlEmailMessageData());
    }

    @Test(expected = IllegalArgumentException.class)
    public void sendHtmlMessageWhenMessageIsNull() throws Exception {
        this.sender.setSmtpHost("hostname");
        this.sender.sendHtmlEmail(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void sendHtmlMessageWhenMessageIsEmpty() throws Exception {
        this.sender.setSmtpHost("hostname");
        this.sender.sendHtmlEmail(new MailData());
    }

    @Test
    public void sendHtmlMessageWhenAllIsOK() throws Exception {
        MailData message = this.createHtmlEmailMessageData();

        this.sender.setSmtpHost("localhost");
        this.sender.setSmtpPort(6666);
        this.sender.sendTextEmail(message);

        assertEquals(4, this.wiser.getMessages().size());

        WiserMessage wiserMessage = this.wiser.getMessages().get(0);
        assertEquals(message.getFrom(), wiserMessage.getEnvelopeSender());
        assertEquals(message.getTo()[0], wiserMessage.getEnvelopeReceiver());
        assertEquals(message.getSubject(), wiserMessage.getMimeMessage().getSubject());
        assertEquals(message.getContent(), wiserMessage.getMimeMessage().getContent());

        wiserMessage = this.wiser.getMessages().get(1);
        assertEquals(message.getFrom(), wiserMessage.getEnvelopeSender());
        assertEquals(message.getTo()[1], wiserMessage.getEnvelopeReceiver());
        assertEquals(message.getSubject(), wiserMessage.getMimeMessage().getSubject());
        assertEquals(message.getContent(), wiserMessage.getMimeMessage().getContent());

        wiserMessage = this.wiser.getMessages().get(2);
        assertEquals(message.getFrom(), wiserMessage.getEnvelopeSender());
        assertEquals(message.getCc()[0], wiserMessage.getEnvelopeReceiver());
        assertEquals(message.getSubject(), wiserMessage.getMimeMessage().getSubject());
        assertEquals(message.getContent(), wiserMessage.getMimeMessage().getContent());

        wiserMessage = this.wiser.getMessages().get(3);
        assertEquals(message.getFrom(), wiserMessage.getEnvelopeSender());
        assertEquals(message.getBcc()[0], wiserMessage.getEnvelopeReceiver());
        assertEquals(message.getSubject(), wiserMessage.getMimeMessage().getSubject());
        assertEquals(message.getContent(), wiserMessage.getMimeMessage().getContent());
    }

    // ********************************************************************************
    //
    // Test fixtures, stubs, ...
    //
    // ********************************************************************************

    private MailData createTextEmailMessageData() {
        MailData message = new MailData();
        message.setFrom("from@from.from");
        message.setSubject("subject");
        message.setContent(textEmailContent);
        message.setTo(new String[]{"to1@to.to", "to2@to.to"});
        message.setCc(new String[]{"cc1@cc.cc"});
        message.setBcc(new String[]{"bcc1@bcc.bcc"});

        return message;
    }

    private MailData createHtmlEmailMessageData() {
        MailData message = new MailData();
        message.setFrom("from@from.from");
        message.setSubject("subject");
        message.setContent(htmlEmailContent);
        message.setTo(new String[]{"to1@to.to", "to2@to.to"});
        message.setCc(new String[]{"cc1@cc.cc"});
        message.setBcc(new String[]{"bcc1@bcc.bcc"});

        return message;
    }
}
