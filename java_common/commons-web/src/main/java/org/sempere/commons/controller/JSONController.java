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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import flexjson.JSONSerializer;
import org.sempere.commons.encryption.PassPhraseProvider;
import org.sempere.commons.servlet.ServletHelper;

/**
 * A "TokenizedController" based on a JSON protocol.
 *
 * @author bsempere
 */
public abstract class JSONController extends TokenizedController {

    private PassPhraseProvider passPhraseProvider;

    // *************************************************************************
    //
    // Methods from TokenizedController class
    //
    // *************************************************************************

    @Override
    public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String objectJSON = this.createSerializer().serialize(this.getObjectToSerialize());
        ServletHelper.writeContent(response, objectJSON.getBytes(), "application/json");

        return null;
    }

    @Override
    public String getCurrentToken(HttpServletRequest request) {
        return request.getParameter("token");
    }

    @Override
    public String getExpectedToken() {
        return this.getEncrypter().encrypt(this.getPassPhraseProvider().getPassPhrase());
    }

    // *************************************************************************
    //
    // Convenience methods
    //
    // *************************************************************************

    public abstract Object getObjectToSerialize();

    protected JSONSerializer createSerializer() {
        JSONSerializer serializer = new JSONSerializer();
        serializer.exclude("*.password");

        return serializer;

    }

    // *************************************************************************
    //
    // Getters and Setters
    //
    // *************************************************************************

    public PassPhraseProvider getPassPhraseProvider() {
        return passPhraseProvider;
    }

    public void setPassPhraseProvider(PassPhraseProvider passPhraseProvider) {
        this.passPhraseProvider = passPhraseProvider;
    }
}
