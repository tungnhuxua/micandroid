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
package org.sempere.commons.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Singleton context loader.
 * 
 * @author sempere
 */
public final class SingletonContextLoader implements ContextLoader {

	private static final Log LOGGER = LogFactory.getLog(SingletonContextLoader.class);
	private static SingletonContextLoader instance = new SingletonContextLoader();

	protected ClassPathXmlApplicationContext context;
	protected boolean contextLoaded;

	// *************************************************************************
	//
	// Constructors
	//
	// *************************************************************************

	private SingletonContextLoader() {
	}

	// ********************************************************************************
	//
	// Methods from ContextLoader interface
	//
	// ********************************************************************************

	public synchronized void loadContext(String[] configLocations) {
		if (!contextLoaded) {
			LOGGER.debug("About to load context.");
			this.context = new ClassPathXmlApplicationContext(configLocations);
			this.contextLoaded = true;
			LOGGER.debug("Context [" + context.getId() + "] loaded successfully.");
		} else {
			LOGGER.debug("Context was already loaded. Please release the current context at first.");
		}
	}

	public synchronized void releaseContext() {
		if (contextLoaded) {
			LOGGER.debug("About to release context [" + context.getId() + "].");
			this.context.close();
			this.context = null;
			this.contextLoaded = false;
			LOGGER.debug("Context released successfully.");
		} else {
			LOGGER.debug("No context to release. Please load the current context at first.");
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T getBean(String beanName) {
		return (T) this.context.getBean(beanName);
	}

	// ********************************************************************************
	//
	// Convenience methods
	//
	// ********************************************************************************

	public static SingletonContextLoader getInstance() {
		return instance;
	}
}
