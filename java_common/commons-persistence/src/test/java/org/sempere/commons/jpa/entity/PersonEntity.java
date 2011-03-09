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
package org.sempere.commons.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Entity that represents a person.
 * 
 * @author bsempere
 */
@Entity
@Table(name = "tblPerson")
@NamedQueries({ 
	@NamedQuery(name = "PersonEntity.findBenjaminSempere", query = "select o from PersonEntity o where o.firstName='benjamin' and o.lastName='sempere'"),
	@NamedQuery(name = "PersonEntity.findPersonByFirstNameAndLastName", query = "select o from PersonEntity o where o.firstName=:firstName and o.lastName=:lastName"),
	@NamedQuery(name = "PersonEntity.findPersonsByFirstName", query = "select o from PersonEntity o where o.firstName=:firstName"),
	@NamedQuery(name = "PersonEntity.findAllPersons", query = "select o from PersonEntity o")
})
public class PersonEntity implements Serializable {

	private static final long serialVersionUID = 1102513994641169623L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PERSON_ID")
	private Long id;

	@Column(name = "PERSON_FIRSTNAME")
	private String firstName;

	@Column(name = "PERSON_LASTNAME")
	private String lastName;

	@Column(name = "PERSON_GENDER")
	private String gender;

	/**
	 * Default constructor
	 */
	public PersonEntity() {
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (object == null || !(object instanceof PersonEntity)) {
			return false;
		}

		PersonEntity entity = (PersonEntity) object;
		EqualsBuilder equalsBuilder = new EqualsBuilder();
		equalsBuilder.append(this.getId(), entity.getId());
		equalsBuilder.append(this.getFirstName(), entity.getFirstName());
		equalsBuilder.append(this.getLastName(), entity.getLastName());
		equalsBuilder.append(this.getGender(), entity.getGender());

		return equalsBuilder.isEquals();
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
		hashCodeBuilder.appendSuper(super.hashCode());
		hashCodeBuilder.append(this.getId());
		hashCodeBuilder.append(this.getFirstName());
		hashCodeBuilder.append(this.getLastName());
		hashCodeBuilder.append(this.getGender());

		return hashCodeBuilder.hashCode();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
