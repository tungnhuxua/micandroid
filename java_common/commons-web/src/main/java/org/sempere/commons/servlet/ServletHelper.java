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
package org.sempere.commons.servlet;

import javax.servlet.http.HttpServletResponse;

/**
 * Helper class for dealing with Servlet API.
 * 
 * @author sempere
 */
public final class ServletHelper {

    // *************************************************************************
    //
    // Constructors
    //
    // *************************************************************************
    
    private ServletHelper() {
    }

    // *************************************************************************
    //
    // Convenience methods
    //
    // *************************************************************************

    public static void writeContent(HttpServletResponse response, byte[] content, String contentType) throws Exception {
        // Writes the content in outputStream
        response.setContentType(contentType);
        response.setContentLength(content.length);
        response.getOutputStream().write(content);
        response.getOutputStream().flush();
    }
}
