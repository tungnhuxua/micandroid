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
package org.sempere.commons.faces;

import java.net.URL;
import java.net.URLClassLoader;

import javax.faces.FactoryFinder;
import javax.faces.application.ApplicationFactory;
import javax.faces.component.UIViewRoot;
import javax.faces.lifecycle.LifecycleFactory;
import javax.faces.render.RenderKitFactory;

import org.apache.shale.test.mock.MockApplication;
import org.apache.shale.test.mock.MockExternalContext;
import org.apache.shale.test.mock.MockFacesContext;
import org.apache.shale.test.mock.MockFacesContextFactory;
import org.apache.shale.test.mock.MockHttpServletRequest;
import org.apache.shale.test.mock.MockHttpServletResponse;
import org.apache.shale.test.mock.MockHttpSession;
import org.apache.shale.test.mock.MockLifecycle;
import org.apache.shale.test.mock.MockLifecycleFactory;
import org.apache.shale.test.mock.MockRenderKit;
import org.apache.shale.test.mock.MockServletConfig;
import org.apache.shale.test.mock.MockServletContext;
import org.junit.After;
import org.junit.Before;

/**
 * Base class for any JSF tests.
 * 
 * @author bsempere
 */
public abstract class AbstractJSFTest {

	// Mock object instances for our tests
	protected MockApplication application;
	protected MockServletConfig config;
	protected MockExternalContext externalContext;
	protected MockFacesContext facesContext;
	protected MockFacesContextFactory facesContextFactory;
	protected MockLifecycle lifecycle;
	protected MockLifecycleFactory lifecycleFactory;
	protected MockRenderKit renderKit;
	protected MockHttpServletRequest request;
	protected MockHttpServletResponse response;
	protected MockServletContext servletContext;
	protected MockHttpSession session;

	// Thread context class loader saved and restored after each test
	private ClassLoader threadContextClassLoader;

	@Before
	public void before() throws Exception {
		// Set up a new thread context class loader
		this.threadContextClassLoader = Thread.currentThread().getContextClassLoader();
		Thread.currentThread().setContextClassLoader(new URLClassLoader(new URL[0], this.getClass().getClassLoader()));

		// Set up JSF API Objects
		FactoryFinder.releaseFactories();
		FactoryFinder.setFactory(FactoryFinder.APPLICATION_FACTORY, "org.apache.shale.test.mock.MockApplicationFactory");
		FactoryFinder.setFactory(FactoryFinder.FACES_CONTEXT_FACTORY, "org.apache.shale.test.mock.MockFacesContextFactory");
		FactoryFinder.setFactory(FactoryFinder.LIFECYCLE_FACTORY, "org.apache.shale.test.mock.MockLifecycleFactory");
		FactoryFinder.setFactory(FactoryFinder.RENDER_KIT_FACTORY, "org.apache.shale.test.mock.MockRenderKitFactory");

		// Set up Servlet API Objects
		this.servletContext = new MockServletContext();
		this.config = new MockServletConfig(servletContext);
		this.session = new MockHttpSession();
		this.session.setServletContext(servletContext);
		this.request = new MockHttpServletRequest(session);
		this.request.setServletContext(servletContext);
		this.response = new MockHttpServletResponse();

		this.externalContext = new MockExternalContext(servletContext, request, response);
		this.lifecycleFactory = (MockLifecycleFactory) FactoryFinder.getFactory(FactoryFinder.LIFECYCLE_FACTORY);
		this.lifecycle = (MockLifecycle) lifecycleFactory.getLifecycle(LifecycleFactory.DEFAULT_LIFECYCLE);
		this.facesContextFactory = (MockFacesContextFactory) FactoryFinder.getFactory(FactoryFinder.FACES_CONTEXT_FACTORY);
		this.facesContext = (MockFacesContext) facesContextFactory.getFacesContext(servletContext, request, response, lifecycle);
		this.externalContext = (MockExternalContext) facesContext.getExternalContext();

		UIViewRoot root = new UIViewRoot();
		root.setViewId("/viewId");
		root.setRenderKitId(RenderKitFactory.HTML_BASIC_RENDER_KIT);
		this.facesContext.setViewRoot(root);

		ApplicationFactory applicationFactory = (ApplicationFactory) FactoryFinder.getFactory(FactoryFinder.APPLICATION_FACTORY);
		this.application = (MockApplication) applicationFactory.getApplication();
		this.facesContext.setApplication(application);

		RenderKitFactory renderKitFactory = (RenderKitFactory) FactoryFinder.getFactory(FactoryFinder.RENDER_KIT_FACTORY);
		this.renderKit = new MockRenderKit();
		renderKitFactory.addRenderKit(RenderKitFactory.HTML_BASIC_RENDER_KIT, renderKit);
	}

	@After
	public void after() throws Exception {
		if (facesContext != null) {
			facesContext.release();
		}

		FactoryFinder.releaseFactories();
		Thread.currentThread().setContextClassLoader(threadContextClassLoader);
	}
}