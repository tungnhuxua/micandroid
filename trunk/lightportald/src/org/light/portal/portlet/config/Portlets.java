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

package org.light.portal.portlet.config;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.util.Vector;

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class Portlets.
 * 
 * @version $Revision$ $Date$
 */
public class Portlets implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _portletList
     */
    private java.util.Vector _portletList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Portlets() 
     {
        super();
        _portletList = new Vector();
    } //-- org.light.portlet.config.Portlets()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addPortlet
     * 
     * 
     * 
     * @param vPortlet
     */
    public void addPortlet(org.light.portal.portlet.config.Portlet vPortlet)
        throws java.lang.IndexOutOfBoundsException
    {
        _portletList.addElement(vPortlet);
    } //-- void addPortlet(org.light.portlet.config.Portlet) 

    /**
     * Method addPortlet
     * 
     * 
     * 
     * @param index
     * @param vPortlet
     */
    public void addPortlet(int index, org.light.portal.portlet.config.Portlet vPortlet)
        throws java.lang.IndexOutOfBoundsException
    {
        _portletList.insertElementAt(vPortlet, index);
    } //-- void addPortlet(int, org.light.portlet.config.Portlet) 

    /**
     * Method enumeratePortlet
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumeratePortlet()
    {
        return _portletList.elements();
    } //-- java.util.Enumeration enumeratePortlet() 

    /**
     * Method getPortlet
     * 
     * 
     * 
     * @param index
     * @return Portlet
     */
    public org.light.portal.portlet.config.Portlet getPortlet(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _portletList.size())) {
            throw new IndexOutOfBoundsException("getPortlet: Index value '"+index+"' not in range [0.."+(_portletList.size() - 1) + "]");
        }
        
        return (org.light.portal.portlet.config.Portlet) _portletList.elementAt(index);
    } //-- org.light.portlet.config.Portlet getPortlet(int) 

    /**
     * Method getPortlet
     * 
     * 
     * 
     * @return Portlet
     */
    public org.light.portal.portlet.config.Portlet[] getPortlet()
    {
        int size = _portletList.size();
        org.light.portal.portlet.config.Portlet[] mArray = new org.light.portal.portlet.config.Portlet[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (org.light.portal.portlet.config.Portlet) _portletList.elementAt(index);
        }
        return mArray;
    } //-- org.light.portlet.config.Portlet[] getPortlet() 

    /**
     * Method getPortletCount
     * 
     * 
     * 
     * @return int
     */
    public int getPortletCount()
    {
        return _portletList.size();
    } //-- int getPortletCount() 

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
     * Method removeAllPortlet
     * 
     */
    public void removeAllPortlet()
    {
        _portletList.removeAllElements();
    } //-- void removeAllPortlet() 

    /**
     * Method removePortlet
     * 
     * 
     * 
     * @param index
     * @return Portlet
     */
    public org.light.portal.portlet.config.Portlet removePortlet(int index)
    {
        java.lang.Object obj = _portletList.elementAt(index);
        _portletList.removeElementAt(index);
        return (org.light.portal.portlet.config.Portlet) obj;
    } //-- org.light.portlet.config.Portlet removePortlet(int) 

    /**
     * Method setPortlet
     * 
     * 
     * 
     * @param index
     * @param vPortlet
     */
    public void setPortlet(int index, org.light.portal.portlet.config.Portlet vPortlet)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _portletList.size())) {
            throw new IndexOutOfBoundsException("setPortlet: Index value '"+index+"' not in range [0.." + (_portletList.size() - 1) + "]");
        }
        _portletList.setElementAt(vPortlet, index);
    } //-- void setPortlet(int, org.light.portlet.config.Portlet) 

    /**
     * Method setPortlet
     * 
     * 
     * 
     * @param portletArray
     */
    public void setPortlet(org.light.portal.portlet.config.Portlet[] portletArray)
    {
        //-- copy array
        _portletList.removeAllElements();
        for (int i = 0; i < portletArray.length; i++) {
            _portletList.addElement(portletArray[i]);
        }
    } //-- void setPortlet(org.light.portlet.config.Portlet) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Portlets
     */
    public static org.light.portal.portlet.config.Portlets unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (org.light.portal.portlet.config.Portlets) Unmarshaller.unmarshal(org.light.portal.portlet.config.Portlets.class, reader);
    } //-- org.light.portlet.config.Portlets unmarshal(java.io.Reader) 

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
