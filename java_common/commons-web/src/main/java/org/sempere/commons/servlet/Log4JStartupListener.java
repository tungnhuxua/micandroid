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
package org.sempere.commons.servlet;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.net.URL;

/**
 * Base listener that initializes Log4J logger.
 *
 * @author sempere
 */
public abstract class Log4JStartupListener implements ServletContextListener {

    private static final long serialVersionUID = 8495429519183232075L;
    private static final Logger LOGGER = Logger.getLogger(Log4JStartupListener.class);

    // *************************************************************************
    //
    // Constructors
    //
    // *************************************************************************

    public Log4JStartupListener() {
    }

    // *************************************************************************
    //
    // Abstract methods
    //
    // *************************************************************************

    public abstract String getApplicationName();

    public abstract String getLoggerFileName();

    // *************************************************************************
    //
    // Methods from HttpServlet class
    //
    // *************************************************************************

    public final void contextInitialized(ServletContextEvent event) {
        // Logger initialization step
        this.initializeLogger();
        LOGGER.debug("Logger for application [" + this.getApplicationName() + "] initialized using [" + this.getLoggerFileName() + "] file.");
    }

    public void contextDestroyed(ServletContextEvent event) {
    }

    // *************************************************************************
    //
    // Convenience methods
    //
    // *************************************************************************

    protected void initializeLogger() {
        String loggerFileName = this.getLoggerFileName();
        if (!StringUtils.isBlank(loggerFileName)) {
            URL loggerFileURL = Thread.currentThread().getContextClassLoader().getResource(loggerFileName);
            if (StringUtils.endsWithIgnoreCase(loggerFileName, ".properties")) {
                PropertyConfigurator.configure(loggerFileURL);
            } else if (StringUtils.endsWithIgnoreCase(loggerFileName, ".xml")) {
                DOMConfigurator.configure(loggerFileURL);
            }
        }
    }
}
