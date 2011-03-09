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
package org.sempere.commons.faces.message;

import org.sempere.commons.base.I18nHelper;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import java.util.Locale;

/**
 * Helper class that provides methods to work with faces messages.
 *
 * @author bsempere
 */
public final class MessageHelper {

    private final static String H_MESSAGES_ID = "h_messages_id";

    // *************************************************************************
    //
    // Constructors
    //
    // *************************************************************************

    private MessageHelper() {
    }

    // *************************************************************************
    //
    // Convenience methods
    //
    // *************************************************************************

    /**
     * Displays a faces message
     *
     * @param severity the message severity to be used
     * @param key      the message key in the resources bundle
     * @param baseName the base name to locate the resources bundle
     * @param locale   the locale to locate the resources bundle
     */
    public static void displayMessage(Severity severity, String key, String baseName, Locale locale) {
        displayMessage(severity, key, null, baseName, locale);
    }

    /**
     * Displays a faces message using the current locale
     *
     * @param severity the message severity to be used
     * @param key      the message key in the resources bundle
     * @param baseName the base name to locate the resources bundle
     */
    public static void displayMessage(Severity severity, String key, String baseName) {
        displayMessage(severity, key, baseName, Locale.getDefault());
    }

    /**
     * Displays a faces message
     *
     * @param severity the message severity to be used
     * @param key      the message key in the resources bundle
     * @param params   the message parameters
     * @param baseName the base name to locate the resources bundle
     * @param locale   the locale to locate the resources bundle
     */
    public static void displayMessage(Severity severity, String key, Object[] params, String baseName, Locale locale) {
        FacesContext context = FacesContext.getCurrentInstance();
        String message = I18nHelper.getMessage(baseName, key, params, locale);

        FacesMessage facesMessage = new FacesMessage(severity, message, message);
        context.addMessage(H_MESSAGES_ID, facesMessage);
    }

    /**
     * Displays a faces message using the current locale
     *
     * @param severity the message severity to be used
     * @param key      the message key in the resources bundle
     * @param params   the message parameters
     * @param baseName the base name to locate the resources bundle
     */
    public static void displayMessage(Severity severity, String key, Object[] params, String baseName) {
        displayMessage(severity, key, params, baseName, Locale.getDefault());
    }
}