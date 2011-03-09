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
package org.sempere.commons;

import static org.sempere.commons.SystemException.LayerType.*;

/**
 * Base exceptions class for system exceptions<br>
 * A system exception causes the current transaction to rollback (in JTA mode).
 *
 * @author bsempere
 */
public class SystemException extends RuntimeException {

    private static final long serialVersionUID = -1207153914915110691L;

    public enum LayerType {
        PERSISTENCE, EJB, JMS, JMX, NAMING, UNDEFINED
    }

    public SystemException(String message) {
        this(message, UNDEFINED.name());
    }

    public SystemException(Throwable th) {
        this(th, UNDEFINED.name());
    }

    public SystemException(String message, Throwable th) {
        this(message, th, UNDEFINED.name());
    }

    public SystemException(String message, String layer) {
        super("A SystemException was encountered in layer [" + layer + "]. " + message);
    }

    public SystemException(Throwable th, String layer) {
        super("A SystemException was encountered in layer [" + layer + "]. ", th);
    }

    public SystemException(String message, Throwable th, String layer) {
        super("A SystemException was encountered in layer [" + layer + "]. " + message, th);
    }

    public SystemException(String message, LayerType layer) {
        this(message, layer.name());
    }

    public SystemException(Throwable th, LayerType layer) {
        this(th, layer.name());
    }

    public SystemException(String message, Throwable th, LayerType layer) {
        this(message, th, layer.name());
    }
}