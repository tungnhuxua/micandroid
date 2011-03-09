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
 * The portlet element contains the declarative data of a portlet. 
 *  Used in: portlet-app
 *  
 * 
 * @version $Revision$ $Date$
 */
public class PortletType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _id
     */
    private java.lang.String _id;

    /**
     * Field _descriptionList
     */
    private java.util.Vector _descriptionList;

    /**
     * Field _portletName
     */
    private org.light.portal.portlet.definition.PortletName _portletName;

    /**
     * Field _displayNameList
     */
    private java.util.Vector _displayNameList;

    /**
     * Field _portletClass
     */
    private java.lang.String _portletClass;

    /**
     * Field _initParamList
     */
    private java.util.Vector _initParamList;

    /**
     * Field _expirationCache
     */
    private org.light.portal.portlet.definition.ExpirationCache _expirationCache;

    /**
     * Field _supportsList
     */
    private java.util.Vector _supportsList;

    /**
     * Field _supportedLocaleList
     */
    private java.util.Vector _supportedLocaleList;

    /**
     * Field _portletTypeChoice
     */
    private org.light.portal.portlet.definition.PortletTypeChoice _portletTypeChoice;

    /**
     * Field _portletPreferences
     */
    private org.light.portal.portlet.definition.PortletPreferences _portletPreferences;

    /**
     * Field _securityRoleRefList
     */
    private java.util.Vector _securityRoleRefList;


      //----------------/
     //- Constructors -/
    //----------------/

    public PortletType() 
     {
        super();
        _descriptionList = new Vector();
        _displayNameList = new Vector();
        _initParamList = new Vector();
        _supportsList = new Vector();
        _supportedLocaleList = new Vector();
        _securityRoleRefList = new Vector();
    } //-- org.light.portlet.definition.PortletType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addDescription
     * 
     * 
     * 
     * @param vDescription
     */
    public void addDescription(org.light.portal.portlet.definition.Description vDescription)
        throws java.lang.IndexOutOfBoundsException
    {
        _descriptionList.addElement(vDescription);
    } //-- void addDescription(org.light.portlet.definition.Description) 

    /**
     * Method addDescription
     * 
     * 
     * 
     * @param index
     * @param vDescription
     */
    public void addDescription(int index, org.light.portal.portlet.definition.Description vDescription)
        throws java.lang.IndexOutOfBoundsException
    {
        _descriptionList.insertElementAt(vDescription, index);
    } //-- void addDescription(int, org.light.portlet.definition.Description) 

    /**
     * Method addDisplayName
     * 
     * 
     * 
     * @param vDisplayName
     */
    public void addDisplayName(org.light.portal.portlet.definition.DisplayName vDisplayName)
        throws java.lang.IndexOutOfBoundsException
    {
        _displayNameList.addElement(vDisplayName);
    } //-- void addDisplayName(org.light.portlet.definition.DisplayName) 

    /**
     * Method addDisplayName
     * 
     * 
     * 
     * @param index
     * @param vDisplayName
     */
    public void addDisplayName(int index, org.light.portal.portlet.definition.DisplayName vDisplayName)
        throws java.lang.IndexOutOfBoundsException
    {
        _displayNameList.insertElementAt(vDisplayName, index);
    } //-- void addDisplayName(int, org.light.portlet.definition.DisplayName) 

    /**
     * Method addInitParam
     * 
     * 
     * 
     * @param vInitParam
     */
    public void addInitParam(org.light.portal.portlet.definition.InitParam vInitParam)
        throws java.lang.IndexOutOfBoundsException
    {
        _initParamList.addElement(vInitParam);
    } //-- void addInitParam(org.light.portlet.definition.InitParam) 

    /**
     * Method addInitParam
     * 
     * 
     * 
     * @param index
     * @param vInitParam
     */
    public void addInitParam(int index, org.light.portal.portlet.definition.InitParam vInitParam)
        throws java.lang.IndexOutOfBoundsException
    {
        _initParamList.insertElementAt(vInitParam, index);
    } //-- void addInitParam(int, org.light.portlet.definition.InitParam) 

    /**
     * Method addSecurityRoleRef
     * 
     * 
     * 
     * @param vSecurityRoleRef
     */
    public void addSecurityRoleRef(org.light.portal.portlet.definition.SecurityRoleRef vSecurityRoleRef)
        throws java.lang.IndexOutOfBoundsException
    {
        _securityRoleRefList.addElement(vSecurityRoleRef);
    } //-- void addSecurityRoleRef(org.light.portlet.definition.SecurityRoleRef) 

    /**
     * Method addSecurityRoleRef
     * 
     * 
     * 
     * @param index
     * @param vSecurityRoleRef
     */
    public void addSecurityRoleRef(int index, org.light.portal.portlet.definition.SecurityRoleRef vSecurityRoleRef)
        throws java.lang.IndexOutOfBoundsException
    {
        _securityRoleRefList.insertElementAt(vSecurityRoleRef, index);
    } //-- void addSecurityRoleRef(int, org.light.portlet.definition.SecurityRoleRef) 

    /**
     * Method addSupportedLocale
     * 
     * 
     * 
     * @param vSupportedLocale
     */
    public void addSupportedLocale(org.light.portal.portlet.definition.SupportedLocale vSupportedLocale)
        throws java.lang.IndexOutOfBoundsException
    {
        _supportedLocaleList.addElement(vSupportedLocale);
    } //-- void addSupportedLocale(org.light.portlet.definition.SupportedLocale) 

    /**
     * Method addSupportedLocale
     * 
     * 
     * 
     * @param index
     * @param vSupportedLocale
     */
    public void addSupportedLocale(int index, org.light.portal.portlet.definition.SupportedLocale vSupportedLocale)
        throws java.lang.IndexOutOfBoundsException
    {
        _supportedLocaleList.insertElementAt(vSupportedLocale, index);
    } //-- void addSupportedLocale(int, org.light.portlet.definition.SupportedLocale) 

    /**
     * Method addSupports
     * 
     * 
     * 
     * @param vSupports
     */
    public void addSupports(org.light.portal.portlet.definition.Supports vSupports)
        throws java.lang.IndexOutOfBoundsException
    {
        _supportsList.addElement(vSupports);
    } //-- void addSupports(org.light.portlet.definition.Supports) 

    /**
     * Method addSupports
     * 
     * 
     * 
     * @param index
     * @param vSupports
     */
    public void addSupports(int index, org.light.portal.portlet.definition.Supports vSupports)
        throws java.lang.IndexOutOfBoundsException
    {
        _supportsList.insertElementAt(vSupports, index);
    } //-- void addSupports(int, org.light.portlet.definition.Supports) 

    /**
     * Method enumerateDescription
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateDescription()
    {
        return _descriptionList.elements();
    } //-- java.util.Enumeration enumerateDescription() 

    /**
     * Method enumerateDisplayName
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateDisplayName()
    {
        return _displayNameList.elements();
    } //-- java.util.Enumeration enumerateDisplayName() 

    /**
     * Method enumerateInitParam
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateInitParam()
    {
        return _initParamList.elements();
    } //-- java.util.Enumeration enumerateInitParam() 

    /**
     * Method enumerateSecurityRoleRef
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateSecurityRoleRef()
    {
        return _securityRoleRefList.elements();
    } //-- java.util.Enumeration enumerateSecurityRoleRef() 

    /**
     * Method enumerateSupportedLocale
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateSupportedLocale()
    {
        return _supportedLocaleList.elements();
    } //-- java.util.Enumeration enumerateSupportedLocale() 

    /**
     * Method enumerateSupports
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateSupports()
    {
        return _supportsList.elements();
    } //-- java.util.Enumeration enumerateSupports() 

    /**
     * Method getDescription
     * 
     * 
     * 
     * @param index
     * @return Description
     */
    public org.light.portal.portlet.definition.Description getDescription(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _descriptionList.size())) {
            throw new IndexOutOfBoundsException("getDescription: Index value '"+index+"' not in range [0.."+(_descriptionList.size() - 1) + "]");
        }
        
        return (org.light.portal.portlet.definition.Description) _descriptionList.elementAt(index);
    } //-- org.light.portlet.definition.Description getDescription(int) 

    /**
     * Method getDescription
     * 
     * 
     * 
     * @return Description
     */
    public org.light.portal.portlet.definition.Description[] getDescription()
    {
        int size = _descriptionList.size();
        org.light.portal.portlet.definition.Description[] mArray = new org.light.portal.portlet.definition.Description[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (org.light.portal.portlet.definition.Description) _descriptionList.elementAt(index);
        }
        return mArray;
    } //-- org.light.portlet.definition.Description[] getDescription() 

    /**
     * Method getDescriptionCount
     * 
     * 
     * 
     * @return int
     */
    public int getDescriptionCount()
    {
        return _descriptionList.size();
    } //-- int getDescriptionCount() 

    /**
     * Method getDisplayName
     * 
     * 
     * 
     * @param index
     * @return DisplayName
     */
    public org.light.portal.portlet.definition.DisplayName getDisplayName(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _displayNameList.size())) {
            throw new IndexOutOfBoundsException("getDisplayName: Index value '"+index+"' not in range [0.."+(_displayNameList.size() - 1) + "]");
        }
        
        return (org.light.portal.portlet.definition.DisplayName) _displayNameList.elementAt(index);
    } //-- org.light.portlet.definition.DisplayName getDisplayName(int) 

    /**
     * Method getDisplayName
     * 
     * 
     * 
     * @return DisplayName
     */
    public org.light.portal.portlet.definition.DisplayName[] getDisplayName()
    {
        int size = _displayNameList.size();
        org.light.portal.portlet.definition.DisplayName[] mArray = new org.light.portal.portlet.definition.DisplayName[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (org.light.portal.portlet.definition.DisplayName) _displayNameList.elementAt(index);
        }
        return mArray;
    } //-- org.light.portlet.definition.DisplayName[] getDisplayName() 

    /**
     * Method getDisplayNameCount
     * 
     * 
     * 
     * @return int
     */
    public int getDisplayNameCount()
    {
        return _displayNameList.size();
    } //-- int getDisplayNameCount() 

    /**
     * Returns the value of field 'expirationCache'.
     * 
     * @return ExpirationCache
     * @return the value of field 'expirationCache'.
     */
    public org.light.portal.portlet.definition.ExpirationCache getExpirationCache()
    {
        return this._expirationCache;
    } //-- org.light.portlet.definition.ExpirationCache getExpirationCache() 

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
     * Method getInitParam
     * 
     * 
     * 
     * @param index
     * @return InitParam
     */
    public org.light.portal.portlet.definition.InitParam getInitParam(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _initParamList.size())) {
            throw new IndexOutOfBoundsException("getInitParam: Index value '"+index+"' not in range [0.."+(_initParamList.size() - 1) + "]");
        }
        
        return (org.light.portal.portlet.definition.InitParam) _initParamList.elementAt(index);
    } //-- org.light.portlet.definition.InitParam getInitParam(int) 

    /**
     * Method getInitParam
     * 
     * 
     * 
     * @return InitParam
     */
    public org.light.portal.portlet.definition.InitParam[] getInitParam()
    {
        int size = _initParamList.size();
        org.light.portal.portlet.definition.InitParam[] mArray = new org.light.portal.portlet.definition.InitParam[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (org.light.portal.portlet.definition.InitParam) _initParamList.elementAt(index);
        }
        return mArray;
    } //-- org.light.portlet.definition.InitParam[] getInitParam() 

    /**
     * Method getInitParamCount
     * 
     * 
     * 
     * @return int
     */
    public int getInitParamCount()
    {
        return _initParamList.size();
    } //-- int getInitParamCount() 

    /**
     * Returns the value of field 'portletClass'.
     * 
     * @return String
     * @return the value of field 'portletClass'.
     */
    public java.lang.String getPortletClass()
    {
        return this._portletClass;
    } //-- java.lang.String getPortletClass() 

    /**
     * Returns the value of field 'portletName'.
     * 
     * @return PortletName
     * @return the value of field 'portletName'.
     */
    public org.light.portal.portlet.definition.PortletName getPortletName()
    {
        return this._portletName;
    } //-- org.light.portlet.definition.PortletName getPortletName() 

    /**
     * Returns the value of field 'portletPreferences'.
     * 
     * @return PortletPreferences
     * @return the value of field 'portletPreferences'.
     */
    public org.light.portal.portlet.definition.PortletPreferences getPortletPreferences()
    {
        return this._portletPreferences;
    } //-- org.light.portlet.definition.PortletPreferences getPortletPreferences() 

    /**
     * Returns the value of field 'portletTypeChoice'.
     * 
     * @return PortletTypeChoice
     * @return the value of field 'portletTypeChoice'.
     */
    public org.light.portal.portlet.definition.PortletTypeChoice getPortletTypeChoice()
    {
        return this._portletTypeChoice;
    } //-- org.light.portlet.definition.PortletTypeChoice getPortletTypeChoice() 

    /**
     * Method getSecurityRoleRef
     * 
     * 
     * 
     * @param index
     * @return SecurityRoleRef
     */
    public org.light.portal.portlet.definition.SecurityRoleRef getSecurityRoleRef(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _securityRoleRefList.size())) {
            throw new IndexOutOfBoundsException("getSecurityRoleRef: Index value '"+index+"' not in range [0.."+(_securityRoleRefList.size() - 1) + "]");
        }
        
        return (org.light.portal.portlet.definition.SecurityRoleRef) _securityRoleRefList.elementAt(index);
    } //-- org.light.portlet.definition.SecurityRoleRef getSecurityRoleRef(int) 

    /**
     * Method getSecurityRoleRef
     * 
     * 
     * 
     * @return SecurityRoleRef
     */
    public org.light.portal.portlet.definition.SecurityRoleRef[] getSecurityRoleRef()
    {
        int size = _securityRoleRefList.size();
        org.light.portal.portlet.definition.SecurityRoleRef[] mArray = new org.light.portal.portlet.definition.SecurityRoleRef[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (org.light.portal.portlet.definition.SecurityRoleRef) _securityRoleRefList.elementAt(index);
        }
        return mArray;
    } //-- org.light.portlet.definition.SecurityRoleRef[] getSecurityRoleRef() 

    /**
     * Method getSecurityRoleRefCount
     * 
     * 
     * 
     * @return int
     */
    public int getSecurityRoleRefCount()
    {
        return _securityRoleRefList.size();
    } //-- int getSecurityRoleRefCount() 

    /**
     * Method getSupportedLocale
     * 
     * 
     * 
     * @param index
     * @return SupportedLocale
     */
    public org.light.portal.portlet.definition.SupportedLocale getSupportedLocale(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _supportedLocaleList.size())) {
            throw new IndexOutOfBoundsException("getSupportedLocale: Index value '"+index+"' not in range [0.."+(_supportedLocaleList.size() - 1) + "]");
        }
        
        return (org.light.portal.portlet.definition.SupportedLocale) _supportedLocaleList.elementAt(index);
    } //-- org.light.portlet.definition.SupportedLocale getSupportedLocale(int) 

    /**
     * Method getSupportedLocale
     * 
     * 
     * 
     * @return SupportedLocale
     */
    public org.light.portal.portlet.definition.SupportedLocale[] getSupportedLocale()
    {
        int size = _supportedLocaleList.size();
        org.light.portal.portlet.definition.SupportedLocale[] mArray = new org.light.portal.portlet.definition.SupportedLocale[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (org.light.portal.portlet.definition.SupportedLocale) _supportedLocaleList.elementAt(index);
        }
        return mArray;
    } //-- org.light.portlet.definition.SupportedLocale[] getSupportedLocale() 

    /**
     * Method getSupportedLocaleCount
     * 
     * 
     * 
     * @return int
     */
    public int getSupportedLocaleCount()
    {
        return _supportedLocaleList.size();
    } //-- int getSupportedLocaleCount() 

    /**
     * Method getSupports
     * 
     * 
     * 
     * @param index
     * @return Supports
     */
    public org.light.portal.portlet.definition.Supports getSupports(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _supportsList.size())) {
            throw new IndexOutOfBoundsException("getSupports: Index value '"+index+"' not in range [0.."+(_supportsList.size() - 1) + "]");
        }
        
        return (org.light.portal.portlet.definition.Supports) _supportsList.elementAt(index);
    } //-- org.light.portlet.definition.Supports getSupports(int) 

    /**
     * Method getSupports
     * 
     * 
     * 
     * @return Supports
     */
    public org.light.portal.portlet.definition.Supports[] getSupports()
    {
        int size = _supportsList.size();
        org.light.portal.portlet.definition.Supports[] mArray = new org.light.portal.portlet.definition.Supports[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (org.light.portal.portlet.definition.Supports) _supportsList.elementAt(index);
        }
        return mArray;
    } //-- org.light.portlet.definition.Supports[] getSupports() 

    /**
     * Method getSupportsCount
     * 
     * 
     * 
     * @return int
     */
    public int getSupportsCount()
    {
        return _supportsList.size();
    } //-- int getSupportsCount() 

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
     * Method removeAllDescription
     * 
     */
    public void removeAllDescription()
    {
        _descriptionList.removeAllElements();
    } //-- void removeAllDescription() 

    /**
     * Method removeAllDisplayName
     * 
     */
    public void removeAllDisplayName()
    {
        _displayNameList.removeAllElements();
    } //-- void removeAllDisplayName() 

    /**
     * Method removeAllInitParam
     * 
     */
    public void removeAllInitParam()
    {
        _initParamList.removeAllElements();
    } //-- void removeAllInitParam() 

    /**
     * Method removeAllSecurityRoleRef
     * 
     */
    public void removeAllSecurityRoleRef()
    {
        _securityRoleRefList.removeAllElements();
    } //-- void removeAllSecurityRoleRef() 

    /**
     * Method removeAllSupportedLocale
     * 
     */
    public void removeAllSupportedLocale()
    {
        _supportedLocaleList.removeAllElements();
    } //-- void removeAllSupportedLocale() 

    /**
     * Method removeAllSupports
     * 
     */
    public void removeAllSupports()
    {
        _supportsList.removeAllElements();
    } //-- void removeAllSupports() 

    /**
     * Method removeDescription
     * 
     * 
     * 
     * @param index
     * @return Description
     */
    public org.light.portal.portlet.definition.Description removeDescription(int index)
    {
        java.lang.Object obj = _descriptionList.elementAt(index);
        _descriptionList.removeElementAt(index);
        return (org.light.portal.portlet.definition.Description) obj;
    } //-- org.light.portlet.definition.Description removeDescription(int) 

    /**
     * Method removeDisplayName
     * 
     * 
     * 
     * @param index
     * @return DisplayName
     */
    public org.light.portal.portlet.definition.DisplayName removeDisplayName(int index)
    {
        java.lang.Object obj = _displayNameList.elementAt(index);
        _displayNameList.removeElementAt(index);
        return (org.light.portal.portlet.definition.DisplayName) obj;
    } //-- org.light.portlet.definition.DisplayName removeDisplayName(int) 

    /**
     * Method removeInitParam
     * 
     * 
     * 
     * @param index
     * @return InitParam
     */
    public org.light.portal.portlet.definition.InitParam removeInitParam(int index)
    {
        java.lang.Object obj = _initParamList.elementAt(index);
        _initParamList.removeElementAt(index);
        return (org.light.portal.portlet.definition.InitParam) obj;
    } //-- org.light.portlet.definition.InitParam removeInitParam(int) 

    /**
     * Method removeSecurityRoleRef
     * 
     * 
     * 
     * @param index
     * @return SecurityRoleRef
     */
    public org.light.portal.portlet.definition.SecurityRoleRef removeSecurityRoleRef(int index)
    {
        java.lang.Object obj = _securityRoleRefList.elementAt(index);
        _securityRoleRefList.removeElementAt(index);
        return (org.light.portal.portlet.definition.SecurityRoleRef) obj;
    } //-- org.light.portlet.definition.SecurityRoleRef removeSecurityRoleRef(int) 

    /**
     * Method removeSupportedLocale
     * 
     * 
     * 
     * @param index
     * @return SupportedLocale
     */
    public org.light.portal.portlet.definition.SupportedLocale removeSupportedLocale(int index)
    {
        java.lang.Object obj = _supportedLocaleList.elementAt(index);
        _supportedLocaleList.removeElementAt(index);
        return (org.light.portal.portlet.definition.SupportedLocale) obj;
    } //-- org.light.portlet.definition.SupportedLocale removeSupportedLocale(int) 

    /**
     * Method removeSupports
     * 
     * 
     * 
     * @param index
     * @return Supports
     */
    public org.light.portal.portlet.definition.Supports removeSupports(int index)
    {
        java.lang.Object obj = _supportsList.elementAt(index);
        _supportsList.removeElementAt(index);
        return (org.light.portal.portlet.definition.Supports) obj;
    } //-- org.light.portlet.definition.Supports removeSupports(int) 

    /**
     * Method setDescription
     * 
     * 
     * 
     * @param index
     * @param vDescription
     */
    public void setDescription(int index, org.light.portal.portlet.definition.Description vDescription)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _descriptionList.size())) {
            throw new IndexOutOfBoundsException("setDescription: Index value '"+index+"' not in range [0.." + (_descriptionList.size() - 1) + "]");
        }
        _descriptionList.setElementAt(vDescription, index);
    } //-- void setDescription(int, org.light.portlet.definition.Description) 

    /**
     * Method setDescription
     * 
     * 
     * 
     * @param descriptionArray
     */
    public void setDescription(org.light.portal.portlet.definition.Description[] descriptionArray)
    {
        //-- copy array
        _descriptionList.removeAllElements();
        for (int i = 0; i < descriptionArray.length; i++) {
            _descriptionList.addElement(descriptionArray[i]);
        }
    } //-- void setDescription(org.light.portlet.definition.Description) 

    /**
     * Method setDisplayName
     * 
     * 
     * 
     * @param index
     * @param vDisplayName
     */
    public void setDisplayName(int index, org.light.portal.portlet.definition.DisplayName vDisplayName)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _displayNameList.size())) {
            throw new IndexOutOfBoundsException("setDisplayName: Index value '"+index+"' not in range [0.." + (_displayNameList.size() - 1) + "]");
        }
        _displayNameList.setElementAt(vDisplayName, index);
    } //-- void setDisplayName(int, org.light.portlet.definition.DisplayName) 

    /**
     * Method setDisplayName
     * 
     * 
     * 
     * @param displayNameArray
     */
    public void setDisplayName(org.light.portal.portlet.definition.DisplayName[] displayNameArray)
    {
        //-- copy array
        _displayNameList.removeAllElements();
        for (int i = 0; i < displayNameArray.length; i++) {
            _displayNameList.addElement(displayNameArray[i]);
        }
    } //-- void setDisplayName(org.light.portlet.definition.DisplayName) 

    /**
     * Sets the value of field 'expirationCache'.
     * 
     * @param expirationCache the value of field 'expirationCache'.
     */
    public void setExpirationCache(org.light.portal.portlet.definition.ExpirationCache expirationCache)
    {
        this._expirationCache = expirationCache;
    } //-- void setExpirationCache(org.light.portlet.definition.ExpirationCache) 

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
     * Method setInitParam
     * 
     * 
     * 
     * @param index
     * @param vInitParam
     */
    public void setInitParam(int index, org.light.portal.portlet.definition.InitParam vInitParam)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _initParamList.size())) {
            throw new IndexOutOfBoundsException("setInitParam: Index value '"+index+"' not in range [0.." + (_initParamList.size() - 1) + "]");
        }
        _initParamList.setElementAt(vInitParam, index);
    } //-- void setInitParam(int, org.light.portlet.definition.InitParam) 

    /**
     * Method setInitParam
     * 
     * 
     * 
     * @param initParamArray
     */
    public void setInitParam(org.light.portal.portlet.definition.InitParam[] initParamArray)
    {
        //-- copy array
        _initParamList.removeAllElements();
        for (int i = 0; i < initParamArray.length; i++) {
            _initParamList.addElement(initParamArray[i]);
        }
    } //-- void setInitParam(org.light.portlet.definition.InitParam) 

    /**
     * Sets the value of field 'portletClass'.
     * 
     * @param portletClass the value of field 'portletClass'.
     */
    public void setPortletClass(java.lang.String portletClass)
    {
        this._portletClass = portletClass;
    } //-- void setPortletClass(java.lang.String) 

    /**
     * Sets the value of field 'portletName'.
     * 
     * @param portletName the value of field 'portletName'.
     */
    public void setPortletName(org.light.portal.portlet.definition.PortletName portletName)
    {
        this._portletName = portletName;
    } //-- void setPortletName(org.light.portlet.definition.PortletName) 

    /**
     * Sets the value of field 'portletPreferences'.
     * 
     * @param portletPreferences the value of field
     * 'portletPreferences'.
     */
    public void setPortletPreferences(org.light.portal.portlet.definition.PortletPreferences portletPreferences)
    {
        this._portletPreferences = portletPreferences;
    } //-- void setPortletPreferences(org.light.portlet.definition.PortletPreferences) 

    /**
     * Sets the value of field 'portletTypeChoice'.
     * 
     * @param portletTypeChoice the value of field
     * 'portletTypeChoice'.
     */
    public void setPortletTypeChoice(org.light.portal.portlet.definition.PortletTypeChoice portletTypeChoice)
    {
        this._portletTypeChoice = portletTypeChoice;
    } //-- void setPortletTypeChoice(org.light.portlet.definition.PortletTypeChoice) 

    /**
     * Method setSecurityRoleRef
     * 
     * 
     * 
     * @param index
     * @param vSecurityRoleRef
     */
    public void setSecurityRoleRef(int index, org.light.portal.portlet.definition.SecurityRoleRef vSecurityRoleRef)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _securityRoleRefList.size())) {
            throw new IndexOutOfBoundsException("setSecurityRoleRef: Index value '"+index+"' not in range [0.." + (_securityRoleRefList.size() - 1) + "]");
        }
        _securityRoleRefList.setElementAt(vSecurityRoleRef, index);
    } //-- void setSecurityRoleRef(int, org.light.portlet.definition.SecurityRoleRef) 

    /**
     * Method setSecurityRoleRef
     * 
     * 
     * 
     * @param securityRoleRefArray
     */
    public void setSecurityRoleRef(org.light.portal.portlet.definition.SecurityRoleRef[] securityRoleRefArray)
    {
        //-- copy array
        _securityRoleRefList.removeAllElements();
        for (int i = 0; i < securityRoleRefArray.length; i++) {
            _securityRoleRefList.addElement(securityRoleRefArray[i]);
        }
    } //-- void setSecurityRoleRef(org.light.portlet.definition.SecurityRoleRef) 

    /**
     * Method setSupportedLocale
     * 
     * 
     * 
     * @param index
     * @param vSupportedLocale
     */
    public void setSupportedLocale(int index, org.light.portal.portlet.definition.SupportedLocale vSupportedLocale)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _supportedLocaleList.size())) {
            throw new IndexOutOfBoundsException("setSupportedLocale: Index value '"+index+"' not in range [0.." + (_supportedLocaleList.size() - 1) + "]");
        }
        _supportedLocaleList.setElementAt(vSupportedLocale, index);
    } //-- void setSupportedLocale(int, org.light.portlet.definition.SupportedLocale) 

    /**
     * Method setSupportedLocale
     * 
     * 
     * 
     * @param supportedLocaleArray
     */
    public void setSupportedLocale(org.light.portal.portlet.definition.SupportedLocale[] supportedLocaleArray)
    {
        //-- copy array
        _supportedLocaleList.removeAllElements();
        for (int i = 0; i < supportedLocaleArray.length; i++) {
            _supportedLocaleList.addElement(supportedLocaleArray[i]);
        }
    } //-- void setSupportedLocale(org.light.portlet.definition.SupportedLocale) 

    /**
     * Method setSupports
     * 
     * 
     * 
     * @param index
     * @param vSupports
     */
    public void setSupports(int index, org.light.portal.portlet.definition.Supports vSupports)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _supportsList.size())) {
            throw new IndexOutOfBoundsException("setSupports: Index value '"+index+"' not in range [0.." + (_supportsList.size() - 1) + "]");
        }
        _supportsList.setElementAt(vSupports, index);
    } //-- void setSupports(int, org.light.portlet.definition.Supports) 

    /**
     * Method setSupports
     * 
     * 
     * 
     * @param supportsArray
     */
    public void setSupports(org.light.portal.portlet.definition.Supports[] supportsArray)
    {
        //-- copy array
        _supportsList.removeAllElements();
        for (int i = 0; i < supportsArray.length; i++) {
            _supportsList.addElement(supportsArray[i]);
        }
    } //-- void setSupports(org.light.portlet.definition.Supports) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return PortletType
     */
    public static org.light.portal.portlet.definition.PortletType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (org.light.portal.portlet.definition.PortletType) Unmarshaller.unmarshal(org.light.portal.portlet.definition.PortletType.class, reader);
    } //-- org.light.portlet.definition.PortletType unmarshal(java.io.Reader) 

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
