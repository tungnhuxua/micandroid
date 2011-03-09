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
 * Persistent preference values that may be used for customization 
 *  and personalization by the portlet.
 *  Used in: portlet-preferences
 *  
 * 
 * @version $Revision$ $Date$
 */
public class PreferenceType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _id
     */
    private java.lang.String _id;

    /**
     * Field _name
     */
    private org.light.portal.portlet.definition.Name _name;

    /**
     * Field _valueList
     */
    private java.util.Vector _valueList;

    /**
     * Field _readOnly
     */
    private org.light.portal.portlet.definition.types.ReadOnlyType _readOnly;


      //----------------/
     //- Constructors -/
    //----------------/

    public PreferenceType() 
     {
        super();
        _valueList = new Vector();
    } //-- org.light.portlet.definition.PreferenceType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addValue
     * 
     * 
     * 
     * @param vValue
     */
    public void addValue(org.light.portal.portlet.definition.Value vValue)
        throws java.lang.IndexOutOfBoundsException
    {
        _valueList.addElement(vValue);
    } //-- void addValue(org.light.portlet.definition.Value) 

    /**
     * Method addValue
     * 
     * 
     * 
     * @param index
     * @param vValue
     */
    public void addValue(int index, org.light.portal.portlet.definition.Value vValue)
        throws java.lang.IndexOutOfBoundsException
    {
        _valueList.insertElementAt(vValue, index);
    } //-- void addValue(int, org.light.portlet.definition.Value) 

    /**
     * Method enumerateValue
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateValue()
    {
        return _valueList.elements();
    } //-- java.util.Enumeration enumerateValue() 

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
     * Returns the value of field 'name'.
     * 
     * @return Name
     * @return the value of field 'name'.
     */
    public org.light.portal.portlet.definition.Name getName()
    {
        return this._name;
    } //-- org.light.portlet.definition.Name getName() 

    /**
     * Returns the value of field 'readOnly'.
     * 
     * @return ReadOnlyType
     * @return the value of field 'readOnly'.
     */
    public org.light.portal.portlet.definition.types.ReadOnlyType getReadOnly()
    {
        return this._readOnly;
    } //-- org.light.portlet.definition.types.ReadOnlyType getReadOnly() 

    /**
     * Method getValue
     * 
     * 
     * 
     * @param index
     * @return Value
     */
    public org.light.portal.portlet.definition.Value getValue(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _valueList.size())) {
            throw new IndexOutOfBoundsException("getValue: Index value '"+index+"' not in range [0.."+(_valueList.size() - 1) + "]");
        }
        
        return (org.light.portal.portlet.definition.Value) _valueList.elementAt(index);
    } //-- org.light.portlet.definition.Value getValue(int) 

    /**
     * Method getValue
     * 
     * 
     * 
     * @return Value
     */
    public org.light.portal.portlet.definition.Value[] getValue()
    {
        int size = _valueList.size();
        org.light.portal.portlet.definition.Value[] mArray = new org.light.portal.portlet.definition.Value[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (org.light.portal.portlet.definition.Value) _valueList.elementAt(index);
        }
        return mArray;
    } //-- org.light.portlet.definition.Value[] getValue() 

    /**
     * Method getValueCount
     * 
     * 
     * 
     * @return int
     */
    public int getValueCount()
    {
        return _valueList.size();
    } //-- int getValueCount() 

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
     * Method removeAllValue
     * 
     */
    public void removeAllValue()
    {
        _valueList.removeAllElements();
    } //-- void removeAllValue() 

    /**
     * Method removeValue
     * 
     * 
     * 
     * @param index
     * @return Value
     */
    public org.light.portal.portlet.definition.Value removeValue(int index)
    {
        java.lang.Object obj = _valueList.elementAt(index);
        _valueList.removeElementAt(index);
        return (org.light.portal.portlet.definition.Value) obj;
    } //-- org.light.portlet.definition.Value removeValue(int) 

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
     * Sets the value of field 'name'.
     * 
     * @param name the value of field 'name'.
     */
    public void setName(org.light.portal.portlet.definition.Name name)
    {
        this._name = name;
    } //-- void setName(org.light.portlet.definition.Name) 

    /**
     * Sets the value of field 'readOnly'.
     * 
     * @param readOnly the value of field 'readOnly'.
     */
    public void setReadOnly(org.light.portal.portlet.definition.types.ReadOnlyType readOnly)
    {
        this._readOnly = readOnly;
    } //-- void setReadOnly(org.light.portlet.definition.types.ReadOnlyType) 

    /**
     * Method setValue
     * 
     * 
     * 
     * @param index
     * @param vValue
     */
    public void setValue(int index, org.light.portal.portlet.definition.Value vValue)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _valueList.size())) {
            throw new IndexOutOfBoundsException("setValue: Index value '"+index+"' not in range [0.." + (_valueList.size() - 1) + "]");
        }
        _valueList.setElementAt(vValue, index);
    } //-- void setValue(int, org.light.portlet.definition.Value) 

    /**
     * Method setValue
     * 
     * 
     * 
     * @param valueArray
     */
    public void setValue(org.light.portal.portlet.definition.Value[] valueArray)
    {
        //-- copy array
        _valueList.removeAllElements();
        for (int i = 0; i < valueArray.length; i++) {
            _valueList.addElement(valueArray[i]);
        }
    } //-- void setValue(org.light.portlet.definition.Value) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return PreferenceType
     */
    public static org.light.portal.portlet.definition.PreferenceType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (org.light.portal.portlet.definition.PreferenceType) Unmarshaller.unmarshal(org.light.portal.portlet.definition.PreferenceType.class, reader);
    } //-- org.light.portlet.definition.PreferenceType unmarshal(java.io.Reader) 

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
