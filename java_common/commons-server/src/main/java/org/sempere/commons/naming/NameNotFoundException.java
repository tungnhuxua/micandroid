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
package org.sempere.commons.naming;

import static javax.naming.Context.*;
import java.util.Hashtable;

/**
 * Exception class that is thrown when an object does not exist in a JNDI.
 *
 * @author bsempere
 */
public class NameNotFoundException extends NamingException {

    private static final long serialVersionUID = -968460130531579220L;

    public NameNotFoundException(String jndiName, Hashtable<?, ?> environnement, Throwable th) {
        super("Cannot resolve JNDI name [" + jndiName + "] at url [" + environnement.get(PROVIDER_URL) + "] with principal [" + environnement.get(SECURITY_PRINCIPAL) + "].", th);
    }
}