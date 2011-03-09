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
package org.sempere.commons.controller;

import static javax.servlet.http.HttpServletResponse.*;
import org.junit.Before;
import org.junit.Test;
import org.sempere.commons.encryption.Encrypter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import static org.mockito.Mockito.*;
import static junit.framework.Assert.*;

/**
 * Unit tests class for TokenizedController class.
 *
 * @author bsempere
 */
public class TokenizedControllerTest {

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Before
    public void before() throws Exception {
        this.request = new MockHttpServletRequest();
        this.response = new MockHttpServletResponse();
    }

    @Test
    public void handleRequestShouldReturnResultOfHandleRequestInternalInvocationWhenTokenCanBeVerified() throws Exception {
        Encrypter encrypter = mock(Encrypter.class);
        when(encrypter.decrypt("123456789")).thenReturn("MyPassPhrase1");

        TokenizedController controller = mock(TokenizedController.class);
        when(controller.getExpectedToken()).thenReturn("123456789");
        when(controller.getCurrentToken(this.request)).thenReturn("123456789");
        when(controller.getEncrypter()).thenReturn(encrypter);
        when(controller.handleRequest(this.request, this.response)).thenReturn(new ModelAndView("MyViewName"));

        assertEquals("MyViewName", controller.handleRequest(this.request, this.response).getViewName());
        assertEquals(SC_OK, this.response.getStatus());
    }

    @Test
    public void handleRequestShouldReturnForbiddenStatusWhenTokenCannotBeVerified() throws Exception {
        Encrypter encrypter = mock(Encrypter.class);
        when(encrypter.decrypt("123456789")).thenReturn("MyPassPhrase1");
        when(encrypter.decrypt("987654321")).thenReturn("MyPassPhrase2");

        TokenizedController controller = mock(TokenizedController.class);
        when(controller.getExpectedToken()).thenReturn("123456789");
        when(controller.getCurrentToken(this.request)).thenReturn("987654321");
        when(controller.getEncrypter()).thenReturn(encrypter);

        assertNull("ModelAndView should be null.", controller.handleRequest(this.request, this.response));
        assertEquals(SC_FORBIDDEN, this.response.getStatus());
    }

    @Test
    public void isValidWhenNoTokenIsExpected() throws Exception {
        TokenizedController controller = mock(TokenizedController.class);
        when(controller.getExpectedToken()).thenReturn(null);

        assertTrue("Tokens verification should return true.", controller.isValid(this.request));
    }

    @Test
    public void isValidWhenTokenIsExpected() throws Exception {
        Encrypter encrypter = mock(Encrypter.class);
        when(encrypter.decrypt("123456789")).thenReturn("MyPassPhrase");

        TokenizedController controller = mock(TokenizedController.class);
        when(controller.getCurrentToken(this.request)).thenReturn("123456789");
        when(controller.getExpectedToken()).thenReturn("123456789");
        when(controller.getEncrypter()).thenReturn(encrypter);

        assertTrue("Tokens verification should return true.", controller.isValid(this.request));
    }

    @Test
    public void isNotValidWhenTokenIsExpected() throws Exception {
        Encrypter encrypter = mock(Encrypter.class);
        when(encrypter.decrypt("123456789")).thenReturn("MyPassPhrase1");
        when(encrypter.decrypt("987654321")).thenReturn("MyPassPhrase2");

        TokenizedController controller = mock(TokenizedController.class);
        when(controller.getCurrentToken(this.request)).thenReturn("123456789");
        when(controller.getExpectedToken()).thenReturn("987654321");
        when(controller.getEncrypter()).thenReturn(encrypter);

        assertFalse("Tokens verification should return false.", controller.isValid(this.request));
    }
}
