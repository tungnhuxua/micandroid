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
package org.sempere.commons.jpa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;

/**
 * Base class for JPA persistence tests against a HSQL database.
 * 
 * @author sempere
 */
public abstract class HSQLJPAPersistenceTest extends JPAPersistenceTest {

	protected Connection databaseConnection;

	@Before
	public void before() throws Exception {
		super.before();
		this.initializeDatabase();
	}

	@Override
	public void startDatabase() throws Exception {
		Class.forName("org.hsqldb.jdbcDriver");
		this.databaseConnection = DriverManager.getConnection("jdbc:hsqldb:mem:" + this.getDatabaseName() + "", "sa", "");
	}

	@Override
	public void stopDatabase() throws Exception {
		Statement statement = this.databaseConnection.createStatement();
		statement.execute("SHUTDOWN");
	}

	@Override
	public EntityManagerFactory createEntityManagerFactory() throws Exception {
		return Persistence.createEntityManagerFactory(this.getPersistenceUnitName());
	}

	public abstract String getDatabaseName() throws Exception;

	public abstract String getPersistenceUnitName() throws Exception;

	public abstract void initializeDatabase() throws Exception;
}
