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
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.Test;
import org.sempere.commons.SystemException;
import static org.mockito.Mockito.*;

/**
 * Unit tests class for OracleHelper.
 *
 * @author bsempere
 */
public class OracleHelperTest {

    @Test(expected = IllegalArgumentException.class)
    public void setClientIdentifierWithDatasourceWhenDatasourceIsNull() throws Exception {
        OracleHelper.setClientIdentifier((DataSource) null, "ClientId");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setClientIdentifierWithDatasourceWhenClientIdentifierIsNull() throws Exception {
        OracleHelper.setClientIdentifier(mock(DataSource.class), null);
    }

    @Test(expected = SystemException.class)
    public void setClientIdentifierWithDatasourceWhenBothParametersAreNotNullAndSQLExceptionOccurs() throws Exception {
        DataSource datasource = mock(DataSource.class);
        when(datasource.getConnection()).thenThrow(new SQLException());

        OracleHelper.setClientIdentifier(datasource, "ClientId");
    }

    @Test
    public void setClientIdentifierWithDatasourceWhenBothParametersAreNotNullAndNoExceptionOccurs() throws Exception {
        DataSource datasource = mock(DataSource.class);
        Connection connection = mock(Connection.class);
        CallableStatement statement = mock(CallableStatement.class);

        when(datasource.getConnection()).thenReturn(connection);
        when(connection.prepareCall(anyString())).thenReturn(statement);

        OracleHelper.setClientIdentifier(datasource, "ClientId");
        verify(statement).setString(1, "ClientId");
        verify(statement).executeUpdate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void setClientIdentifierWithConnectionWhenConnectionIsNull() throws Exception {
        OracleHelper.setClientIdentifier((Connection) null, "ClientId");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setClientIdentifierWithConnectionWhenClientIdentifierIsNull() throws Exception {
        OracleHelper.setClientIdentifier(mock(Connection.class), null);
    }

    @Test(expected = SystemException.class)
    public void setClientIdentifierWithConnectionWhenBothParametersAreNotNullAndSQLExceptionOccurs() throws Exception {
        Connection connection = mock(Connection.class);
        CallableStatement statement = mock(CallableStatement.class);

        when(connection.prepareCall(anyString())).thenReturn(statement);
        when(statement.executeUpdate()).thenThrow(new SQLException());

        OracleHelper.setClientIdentifier(connection, "ClientId");
    }

    @Test
    public void setClientIdentifierWithConnectionWhenBothParametersAreNotNullAndNoExceptionOccurs() throws Exception {
        Connection connection = mock(Connection.class);
        CallableStatement statement = mock(CallableStatement.class);
        when(connection.prepareCall(anyString())).thenReturn(statement);

        OracleHelper.setClientIdentifier(connection, "ClientId");
        verify(statement).setString(1, "ClientId");
        verify(statement).executeUpdate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkClientIdentifierValueWithDatasourceWhenDatasourceIsNull() throws Exception {
        OracleHelper.checkClientIdentifierValue((DataSource) null, "ClientId");
    }

    @Test(expected = SystemException.class)
    public void checkClientIdentifierValueWithDatasourceWhenBothParametersAreNotNullAndSQLExceptionOccurs() throws Exception {
        DataSource datasource = mock(DataSource.class);
        when(datasource.getConnection()).thenThrow(new SQLException());

        OracleHelper.checkClientIdentifierValue(datasource, "ClientId");
    }

    @Test
    public void checkClientIdentifierValueWithDatasourceWhenClientIdentifierIsVerified() throws Exception {
        DataSource datasource = mock(DataSource.class);
        Connection connection = mock(Connection.class);
        CallableStatement statement = mock(CallableStatement.class);
        ResultSet resultSet = mock(ResultSet.class);

        when(datasource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString(1)).thenReturn("ClientId");

        OracleHelper.checkClientIdentifierValue(datasource, "ClientId");
    }

    @Test(expected = SystemException.class)
    public void checkClientIdentifierValueWithDatasourceWhenClientIdentifierIsNotVerified() throws Exception {
        DataSource datasource = mock(DataSource.class);
        Connection connection = mock(Connection.class);
        CallableStatement statement = mock(CallableStatement.class);
        ResultSet resultSet = mock(ResultSet.class);

        when(datasource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString(1)).thenReturn("ClientId2");

        OracleHelper.checkClientIdentifierValue(datasource, "ClientId");
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkClientIdentifierValueWithConnectionWhenConnectionIsNull() throws Exception {
        OracleHelper.checkClientIdentifierValue((Connection) null, "ClientId");
    }

    @Test(expected = SystemException.class)
    public void checkClientIdentifierValueWithConnectionWhenBothParametersAreNotNullAndSQLExceptionOccurs() throws Exception {
        Connection connection = mock(Connection.class);
        CallableStatement statement = mock(CallableStatement.class);

        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenThrow(new SQLException());

        OracleHelper.checkClientIdentifierValue(connection, "ClientId");
    }

    @Test
    public void checkClientIdentifierValueWithConnectionWhenClientIdentifierIsVerified() throws Exception {
        Connection connection = mock(Connection.class);
        CallableStatement statement = mock(CallableStatement.class);
        ResultSet resultSet = mock(ResultSet.class);

        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString(1)).thenReturn("ClientId");

        OracleHelper.checkClientIdentifierValue(connection, "ClientId");
    }

    @Test(expected = SystemException.class)
    public void checkClientIdentifierValueWithConnectionWhenClientIdentifierIsNotVerified() throws Exception {
        Connection connection = mock(Connection.class);
        CallableStatement statement = mock(CallableStatement.class);
        ResultSet resultSet = mock(ResultSet.class);

        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString(1)).thenReturn("ClientId2");

        OracleHelper.checkClientIdentifierValue(connection, "ClientId");
    }
}
