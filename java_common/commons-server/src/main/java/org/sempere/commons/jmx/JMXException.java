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
package org.sempere.commons.jmx;

import org.sempere.commons.SystemException;
import static org.sempere.commons.SystemException.LayerType.*;

/**
 * Base exception class for exceptions encountered while using the JMXManager.<br>
 * This class is an instance of SystemException. So, it causes the current transaction to rollback (in JTA mode).
 *
 * @author bsempere
 */
public class JMXException extends SystemException {

    private static final long serialVersionUID = -4016558134965643701L;

    public JMXException(String message) {
        super(message, JMX);
    }

    public JMXException(Throwable th) {
        super(th, JMX);
    }

    public JMXException(String message, Throwable th) {
        super(message, th, JMX);
    }
}