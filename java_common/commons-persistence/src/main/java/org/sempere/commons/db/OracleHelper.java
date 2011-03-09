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

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.sempere.commons.SystemException;

/**
 * Helper class for dealing with Oracle Databases.
 *
 * @author bsempere
 */
public final class OracleHelper {

    private static final String SELECT_FROM_USER_CONTEXT_QUERY = "select sys_context('userenv',?) from dual";
    private static final String CLIENT_IDENTIFIER_VAR = "client_identifier";
    private static final String CLIENT_IDENTIFIER_QUERY = "{call DBMS_SESSION.SET_IDENTIFIER(?)}";

    // *************************************************************************
    //
    // Constructors
    //
    // *************************************************************************
    
    private OracleHelper() {
    }

    // *************************************************************************
    //
    // Convenience methods
    //
    // *************************************************************************

    /**
     * Set client identifier in Oracle context
     *
     * @param dataSource the datasource that will provide our connection
     * @param clientIdentifier the client identifier (should not be null)
     * @throws SQLException
     */
    public static void setClientIdentifier(DataSource dataSource, String clientIdentifier) {
        if (dataSource == null) {
            throw new IllegalArgumentException("DataSource cannot be null.");
        }

        try {
            OracleHelper.setClientIdentifier(dataSource.getConnection(), clientIdentifier);
        } catch (SQLException e) {
            throw new SystemException("Could not get connection to datasource.", SystemException.LayerType.PERSISTENCE);
        }
    }

    /**
     * Set client identifier in Oracle context
     *
     * @param connection the database connection
     * @param clientIdentifier the client identifier (should not be null)
     * @throws SQLException
     */
    public static void setClientIdentifier(Connection connection, String clientIdentifier) {
        if (connection == null) {
            throw new IllegalArgumentException("Connection cannot be null.");
        }
        if (clientIdentifier == null) {
            throw new IllegalArgumentException("Client identifier cannot be null.");
        }

        CallableStatement statement = null;
        try {
            statement = connection.prepareCall(CLIENT_IDENTIFIER_QUERY);
            statement.setString(1, clientIdentifier);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SystemException("Cannot execute statement [" + CLIENT_IDENTIFIER_QUERY + "] with parameter [" + clientIdentifier + "].", SystemException.LayerType.PERSISTENCE);
        } finally {
            SQLHelper.closeQuietly(statement);
            SQLHelper.closeQuietly(connection);
        }
    }

    /**
     * Checks the client identifier stored in Oracle's user context
     *
     * @param datasource the datasource that will provide our connection
     * @param expectedValue the expected that must be found
     */
    public static void checkClientIdentifierValue(DataSource dataSource, String expectedValue) {
        if (dataSource == null) {
            throw new IllegalArgumentException("DataSource cannot be null.");
        }

        try {
            OracleHelper.checkClientIdentifierValue(dataSource.getConnection(), expectedValue);
        } catch (SQLException e) {
            throw new SystemException("Could not get connection to datasource.", SystemException.LayerType.PERSISTENCE);
        }
    }

    /**
     * Checks the client identifier stored in Oracle's user context
     *
     * @param connection the database connection
     * @param expectedValue the expected that must be found
     */
    public static void checkClientIdentifierValue(Connection connection, String expectedValue) {
        if (connection == null) {
            throw new IllegalArgumentException("Connection cannot be null.");
        }

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_FROM_USER_CONTEXT_QUERY);
            statement.setString(1, CLIENT_IDENTIFIER_VAR);
            resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                throw new SystemException(SELECT_FROM_USER_CONTEXT_QUERY + " for field " + CLIENT_IDENTIFIER_VAR + " did not return any row", SystemException.LayerType.PERSISTENCE);
            }

            String value = resultSet.getString(1);
            if (!expectedValue.equals(value)) {
                throw new SystemException("dbms session identifier " + CLIENT_IDENTIFIER_VAR + " incorrectly set: " + value + ", expected: " + expectedValue, SystemException.LayerType.PERSISTENCE);
            }
        } catch (SQLException e) {
            throw new SystemException("Could not check clientIdentifier against this value [" + expectedValue + "].", SystemException.LayerType.PERSISTENCE);
        } finally {
            SQLHelper.closeQuietly(resultSet);
            SQLHelper.closeQuietly(statement);
            SQLHelper.closeQuietly(connection);
        }
    }
}
