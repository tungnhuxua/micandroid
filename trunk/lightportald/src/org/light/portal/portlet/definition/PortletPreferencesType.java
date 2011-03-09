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
 * Portlet persistent preference store.
 *  Used in: portlet
 *  
 * 
 * @version $Revision$ $Date$
 */
public class PortletPreferencesType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _id
     */
    private java.lang.String _id;

    /**
     * Field _preferenceList
     */
    private java.util.Vector _preferenceList;

    /**
     * Field _preferencesValidator
     */
    private java.lang.String _preferencesValidator;


      //----------------/
     //- Constructors -/
    //----------------/

    public PortletPreferencesType() 
     {
        super();
        _preferenceList = new Vector();
    } //-- org.light.portlet.definition.PortletPreferencesType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addPreference
     * 
     * 
     * 
     * @param vPreference
     */
    public void addPreference(org.light.portal.portlet.definition.Preference vPreference)
        throws java.lang.IndexOutOfBoundsException
    {
        _preferenceList.addElement(vPreference);
    } //-- void addPreference(org.light.portlet.definition.Preference) 

    /**
     * Method addPreference
     * 
     * 
     * 
     * @param index
     * @param vPreference
     */
    public void addPreference(int index, org.light.portal.portlet.definition.Preference vPreference)
        throws java.lang.IndexOutOfBoundsException
    {
        _preferenceList.insertElementAt(vPreference, index);
    } //-- void addPreference(int, org.light.portlet.definition.Preference) 

    /**
     * Method enumeratePreference
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumeratePreference()
    {
        return _preferenceList.elements();
    } //-- java.util.Enumeration enumeratePreference() 

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
     * Method getPreference
     * 
     * 
     * 
     * @param index
     * @return Preference
     */
    public org.light.portal.portlet.definition.Preference getPreference(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _preferenceList.size())) {
            throw new IndexOutOfBoundsException("getPreference: Index value '"+index+"' not in range [0.."+(_preferenceList.size() - 1) + "]");
        }
        
        return (org.light.portal.portlet.definition.Preference) _preferenceList.elementAt(index);
    } //-- org.light.portlet.definition.Preference getPreference(int) 

    /**
     * Method getPreference
     * 
     * 
     * 
     * @return Preference
     */
    public org.light.portal.portlet.definition.Preference[] getPreference()
    {
        int size = _preferenceList.size();
        org.light.portal.portlet.definition.Preference[] mArray = new org.light.portal.portlet.definition.Preference[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (org.light.portal.portlet.definition.Preference) _preferenceList.elementAt(index);
        }
        return mArray;
    } //-- org.light.portlet.definition.Preference[] getPreference() 

    /**
     * Method getPreferenceCount
     * 
     * 
     * 
     * @return int
     */
    public int getPreferenceCount()
    {
        return _preferenceList.size();
    } //-- int getPreferenceCount() 

    /**
     * Returns the value of field 'preferencesValidator'.
     * 
     * @return String
     * @return the value of field 'preferencesValidator'.
     */
    public java.lang.String getPreferencesValidator()
    {
        return this._preferencesValidator;
    } //-- java.lang.String getPreferencesValidator() 

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
     * Method removeAllPreference
     * 
     */
    public void removeAllPreference()
    {
        _preferenceList.removeAllElements();
    } //-- void removeAllPreference() 

    /**
     * Method removePreference
     * 
     * 
     * 
     * @param index
     * @return Preference
     */
    public org.light.portal.portlet.definition.Preference removePreference(int index)
    {
        java.lang.Object obj = _preferenceList.elementAt(index);
        _preferenceList.removeElementAt(index);
        return (org.light.portal.portlet.definition.Preference) obj;
    } //-- org.light.portlet.definition.Preference removePreference(int) 

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
     * Method setPreference
     * 
     * 
     * 
     * @param index
     * @param vPreference
     */
    public void setPreference(int index, org.light.portal.portlet.definition.Preference vPreference)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _preferenceList.size())) {
            throw new IndexOutOfBoundsException("setPreference: Index value '"+index+"' not in range [0.." + (_preferenceList.size() - 1) + "]");
        }
        _preferenceList.setElementAt(vPreference, index);
    } //-- void setPreference(int, org.light.portlet.definition.Preference) 

    /**
     * Method setPreference
     * 
     * 
     * 
     * @param preferenceArray
     */
    public void setPreference(org.light.portal.portlet.definition.Preference[] preferenceArray)
    {
        //-- copy array
        _preferenceList.removeAllElements();
        for (int i = 0; i < preferenceArray.length; i++) {
            _preferenceList.addElement(preferenceArray[i]);
        }
    } //-- void setPreference(org.light.portlet.definition.Preference) 

    /**
     * Sets the value of field 'preferencesValidator'.
     * 
     * @param preferencesValidator the value of field
     * 'preferencesValidator'.
     */
    public void setPreferencesValidator(java.lang.String preferencesValidator)
    {
        this._preferencesValidator = preferencesValidator;
    } //-- void setPreferencesValidator(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return PortletPreferencesType
     */
    public static org.light.portal.portlet.definition.PortletPreferencesType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (org.light.portal.portlet.definition.PortletPreferencesType) Unmarshaller.unmarshal(org.light.portal.portlet.definition.PortletPreferencesType.class, reader);
    } //-- org.light.portlet.definition.PortletPreferencesType unmarshal(java.io.Reader) 

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
