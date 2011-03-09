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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJBHome;
import javax.ejb.EJBLocalHome;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import java.util.Hashtable;

/**
 * Base implementation of NamingManager.
 *
 * @author bsempere
 */
public class NamingManagerImpl implements NamingManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(NamingManagerImpl.class);

    // JEE context
    private Context context;

    // *************************************************************************
    //
    // Constructors
    //
    // *************************************************************************

    public NamingManagerImpl() {
        try {
            this.context = new InitialContext();
            LOGGER.debug("No context was given. Default initialContext will be used.");
        } catch (NamingException e) {
            throw new org.sempere.commons.naming.NamingException(e);
        }
    }

    public NamingManagerImpl(Context context) {
        this.context = context;
    }

    // *************************************************************************
    //
    // Methods from NamingManager interface
    //
    // *************************************************************************

    public Object lookup(String jndiName) {
        LOGGER.debug("lookup with JNDI name [" + jndiName + "]");
        Object object = null;
        try {
            object = this.context.lookup(jndiName);
        } catch (NamingException e) {
            throw new NameNotFoundException(jndiName, this.getEnvironment(), e);
        }

        return object;
    }

    @SuppressWarnings("unchecked")
    public <T> T getLocalObject(String jndiName) {
        LOGGER.debug("getLocalObject with JNDI name [" + jndiName + "]");
        return (T) this.lookup(jndiName);
    }

    @SuppressWarnings("unchecked")
    public <T> T getRemoteObject(String jndiName, Class<T> clazz) {
        LOGGER.debug("getRemoteObject with JNDI name [" + jndiName + "] and class [" + clazz + "]");
        return (T) PortableRemoteObject.narrow(this.lookup(jndiName), clazz);
    }

    @SuppressWarnings("unchecked")
    public <T extends EJBLocalHome> T getLocalEJBHome(String jndiName) {
        LOGGER.debug("getLocalEJBHome with JNDI name [" + jndiName + "]");
        return (T) this.getLocalObject(jndiName);
    }

    public <T extends EJBHome> T getRemoteEJBHome(String jndiName, Class<T> clazz) {
        LOGGER.debug("getRemoteEJBHome with JNDI name [" + jndiName + "] and class [" + clazz + "]");
        return this.getRemoteObject(jndiName, clazz);
    }

    public Context getContext() {
        return this.context;
    }

    // *************************************************************************
    //
    // Convenience methods
    //
    // *************************************************************************

    protected Hashtable<?, ?> getEnvironment() {
        Hashtable<?, ?> environment = null;
        try {
            environment = this.context.getEnvironment();
        } catch (NamingException e) {
            throw new org.sempere.commons.naming.NamingException(e);
        }

        if (environment == null) {
            environment = new Hashtable<String, String>();
        }

        return environment;
    }
}
