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

import org.dbunit.database.*;
import org.dbunit.dataset.*;
import org.dbunit.dataset.xml.*;
import org.dbunit.ext.hsqldb.*;
import org.dbunit.operation.*;
import org.junit.*;
import org.sempere.commons.jpa.entity.*;

import javax.persistence.*;
import java.io.*;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests class for AbstractDAO class.
 *
 * @author bsempere
 */
public class AbstractDAOTest extends HSQLJPAPersistenceTest {

    private AbstractDAO dao;

    @Before
    public void before() throws Exception {
        super.before();
        this.dao = new PersonDAO(this.entityManager);
    }

    @Test(expected = NoResultException.class)
    public void findEntityWhenEntityDoesNotExistShouldThrowException() throws Exception {
        this.dao.find(PersonEntity.class, -1L);
    }

    @Test
    public void findEntityWhenEntityExistsShouldReturnIt() throws Exception {
        PersonEntity person = this.dao.find(PersonEntity.class, 1L);
        this.verifyBenjaminSemperePerson(person);
    }

    @Test
    public void findNullableEntityWhenEntityDoesNotExistShouldReturnNull() throws Exception {
        assertNull("Entity should be null.", this.dao.findNullable(PersonEntity.class, -1L));
    }

    @Test
    public void findNullableEntityWhenEntityExistsShouldReturnIt() throws Exception {
        PersonEntity person = this.dao.findNullable(PersonEntity.class, 1L);
        this.verifyBenjaminSemperePerson(person);
    }

    @Test
    public void mergeEntity() throws Exception {
        PersonEntity person = this.dao.find(PersonEntity.class, 1L);
        this.dao.getEntityManager().clear();

        person.setLastName("sempere-piton");
        this.dao.merge(person);

        person = this.dao.find(PersonEntity.class, 1L);
        this.verifyBenjaminSemperePitonPerson(person);
    }

    @Test
    public void persistEntity() throws Exception {
        PersonEntity person = this.createBernadetteSemperePerson();
        person = this.dao.persist(person);

        person = this.dao.find(PersonEntity.class, person.getId());
        this.verifyBernadetteSemperePerson(person);
    }

    @Test(expected = NoResultException.class)
    public void removeEntityWhenEntityDoesNotExistShouldThrowException() throws Exception {
        this.dao.remove(PersonEntity.class, -1L);
    }

    @Test
    public void removeEntityWhenEntityExistsShouldRemoveIt() throws Exception {
        this.dao.remove(PersonEntity.class, 1L);
    }

    @Test
    public void removeEntitiesCollectionWhenEntitiesCollectionIsEmptyShouldDoNothing() throws Exception {
        this.dao.remove(new ArrayList<PersonEntity>());
    }

    @Test
    public void removeEntitiesCollectionWhenEntitiesCollectionIsNotEmptyShouldRemoveEntities() throws Exception {
        PersonEntity person = this.dao.find(PersonEntity.class, 1L);
        this.dao.remove(Arrays.asList(person));
        assertNull("Entity should be null.", this.dao.findNullable(PersonEntity.class, person.getId()));
    }

    @Test
    public void removeEntitiesArrayWhenEntitiesArrayIsEmptyShouldDoNothing() throws Exception {
        this.dao.remove();
    }

    @Test
    public void removeEntitiesArrayWhenEntitiesArrayIsNotEmptyShouldRemoveEntities() throws Exception {
        PersonEntity person = this.dao.find(PersonEntity.class, 1L);
        this.dao.remove(person);
        assertNull("Entity should be null.", this.dao.findNullable(PersonEntity.class, person.getId()));
    }

    @Test
    public void getResultFromNamedQueryWithoutParameters() throws Exception {
        PersonEntity person = this.dao.getResultFromNamedQuery("PersonEntity.findBenjaminSempere");
        this.verifyBenjaminSemperePerson(person);
    }

    @Test
    public void getResultFromNamedQueryWithParameters() throws Exception {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("firstName", "benjamin");
        parameters.put("lastName", "sempere");

        PersonEntity person = this.dao.getResultFromNamedQuery("PersonEntity.findPersonByFirstNameAndLastName", parameters);
        this.verifyBenjaminSemperePerson(person);
    }

    @Test
    public void getResultsFromNamedQueryWithoutParameters() throws Exception {
        Collection<PersonEntity> persons = this.dao.getResultsFromNamedQuery("PersonEntity.findAllPersons");
        assertEquals(2, persons.size());

        Iterator<PersonEntity> it = persons.iterator();
        this.verifyBenjaminSemperePerson(it.next());
        this.verifyAngelinaSemperePerson(it.next());
    }

    @Test
    public void getResultsFromNamedQueryWithParameters() throws Exception {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("firstName", "benjamin");

        Collection<PersonEntity> persons = this.dao.getResultsFromNamedQuery("PersonEntity.findPersonsByFirstName", parameters);
        assertEquals(1, persons.size());

        Iterator<PersonEntity> it = persons.iterator();
        this.verifyBenjaminSemperePerson(it.next());
    }

    @Test
    public void setParametersWhenParametersMapIsNull() throws Exception {
        Query query = mock(Query.class);
        when(query.setParameter(anyString(), anyString())).thenThrow(new UnsupportedOperationException("No supported"));
        this.dao.setParameters(query, null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void setParametersWhenParametersMapIsNotNulll() throws Exception {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("firstName", "benjamin");

        Query query = mock(Query.class);
        when(query.setParameter(anyString(), anyString())).thenThrow(new UnsupportedOperationException("No supported"));
        this.dao.setParameters(query, parameters);
    }

    // *************************************************************************
    //
    // DB configuration methods
    //
    // *************************************************************************

    @Override
    public String getDatabaseName() throws Exception {
        return "myDatabase";
    }

    @Override
    public String getPersistenceUnitName() throws Exception {
        return "myPersistenceUnit";
    }

    // *************************************************************************
    //
    // DB population methods
    //
    // *************************************************************************

    @Override
    public void initializeDatabase() throws Exception {
        InputStream inputStream = this.getClass().getResourceAsStream("/org/sempere/commons/AbstractDAOTestData.xml");
        IDataSet dataSet = new FlatXmlDataSet(inputStream);

        IDatabaseConnection databaseConnection = new DatabaseConnection(this.databaseConnection);

        DatabaseConfig config = databaseConnection.getConfig();
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new HsqldbDataTypeFactory());

        DatabaseOperation operation = DatabaseOperation.CLEAN_INSERT;
        operation.execute(databaseConnection, dataSet);
    }

    // ********************************************************************************
    //
    // Test fixtures, stubs, ...
    //
    // ********************************************************************************

    private class PersonDAO extends AbstractDAO {

        public PersonDAO(EntityManager entityManager) {
            super(entityManager);
        }
    }

    private PersonEntity createBernadetteSemperePerson() {
        PersonEntity person = new PersonEntity();
        person.setFirstName("bernadette");
        person.setLastName("sempere");
        person.setGender("female");

        return person;
    }

    // ********************************************************************************
    //
    // Test verification methods
    //
    // ********************************************************************************

    private void verifyBenjaminSemperePerson(PersonEntity person) {
        assertEquals("benjamin", person.getFirstName());
        assertEquals("sempere", person.getLastName());
        assertEquals("male", person.getGender());
    }

    private void verifyBenjaminSemperePitonPerson(PersonEntity person) {
        assertEquals("benjamin", person.getFirstName());
        assertEquals("sempere-piton", person.getLastName());
        assertEquals("male", person.getGender());
    }

    private void verifyAngelinaSemperePerson(PersonEntity person) {
        assertEquals("angelina", person.getFirstName());
        assertEquals("sempere", person.getLastName());
        assertEquals("female", person.getGender());
    }

    private void verifyBernadetteSemperePerson(PersonEntity person) {
        assertEquals("bernadette", person.getFirstName());
        assertEquals("sempere", person.getLastName());
        assertEquals("female", person.getGender());
    }
}
