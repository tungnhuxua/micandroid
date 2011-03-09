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
 * The display-name type contains a short name that is intended
 *  to be displayed by tools. It is used by display-name
 *  elements. The display name need not be unique.
 *  Example:
 *  ...
 *  Employee Self Service
 * 
 *  It has an optional attribute xml:lang to indicate 
 *  which language is used in the description according to 
 *  RFC 1766 (http://www.ietf.org/rfc/rfc1766.txt). The default
 *  value of this attribute is English(â€œenâ€?).
 *  
 * 
 * @version $Revision$ $Date$
 */
public class DisplayNameType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * internal content storage
     */
    private java.lang.String _content = "";

    /**
     * Attempting to install the relevant ISO 2- and 3-letter
     *  codes as the enumerated possible values is probably never
     *  going to be a realistic possibility. See
     *  RFC 3066 at http://www.ietf.org/rfc/rfc3066.txt and the
     * IANA registry
     *  at http://www.iana.org/assignments/lang-tag-apps.htm for
     *  further information.
     * 
     *  The union allows for the 'un-declaration' of xml:lang with
     *  the empty string.
     */
    private java.lang.Object _lang;


      //----------------/
     //- Constructors -/
    //----------------/

    public DisplayNameType() 
     {
        super();
        setContent("");
    } //-- org.light.portlet.definition.DisplayNameType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'content'. The field 'content'
     * has the following description: internal content storage
     * 
     * @return String
     * @return the value of field 'content'.
     */
    public java.lang.String getContent()
    {
        return this._content;
    } //-- java.lang.String getContent() 

    /**
     * Returns the value of field 'lang'. The field 'lang' has the
     * following description: Attempting to install the relevant
     * ISO 2- and 3-letter
     *  codes as the enumerated possible values is probably never
     *  going to be a realistic possibility. See
     *  RFC 3066 at http://www.ietf.org/rfc/rfc3066.txt and the
     * IANA registry
     *  at http://www.iana.org/assignments/lang-tag-apps.htm for
     *  further information.
     * 
     *  The union allows for the 'un-declaration' of xml:lang with
     *  the empty string.
     * 
     * @return Object
     * @return the value of field 'lang'.
     */
    public java.lang.Object getLang()
    {
        return this._lang;
    } //-- java.lang.Object getLang() 

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
     * Sets the value of field 'content'. The field 'content' has
     * the following description: internal content storage
     * 
     * @param content the value of field 'content'.
     */
    public void setContent(java.lang.String content)
    {
        this._content = content;
    } //-- void setContent(java.lang.String) 

    /**
     * Sets the value of field 'lang'. The field 'lang' has the
     * following description: Attempting to install the relevant
     * ISO 2- and 3-letter
     *  codes as the enumerated possible values is probably never
     *  going to be a realistic possibility. See
     *  RFC 3066 at http://www.ietf.org/rfc/rfc3066.txt and the
     * IANA registry
     *  at http://www.iana.org/assignments/lang-tag-apps.htm for
     *  further information.
     * 
     *  The union allows for the 'un-declaration' of xml:lang with
     *  the empty string.
     * 
     * @param lang the value of field 'lang'.
     */
    public void setLang(java.lang.Object lang)
    {
        this._lang = lang;
    } //-- void setLang(java.lang.Object) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return DisplayNameType
     */
    public static org.light.portal.portlet.definition.DisplayNameType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (org.light.portal.portlet.definition.DisplayNameType) Unmarshaller.unmarshal(org.light.portal.portlet.definition.DisplayNameType.class, reader);
    } //-- org.light.portlet.definition.DisplayNameType unmarshal(java.io.Reader) 

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
