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
package org.sempere.commons.mail;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.sempere.commons.print.PrintableObject;

/**
 * Bean that encapsulates mail data.
 *
 * @author bsempere
 */
public class MailData extends PrintableObject {

    private String subject;
    private String content;
    private String from;
    private String[] to;
    private String[] cc;
    private String[] bcc;

    // *************************************************************************
    //
    // Constructors
    //
    // *************************************************************************

    public MailData() {
    }

    // *************************************************************************
    //
    // Convenience methods
    //
    // *************************************************************************

    public boolean isEmpty() {
        return StringUtils.isBlank(this.getFrom()) || StringUtils.isBlank(this.getSubject()) || ArrayUtils.isEmpty(this.getTo());
    }

    // *************************************************************************
    //
    // Getters and Setters
    //
    // ************************************************************************* 

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public String[] getBcc() {
        return bcc;
    }

    public void setBcc(String[] bcc) {
        this.bcc = bcc;
    }
}
