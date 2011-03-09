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
package org.sempere.commons.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper class for dealing with databases, ...
 * 
 * @author bsempere
 */
public final class SQLHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(SQLHelper.class);

    // *************************************************************************
    //
    // Constructors
    //
    // *************************************************************************
    
    private SQLHelper() {
    }

    // *************************************************************************
    //
    // Convenience methods
    //
    // *************************************************************************

    public static void closeQuietly(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // logs the exception
                LOGGER.warn("Cannot close the connection.", e);
            }
        }
    }

    public static void closeQuietly(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                // logs the exception
                LOGGER.warn("Cannot close the statement.", e);
            }
        }
    }

    public static void closeQuietly(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                // logs the exception
                LOGGER.warn("Cannot close the resultSet.", e);
            }
        }
    }
}
