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
 * The portlet-collectionType is used to identify a subset
 *  of portlets within a portlet application to which a 
 *  security constraint applies.
 *  Used in: security-constraint
 *  
 * 
 * @version $Revision$ $Date$
 */
public class PortletCollectionType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _portletNameList
     */
    private java.util.Vector _portletNameList;


      //----------------/
     //- Constructors -/
    //----------------/

    public PortletCollectionType() 
     {
        super();
        _portletNameList = new Vector();
    } //-- org.light.portlet.definition.PortletCollectionType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addPortletName
     * 
     * 
     * 
     * @param vPortletName
     */
    public void addPortletName(org.light.portal.portlet.definition.PortletName vPortletName)
        throws java.lang.IndexOutOfBoundsException
    {
        _portletNameList.addElement(vPortletName);
    } //-- void addPortletName(org.light.portlet.definition.PortletName) 

    /**
     * Method addPortletName
     * 
     * 
     * 
     * @param index
     * @param vPortletName
     */
    public void addPortletName(int index, org.light.portal.portlet.definition.PortletName vPortletName)
        throws java.lang.IndexOutOfBoundsException
    {
        _portletNameList.insertElementAt(vPortletName, index);
    } //-- void addPortletName(int, org.light.portlet.definition.PortletName) 

    /**
     * Method enumeratePortletName
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumeratePortletName()
    {
        return _portletNameList.elements();
    } //-- java.util.Enumeration enumeratePortletName() 

    /**
     * Method getPortletName
     * 
     * 
     * 
     * @param index
     * @return PortletName
     */
    public org.light.portal.portlet.definition.PortletName getPortletName(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _portletNameList.size())) {
            throw new IndexOutOfBoundsException("getPortletName: Index value '"+index+"' not in range [0.."+(_portletNameList.size() - 1) + "]");
        }
        
        return (org.light.portal.portlet.definition.PortletName) _portletNameList.elementAt(index);
    } //-- org.light.portlet.definition.PortletName getPortletName(int) 

    /**
     * Method getPortletName
     * 
     * 
     * 
     * @return PortletName
     */
    public org.light.portal.portlet.definition.PortletName[] getPortletName()
    {
        int size = _portletNameList.size();
        org.light.portal.portlet.definition.PortletName[] mArray = new org.light.portal.portlet.definition.PortletName[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (org.light.portal.portlet.definition.PortletName) _portletNameList.elementAt(index);
        }
        return mArray;
    } //-- org.light.portlet.definition.PortletName[] getPortletName() 

    /**
     * Method getPortletNameCount
     * 
     * 
     * 
     * @return int
     */
    public int getPortletNameCount()
    {
        return _portletNameList.size();
    } //-- int getPortletNameCount() 

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
     * Method removeAllPortletName
     * 
     */
    public void removeAllPortletName()
    {
        _portletNameList.removeAllElements();
    } //-- void removeAllPortletName() 

    /**
     * Method removePortletName
     * 
     * 
     * 
     * @param index
     * @return PortletName
     */
    public org.light.portal.portlet.definition.PortletName removePortletName(int index)
    {
        java.lang.Object obj = _portletNameList.elementAt(index);
        _portletNameList.removeElementAt(index);
        return (org.light.portal.portlet.definition.PortletName) obj;
    } //-- org.light.portlet.definition.PortletName removePortletName(int) 

    /**
     * Method setPortletName
     * 
     * 
     * 
     * @param index
     * @param vPortletName
     */
    public void setPortletName(int index, org.light.portal.portlet.definition.PortletName vPortletName)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _portletNameList.size())) {
            throw new IndexOutOfBoundsException("setPortletName: Index value '"+index+"' not in range [0.." + (_portletNameList.size() - 1) + "]");
        }
        _portletNameList.setElementAt(vPortletName, index);
    } //-- void setPortletName(int, org.light.portlet.definition.PortletName) 

    /**
     * Method setPortletName
     * 
     * 
     * 
     * @param portletNameArray
     */
    public void setPortletName(org.light.portal.portlet.definition.PortletName[] portletNameArray)
    {
        //-- copy array
        _portletNameList.removeAllElements();
        for (int i = 0; i < portletNameArray.length; i++) {
            _portletNameList.addElement(portletNameArray[i]);
        }
    } //-- void setPortletName(org.light.portlet.definition.PortletName) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return PortletCollectionType
     */
    public static org.light.portal.portlet.definition.PortletCollectionType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (org.light.portal.portlet.definition.PortletCollectionType) Unmarshaller.unmarshal(org.light.portal.portlet.definition.PortletCollectionType.class, reader);
    } //-- org.light.portlet.definition.PortletCollectionType unmarshal(java.io.Reader) 

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
