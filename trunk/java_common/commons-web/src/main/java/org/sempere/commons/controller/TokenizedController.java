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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;

import org.apache.log4j.Logger;
import org.sempere.commons.encryption.Encrypter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * A "Controller" based on a security token.
 *
 * @author bsempere
 */
public abstract class TokenizedController implements Controller {

    private static final Logger LOGGER = Logger.getLogger(TokenizedController.class);

    // Underlying encrypter to be used
    private Encrypter encrypter;

    // *************************************************************************
    //
    // Methods from Controller
    //
    // *************************************************************************
    
    public final ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.debug("IN handleRequest");
        if (this.isValid(request)) {
            return this.handleRequestInternal(request, response);
        }

        // Unauthorized access
        response.setStatus(SC_FORBIDDEN);
        return null;
    }

    // *************************************************************************
    //
    // Convenience methods
    //
    // *************************************************************************

    public abstract ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * Return the current token stored in the client request (the token should be encypted using the encrypter's encrypt method)
     *
     * @param request the client request
     * @return String
     */
    public abstract String getCurrentToken(HttpServletRequest request);

    /**
     * Return the expected token (the token should be encypted using the encrypter's encrypt method)
     * 
     * @return String
     */
    public abstract String getExpectedToken();

    protected final boolean isValid(HttpServletRequest request) {
        if (!StringUtils.isBlank(this.getExpectedToken())) {
            // Expected token
            String expectedClearToken = this.getEncrypter().decrypt(this.getExpectedToken());

            // Current token
            String currentClearToken = this.getEncrypter().decrypt(this.getCurrentToken(request));

            // Validation
            return expectedClearToken.equals(currentClearToken);
        }

        return true;
    }

    // *************************************************************************
    //
    // Getters and Setters
    //
    // *************************************************************************

    public Encrypter getEncrypter() {
        return encrypter;
    }

    public void setEncrypter(Encrypter encrypter) {
        this.encrypter = encrypter;
    }
}
