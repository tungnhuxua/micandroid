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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.sempere.commons.AbstractPersistenceTest;

/**
 * Base class for JPA persistence tests.
 * 
 * @author sempere
 */
public abstract class JPAPersistenceTest extends AbstractPersistenceTest {

	protected Connection databaseConnection;

	protected EntityManagerFactory entityManagerFactory;
	protected EntityManager entityManager;
	private boolean persistenceLayerInitialized;

	@Override
	public void initializePersistenceLayer() throws Exception {
		// Initializes persistence components
		this.entityManagerFactory = this.createEntityManagerFactory();
		this.entityManager = this.entityManagerFactory.createEntityManager();
		this.persistenceLayerInitialized = true;

		// Starts a new transaction
		this.entityManager.getTransaction().begin();
	}

	@Override
	public void destroyPersistenceLayer() throws Exception {
		if (this.persistenceLayerInitialized) {
			// Commits the current transaction if it is active
			if (this.entityManager.getTransaction().isActive()) {
				this.entityManager.getTransaction().commit();
			}

			// Destroys persistence components
			this.entityManager.close();
			this.entityManagerFactory.close();
			this.persistenceLayerInitialized = false;
		}
	}

	public abstract EntityManagerFactory createEntityManagerFactory() throws Exception;
}
