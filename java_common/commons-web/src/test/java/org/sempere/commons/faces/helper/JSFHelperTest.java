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
package org.sempere.commons.faces.helper;

import static org.junit.Assert.*;

import java.util.Locale;
import java.util.Map;

import org.apache.shale.test.mock.MockPrincipal;
import org.junit.Before;
import org.junit.Test;
import org.sempere.commons.faces.AbstractJSFTest;

/**
 * Unit tests class for JSFHelper class.
 * 
 * @author bsempere
 */
public class JSFHelperTest extends AbstractJSFTest {

	@Before
	public void before() throws Exception {
		super.before();
		this.request.setPathElements("/JSFHelper", "/UnitTests", "", "");
	}

	@Test
	public void getFacesContext() throws Exception {
		assertSame(this.facesContext, JSFHelper.getFacesContext());
	}

	// ********************************************************************************
	//
	// Scopes maps related methods
	//
	// ********************************************************************************

	@Test
	public void getApplicationScopeMap() throws Exception {
		this.servletContext.setAttribute("attribute1", "Object1");

		Map<String, Object> map = JSFHelper.getApplicationScopeMap();
		assertTrue("Application map should contain attribute.", map.containsKey("attribute1"));

	}

	@Test
	public void getSessionScopeMap() throws Exception {
		this.session.setAttribute("attribute1", "Object1");

		Map<String, Object> map = JSFHelper.getSessionScopeMap();
		assertTrue("Session map should contain attribute.", map.containsKey("attribute1"));
	}

	@Test
	public void getRequestScopeMap() throws Exception {
		this.request.setAttribute("attribute1", "Object1");

		Map<String, Object> map = JSFHelper.getRequestScopeMap();
		assertTrue("Request map should contain attribute.", map.containsKey("attribute1"));
	}

	// ********************************************************************************
	//
	// Context related methods
	//
	// ********************************************************************************

	@Test
	public void getServletContext() throws Exception {
		assertSame(this.servletContext, JSFHelper.getServletContext());
	}

	@Test
	public void getServletContextAttributeWhenAttributeDoesNotExist() throws Exception {
		assertNull("The returned value should be null.", JSFHelper.getServletContextAttribute("unknown"));
	}

	@Test
	public void setAndGetServletContextAttribute() throws Exception {
		String attributeName = "attributeName";
		String attributeValue = "attributeValue";

		JSFHelper.setServletContextAttribute(attributeName, attributeValue);
		assertEquals(attributeValue, JSFHelper.getServletContextAttribute(attributeName));
	}

	@Test
	public void removeServletContextAttribute() throws Exception {
		String attributeName = "attributeName";
		String attributeValue = "attributeValue";

		JSFHelper.setServletContextAttribute(attributeName, attributeValue);
		JSFHelper.removeServletContextAttribute(attributeName);
		assertNull("The returned value should be null.", JSFHelper.getServletContextAttribute("unknown"));
	}

	// ********************************************************************************
	//
	// Session related methods
	//
	// ********************************************************************************

	@Test
	public void getSession() throws Exception {
		assertSame(this.session, JSFHelper.getSession());
	}

	@Test
	public void getSessionAttributeWhenAttributeDoesNotExist() throws Exception {
		assertNull("The returned value should be null.", JSFHelper.getSessionAttribute("unknown"));
	}

	@Test
	public void setAndGetSessionAttribute() throws Exception {
		String attributeName = "attributeName";
		String attributeValue = "attributeValue";

		JSFHelper.setSessionAttribute(attributeName, attributeValue);
		assertEquals(attributeValue, JSFHelper.getSessionAttribute(attributeName));
	}

	@Test
	public void removeSessionAttribute() throws Exception {
		String attributeName = "attributeName";
		String attributeValue = "attributeValue";

		JSFHelper.setSessionAttribute(attributeName, attributeValue);
		JSFHelper.removeSessionAttribute(attributeName);
		assertNull("The returned value should be null.", JSFHelper.getSessionAttribute("unknown"));
	}

	// ********************************************************************************
	//
	// Request related methods
	//
	// ********************************************************************************

	@Test
	public void getRequest() throws Exception {
		assertSame(this.request, JSFHelper.getRequest());
	}

	@Test
	public void getRequestAttributeWhenAttributeDoesNotExist() throws Exception {
		assertNull("The returned value should be null.", JSFHelper.getRequestAttribute("unknown"));
	}

	@Test
	public void setAndGetRequestAttribute() throws Exception {
		String attributeName = "attributeName";
		String attributeValue = "attributeValue";

		JSFHelper.setRequestAttribute(attributeName, attributeValue);
		assertEquals(attributeValue, JSFHelper.getRequestAttribute(attributeName));
	}

	@Test
	public void removeRequestAttribute() throws Exception {
		String attributeName = "attributeName";
		String attributeValue = "attributeValue";

		JSFHelper.setRequestAttribute(attributeName, attributeValue);
		JSFHelper.removeRequestAttribute(attributeName);
		assertNull("The returned value should be null.", JSFHelper.getRequestAttribute("unknown"));
	}

	@Test
	public void getRequestParameterWhenParameterDoesNotExist() throws Exception {
		assertNull("The returned value should be null.", JSFHelper.getRequestParameter("unknown"));
	}

	@Test
	public void getRequestParameterWhenParameterExists() throws Exception {
		String parameterName = "paramName";
		String parameterValue = "paramValue";

		this.request.addParameter(parameterName, parameterValue);
		assertEquals(parameterValue, JSFHelper.getRequestParameter(parameterName));
	}

	@Test
	public void getRequestParameterLikeWhenParameterDoesNotExist() throws Exception {
		assertNull("The returned value should be null.", JSFHelper.getRequestParameterLike("form1:unknown"));
	}

	@Test
	public void getRequestParameterLikeWhenParameterExists() throws Exception {
		String parameterName = "form1:paramName";
		String parameterValue = "paramValue";

		this.request.addParameter(parameterName, parameterValue);
		this.externalContext.setRequestParameterMap(this.request.getParameterMap());
		assertEquals(parameterValue, JSFHelper.getRequestParameterLike(parameterName));
	}

	@Test
	public void getContextPath() throws Exception {
		assertEquals("/JSFHelper", JSFHelper.getContextPath());
	}

	// ********************************************************************************
	//
	// Response related methods
	//
	// ********************************************************************************

	@Test
	public void getResponse() throws Exception {
		assertSame(this.response, JSFHelper.getResponse());
	}

	// ********************************************************************************
	//
	// Credentials related methods
	//
	// ********************************************************************************

	@Test
	public void hasUserPrincipalWhenRequestDoesNotContainUserPrincipal() throws Exception {
		assertFalse("No user principal should be found.", JSFHelper.hasUserPrincipal());
	}

	@Test
	public void hasUserPrincipalWhenRequestContainsUserPrincipal() throws Exception {
		this.request.setUserPrincipal(new MockPrincipal("benjamin.sempere"));
		assertTrue("A user principal should be found.", JSFHelper.hasUserPrincipal());
	}

	@Test
	public void getUserNameWhenRequestDoesNotContainUserPrincipal() throws Exception {
		assertEquals(JSFHelper.UNAUTHENTICATED_USER_PRINCIPAL, JSFHelper.getUserName());
	}

	@Test
	public void getUserNameWhenRequestContainsUserPrincipal() throws Exception {
		this.request.setUserPrincipal(new MockPrincipal("benjamin.sempere"));
		assertEquals("benjamin.sempere", JSFHelper.getUserName());
	}

	// ********************************************************************************
	//
	// Internationalization related methods
	//
	// ********************************************************************************

	@Test
	public void getLocaleWhenStoredLocaleIsNull() throws Exception {
		this.request.setLocale(null);
		assertEquals(Locale.ENGLISH, JSFHelper.getLocale());
	}

	@Test
	public void getLocaleWhenStoredLocaleIsNotNull() throws Exception {
		this.request.setLocale(Locale.FRANCE);
		assertEquals(Locale.FRANCE, JSFHelper.getLocale());
	}

	@Test
	public void getLanguageWhenStoredLocaleIsNull() throws Exception {
		this.request.setLocale(null);
		assertEquals(Locale.ENGLISH.getLanguage(), JSFHelper.getLanguage());
	}

	@Test
	public void getLanguageWhenStoredLocaleIsNotNull() throws Exception {
		this.request.setLocale(Locale.FRANCE);
		assertEquals(Locale.FRANCE.getLanguage(), JSFHelper.getLanguage());
	}

	// ********************************************************************************
	//
	// Bean related methods
	//
	// ********************************************************************************

	@Test
	public void getBeanWhenBeanDoesNotExist() throws Exception {
		assertNull("The returned bean should be null.", JSFHelper.getBean("unknown"));
	}

	@Test
	public void getBeanWhenBeanExists() throws Exception {
		String beanName = "myBean";
		String stringBean = "hello";

		JSFHelper.setBean(beanName, stringBean);
		assertSame(stringBean, JSFHelper.getBean(beanName));
	}
}
