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
 * Class PortletAppType.
 * 
 * @version $Revision$ $Date$
 */
public class PortletAppType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _version
     */
    private java.lang.String _version;

    /**
     * Field _id
     */
    private java.lang.String _id;

    /**
     * Field _portletList
     */
    private java.util.Vector _portletList;

    /**
     * Field _customPortletModeList
     */
    private java.util.Vector _customPortletModeList;

    /**
     * Field _customWindowStateList
     */
    private java.util.Vector _customWindowStateList;

    /**
     * Field _userAttributeList
     */
    private java.util.Vector _userAttributeList;

    /**
     * Field _securityConstraintList
     */
    private java.util.Vector _securityConstraintList;


      //----------------/
     //- Constructors -/
    //----------------/

    public PortletAppType() 
     {
        super();
        _portletList = new Vector();
        _customPortletModeList = new Vector();
        _customWindowStateList = new Vector();
        _userAttributeList = new Vector();
        _securityConstraintList = new Vector();
    } //-- org.light.portlet.definition.PortletAppType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addCustomPortletMode
     * 
     * 
     * 
     * @param vCustomPortletMode
     */
    public void addCustomPortletMode(org.light.portal.portlet.definition.CustomPortletMode vCustomPortletMode)
        throws java.lang.IndexOutOfBoundsException
    {
        _customPortletModeList.addElement(vCustomPortletMode);
    } //-- void addCustomPortletMode(org.light.portlet.definition.CustomPortletMode) 

    /**
     * Method addCustomPortletMode
     * 
     * 
     * 
     * @param index
     * @param vCustomPortletMode
     */
    public void addCustomPortletMode(int index, org.light.portal.portlet.definition.CustomPortletMode vCustomPortletMode)
        throws java.lang.IndexOutOfBoundsException
    {
        _customPortletModeList.insertElementAt(vCustomPortletMode, index);
    } //-- void addCustomPortletMode(int, org.light.portlet.definition.CustomPortletMode) 

    /**
     * Method addCustomWindowState
     * 
     * 
     * 
     * @param vCustomWindowState
     */
    public void addCustomWindowState(org.light.portal.portlet.definition.CustomWindowState vCustomWindowState)
        throws java.lang.IndexOutOfBoundsException
    {
        _customWindowStateList.addElement(vCustomWindowState);
    } //-- void addCustomWindowState(org.light.portlet.definition.CustomWindowState) 

    /**
     * Method addCustomWindowState
     * 
     * 
     * 
     * @param index
     * @param vCustomWindowState
     */
    public void addCustomWindowState(int index, org.light.portal.portlet.definition.CustomWindowState vCustomWindowState)
        throws java.lang.IndexOutOfBoundsException
    {
        _customWindowStateList.insertElementAt(vCustomWindowState, index);
    } //-- void addCustomWindowState(int, org.light.portlet.definition.CustomWindowState) 

    /**
     * Method addPortlet
     * 
     * 
     * 
     * @param vPortlet
     */
    public void addPortlet(org.light.portal.portlet.definition.Portlet vPortlet)
        throws java.lang.IndexOutOfBoundsException
    {
        _portletList.addElement(vPortlet);
    } //-- void addPortlet(org.light.portlet.definition.Portlet) 

    /**
     * Method addPortlet
     * 
     * 
     * 
     * @param index
     * @param vPortlet
     */
    public void addPortlet(int index, org.light.portal.portlet.definition.Portlet vPortlet)
        throws java.lang.IndexOutOfBoundsException
    {
        _portletList.insertElementAt(vPortlet, index);
    } //-- void addPortlet(int, org.light.portlet.definition.Portlet) 

    /**
     * Method addSecurityConstraint
     * 
     * 
     * 
     * @param vSecurityConstraint
     */
    public void addSecurityConstraint(org.light.portal.portlet.definition.SecurityConstraint vSecurityConstraint)
        throws java.lang.IndexOutOfBoundsException
    {
        _securityConstraintList.addElement(vSecurityConstraint);
    } //-- void addSecurityConstraint(org.light.portlet.definition.SecurityConstraint) 

    /**
     * Method addSecurityConstraint
     * 
     * 
     * 
     * @param index
     * @param vSecurityConstraint
     */
    public void addSecurityConstraint(int index, org.light.portal.portlet.definition.SecurityConstraint vSecurityConstraint)
        throws java.lang.IndexOutOfBoundsException
    {
        _securityConstraintList.insertElementAt(vSecurityConstraint, index);
    } //-- void addSecurityConstraint(int, org.light.portlet.definition.SecurityConstraint) 

    /**
     * Method addUserAttribute
     * 
     * 
     * 
     * @param vUserAttribute
     */
    public void addUserAttribute(org.light.portal.portlet.definition.UserAttribute vUserAttribute)
        throws java.lang.IndexOutOfBoundsException
    {
        _userAttributeList.addElement(vUserAttribute);
    } //-- void addUserAttribute(org.light.portlet.definition.UserAttribute) 

    /**
     * Method addUserAttribute
     * 
     * 
     * 
     * @param index
     * @param vUserAttribute
     */
    public void addUserAttribute(int index, org.light.portal.portlet.definition.UserAttribute vUserAttribute)
        throws java.lang.IndexOutOfBoundsException
    {
        _userAttributeList.insertElementAt(vUserAttribute, index);
    } //-- void addUserAttribute(int, org.light.portlet.definition.UserAttribute) 

    /**
     * Method enumerateCustomPortletMode
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateCustomPortletMode()
    {
        return _customPortletModeList.elements();
    } //-- java.util.Enumeration enumerateCustomPortletMode() 

    /**
     * Method enumerateCustomWindowState
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateCustomWindowState()
    {
        return _customWindowStateList.elements();
    } //-- java.util.Enumeration enumerateCustomWindowState() 

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
     * Method enumerateSecurityConstraint
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateSecurityConstraint()
    {
        return _securityConstraintList.elements();
    } //-- java.util.Enumeration enumerateSecurityConstraint() 

    /**
     * Method enumerateUserAttribute
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateUserAttribute()
    {
        return _userAttributeList.elements();
    } //-- java.util.Enumeration enumerateUserAttribute() 

    /**
     * Method getCustomPortletMode
     * 
     * 
     * 
     * @param index
     * @return CustomPortletMode
     */
    public org.light.portal.portlet.definition.CustomPortletMode getCustomPortletMode(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _customPortletModeList.size())) {
            throw new IndexOutOfBoundsException("getCustomPortletMode: Index value '"+index+"' not in range [0.."+(_customPortletModeList.size() - 1) + "]");
        }
        
        return (org.light.portal.portlet.definition.CustomPortletMode) _customPortletModeList.elementAt(index);
    } //-- org.light.portlet.definition.CustomPortletMode getCustomPortletMode(int) 

    /**
     * Method getCustomPortletMode
     * 
     * 
     * 
     * @return CustomPortletMode
     */
    public org.light.portal.portlet.definition.CustomPortletMode[] getCustomPortletMode()
    {
        int size = _customPortletModeList.size();
        org.light.portal.portlet.definition.CustomPortletMode[] mArray = new org.light.portal.portlet.definition.CustomPortletMode[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (org.light.portal.portlet.definition.CustomPortletMode) _customPortletModeList.elementAt(index);
        }
        return mArray;
    } //-- org.light.portlet.definition.CustomPortletMode[] getCustomPortletMode() 

    /**
     * Method getCustomPortletModeCount
     * 
     * 
     * 
     * @return int
     */
    public int getCustomPortletModeCount()
    {
        return _customPortletModeList.size();
    } //-- int getCustomPortletModeCount() 

    /**
     * Method getCustomWindowState
     * 
     * 
     * 
     * @param index
     * @return CustomWindowState
     */
    public org.light.portal.portlet.definition.CustomWindowState getCustomWindowState(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _customWindowStateList.size())) {
            throw new IndexOutOfBoundsException("getCustomWindowState: Index value '"+index+"' not in range [0.."+(_customWindowStateList.size() - 1) + "]");
        }
        
        return (org.light.portal.portlet.definition.CustomWindowState) _customWindowStateList.elementAt(index);
    } //-- org.light.portlet.definition.CustomWindowState getCustomWindowState(int) 

    /**
     * Method getCustomWindowState
     * 
     * 
     * 
     * @return CustomWindowState
     */
    public org.light.portal.portlet.definition.CustomWindowState[] getCustomWindowState()
    {
        int size = _customWindowStateList.size();
        org.light.portal.portlet.definition.CustomWindowState[] mArray = new org.light.portal.portlet.definition.CustomWindowState[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (org.light.portal.portlet.definition.CustomWindowState) _customWindowStateList.elementAt(index);
        }
        return mArray;
    } //-- org.light.portlet.definition.CustomWindowState[] getCustomWindowState() 

    /**
     * Method getCustomWindowStateCount
     * 
     * 
     * 
     * @return int
     */
    public int getCustomWindowStateCount()
    {
        return _customWindowStateList.size();
    } //-- int getCustomWindowStateCount() 

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
     * Method getPortlet
     * 
     * 
     * 
     * @param index
     * @return Portlet
     */
    public org.light.portal.portlet.definition.Portlet getPortlet(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _portletList.size())) {
            throw new IndexOutOfBoundsException("getPortlet: Index value '"+index+"' not in range [0.."+(_portletList.size() - 1) + "]");
        }
        
        return (org.light.portal.portlet.definition.Portlet) _portletList.elementAt(index);
    } //-- org.light.portlet.definition.Portlet getPortlet(int) 

    /**
     * Method getPortlet
     * 
     * 
     * 
     * @return Portlet
     */
    public org.light.portal.portlet.definition.Portlet[] getPortlet()
    {
        int size = _portletList.size();
        org.light.portal.portlet.definition.Portlet[] mArray = new org.light.portal.portlet.definition.Portlet[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (org.light.portal.portlet.definition.Portlet) _portletList.elementAt(index);
        }
        return mArray;
    } //-- org.light.portlet.definition.Portlet[] getPortlet() 

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
     * Method getSecurityConstraint
     * 
     * 
     * 
     * @param index
     * @return SecurityConstraint
     */
    public org.light.portal.portlet.definition.SecurityConstraint getSecurityConstraint(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _securityConstraintList.size())) {
            throw new IndexOutOfBoundsException("getSecurityConstraint: Index value '"+index+"' not in range [0.."+(_securityConstraintList.size() - 1) + "]");
        }
        
        return (org.light.portal.portlet.definition.SecurityConstraint) _securityConstraintList.elementAt(index);
    } //-- org.light.portlet.definition.SecurityConstraint getSecurityConstraint(int) 

    /**
     * Method getSecurityConstraint
     * 
     * 
     * 
     * @return SecurityConstraint
     */
    public org.light.portal.portlet.definition.SecurityConstraint[] getSecurityConstraint()
    {
        int size = _securityConstraintList.size();
        org.light.portal.portlet.definition.SecurityConstraint[] mArray = new org.light.portal.portlet.definition.SecurityConstraint[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (org.light.portal.portlet.definition.SecurityConstraint) _securityConstraintList.elementAt(index);
        }
        return mArray;
    } //-- org.light.portlet.definition.SecurityConstraint[] getSecurityConstraint() 

    /**
     * Method getSecurityConstraintCount
     * 
     * 
     * 
     * @return int
     */
    public int getSecurityConstraintCount()
    {
        return _securityConstraintList.size();
    } //-- int getSecurityConstraintCount() 

    /**
     * Method getUserAttribute
     * 
     * 
     * 
     * @param index
     * @return UserAttribute
     */
    public org.light.portal.portlet.definition.UserAttribute getUserAttribute(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _userAttributeList.size())) {
            throw new IndexOutOfBoundsException("getUserAttribute: Index value '"+index+"' not in range [0.."+(_userAttributeList.size() - 1) + "]");
        }
        
        return (org.light.portal.portlet.definition.UserAttribute) _userAttributeList.elementAt(index);
    } //-- org.light.portlet.definition.UserAttribute getUserAttribute(int) 

    /**
     * Method getUserAttribute
     * 
     * 
     * 
     * @return UserAttribute
     */
    public org.light.portal.portlet.definition.UserAttribute[] getUserAttribute()
    {
        int size = _userAttributeList.size();
        org.light.portal.portlet.definition.UserAttribute[] mArray = new org.light.portal.portlet.definition.UserAttribute[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (org.light.portal.portlet.definition.UserAttribute) _userAttributeList.elementAt(index);
        }
        return mArray;
    } //-- org.light.portlet.definition.UserAttribute[] getUserAttribute() 

    /**
     * Method getUserAttributeCount
     * 
     * 
     * 
     * @return int
     */
    public int getUserAttributeCount()
    {
        return _userAttributeList.size();
    } //-- int getUserAttributeCount() 

    /**
     * Returns the value of field 'version'.
     * 
     * @return String
     * @return the value of field 'version'.
     */
    public java.lang.String getVersion()
    {
        return this._version;
    } //-- java.lang.String getVersion() 

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
     * Method removeAllCustomPortletMode
     * 
     */
    public void removeAllCustomPortletMode()
    {
        _customPortletModeList.removeAllElements();
    } //-- void removeAllCustomPortletMode() 

    /**
     * Method removeAllCustomWindowState
     * 
     */
    public void removeAllCustomWindowState()
    {
        _customWindowStateList.removeAllElements();
    } //-- void removeAllCustomWindowState() 

    /**
     * Method removeAllPortlet
     * 
     */
    public void removeAllPortlet()
    {
        _portletList.removeAllElements();
    } //-- void removeAllPortlet() 

    /**
     * Method removeAllSecurityConstraint
     * 
     */
    public void removeAllSecurityConstraint()
    {
        _securityConstraintList.removeAllElements();
    } //-- void removeAllSecurityConstraint() 

    /**
     * Method removeAllUserAttribute
     * 
     */
    public void removeAllUserAttribute()
    {
        _userAttributeList.removeAllElements();
    } //-- void removeAllUserAttribute() 

    /**
     * Method removeCustomPortletMode
     * 
     * 
     * 
     * @param index
     * @return CustomPortletMode
     */
    public org.light.portal.portlet.definition.CustomPortletMode removeCustomPortletMode(int index)
    {
        java.lang.Object obj = _customPortletModeList.elementAt(index);
        _customPortletModeList.removeElementAt(index);
        return (org.light.portal.portlet.definition.CustomPortletMode) obj;
    } //-- org.light.portlet.definition.CustomPortletMode removeCustomPortletMode(int) 

    /**
     * Method removeCustomWindowState
     * 
     * 
     * 
     * @param index
     * @return CustomWindowState
     */
    public org.light.portal.portlet.definition.CustomWindowState removeCustomWindowState(int index)
    {
        java.lang.Object obj = _customWindowStateList.elementAt(index);
        _customWindowStateList.removeElementAt(index);
        return (org.light.portal.portlet.definition.CustomWindowState) obj;
    } //-- org.light.portlet.definition.CustomWindowState removeCustomWindowState(int) 

    /**
     * Method removePortlet
     * 
     * 
     * 
     * @param index
     * @return Portlet
     */
    public org.light.portal.portlet.definition.Portlet removePortlet(int index)
    {
        java.lang.Object obj = _portletList.elementAt(index);
        _portletList.removeElementAt(index);
        return (org.light.portal.portlet.definition.Portlet) obj;
    } //-- org.light.portlet.definition.Portlet removePortlet(int) 

    /**
     * Method removeSecurityConstraint
     * 
     * 
     * 
     * @param index
     * @return SecurityConstraint
     */
    public org.light.portal.portlet.definition.SecurityConstraint removeSecurityConstraint(int index)
    {
        java.lang.Object obj = _securityConstraintList.elementAt(index);
        _securityConstraintList.removeElementAt(index);
        return (org.light.portal.portlet.definition.SecurityConstraint) obj;
    } //-- org.light.portlet.definition.SecurityConstraint removeSecurityConstraint(int) 

    /**
     * Method removeUserAttribute
     * 
     * 
     * 
     * @param index
     * @return UserAttribute
     */
    public org.light.portal.portlet.definition.UserAttribute removeUserAttribute(int index)
    {
        java.lang.Object obj = _userAttributeList.elementAt(index);
        _userAttributeList.removeElementAt(index);
        return (org.light.portal.portlet.definition.UserAttribute) obj;
    } //-- org.light.portlet.definition.UserAttribute removeUserAttribute(int) 

    /**
     * Method setCustomPortletMode
     * 
     * 
     * 
     * @param index
     * @param vCustomPortletMode
     */
    public void setCustomPortletMode(int index, org.light.portal.portlet.definition.CustomPortletMode vCustomPortletMode)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _customPortletModeList.size())) {
            throw new IndexOutOfBoundsException("setCustomPortletMode: Index value '"+index+"' not in range [0.." + (_customPortletModeList.size() - 1) + "]");
        }
        _customPortletModeList.setElementAt(vCustomPortletMode, index);
    } //-- void setCustomPortletMode(int, org.light.portlet.definition.CustomPortletMode) 

    /**
     * Method setCustomPortletMode
     * 
     * 
     * 
     * @param customPortletModeArray
     */
    public void setCustomPortletMode(org.light.portal.portlet.definition.CustomPortletMode[] customPortletModeArray)
    {
        //-- copy array
        _customPortletModeList.removeAllElements();
        for (int i = 0; i < customPortletModeArray.length; i++) {
            _customPortletModeList.addElement(customPortletModeArray[i]);
        }
    } //-- void setCustomPortletMode(org.light.portlet.definition.CustomPortletMode) 

    /**
     * Method setCustomWindowState
     * 
     * 
     * 
     * @param index
     * @param vCustomWindowState
     */
    public void setCustomWindowState(int index, org.light.portal.portlet.definition.CustomWindowState vCustomWindowState)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _customWindowStateList.size())) {
            throw new IndexOutOfBoundsException("setCustomWindowState: Index value '"+index+"' not in range [0.." + (_customWindowStateList.size() - 1) + "]");
        }
        _customWindowStateList.setElementAt(vCustomWindowState, index);
    } //-- void setCustomWindowState(int, org.light.portlet.definition.CustomWindowState) 

    /**
     * Method setCustomWindowState
     * 
     * 
     * 
     * @param customWindowStateArray
     */
    public void setCustomWindowState(org.light.portal.portlet.definition.CustomWindowState[] customWindowStateArray)
    {
        //-- copy array
        _customWindowStateList.removeAllElements();
        for (int i = 0; i < customWindowStateArray.length; i++) {
            _customWindowStateList.addElement(customWindowStateArray[i]);
        }
    } //-- void setCustomWindowState(org.light.portlet.definition.CustomWindowState) 

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
     * Method setPortlet
     * 
     * 
     * 
     * @param index
     * @param vPortlet
     */
    public void setPortlet(int index, org.light.portal.portlet.definition.Portlet vPortlet)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _portletList.size())) {
            throw new IndexOutOfBoundsException("setPortlet: Index value '"+index+"' not in range [0.." + (_portletList.size() - 1) + "]");
        }
        _portletList.setElementAt(vPortlet, index);
    } //-- void setPortlet(int, org.light.portlet.definition.Portlet) 

    /**
     * Method setPortlet
     * 
     * 
     * 
     * @param portletArray
     */
    public void setPortlet(org.light.portal.portlet.definition.Portlet[] portletArray)
    {
        //-- copy array
        _portletList.removeAllElements();
        for (int i = 0; i < portletArray.length; i++) {
            _portletList.addElement(portletArray[i]);
        }
    } //-- void setPortlet(org.light.portlet.definition.Portlet) 

    /**
     * Method setSecurityConstraint
     * 
     * 
     * 
     * @param index
     * @param vSecurityConstraint
     */
    public void setSecurityConstraint(int index, org.light.portal.portlet.definition.SecurityConstraint vSecurityConstraint)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _securityConstraintList.size())) {
            throw new IndexOutOfBoundsException("setSecurityConstraint: Index value '"+index+"' not in range [0.." + (_securityConstraintList.size() - 1) + "]");
        }
        _securityConstraintList.setElementAt(vSecurityConstraint, index);
    } //-- void setSecurityConstraint(int, org.light.portlet.definition.SecurityConstraint) 

    /**
     * Method setSecurityConstraint
     * 
     * 
     * 
     * @param securityConstraintArray
     */
    public void setSecurityConstraint(org.light.portal.portlet.definition.SecurityConstraint[] securityConstraintArray)
    {
        //-- copy array
        _securityConstraintList.removeAllElements();
        for (int i = 0; i < securityConstraintArray.length; i++) {
            _securityConstraintList.addElement(securityConstraintArray[i]);
        }
    } //-- void setSecurityConstraint(org.light.portlet.definition.SecurityConstraint) 

    /**
     * Method setUserAttribute
     * 
     * 
     * 
     * @param index
     * @param vUserAttribute
     */
    public void setUserAttribute(int index, org.light.portal.portlet.definition.UserAttribute vUserAttribute)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _userAttributeList.size())) {
            throw new IndexOutOfBoundsException("setUserAttribute: Index value '"+index+"' not in range [0.." + (_userAttributeList.size() - 1) + "]");
        }
        _userAttributeList.setElementAt(vUserAttribute, index);
    } //-- void setUserAttribute(int, org.light.portlet.definition.UserAttribute) 

    /**
     * Method setUserAttribute
     * 
     * 
     * 
     * @param userAttributeArray
     */
    public void setUserAttribute(org.light.portal.portlet.definition.UserAttribute[] userAttributeArray)
    {
        //-- copy array
        _userAttributeList.removeAllElements();
        for (int i = 0; i < userAttributeArray.length; i++) {
            _userAttributeList.addElement(userAttributeArray[i]);
        }
    } //-- void setUserAttribute(org.light.portlet.definition.UserAttribute) 

    /**
     * Sets the value of field 'version'.
     * 
     * @param version the value of field 'version'.
     */
    public void setVersion(java.lang.String version)
    {
        this._version = version;
    } //-- void setVersion(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return PortletAppType
     */
    public static org.light.portal.portlet.definition.PortletAppType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (org.light.portal.portlet.definition.PortletAppType) Unmarshaller.unmarshal(org.light.portal.portlet.definition.PortletAppType.class, reader);
    } //-- org.light.portlet.definition.PortletAppType unmarshal(java.io.Reader) 

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
