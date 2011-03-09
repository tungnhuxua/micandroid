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
package org.sempere.commons.mime;

/**
 * Helper class that provides methods to work with MIME types.
 *
 * @author bsempere
 */
public final class MimeMappingHelper {

    public static final String HTML_MIME_TYPE = "text/html";
    public static final String TEXT_MIME_TYPE = "text/plain";
    public static final String EXCEL_MIME_TYPE = "application/vnd.ms-excel";
    public static final String PPT_MIME_TYPE = "application/vnd.ms-powerpoint";
    public static final String WORD_MIME_TYPE = "application/vnd.ms-word";
    public static final String PDF_MIME_TYPE = "application/vnd.pdf";
    public static final String RTF_MIME_TYPE = "application/rtf";
    public static final String HTML_EXTENSION = "html";
    public static final String TEXT_EXTENSION = "txt";
    public static final String EXCEL_EXTENSION = "xls";
    public static final String CSV_EXTENSION = "csv";
    public static final String PPT_EXTENSION = "ppt";
    public static final String WORD_EXTENSION = "doc";
    public static final String PDF_EXTENSION = "pdf";
    public static final String RTF_EXTENSION = "rtf";

    // *************************************************************************
    //
    // Constructors
    //
    // *************************************************************************

    private MimeMappingHelper() {
    }

    /**
     * Returns the content type associated to the given file extension
     *
     * @param extension the file extension
     * @return String
     */
    public static String getMimeTypeForExt(String extension) {
        String mimeType = TEXT_MIME_TYPE;

        if (extension != null) {
            if (extension.equals(TEXT_EXTENSION)) {
                mimeType = TEXT_MIME_TYPE;
            } else if (extension.equals(CSV_EXTENSION)) {
                mimeType = TEXT_MIME_TYPE;
            } else if (extension.equals(WORD_EXTENSION)) {
                mimeType = WORD_MIME_TYPE;
            } else if (extension.equals(PPT_EXTENSION)) {
                mimeType = PPT_MIME_TYPE;
            } else if (extension.equals(EXCEL_EXTENSION)) {
                mimeType = EXCEL_MIME_TYPE;
            } else if (extension.equals(RTF_EXTENSION)) {
                mimeType = RTF_MIME_TYPE;
            } else if (extension.equals(PDF_EXTENSION)) {
                mimeType = PDF_MIME_TYPE;
            } else if (extension.equals(HTML_EXTENSION)) {
                mimeType = HTML_MIME_TYPE;
            }
        }

        return mimeType;
    }
}
