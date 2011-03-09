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

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Hashtable;

import javax.ejb.EJBLocalHome;
import javax.naming.Context;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests class for NamingManagerImpl class.
 * 
 * @author bsempere
 */
public class NamingManagerImplTest {

	private NamingManagerImpl manager;
	private Context context;

	@Before
	public void before() throws Exception {
		this.context = mock(Context.class);
		this.manager = new NamingManagerImpl(this.context);
	}

	@Test(expected = NameNotFoundException.class)
	public void lookupWhenJndiNameDoesNotExistShouldThrowException() throws Exception {
		String jndiName = "org.sempere.jee.ejb.session.MyLocalObject";

		when(this.context.lookup(jndiName)).thenThrow(new NamingException());
		this.manager.lookup(jndiName);
	}

	@Test
	public void lookupWhenJndiNameExistsShouldReturnObjectReference() throws Exception {
		String jndiName = "org.sempere.jee.ejb.session.MyLocalObject";
		Object expectedObject = "MyObject";

		when(this.context.lookup(jndiName)).thenReturn(expectedObject);
		Object actualObject = this.manager.lookup(jndiName);

		assertSame(expectedObject, actualObject);
	}

	@Test(expected = NameNotFoundException.class)
	public void getLocalObjectWhenJndiNameDoesNotExistShouldThrowException() throws Exception {
		String jndiName = "org.sempere.jee.ejb.session.MyLocalObject";

		when(this.context.lookup(jndiName)).thenThrow(new NamingException());
		this.manager.getLocalObject(jndiName);
	}

	@Test
	public void getLocalObjectWhenJndiNameExistsShouldReturnObjectReference() throws Exception {
		String jndiName = "org.sempere.jee.ejb.session.MyLocalObject";
		Object expectedObject = "MyObject";

		when(this.context.lookup(jndiName)).thenReturn(expectedObject);
		Object actualObject = this.manager.getLocalObject(jndiName);

		assertSame(expectedObject, actualObject);
	}

	@Test(expected = NameNotFoundException.class)
	public void getLocalEJBHomeWhenJndiNameDoesNotExistShouldThrowException() throws Exception {
		String jndiName = "org.sempere.jee.ejb.session.MyLocalEJBHome";

		when(this.context.lookup(jndiName)).thenThrow(new NamingException());
		this.manager.getLocalEJBHome(jndiName);
	}

	@Test
	public void getLocalEJBHomeWhenJndiNameExistsShouldReturnObjectReference() throws Exception {
		String jndiName = "org.sempere.jee.ejb.session.MyLocalEJBHome";
		EJBLocalHome expectedEJBLocalHome = mock(EJBLocalHome.class);

		when(this.context.lookup(jndiName)).thenReturn(expectedEJBLocalHome);
		EJBLocalHome actualEJBLocalHome = this.manager.getLocalEJBHome(jndiName);

		assertSame(expectedEJBLocalHome, actualEJBLocalHome);
	}

	@Test
	public void getContext() throws Exception {
		assertSame(this.context, this.manager.getContext());
	}

	@Test
	public void getEnvironementWhenItIsNullShouldReturnAnEmptyHashtable() throws Exception {
		assertTrue("Environment should be empty.", this.manager.getEnvironment().isEmpty());
	}

	@Test
	public void getEnvironementWhenItIsNotNullShouldReturnIt() throws Exception {
		Hashtable<String, String> expectedEnvironment = new Hashtable<String, String>();
		expectedEnvironment.put(Context.PROVIDER_URL, "t3://localhost:9001");
	}
}
