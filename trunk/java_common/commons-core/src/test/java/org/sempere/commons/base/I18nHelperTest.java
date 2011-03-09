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
package org.sempere.commons.base;

import static org.junit.Assert.*;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests class for I18nHelper class.
 * 
 * @author bsempere
 */
public class I18nHelperTest {

	private static final String BASE_NAME = "org.sempere.commons.i18n.bundle";

	@Before
	public void before() throws Exception {
		Locale.setDefault(Locale.ENGLISH);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getMessageWithLocaleWhenBasenameIsNull() throws Exception {
		I18nHelper.getMessage(null, "msg.hello", new Object[] { "benjamin" }, Locale.FRENCH);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getMessageWithLocaleWhenBasenameIsEmpty() throws Exception {
		I18nHelper.getMessage("", "msg.hello", new Object[] { "benjamin" }, Locale.FRENCH);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getMessageWithLocaleWhenKeyIsNull() throws Exception {
		I18nHelper.getMessage(BASE_NAME, null, new Object[] { "benjamin" }, Locale.FRENCH);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getMessageWithLocaleWhenKeyIsEmpty() throws Exception {
		I18nHelper.getMessage(BASE_NAME, "", new Object[] { "benjamin" }, Locale.FRENCH);
	}

	@Test
	public void getMessageWithLocaleWhenKeyDoesNotExist() throws Exception {
		String msg = I18nHelper.getMessage(BASE_NAME, "unknown", new Object[] {}, Locale.FRENCH);
		assertEquals("???_unknown_???", msg);
	}

	@Test
	public void getMessageWithLocaleWithParametersWhenParametersArrayIsNull() throws Exception {
		String msg = I18nHelper.getMessage(BASE_NAME, "msg.hello", null, Locale.FRENCH);
		assertEquals("bonjour {0}", msg);
	}

	@Test
	public void getMessageWithLocaleWithParametersWhenOneParameterIsExpected() throws Exception {
		String msg = I18nHelper.getMessage(BASE_NAME, "msg.hello", new Object[] { "benjamin" }, Locale.FRENCH);
		assertEquals("bonjour benjamin", msg);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getMessageWithoutLocaleWhenBasenameIsNull() throws Exception {
		I18nHelper.getMessage(null, "msg.hello", new Object[] { "benjamin" });
	}

	@Test(expected = IllegalArgumentException.class)
	public void getMessageWithoutLocaleWhenBasenameIsEmpty() throws Exception {
		I18nHelper.getMessage("", "msg.hello", new Object[] { "benjamin" });
	}

	@Test(expected = IllegalArgumentException.class)
	public void getMessageWithoutLocaleWhenKeyIsNull() throws Exception {
		I18nHelper.getMessage(BASE_NAME, null, new Object[] { "benjamin" });
	}

	@Test(expected = IllegalArgumentException.class)
	public void getMessageWithoutLocaleWhenKeyIsEmpty() throws Exception {
		I18nHelper.getMessage(BASE_NAME, "", new Object[] { "benjamin" });
	}

	@Test
	public void getMessageWithoutLocaleWhenKeyDoesNotExist() throws Exception {
		String msg = I18nHelper.getMessage(BASE_NAME, "unknown", new Object[] {});
		assertEquals("???_unknown_???", msg);
	}

	@Test
	public void getMessageWithoutLocaleWithParametersWhenParametersArrayIsNull() throws Exception {
		String msg = I18nHelper.getMessage(BASE_NAME, "msg.hello", null);
		assertEquals("hello {0}", msg);
	}

	@Test
	public void getMessageWithoutLocaleWithParametersWhenOneParameterIsExpected() throws Exception {
		String msg = I18nHelper.getMessage(BASE_NAME, "msg.hello", new Object[] { "benjamin" });
		assertEquals("hello benjamin", msg);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getResourceBundleWhenBaseNameIsNull() throws Exception {
		I18nHelper.getResourceBundle(null, Locale.FRENCH);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getResourceBundleWhenBaseNameIsEmpty() throws Exception {
		I18nHelper.getResourceBundle(" ", Locale.FRENCH);
	}

	@Test
	public void getResourceBundleWhenLocaleIsNull() throws Exception {
		assertEquals("hello {0}", I18nHelper.getResourceBundle(BASE_NAME, null).getObject("msg.hello"));
	}

	@Test
	public void getResourceBundleWhenBaseNameAndLocaleAreValid() throws Exception {
		assertEquals("bonjour {0}", I18nHelper.getResourceBundle(BASE_NAME, Locale.FRENCH).getObject("msg.hello"));
	}

	@Test
	public void substituteWhenMessageIsNull() throws Exception {
		assertNull(I18nHelper.substitute(null, new Object[] { "test" }));
	}

	@Test
	public void substituteWhenParametersArrayIsNull() throws Exception {
		String msg = I18nHelper.substitute("this is a {0}", null);
		assertEquals("this is a {0}", msg);
	}

	@Test
	public void substitute() throws Exception {
		String msg = I18nHelper.substitute("this is a {0} {1}", new Object[] { "simple", "test" });
		assertEquals("this is a simple test", msg);
	}
}
