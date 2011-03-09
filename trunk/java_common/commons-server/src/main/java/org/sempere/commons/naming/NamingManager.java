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

import javax.ejb.EJBHome;
import javax.ejb.EJBLocalHome;
import javax.naming.Context;

/**
 * Interface that defines methods to work with JNDI.
 * 
 * @author bsempere
 */
public interface NamingManager {

	/**
	 * Looks up an object registered with the given JNDI name
	 * 
	 * @param jndiName the JNDI name of the object to be retrieved
	 * @return Object
	 */
	public Object lookup(String jndiName);

	/**
	 * Returns a local reference to an object registered with the given JNDI name
	 * 
	 * @param jndiName the JNDI name of the object to be retrieved
	 * @return T (Object instance)
	 */
	public <T> T getLocalObject(String jndiName);

	/**
	 * Returns a remote reference to an object registered with the given JNDI name
	 * 
	 * @param jndiName the JNDI name of the object to be retrieved
	 * @param clazz the object class name
	 * @return T (Object instance)
	 */
	public <T> T getRemoteObject(String jndiName, Class<T> clazz);

	/**
	 * Returns a local reference to an EJBLocalHome registered with the given JNDI name
	 * 
	 * @param jndiName the JNDI name of the home to be retrieved
	 * @return T (EJBLocalHome instance)
	 */
	public <T extends EJBLocalHome> T getLocalEJBHome(String jndiName);

	/**
	 * Returns a remote reference to an EJBHome registered with the given JNDI name
	 * 
	 * @param jndiName the JNDI name of the home to be retrieved
	 * @param clazz the home class name
	 * @return T (EJBHome instance)
	 */
	public <T extends EJBHome> T getRemoteEJBHome(String jndiName, Class<T> clazz);

	/**
	 * Returns the current JEE context used with this NamingManager
	 * 
	 * @return Context
	 */
	public Context getContext();
}
