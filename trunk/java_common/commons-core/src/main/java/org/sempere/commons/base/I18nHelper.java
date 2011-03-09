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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrSubstitutor;

import java.util.*;

/**
 * Helper class that provides methods to work with localization.
 *
 * @author bsempere
 */
public final class I18nHelper {

    // *************************************************************************
    //
    // Constructors
    //
    // *************************************************************************   

    private I18nHelper() {
    }

    // *************************************************************************
    //
    // Convenience methods
    //
    // ************************************************************************* 

    /**
     * Get the localized message for the given key using the current locale<br>
     * If the key does not exist, returns "???_key_???" where "key" is the given key
     *
     * @param baseName the resource bundle base name
     * @param key      the message key to be searched
     * @param params   message parameters if any
     * @return String
     */
    public static String getMessage(String baseName, String key, Object[] params) {
        return getMessage(baseName, key, params, null);
    }

    /**
     * Get the localized message for the given key and using the given locale<br>
     * If the key does not exist, returns "???_key_???" where "key" is the given key<br>
     * If the locale is null, the current locale will took into consideration.
     *
     * @param baseName the resource bundle base name
     * @param key      the message key to be searched
     * @param params   the message parameters if any
     * @param locale   the locale to be used
     * @return String
     */
    public static String getMessage(String baseName, String key, Object[] params, Locale locale) {
        if (StringUtils.isBlank(key)) {
            throw new IllegalArgumentException("Key cannot be empty.");
        }

        String message = null;
        try {
            message = getResourceBundle(baseName, locale).getString(key);
            message = substitute(message, params);
        } catch (MissingResourceException e) {
            message = "???_" + key + "_???";
        }

        return message;
    }

    /**
     * Returns the resources bundle
     *
     * @param baseName the bundle base name
     * @param locale   the locale to be used to select the right properties file
     * @return ResourceBundle
     */
    public static ResourceBundle getResourceBundle(String baseName, Locale locale) {
        if (StringUtils.isBlank(baseName)) {
            throw new IllegalArgumentException("Base name cannot be empty.");
        }

        ResourceBundle resourceBundle = null;
        if (locale == null) {
            // Get the resource bundle for the current locale
            resourceBundle = ResourceBundle.getBundle(baseName);
        } else {
            // Get the resource bundle for the given locale
            resourceBundle = ResourceBundle.getBundle(baseName, locale);
        }

        return resourceBundle;
    }

    /**
     * Provide a mechanism that allows parameters to be replaced by given data.<br>
     * Example: "this is a {0} {1}" with params = {"simple", "test"} returns => "this is a simple test"
     *
     * @param msg    the message to be filled in
     * @param params the parameters to be applied
     * @return String
     */
    protected static String substitute(String msg, Object[] params) {
        String filledString = msg;

        if (msg != null) {
            Map<String, String> valuesMap = new HashMap<String, String>();
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    Object param = params[i];
                    if (param != null) {
                        valuesMap.put(String.valueOf(i), param.toString());
                    }
                }

                StrSubstitutor substitutor = new StrSubstitutor(valuesMap);
                substitutor.setVariablePrefix("{");
                substitutor.setVariableSuffix("}");
                filledString = substitutor.replace(filledString);
            }
        }

        return filledString;
    }
}
