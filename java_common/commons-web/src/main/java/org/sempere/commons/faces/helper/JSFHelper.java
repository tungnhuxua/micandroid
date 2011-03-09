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

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Locale;
import java.util.Map;

/**
 * Helper class that provides methods to work with JSF.
 *
 * @author sempere
 */
public final class JSFHelper {

    // Existing scope
    protected static final String APPLICATION_SCOPE = "applicationScope";
    protected static final String SESSION_SCOPE = "sessionScope";
    protected static final String REQUEST_SCOPE = "requestScope";
    protected static final String REQUEST_PARAM = "param";

    protected static final String UNAUTHENTICATED_USER_PRINCIPAL = "#N/A#";

    // *************************************************************************
    //
    // Constructors
    //
    // *************************************************************************

    private JSFHelper() {
    }

    public static FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    // ********************************************************************************
    //
    // Scopes maps related methods
    //
    // ********************************************************************************

    public static Map<String, Object> getApplicationScopeMap() {
        return getBean(APPLICATION_SCOPE);
    }

    public static Map<String, Object> getSessionScopeMap() {
        return getBean(SESSION_SCOPE);
    }

    public static Map<String, Object> getRequestScopeMap() {
        return getBean(REQUEST_SCOPE);
    }

    // ********************************************************************************
    //
    // Context related methods
    //
    // ********************************************************************************

    public static ServletContext getServletContext() {
        return getSession().getServletContext();
    }

    @SuppressWarnings("unchecked")
    public static <T> T getServletContextAttribute(String attributeName) {
        return (T) getServletContext().getAttribute(attributeName);
    }

    public static <T> void setServletContextAttribute(String attributeName, T attribute) {
        getServletContext().setAttribute(attributeName, attribute);
    }

    public static <T> void removeServletContextAttribute(String attributeName) {
        getServletContext().removeAttribute(attributeName);
    }

    // ********************************************************************************
    //
    // Session related methods
    //
    // ********************************************************************************

    public static HttpSession getSession() {
        return (HttpSession) getFacesContext().getExternalContext().getSession(true);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getSessionAttribute(String attributeName) {
        return (T) getSession().getAttribute(attributeName);
    }

    public static <T> void setSessionAttribute(String attributeName, T attribute) {
        getSession().setAttribute(attributeName, attribute);
    }

    public static void removeSessionAttribute(String attributeName) {
        getSession().removeAttribute(attributeName);
    }

    // ********************************************************************************
    //
    // Request related methods
    //
    // ********************************************************************************

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
    }

    @SuppressWarnings("unchecked")
    public static <T> T getRequestAttribute(String attributeName) {
        return (T) getRequest().getAttribute(attributeName);
    }

    public static <T> void setRequestAttribute(String attributeName, T attribute) {
        getRequest().setAttribute(attributeName, attribute);
    }

    public static void removeRequestAttribute(String attributeName) {
        getRequest().removeAttribute(attributeName);
    }

    public static String getRequestParameter(String paramName) {
        return getRequest().getParameter(paramName);
    }

    @SuppressWarnings("unchecked")
    public static String getRequestParameterLike(String parameterToken) {
        String parameterValue = null;
        Map<String, String> parametersMap = getFacesContext().getExternalContext().getRequestParameterMap();
        for (String key : parametersMap.keySet()) {
            if (key.contains(parameterToken)) {
                parameterValue = getRequestParameter(key);
            }
        }

        return parameterValue;
    }

    public static String getContextPath() {
        return getRequest().getContextPath();
    }

    // ********************************************************************************
    //
    // Response related methods
    //
    // ********************************************************************************

    public static HttpServletResponse getResponse() {
        return (HttpServletResponse) getFacesContext().getExternalContext().getResponse();
    }

    // ********************************************************************************
    //
    // Credentials related methods
    //
    // ********************************************************************************

    public static boolean hasUserPrincipal() {
        return getRequest().getUserPrincipal() != null;
    }

    public static String getUserName() {
        String userPrincipal = UNAUTHENTICATED_USER_PRINCIPAL;
        if (hasUserPrincipal()) {
            Principal principal = getRequest().getUserPrincipal();
            userPrincipal = principal.getName();
        }

        return userPrincipal;
    }

    // ********************************************************************************
    //
    // Internationalization related methods
    //
    // ********************************************************************************

    public static Locale getLocale() {
        Locale locale = getRequest().getLocale();
        if (locale == null) {
            locale = Locale.ENGLISH;
        }

        return locale;
    }

    public static String getLanguage() {
        return getLocale().getLanguage();
    }

    // ********************************************************************************
    //
    // Beans related methods
    //
    // ********************************************************************************

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName) {
        return (T) getFacesContext().getApplication().createValueBinding("#{" + beanName + "}").getValue(getFacesContext());
    }

    public static <T> void setBean(String beanName, T bean) {
        getFacesContext().getApplication().createValueBinding("#{" + beanName + "}").setValue(getFacesContext(), bean);
    }
}