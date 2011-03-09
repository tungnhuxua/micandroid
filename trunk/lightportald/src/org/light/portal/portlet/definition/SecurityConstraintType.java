 /*
 * Light Portal
 *
 * Copyright (c) 2009, Light Portal, Inc or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Light Portal, Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 *
 */

/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0</a>, using an XML
 * Schema.
 * $Id$
 */

package org.light.portal.portlet.definition;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.util.Vector;

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * The security-constraintType is used to associate
 *  intended security constraints with one or more portlets.
 *  Used in: portlet-app
 *  
 * 
 * @version $Revision$ $Date$
 */
public class SecurityConstraintType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _id
     */
    private java.lang.String _id;

    /**
     * Field _displayNameList
     */
    private java.util.Vector _displayNameList;

    /**
     * Field _portletCollection
     */
    private org.light.portal.portlet.definition.PortletCollection _portletCollection;

    /**
     * Field _userDataConstraint
     */
    private org.light.portal.portlet.definition.UserDataConstraint _userDataConstraint;


      //----------------/
     //- Constructors -/
    //----------------/

    public SecurityConstraintType() 
     {
        super();
        _displayNameList = new Vector();
    } //-- org.light.portlet.definition.SecurityConstraintType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addDisplayName
     * 
     * 
     * 
     * @param vDisplayName
     */
    public void addDisplayName(org.light.portal.portlet.definition.DisplayName vDisplayName)
        throws java.lang.IndexOutOfBoundsException
    {
        _displayNameList.addElement(vDisplayName);
    } //-- void addDisplayName(org.light.portlet.definition.DisplayName) 

    /**
     * Method addDisplayName
     * 
     * 
     * 
     * @param index
     * @param vDisplayName
     */
    public void addDisplayName(int index, org.light.portal.portlet.definition.DisplayName vDisplayName)
        throws java.lang.IndexOutOfBoundsException
    {
        _displayNameList.insertElementAt(vDisplayName, index);
    } //-- void addDisplayName(int, org.light.portlet.definition.DisplayName) 

    /**
     * Method enumerateDisplayName
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateDisplayName()
    {
        return _displayNameList.elements();
    } //-- java.util.Enumeration enumerateDisplayName() 

    /**
     * Method getDisplayName
     * 
     * 
     * 
     * @param index
     * @return DisplayName
     */
    public org.light.portal.portlet.definition.DisplayName getDisplayName(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _displayNameList.size())) {
            throw new IndexOutOfBoundsException("getDisplayName: Index value '"+index+"' not in range [0.."+(_displayNameList.size() - 1) + "]");
        }
        
        return (org.light.portal.portlet.definition.DisplayName) _displayNameList.elementAt(index);
    } //-- org.light.portlet.definition.DisplayName getDisplayName(int) 

    /**
     * Method getDisplayName
     * 
     * 
     * 
     * @return DisplayName
     */
    public org.light.portal.portlet.definition.DisplayName[] getDisplayName()
    {
        int size = _displayNameList.size();
        org.light.portal.portlet.definition.DisplayName[] mArray = new org.light.portal.portlet.definition.DisplayName[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (org.light.portal.portlet.definition.DisplayName) _displayNameList.elementAt(index);
        }
        return mArray;
    } //-- org.light.portlet.definition.DisplayName[] getDisplayName() 

    /**
     * Method getDisplayNameCount
     * 
     * 
     * 
     * @return int
     */
    public int getDisplayNameCount()
    {
        return _displayNameList.size();
    } //-- int getDisplayNameCount() 

    /**
     * Returns the value of field 'id'.
     * 
     * @return String
     * @return the value of field 'id'.
     */
    public java.lang.String getId()
    {
        return this._id;
    } //-- java.lang.String getId() 

    /**
     * Returns the value of field 'portletCollection'.
     * 
     * @return PortletCollection
     * @return the value of field 'portletCollection'.
     */
    public org.light.portal.portlet.definition.PortletCollection getPortletCollection()
    {
        return this._portletCollection;
    } //-- org.light.portlet.definition.PortletCollection getPortletCollection() 

    /**
     * Returns the value of field 'userDataConstraint'.
     * 
     * @return UserDataConstraint
     * @return the value of field 'userDataConstraint'.
     */
    public org.light.portal.portlet.definition.UserDataConstraint getUserDataConstraint()
    {
        return this._userDataConstraint;
    } //-- org.light.portlet.definition.UserDataConstraint getUserDataConstraint() 

    /**
     * Method isValid
     * 
     * 
     * 
     * @return boolean
     */
    public boolean isValid()
    {
        try {
            validate();
        }
        catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    } //-- boolean isValid() 

    /**
     * Method marshal
     * 
     * 
     * 
     * @param out
     */
    public void marshal(java.io.Writer out)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, out);
    } //-- void marshal(java.io.Writer) 

    /**
     * Method marshal
     * 
     * 
     * 
     * @param handler
     */
    public void marshal(org.xml.sax.ContentHandler handler)
        throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, handler);
    } //-- void marshal(org.xml.sax.ContentHandler) 

    /**
     * Method removeAllDisplayName
     * 
     */
    public void removeAllDisplayName()
    {
        _displayNameList.removeAllElements();
    } //-- void removeAllDisplayName() 

    /**
     * Method removeDisplayName
     * 
     * 
     * 
     * @param index
     * @return DisplayName
     */
    public org.light.portal.portlet.definition.DisplayName removeDisplayName(int index)
    {
        java.lang.Object obj = _displayNameList.elementAt(index);
        _displayNameList.removeElementAt(index);
        return (org.light.portal.portlet.definition.DisplayName) obj;
    } //-- org.light.portlet.definition.DisplayName removeDisplayName(int) 

    /**
     * Method setDisplayName
     * 
     * 
     * 
     * @param index
     * @param vDisplayName
     */
    public void setDisplayName(int index, org.light.portal.portlet.definition.DisplayName vDisplayName)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _displayNameList.size())) {
            throw new IndexOutOfBoundsException("setDisplayName: Index value '"+index+"' not in range [0.." + (_displayNameList.size() - 1) + "]");
        }
        _displayNameList.setElementAt(vDisplayName, index);
    } //-- void setDisplayName(int, org.light.portlet.definition.DisplayName) 

    /**
     * Method setDisplayName
     * 
     * 
     * 
     * @param displayNameArray
     */
    public void setDisplayName(org.light.portal.portlet.definition.DisplayName[] displayNameArray)
    {
        //-- copy array
        _displayNameList.removeAllElements();
        for (int i = 0; i < displayNameArray.length; i++) {
            _displayNameList.addElement(displayNameArray[i]);
        }
    } //-- void setDisplayName(org.light.portlet.definition.DisplayName) 

    /**
     * Sets the value of field 'id'.
     * 
     * @param id the value of field 'id'.
     */
    public void setId(java.lang.String id)
    {
        this._id = id;
    } //-- void setId(java.lang.String) 

    /**
     * Sets the value of field 'portletCollection'.
     * 
     * @param portletCollection the value of field
     * 'portletCollection'.
     */
    public void setPortletCollection(org.light.portal.portlet.definition.PortletCollection portletCollection)
    {
        this._portletCollection = portletCollection;
    } //-- void setPortletCollection(org.light.portlet.definition.PortletCollection) 

    /**
     * Sets the value of field 'userDataConstraint'.
     * 
     * @param userDataConstraint the value of field
     * 'userDataConstraint'.
     */
    public void setUserDataConstraint(org.light.portal.portlet.definition.UserDataConstraint userDataConstraint)
    {
        this._userDataConstraint = userDataConstraint;
    } //-- void setUserDataConstraint(org.light.portlet.definition.UserDataConstraint) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return SecurityConstraintType
     */
    public static org.light.portal.portlet.definition.SecurityConstraintType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (org.light.portal.portlet.definition.SecurityConstraintType) Unmarshaller.unmarshal(org.light.portal.portlet.definition.SecurityConstraintType.class, reader);
    } //-- org.light.portlet.definition.SecurityConstraintType unmarshal(java.io.Reader) 

    /**
     * Method validate
     * 
     */
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
