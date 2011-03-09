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


import org.slf4j.*;

import javax.persistence.*;
import java.util.*;

/**
 * Base class for any Data Access Object (DAO) built on JPA API.
 *
 * @author bsempere
 */
public abstract class AbstractDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDAO.class);

    // Underlying entityManager
    private EntityManager entityManager;

    // *************************************************************************
    //
    // Constructors
    //
    // *************************************************************************

    public AbstractDAO() {
    }

    public AbstractDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // *************************************************************************
    //
    // Convenience methods ...
    // 
    // *************************************************************************

    /**
     * Returns the entity manager built around the given persistence unit name
     *
     * @return EntityManager
     */
    public EntityManager getEntityManager() {
        return this.entityManager;
    }


    //TODO : to be tested

    protected Query createQuery(String jpaQueryString) {
        LOGGER.debug("About to create JPA query [{}].", jpaQueryString);
        return this.getEntityManager().createQuery(jpaQueryString);
    }

    protected Query createNamedQuery(String queryName) {
        LOGGER.debug("About to create Named query [{}].", queryName);
        return this.getEntityManager().createNamedQuery(queryName);
    }

    protected void setParameters(Query query, Map<String, Object> parameters) {
        if (parameters != null) {
            for (Map.Entry<String, Object> parameterEntry : parameters.entrySet()) {
                String key = parameterEntry.getKey();
                Object value = parameterEntry.getValue();

                LOGGER.debug("Set parameter with key [{}] and value [{}] to query [{}].", new Object[]{key, value, query});
                query.setParameter(key, value);
            }
        }
    }

    protected <T> T find(Class<T> entityClass, Object entityPK) {
        LOGGER.debug("About to find entityClass [{}] with PK [{}].", new Object[]{entityClass.getName(), entityPK});
        T entity = this.getEntityManager().find(entityClass, entityPK);
        LOGGER.debug("EntityClass [{}] with PK [{}] found? [{}] (null means false).", new Object[]{entityClass.getName(), entityPK, entity});

        if (entity == null) {
            throw new NoResultException("Entity with class [" + entityClass + "] and  PK [" + entityPK + "] does not exist in the database.");
        }
        return entity;
    }

    protected <T> T findNullable(Class<T> entityClass, Object entityPK) {
        LOGGER.debug("About to find entityClass [{}] with PK [{}].", new Object[]{entityClass.getName(), entityPK});
        T entity = this.getEntityManager().find(entityClass, entityPK);
        LOGGER.debug("EntityClass [{}] with PK [{}] found? [{}] (null means false).", new Object[]{entityClass.getName(), entityPK, entity});

        return entity;
    }

    protected <T> T persist(T entity) {
        LOGGER.debug("About to persist entityClass [{}].", entity.getClass().getName());
        this.getEntityManager().persist(entity);
        LOGGER.debug("EntityClass [{}] persisted.");

        return entity;
    }

    protected <T> T merge(T entity) {
        LOGGER.debug("About to merge entityClass [{}].", entity.getClass().getName());
        this.getEntityManager().merge(entity);
        LOGGER.debug("EntityClass [{}] merged.");

        return entity;
    }

    protected <T> void remove(Class<T> entityClass, Object entityPK) {
        LOGGER.debug("About to remove entityClass [{}] with PK [{}].", new Object[]{entityClass.getName(), entityPK});
        T entity = this.find(entityClass, entityPK);
        this.getEntityManager().remove(entity);
        LOGGER.debug("EntityClass [{}] with PK [{}] removed.", new Object[]{entityClass.getName(), entityPK});
    }

    protected void remove(Object entity) {
        this.getEntityManager().remove(entity);
    }

    protected void remove(Collection<?> entities) {
        if (entities != null) {
            for (Object entity : entities) {
                this.remove(entity);
            }
        }
    }

    protected void remove(Object... entities) {
        this.remove(Arrays.asList(entities));
    }

    @SuppressWarnings("unchecked")
    protected <T> T getResultFromNamedQuery(String queryName) {
        return (T) this.getResultFromNamedQuery(queryName, null);
    }

    @SuppressWarnings("unchecked")
    protected <T> T getResultFromNamedQuery(String queryName, Map<String, Object> parameters) {
        LOGGER.debug("About to execute [getResultFromNamedQuery] with queryName [{}].", queryName);
        Query query = this.createNamedQuery(queryName);
        this.setParameters(query, parameters);

        return (T) query.getSingleResult();
    }

    //TODO : to be tested

    @SuppressWarnings("unchecked")
    protected <T> T getNullableResultFromNamedQuery(String queryName, Map<String, Object> parameters) {
        LOGGER.debug("About to execute [getNullableResultFromNamedQuery] with queryName [{}].", queryName);
        T entity = null;
        try {
            entity = (T) this.getResultFromNamedQuery(queryName, parameters);
        } catch (NoResultException e) {
            // Nothing to do here. It is an expected result.
        }
        LOGGER.debug("Entity found? [{}] (null means false).", entity);

        return entity;
    }

    protected <T> List<T> getResultsFromNamedQuery(String queryName) {
        return this.getResultsFromNamedQuery(queryName, null);
    }

    @SuppressWarnings("unchecked")
    protected <T> List<T> getResultsFromNamedQuery(String queryName, Map<String, Object> parameters) {
        LOGGER.debug("About to execute [getResultsFromNamedQuery] with queryName [{}].", queryName);
        Query query = this.createNamedQuery(queryName);
        this.setParameters(query, parameters);

        List<T> entities = query.getResultList();
        LOGGER.debug("[{}] entities found.", entities.size());

        return entities;
    }

    // ********************************************************************************
    //
    // Setter for dependencies injection
    //
    // ********************************************************************************

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }
}
