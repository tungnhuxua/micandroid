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
 * Supports indicates the portlet modes a 
 *  portlet supports for a specific content type. All portlets must
 * 
 *  support the view mode. 
 *  Used in: portlet
 *  
 * 
 * @version $Revision$ $Date$
 */
public class SupportsType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _id
     */
    private java.lang.String _id;

    /**
     * Field _mimeType
     */
    private org.light.portal.portlet.definition.MimeType _mimeType;

    /**
     * Field _portletModeList
     */
    private java.util.Vector _portletModeList;


      //----------------/
     //- Constructors -/
    //----------------/

    public SupportsType() 
     {
        super();
        _portletModeList = new Vector();
    } //-- org.light.portlet.definition.SupportsType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addPortletMode
     * 
     * 
     * 
     * @param vPortletMode
     */
    public void addPortletMode(org.light.portal.portlet.definition.PortletMode vPortletMode)
        throws java.lang.IndexOutOfBoundsException
    {
        _portletModeList.addElement(vPortletMode);
    } //-- void addPortletMode(org.light.portlet.definition.PortletMode) 

    /**
     * Method addPortletMode
     * 
     * 
     * 
     * @param index
     * @param vPortletMode
     */
    public void addPortletMode(int index, org.light.portal.portlet.definition.PortletMode vPortletMode)
        throws java.lang.IndexOutOfBoundsException
    {
        _portletModeList.insertElementAt(vPortletMode, index);
    } //-- void addPortletMode(int, org.light.portlet.definition.PortletMode) 

    /**
     * Method enumeratePortletMode
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumeratePortletMode()
    {
        return _portletModeList.elements();
    } //-- java.util.Enumeration enumeratePortletMode() 

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
     * Returns the value of field 'mimeType'.
     * 
     * @return MimeType
     * @return the value of field 'mimeType'.
     */
    public org.light.portal.portlet.definition.MimeType getMimeType()
    {
        return this._mimeType;
    } //-- org.light.portlet.definition.MimeType getMimeType() 

    /**
     * Method getPortletMode
     * 
     * 
     * 
     * @param index
     * @return PortletMode
     */
    public org.light.portal.portlet.definition.PortletMode getPortletMode(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _portletModeList.size())) {
            throw new IndexOutOfBoundsException("getPortletMode: Index value '"+index+"' not in range [0.."+(_portletModeList.size() - 1) + "]");
        }
        
        return (org.light.portal.portlet.definition.PortletMode) _portletModeList.elementAt(index);
    } //-- org.light.portlet.definition.PortletMode getPortletMode(int) 

    /**
     * Method getPortletMode
     * 
     * 
     * 
     * @return PortletMode
     */
    public org.light.portal.portlet.definition.PortletMode[] getPortletMode()
    {
        int size = _portletModeList.size();
        org.light.portal.portlet.definition.PortletMode[] mArray = new org.light.portal.portlet.definition.PortletMode[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (org.light.portal.portlet.definition.PortletMode) _portletModeList.elementAt(index);
        }
        return mArray;
    } //-- org.light.portlet.definition.PortletMode[] getPortletMode() 

    /**
     * Method getPortletModeCount
     * 
     * 
     * 
     * @return int
     */
    public int getPortletModeCount()
    {
        return _portletModeList.size();
    } //-- int getPortletModeCount() 

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
     * Method removeAllPortletMode
     * 
     */
    public void removeAllPortletMode()
    {
        _portletModeList.removeAllElements();
    } //-- void removeAllPortletMode() 

    /**
     * Method removePortletMode
     * 
     * 
     * 
     * @param index
     * @return PortletMode
     */
    public org.light.portal.portlet.definition.PortletMode removePortletMode(int index)
    {
        java.lang.Object obj = _portletModeList.elementAt(index);
        _portletModeList.removeElementAt(index);
        return (org.light.portal.portlet.definition.PortletMode) obj;
    } //-- org.light.portlet.definition.PortletMode removePortletMode(int) 

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
     * Sets the value of field 'mimeType'.
     * 
     * @param mimeType the value of field 'mimeType'.
     */
    public void setMimeType(org.light.portal.portlet.definition.MimeType mimeType)
    {
        this._mimeType = mimeType;
    } //-- void setMimeType(org.light.portlet.definition.MimeType) 

    /**
     * Method setPortletMode
     * 
     * 
     * 
     * @param index
     * @param vPortletMode
     */
    public void setPortletMode(int index, org.light.portal.portlet.definition.PortletMode vPortletMode)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _portletModeList.size())) {
            throw new IndexOutOfBoundsException("setPortletMode: Index value '"+index+"' not in range [0.." + (_portletModeList.size() - 1) + "]");
        }
        _portletModeList.setElementAt(vPortletMode, index);
    } //-- void setPortletMode(int, org.light.portlet.definition.PortletMode) 

    /**
     * Method setPortletMode
     * 
     * 
     * 
     * @param portletModeArray
     */
    public void setPortletMode(org.light.portal.portlet.definition.PortletMode[] portletModeArray)
    {
        //-- copy array
        _portletModeList.removeAllElements();
        for (int i = 0; i < portletModeArray.length; i++) {
            _portletModeList.addElement(portletModeArray[i]);
        }
    } //-- void setPortletMode(org.light.portlet.definition.PortletMode) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return SupportsType
     */
    public static org.light.portal.portlet.definition.SupportsType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (org.light.portal.portlet.definition.SupportsType) Unmarshaller.unmarshal(org.light.portal.portlet.definition.SupportsType.class, reader);
    } //-- org.light.portlet.definition.SupportsType unmarshal(java.io.Reader) 

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
