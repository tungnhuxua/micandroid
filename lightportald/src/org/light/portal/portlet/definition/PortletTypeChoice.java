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
 * Class PortletTypeChoice.
 * 
 * @version $Revision$ $Date$
 */
public class PortletTypeChoice implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _portletTypeChoiceSequence
     */
    private org.light.portal.portlet.definition.PortletTypeChoiceSequence _portletTypeChoiceSequence;

    /**
     * Field _portletInfo
     */
    private org.light.portal.portlet.definition.PortletInfo _portletInfo;


      //----------------/
     //- Constructors -/
    //----------------/

    public PortletTypeChoice() 
     {
        super();
    } //-- org.light.portlet.definition.PortletTypeChoice()


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
     * Returns the value of field 'portletTypeChoiceSequence'.
     * 
     * @return PortletTypeChoiceSequence
     * @return the value of field 'portletTypeChoiceSequence'.
     */
    public org.light.portal.portlet.definition.PortletTypeChoiceSequence getPortletTypeChoiceSequence()
    {
        return this._portletTypeChoiceSequence;
    } //-- org.light.portlet.definition.PortletTypeChoiceSequence getPortletTypeChoiceSequence() 

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
     * Sets the value of field 'portletTypeChoiceSequence'.
     * 
     * @param portletTypeChoiceSequence the value of field
     * 'portletTypeChoiceSequence'.
     */
    public void setPortletTypeChoiceSequence(org.light.portal.portlet.definition.PortletTypeChoiceSequence portletTypeChoiceSequence)
    {
        this._portletTypeChoiceSequence = portletTypeChoiceSequence;
    } //-- void setPortletTypeChoiceSequence(org.light.portlet.definition.PortletTypeChoiceSequence) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return PortletTypeChoice
     */
    public static org.light.portal.portlet.definition.PortletTypeChoice unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (org.light.portal.portlet.definition.PortletTypeChoice) Unmarshaller.unmarshal(org.light.portal.portlet.definition.PortletTypeChoice.class, reader);
    } //-- org.light.portlet.definition.PortletTypeChoice unmarshal(java.io.Reader) 

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
