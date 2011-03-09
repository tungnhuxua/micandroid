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
 * Class Roles.
 * 
 * @version $Revision$ $Date$
 */
public class Roles implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _roleList
     */
    private java.util.Vector _roleList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Roles() 
     {
        super();
        _roleList = new Vector();
    } //-- org.light.portlet.config.Roles()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addRole
     * 
     * 
     * 
     * @param vRole
     */
    public void addRole(java.lang.String vRole)
        throws java.lang.IndexOutOfBoundsException
    {
        _roleList.addElement(vRole);
    } //-- void addRole(java.lang.String) 

    /**
     * Method addRole
     * 
     * 
     * 
     * @param index
     * @param vRole
     */
    public void addRole(int index, java.lang.String vRole)
        throws java.lang.IndexOutOfBoundsException
    {
        _roleList.insertElementAt(vRole, index);
    } //-- void addRole(int, java.lang.String) 

    /**
     * Method enumerateRole
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateRole()
    {
        return _roleList.elements();
    } //-- java.util.Enumeration enumerateRole() 

    /**
     * Method getRole
     * 
     * 
     * 
     * @param index
     * @return String
     */
    public java.lang.String getRole(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _roleList.size())) {
            throw new IndexOutOfBoundsException("getRole: Index value '"+index+"' not in range [0.."+(_roleList.size() - 1) + "]");
        }
        
        return (String)_roleList.elementAt(index);
    } //-- java.lang.String getRole(int) 

    /**
     * Method getRole
     * 
     * 
     * 
     * @return String
     */
    public java.lang.String[] getRole()
    {
        int size = _roleList.size();
        java.lang.String[] mArray = new java.lang.String[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (String)_roleList.elementAt(index);
        }
        return mArray;
    } //-- java.lang.String[] getRole() 

    /**
     * Method getRoleCount
     * 
     * 
     * 
     * @return int
     */
    public int getRoleCount()
    {
        return _roleList.size();
    } //-- int getRoleCount() 

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
     * Method removeAllRole
     * 
     */
    public void removeAllRole()
    {
        _roleList.removeAllElements();
    } //-- void removeAllRole() 

    /**
     * Method removeRole
     * 
     * 
     * 
     * @param index
     * @return String
     */
    public java.lang.String removeRole(int index)
    {
        java.lang.Object obj = _roleList.elementAt(index);
        _roleList.removeElementAt(index);
        return (String)obj;
    } //-- java.lang.String removeRole(int) 

    /**
     * Method setRole
     * 
     * 
     * 
     * @param index
     * @param vRole
     */
    public void setRole(int index, java.lang.String vRole)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _roleList.size())) {
            throw new IndexOutOfBoundsException("setRole: Index value '"+index+"' not in range [0.." + (_roleList.size() - 1) + "]");
        }
        _roleList.setElementAt(vRole, index);
    } //-- void setRole(int, java.lang.String) 

    /**
     * Method setRole
     * 
     * 
     * 
     * @param roleArray
     */
    public void setRole(java.lang.String[] roleArray)
    {
        //-- copy array
        _roleList.removeAllElements();
        for (int i = 0; i < roleArray.length; i++) {
            _roleList.addElement(roleArray[i]);
        }
    } //-- void setRole(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Roles
     */
    public static org.light.portal.portlet.config.Roles unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (org.light.portal.portlet.config.Roles) Unmarshaller.unmarshal(org.light.portal.portlet.config.Roles.class, reader);
    } //-- org.light.portlet.config.Roles unmarshal(java.io.Reader) 

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
