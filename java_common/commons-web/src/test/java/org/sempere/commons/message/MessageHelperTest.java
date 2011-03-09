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
package org.sempere.commons.message;

import static junit.framework.Assert.*;

import java.util.Iterator;
import java.util.Locale;

import javax.faces.application.FacesMessage;

import org.junit.Test;
import org.sempere.commons.faces.AbstractJSFTest;
import org.sempere.commons.faces.message.MessageHelper;

/**
 * Unit tests class for MessageHelperTest class.
 * 
 * @author bsempere
 */
public class MessageHelperTest extends AbstractJSFTest {

	private static final String BASE_NAME = "org.sempere.commons.message.bundle";

	@Override
	public void before() throws Exception {
		super.before();
		Locale.setDefault(Locale.FRENCH);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void displayMessageWithBaseNameWhenKeyDoesNotExist() throws Exception {
		MessageHelper.displayMessage(FacesMessage.SEVERITY_ERROR, "unknown", BASE_NAME, Locale.FRENCH);

		Iterator<FacesMessage> it = this.facesContext.getMessages();

		FacesMessage message = it.next();
		assertEquals("???_unknown_???", message.getSummary());
		assertFalse("Messages list should not contain another message.", it.hasNext());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void displayMessageWithBaseNameAndLocaleIsFR() throws Exception {
		MessageHelper.displayMessage(FacesMessage.SEVERITY_ERROR, "msg.hello", BASE_NAME, Locale.FRENCH);

		Iterator<FacesMessage> it = this.facesContext.getMessages();

		FacesMessage message = it.next();
		assertEquals("bonjour {0}", message.getSummary());
		assertFalse("Messages list should not contain another message.", it.hasNext());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void displayMessageWithBaseNameAndLocaleIsEN() throws Exception {
		MessageHelper.displayMessage(FacesMessage.SEVERITY_ERROR, "msg.hello", BASE_NAME, Locale.ENGLISH);

		Iterator<FacesMessage> it = this.facesContext.getMessages();

		FacesMessage message = it.next();
		assertEquals("hello {0}", message.getSummary());
		assertFalse("Messages list should not contain another message.", it.hasNext());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void displayMessageWithBaseNameAndLocaleIsNotProvided() throws Exception {
		MessageHelper.displayMessage(FacesMessage.SEVERITY_ERROR, "msg.hello", BASE_NAME);

		Iterator<FacesMessage> it = this.facesContext.getMessages();

		FacesMessage message = it.next();
		assertEquals("bonjour {0}", message.getSummary());
		assertFalse("Messages list should not contain another message.", it.hasNext());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void displayMessageWithParametersAndBaseNameWhenKeyDoesNotExist() throws Exception {
		MessageHelper.displayMessage(FacesMessage.SEVERITY_ERROR, "unknown", new Object[] { "benjamin" }, BASE_NAME, Locale.FRENCH);

		Iterator<FacesMessage> it = this.facesContext.getMessages();

		FacesMessage message = it.next();
		assertEquals("???_unknown_???", message.getSummary());
		assertFalse("Messages list should not contain another message.", it.hasNext());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void displayMessageWithParametersAndBaseNameAndLocaleFR() throws Exception {
		MessageHelper.displayMessage(FacesMessage.SEVERITY_ERROR, "msg.hello", new Object[] { "benjamin" }, BASE_NAME, Locale.FRENCH);

		Iterator<FacesMessage> it = this.facesContext.getMessages();

		FacesMessage message = it.next();
		assertEquals("bonjour benjamin", message.getSummary());
		assertFalse("Messages list should not contain another message.", it.hasNext());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void displayMessageWithParametersAndBaseNameAndLocaleEN() throws Exception {
		MessageHelper.displayMessage(FacesMessage.SEVERITY_ERROR, "msg.hello", new Object[] { "benjamin" }, BASE_NAME, Locale.ENGLISH);

		Iterator<FacesMessage> it = this.facesContext.getMessages();

		FacesMessage message = it.next();
		assertEquals("hello benjamin", message.getSummary());
		assertFalse("Messages list should not contain another message.", it.hasNext());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void displayMessageWithParametersAndBaseNameAndLocaleIsNotProvided() throws Exception {
		MessageHelper.displayMessage(FacesMessage.SEVERITY_ERROR, "msg.hello", new Object[] { "benjamin" }, BASE_NAME);

		Iterator<FacesMessage> it = this.facesContext.getMessages();

		FacesMessage message = it.next();
		assertEquals("bonjour benjamin", message.getSummary());
		assertFalse("Messages list should not contain another message.", it.hasNext());
	}
}
