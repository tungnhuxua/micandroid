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
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 * Unit tests class for SQLHelper.
 *
 * @author bsempere
 */
public class SQLHelperTest {

    @Test
    public void closeConnectionQuietlyWhenConnectionIsNull() throws Exception {
        SQLHelper.closeQuietly((Connection) null);
    }

    @Test
    public void closeConnectionQuietlyWhenSQLExceptionOccurs() throws Exception {
        Connection connection = mock(Connection.class);
        doThrow(new SQLException()).when(connection).close();
    }

    @Test
    public void closeConnectionQuietlyWhenConnectionIsNotNull() throws Exception {
        Connection connection = mock(Connection.class);
        connection.close();
        verify(connection).close();
    }

    @Test
    public void closeStatementQuietlyWhenStatementIsNull() throws Exception {
        SQLHelper.closeQuietly((Statement) null);
    }

    @Test
    public void closeStatementQuietlyWhenSQLExceptionOccurs() throws Exception {
        Statement statement = mock(Statement.class);
        doThrow(new SQLException()).when(statement).close();
    }

    @Test
    public void closeStatementQuietlyWhenStatementIsNotNull() throws Exception {
        Statement statement = mock(Statement.class);
        statement.close();
        verify(statement).close();
    }

    @Test
    public void closeResultSetQuietlyWhenResultIsNull() throws Exception {
        SQLHelper.closeQuietly((ResultSet) null);
    }

    @Test
    public void closeResultSetQuietlyWhenSQLExceptionOccurs() throws Exception {
        ResultSet resultSet = mock(ResultSet.class);
        doThrow(new SQLException()).when(resultSet).close();
    }

    @Test
    public void closeResultSetQuietlyWhenResultIsNotNull() throws Exception {
        ResultSet resultSet = mock(ResultSet.class);
        resultSet.close();
        verify(resultSet).close();
    }
}
