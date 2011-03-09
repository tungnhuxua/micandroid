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
 * Class PortletInfoType.
 * 
 * @version $Revision$ $Date$
 */
public class PortletInfoType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _id
     */
    private java.lang.String _id;

    /**
     * Field _title
     */
    private org.light.portal.portlet.definition.Title _title;

    /**
     * Field _shortTitle
     */
    private org.light.portal.portlet.definition.ShortTitle _shortTitle;

    /**
     * Field _keywords
     */
    private org.light.portal.portlet.definition.Keywords _keywords;


      //----------------/
     //- Constructors -/
    //----------------/

    public PortletInfoType() 
     {
        super();
    } //-- org.light.portlet.definition.PortletInfoType()


      //-----------/
     //- Methods -/
    //-----------/

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
     * Returns the value of field 'keywords'.
     * 
     * @return Keywords
     * @return the value of field 'keywords'.
     */
    public org.light.portal.portlet.definition.Keywords getKeywords()
    {
        return this._keywords;
    } //-- org.light.portlet.definition.Keywords getKeywords() 

    /**
     * Returns the value of field 'shortTitle'.
     * 
     * @return ShortTitle
     * @return the value of field 'shortTitle'.
     */
    public org.light.portal.portlet.definition.ShortTitle getShortTitle()
    {
        return this._shortTitle;
    } //-- org.light.portlet.definition.ShortTitle getShortTitle() 

    /**
     * Returns the value of field 'title'.
     * 
     * @return Title
     * @return the value of field 'title'.
     */
    public org.light.portal.portlet.definition.Title getTitle()
    {
        return this._title;
    } //-- org.light.portlet.definition.Title getTitle() 

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
     * Sets the value of field 'id'.
     * 
     * @param id the value of field 'id'.
     */
    public void setId(java.lang.String id)
    {
        this._id = id;
    } //-- void setId(java.lang.String) 

    /**
     * Sets the value of field 'keywords'.
     * 
     * @param keywords the value of field 'keywords'.
     */
    public void setKeywords(org.light.portal.portlet.definition.Keywords keywords)
    {
        this._keywords = keywords;
    } //-- void setKeywords(org.light.portlet.definition.Keywords) 

    /**
     * Sets the value of field 'shortTitle'.
     * 
     * @param shortTitle the value of field 'shortTitle'.
     */
    public void setShortTitle(org.light.portal.portlet.definition.ShortTitle shortTitle)
    {
        this._shortTitle = shortTitle;
    } //-- void setShortTitle(org.light.portlet.definition.ShortTitle) 

    /**
     * Sets the value of field 'title'.
     * 
     * @param title the value of field 'title'.
     */
    public void setTitle(org.light.portal.portlet.definition.Title title)
    {
        this._title = title;
    } //-- void setTitle(org.light.portlet.definition.Title) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return PortletInfoType
     */
    public static org.light.portal.portlet.definition.PortletInfoType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (org.light.portal.portlet.definition.PortletInfoType) Unmarshaller.unmarshal(org.light.portal.portlet.definition.PortletInfoType.class, reader);
    } //-- org.light.portlet.definition.PortletInfoType unmarshal(java.io.Reader) 

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
