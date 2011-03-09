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

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class PortletTypeChoiceSequence.
 * 
 * @version $Revision$ $Date$
 */
public class PortletTypeChoiceSequence implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _resourceBundle
     */
    private org.light.portal.portlet.definition.ResourceBundle _resourceBundle;

    /**
     * Field _portletInfo
     */
    private org.light.portal.portlet.definition.PortletInfo _portletInfo;


      //----------------/
     //- Constructors -/
    //----------------/

    public PortletTypeChoiceSequence() 
     {
        super();
    } //-- org.light.portlet.definition.PortletTypeChoiceSequence()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'portletInfo'.
     * 
     * @return PortletInfo
     * @return the value of field 'portletInfo'.
     */
    public org.light.portal.portlet.definition.PortletInfo getPortletInfo()
    {
        return this._portletInfo;
    } //-- org.light.portlet.definition.PortletInfo getPortletInfo() 

    /**
     * Returns the value of field 'resourceBundle'.
     * 
     * @return ResourceBundle
     * @return the value of field 'resourceBundle'.
     */
    public org.light.portal.portlet.definition.ResourceBundle getResourceBundle()
    {
        return this._resourceBundle;
    } //-- org.light.portlet.definition.ResourceBundle getResourceBundle() 

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
     * Sets the value of field 'portletInfo'.
     * 
     * @param portletInfo the value of field 'portletInfo'.
     */
    public void setPortletInfo(org.light.portal.portlet.definition.PortletInfo portletInfo)
    {
        this._portletInfo = portletInfo;
    } //-- void setPortletInfo(org.light.portlet.definition.PortletInfo) 

    /**
     * Sets the value of field 'resourceBundle'.
     * 
     * @param resourceBundle the value of field 'resourceBundle'.
     */
    public void setResourceBundle(org.light.portal.portlet.definition.ResourceBundle resourceBundle)
    {
        this._resourceBundle = resourceBundle;
    } //-- void setResourceBundle(org.light.portlet.definition.ResourceBundle) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return PortletTypeChoiceSequence
     */
    public static org.light.portal.portlet.definition.PortletTypeChoiceSequence unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (org.light.portal.portlet.definition.PortletTypeChoiceSequence) Unmarshaller.unmarshal(org.light.portal.portlet.definition.PortletTypeChoiceSequence.class, reader);
    } //-- org.light.portlet.definition.PortletTypeChoiceSequence unmarshal(java.io.Reader) 

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
