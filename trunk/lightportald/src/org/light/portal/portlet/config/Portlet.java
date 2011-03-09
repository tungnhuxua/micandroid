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
 * Class Portlet.
 * 
 * @version $Revision$ $Date$
 */
public class Portlet implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _name
     */
    private java.lang.String _name;

    /**
     * Field _path
     */
    private java.lang.String _path;

    /**
     * Field _title
     */
    private java.lang.String _title;

    /**
     * Field _icon
     */
    private java.lang.String _icon;
    
    /**
     * Field _icon
     */
    private java.lang.String _iconCssSprite;
    
    /**
     * Field _url
     */
    private java.lang.String _url;

    /**
     * Field _subTag
     */
    private java.lang.String _subTag;

    /**
     * Field _tag
     */
    private java.lang.String _tag;

    /**
     * Field _language
     */
    private java.lang.String _language;

    /**
     * Field _refreshMode
     */
    private boolean _refreshMode;

    /**
     * keeps track of state for field: _refreshMode
     */
    private boolean _has_refreshMode;

    /**
     * Field _editMode
     */
    private boolean _editMode;

    /**
     * keeps track of state for field: _editMode
     */
    private boolean _has_editMode;

    /**
     * Field _helpMode
     */
    private boolean _helpMode;

    /**
     * keeps track of state for field: _helpMode
     */
    private boolean _has_helpMode;

    /**
     * Field _configMode
     */
    private boolean _configMode;

    /**
     * keeps track of state for field: _configMode
     */
    private boolean _has_configMode;
    
    /**
     * Field __minimized
     */
    private boolean _minimized;

    /**
     * keeps track of state for field: _minimized
     */
    private boolean _has_minimized;
    
    /**
     * Field __maximized
     */
    private boolean _maximized;

    /**
     * keeps track of state for field: _maximized
     */
    private boolean _has_maximized;
    
    /**
     * Field _windowSkin
     */
    private java.lang.String _windowSkin;
    /**
     * Field _autoRefreshed
     */
    private boolean _autoRefreshed;

    /**
     * keeps track of state for field: _autoRefreshed
     */
    private boolean _has_autoRefreshed;

    /**
     * Field _periodTime
     */
    private int _periodTime;

    /**
     * keeps track of state for field: _periodTime
     */
    private boolean _has_periodTime;

    /**
     * Field _allowJS
     */
    private boolean _allowJS;

    /**
     * keeps track of state for field: _allowJS
     */
    private boolean _has_allowJS;

    /**
     * Field _pageRefreshed
     */
    private boolean _pageRefreshed;

    /**
     * keeps track of state for field: _pageRefreshed
     */
    private boolean _has_pageRefreshed;

    /**
     * Field _showNumber
     */
    private int _showNumber;

    /**
     * keeps track of state for field: _showNumber
     */
    private boolean _has_showNumber;

    /**
     * Field _showType
     */
    private int _showType;

    /**
     * keeps track of state for field: _showType
     */
    private boolean _has_showType;

    /**
     * Field _windowStatus
     */
    private int _windowStatus;

    /**
     * keeps track of state for field: _windowStatus
     */
    private boolean _has_windowStatus;

    /**
     * Field _mode
     */
    private int _mode;

    /**
     * keeps track of state for field: _mode
     */
    private boolean _has_mode;

    /**
     * Field _type
     */
    private int _type;

    /**
     * keeps track of state for field: _type
     */
    private boolean _has_type;

    /**
     * Field _parameterList
     */
    private java.util.Vector _parameterList;

    /**
     * Field _roles
     */
    private org.light.portal.portlet.config.Roles _roles;


      //----------------/
     //- Constructors -/
    //----------------/

    public Portlet() 
     {
        super();
        _parameterList = new Vector();
    } //-- org.light.portlet.config.Portlet()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addParameter
     * 
     * 
     * 
     * @param vParameter
     */
    public void addParameter(org.light.portal.portlet.config.Parameter vParameter)
        throws java.lang.IndexOutOfBoundsException
    {
        _parameterList.addElement(vParameter);
    } //-- void addParameter(org.light.portlet.config.Parameter) 

    /**
     * Method addParameter
     * 
     * 
     * 
     * @param index
     * @param vParameter
     */
    public void addParameter(int index, org.light.portal.portlet.config.Parameter vParameter)
        throws java.lang.IndexOutOfBoundsException
    {
        _parameterList.insertElementAt(vParameter, index);
    } //-- void addParameter(int, org.light.portlet.config.Parameter) 

    /**
     * Method deleteAllowJS
     * 
     */
    public void deleteAllowJS()
    {
        this._has_allowJS= false;
    } //-- void deleteAllowJS() 

    /**
     * Method deleteAutoRefreshed
     * 
     */
    public void deleteAutoRefreshed()
    {
        this._has_autoRefreshed= false;
    } //-- void deleteAutoRefreshed() 

    /**
     * Method deleteConfigMode
     * 
     */
    public void deleteConfigMode()
    {
        this._has_configMode= false;
    } //-- void deleteConfigMode() 

    /**
     * Method deleteEditMode
     * 
     */
    public void deleteEditMode()
    {
        this._has_editMode= false;
    } //-- void deleteEditMode() 

    /**
     * Method deleteHelpMode
     * 
     */
    public void deleteHelpMode()
    {
        this._has_helpMode= false;
    } //-- void deleteHelpMode() 

    /**
     * Method deleteMode
     * 
     */
    public void deleteMode()
    {
        this._has_mode= false;
    } //-- void deleteMode() 

    /**
     * Method deletePageRefreshed
     * 
     */
    public void deletePageRefreshed()
    {
        this._has_pageRefreshed= false;
    } //-- void deletePageRefreshed() 

    /**
     * Method deletePeriodTime
     * 
     */
    public void deletePeriodTime()
    {
        this._has_periodTime= false;
    } //-- void deletePeriodTime() 

    /**
     * Method deleteRefreshMode
     * 
     */
    public void deleteRefreshMode()
    {
        this._has_refreshMode= false;
    } //-- void deleteRefreshMode() 

    /**
     * Method deleteShowNumber
     * 
     */
    public void deleteShowNumber()
    {
        this._has_showNumber= false;
    } //-- void deleteShowNumber() 

    /**
     * Method deleteShowType
     * 
     */
    public void deleteShowType()
    {
        this._has_showType= false;
    } //-- void deleteShowType() 

    /**
     * Method deleteType
     * 
     */
    public void deleteType()
    {
        this._has_type= false;
    } //-- void deleteType() 

    /**
     * Method deleteWindowStatus
     * 
     */
    public void deleteWindowStatus()
    {
        this._has_windowStatus= false;
    } //-- void deleteWindowStatus() 

    /**
     * Method enumerateParameter
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateParameter()
    {
        return _parameterList.elements();
    } //-- java.util.Enumeration enumerateParameter() 

    /**
     * Returns the value of field 'allowJS'.
     * 
     * @return boolean
     * @return the value of field 'allowJS'.
     */
    public boolean getAllowJS()
    {
        return this._allowJS;
    } //-- boolean getAllowJS() 

    /**
     * Returns the value of field 'autoRefreshed'.
     * 
     * @return boolean
     * @return the value of field 'autoRefreshed'.
     */
    public boolean getAutoRefreshed()
    {
        return this._autoRefreshed;
    } //-- boolean getAutoRefreshed() 

    /**
     * Returns the value of field 'configMode'.
     * 
     * @return boolean
     * @return the value of field 'configMode'.
     */
    public boolean getConfigMode()
    {
        return this._configMode;
    } //-- boolean getConfigMode() 

    /**
     * Returns the value of field 'editMode'.
     * 
     * @return boolean
     * @return the value of field 'editMode'.
     */
    public boolean getEditMode()
    {
        return this._editMode;
    } //-- boolean getEditMode() 

    /**
     * Returns the value of field 'helpMode'.
     * 
     * @return boolean
     * @return the value of field 'helpMode'.
     */
    public boolean getHelpMode()
    {
        return this._helpMode;
    } //-- boolean getHelpMode() 

    public boolean getMinimized()
    {
        return this._minimized;
    }
    
    public boolean getMaximized()
    {
        return this._maximized;
    }
    
    /**
     * Returns the value of field 'icon'.
     * 
     * @return String
     * @return the value of field 'icon'.
     */
    public java.lang.String getIcon()
    {
        return this._icon;
    } //-- java.lang.String getIcon() 
    
    /**
     * Returns the value of field 'iconCssSprite'.
     * 
     * @return String
     * @return the value of field 'iconCssSprite'.
     */
    public java.lang.String getIconCssSprite()
    {
        return this._iconCssSprite;
    } //-- java.lang.String getIconCssSprite() 
    
    /**
     * Returns the value of field 'language'.
     * 
     * @return String
     * @return the value of field 'language'.
     */
    public java.lang.String getLanguage()
    {
        return this._language;
    } //-- java.lang.String getLanguage() 

    public java.lang.String getWindowSkin()
    {
        return this._windowSkin;
    } 
    
    /**
     * Returns the value of field 'mode'.
     * 
     * @return int
     * @return the value of field 'mode'.
     */
    public int getMode()
    {
        return this._mode;
    } //-- int getMode() 

    /**
     * Returns the value of field 'name'.
     * 
     * @return String
     * @return the value of field 'name'.
     */
    public java.lang.String getName()
    {
        return this._name;
    } //-- java.lang.String getName() 

    /**
     * Returns the value of field 'pageRefreshed'.
     * 
     * @return boolean
     * @return the value of field 'pageRefreshed'.
     */
    public boolean getPageRefreshed()
    {
        return this._pageRefreshed;
    } //-- boolean getPageRefreshed() 

    /**
     * Method getParameter
     * 
     * 
     * 
     * @param index
     * @return Parameter
     */
    public org.light.portal.portlet.config.Parameter getParameter(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _parameterList.size())) {
            throw new IndexOutOfBoundsException("getParameter: Index value '"+index+"' not in range [0.."+(_parameterList.size() - 1) + "]");
        }
        
        return (org.light.portal.portlet.config.Parameter) _parameterList.elementAt(index);
    } //-- org.light.portlet.config.Parameter getParameter(int) 

    /**
     * Method getParameter
     * 
     * 
     * 
     * @return Parameter
     */
    public org.light.portal.portlet.config.Parameter[] getParameter()
    {
        int size = _parameterList.size();
        org.light.portal.portlet.config.Parameter[] mArray = new org.light.portal.portlet.config.Parameter[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (org.light.portal.portlet.config.Parameter) _parameterList.elementAt(index);
        }
        return mArray;
    } //-- org.light.portlet.config.Parameter[] getParameter() 

    /**
     * Method getParameterCount
     * 
     * 
     * 
     * @return int
     */
    public int getParameterCount()
    {
        return _parameterList.size();
    } //-- int getParameterCount() 

    /**
     * Returns the value of field 'path'.
     * 
     * @return String
     * @return the value of field 'path'.
     */
    public java.lang.String getPath()
    {
        return this._path;
    } //-- java.lang.String getPath() 

    /**
     * Returns the value of field 'periodTime'.
     * 
     * @return int
     * @return the value of field 'periodTime'.
     */
    public int getPeriodTime()
    {
        return this._periodTime;
    } //-- int getPeriodTime() 

    /**
     * Returns the value of field 'refreshMode'.
     * 
     * @return boolean
     * @return the value of field 'refreshMode'.
     */
    public boolean getRefreshMode()
    {
        return this._refreshMode;
    } //-- boolean getRefreshMode() 

    /**
     * Returns the value of field 'roles'.
     * 
     * @return Roles
     * @return the value of field 'roles'.
     */
    public org.light.portal.portlet.config.Roles getRoles()
    {
        return this._roles;
    } //-- org.light.portlet.config.Roles getRoles() 

    /**
     * Returns the value of field 'showNumber'.
     * 
     * @return int
     * @return the value of field 'showNumber'.
     */
    public int getShowNumber()
    {
        return this._showNumber;
    } //-- int getShowNumber() 

    /**
     * Returns the value of field 'showType'.
     * 
     * @return int
     * @return the value of field 'showType'.
     */
    public int getShowType()
    {
        return this._showType;
    } //-- int getShowType() 

    /**
     * Returns the value of field 'subTag'.
     * 
     * @return String
     * @return the value of field 'subTag'.
     */
    public java.lang.String getSubTag()
    {
        return this._subTag;
    } //-- java.lang.String getSubTag() 

    /**
     * Returns the value of field 'tag'.
     * 
     * @return String
     * @return the value of field 'tag'.
     */
    public java.lang.String getTag()
    {
        return this._tag;
    } //-- java.lang.String getTag() 

    /**
     * Returns the value of field 'title'.
     * 
     * @return String
     * @return the value of field 'title'.
     */
    public java.lang.String getTitle()
    {
        return this._title;
    } //-- java.lang.String getTitle() 

    /**
     * Returns the value of field 'type'.
     * 
     * @return int
     * @return the value of field 'type'.
     */
    public int getType()
    {
        return this._type;
    } //-- int getType() 

    /**
     * Returns the value of field 'url'.
     * 
     * @return String
     * @return the value of field 'url'.
     */
    public java.lang.String getUrl()
    {
        return this._url;
    } //-- java.lang.String getUrl() 

    /**
     * Returns the value of field 'windowStatus'.
     * 
     * @return int
     * @return the value of field 'windowStatus'.
     */
    public int getWindowStatus()
    {
        return this._windowStatus;
    } //-- int getWindowStatus() 

    /**
     * Method hasAllowJS
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasAllowJS()
    {
        return this._has_allowJS;
    } //-- boolean hasAllowJS() 

    /**
     * Method hasAutoRefreshed
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasAutoRefreshed()
    {
        return this._has_autoRefreshed;
    } //-- boolean hasAutoRefreshed() 

    /**
     * Method hasConfigMode
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasConfigMode()
    {
        return this._has_configMode;
    } //-- boolean hasConfigMode() 

    /**
     * Method hasEditMode
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasEditMode()
    {
        return this._has_editMode;
    } //-- boolean hasEditMode() 

    /**
     * Method hasHelpMode
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasHelpMode()
    {
        return this._has_helpMode;
    } //-- boolean hasHelpMode() 

    /**
     * Method maximized
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasMaximized()
    {
        return this._has_maximized;
    } //-- boolean hasMaximized() 

    /**
     * Method minimized
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasMinimized()
    {
        return this._has_minimized;
    } //-- boolean hasMinimized() 
    
    /**
     * Method hasMode
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasMode()
    {
        return this._has_mode;
    } //-- boolean hasMode() 

    /**
     * Method hasPageRefreshed
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasPageRefreshed()
    {
        return this._has_pageRefreshed;
    } //-- boolean hasPageRefreshed() 

    /**
     * Method hasPeriodTime
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasPeriodTime()
    {
        return this._has_periodTime;
    } //-- boolean hasPeriodTime() 

    /**
     * Method hasRefreshMode
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasRefreshMode()
    {
        return this._has_refreshMode;
    } //-- boolean hasRefreshMode() 

    /**
     * Method hasShowNumber
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasShowNumber()
    {
        return this._has_showNumber;
    } //-- boolean hasShowNumber() 

    /**
     * Method hasShowType
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasShowType()
    {
        return this._has_showType;
    } //-- boolean hasShowType() 

    /**
     * Method hasType
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasType()
    {
        return this._has_type;
    } //-- boolean hasType() 

    /**
     * Method hasWindowStatus
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasWindowStatus()
    {
        return this._has_windowStatus;
    } //-- boolean hasWindowStatus() 

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
     * Method removeAllParameter
     * 
     */
    public void removeAllParameter()
    {
        _parameterList.removeAllElements();
    } //-- void removeAllParameter() 

    /**
     * Method removeParameter
     * 
     * 
     * 
     * @param index
     * @return Parameter
     */
    public org.light.portal.portlet.config.Parameter removeParameter(int index)
    {
        java.lang.Object obj = _parameterList.elementAt(index);
        _parameterList.removeElementAt(index);
        return (org.light.portal.portlet.config.Parameter) obj;
    } //-- org.light.portlet.config.Parameter removeParameter(int) 

    /**
     * Sets the value of field 'allowJS'.
     * 
     * @param allowJS the value of field 'allowJS'.
     */
    public void setAllowJS(boolean allowJS)
    {
        this._allowJS = allowJS;
        this._has_allowJS = true;
    } //-- void setAllowJS(boolean) 

    /**
     * Sets the value of field 'autoRefreshed'.
     * 
     * @param autoRefreshed the value of field 'autoRefreshed'.
     */
    public void setAutoRefreshed(boolean autoRefreshed)
    {
        this._autoRefreshed = autoRefreshed;
        this._has_autoRefreshed = true;
    } //-- void setAutoRefreshed(boolean) 

    /**
     * Sets the value of field 'configMode'.
     * 
     * @param configMode the value of field 'configMode'.
     */
    public void setConfigMode(boolean configMode)
    {
        this._configMode = configMode;
        this._has_configMode = true;
    } //-- void setConfigMode(boolean) 

    /**
     * Sets the value of field 'editMode'.
     * 
     * @param editMode the value of field 'editMode'.
     */
    public void setEditMode(boolean editMode)
    {
        this._editMode = editMode;
        this._has_editMode = true;
    } //-- void setEditMode(boolean) 

    /**
     * Sets the value of field 'helpMode'.
     * 
     * @param helpMode the value of field 'helpMode'.
     */
    public void setHelpMode(boolean helpMode)
    {
        this._helpMode = helpMode;
        this._has_helpMode = true;
    } //-- void setHelpMode(boolean) 

    /**
     * Sets the value of field 'minimized'.
     * 
     * @param helpMode the value of field 'minimized'.
     */
    public void setMinimized(boolean minimized)
    {
        this._minimized = minimized;
        this._has_minimized = true;
    } //-- void setMinimized(boolean) 
    
    /**
     * Sets the value of field 'maximized'.
     * 
     * @param helpMode the value of field 'maximized'.
     */
    public void setMaximized(boolean maximized)
    {
        this._maximized = maximized;
        this._has_maximized = true;
    } //-- void setMaximized(boolean) 
    
    /**
     * Sets the value of field 'icon'.
     * 
     * @param icon the value of field 'icon'.
     */
    public void setIcon(java.lang.String icon)
    {
        this._icon = icon;
    } //-- void setIcon(java.lang.String) 
    
    /**
     * Sets the value of field 'iconCssSprite'.
     * 
     * @param icon the value of field 'iconCssSprite'.
     */
    public void setIconCssSprite(java.lang.String iconCssSprite)
    {
        this._iconCssSprite = iconCssSprite;
    } //-- void setIconCssSprite(java.lang.String) 
    
    /**
     * Sets the value of field 'language'.
     * 
     * @param language the value of field 'language'.
     */
    public void setLanguage(java.lang.String language)
    {
        this._language = language;
    } //-- void setLanguage(java.lang.String) 
    
    public void setWindowSkin(java.lang.String windowSkin)
    {
        this._windowSkin = windowSkin;
    } 
    /**
     * Sets the value of field 'mode'.
     * 
     * @param mode the value of field 'mode'.
     */
    public void setMode(int mode)
    {
        this._mode = mode;
        this._has_mode = true;
    } //-- void setMode(int) 

    /**
     * Sets the value of field 'name'.
     * 
     * @param name the value of field 'name'.
     */
    public void setName(java.lang.String name)
    {
        this._name = name;
    } //-- void setName(java.lang.String) 

    /**
     * Sets the value of field 'pageRefreshed'.
     * 
     * @param pageRefreshed the value of field 'pageRefreshed'.
     */
    public void setPageRefreshed(boolean pageRefreshed)
    {
        this._pageRefreshed = pageRefreshed;
        this._has_pageRefreshed = true;
    } //-- void setPageRefreshed(boolean) 

    /**
     * Method setParameter
     * 
     * 
     * 
     * @param index
     * @param vParameter
     */
    public void setParameter(int index, org.light.portal.portlet.config.Parameter vParameter)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _parameterList.size())) {
            throw new IndexOutOfBoundsException("setParameter: Index value '"+index+"' not in range [0.." + (_parameterList.size() - 1) + "]");
        }
        _parameterList.setElementAt(vParameter, index);
    } //-- void setParameter(int, org.light.portlet.config.Parameter) 

    /**
     * Method setParameter
     * 
     * 
     * 
     * @param parameterArray
     */
    public void setParameter(org.light.portal.portlet.config.Parameter[] parameterArray)
    {
        //-- copy array
        _parameterList.removeAllElements();
        for (int i = 0; i < parameterArray.length; i++) {
            _parameterList.addElement(parameterArray[i]);
        }
    } //-- void setParameter(org.light.portlet.config.Parameter) 

    /**
     * Sets the value of field 'path'.
     * 
     * @param path the value of field 'path'.
     */
    public void setPath(java.lang.String path)
    {
        this._path = path;
    } //-- void setPath(java.lang.String) 

    /**
     * Sets the value of field 'periodTime'.
     * 
     * @param periodTime the value of field 'periodTime'.
     */
    public void setPeriodTime(int periodTime)
    {
        this._periodTime = periodTime;
        this._has_periodTime = true;
    } //-- void setPeriodTime(int) 

    /**
     * Sets the value of field 'refreshMode'.
     * 
     * @param refreshMode the value of field 'refreshMode'.
     */
    public void setRefreshMode(boolean refreshMode)
    {
        this._refreshMode = refreshMode;
        this._has_refreshMode = true;
    } //-- void setRefreshMode(boolean) 

    /**
     * Sets the value of field 'roles'.
     * 
     * @param roles the value of field 'roles'.
     */
    public void setRoles(org.light.portal.portlet.config.Roles roles)
    {
        this._roles = roles;
    } //-- void setRoles(org.light.portlet.config.Roles) 

    /**
     * Sets the value of field 'showNumber'.
     * 
     * @param showNumber the value of field 'showNumber'.
     */
    public void setShowNumber(int showNumber)
    {
        this._showNumber = showNumber;
        this._has_showNumber = true;
    } //-- void setShowNumber(int) 

    /**
     * Sets the value of field 'showType'.
     * 
     * @param showType the value of field 'showType'.
     */
    public void setShowType(int showType)
    {
        this._showType = showType;
        this._has_showType = true;
    } //-- void setShowType(int) 

    /**
     * Sets the value of field 'subTag'.
     * 
     * @param subTag the value of field 'subTag'.
     */
    public void setSubTag(java.lang.String subTag)
    {
        this._subTag = subTag;
    } //-- void setSubTag(java.lang.String) 

    /**
     * Sets the value of field 'tag'.
     * 
     * @param tag the value of field 'tag'.
     */
    public void setTag(java.lang.String tag)
    {
        this._tag = tag;
    } //-- void setTag(java.lang.String) 

    /**
     * Sets the value of field 'title'.
     * 
     * @param title the value of field 'title'.
     */
    public void setTitle(java.lang.String title)
    {
        this._title = title;
    } //-- void setTitle(java.lang.String) 

    /**
     * Sets the value of field 'type'.
     * 
     * @param type the value of field 'type'.
     */
    public void setType(int type)
    {
        this._type = type;
        this._has_type = true;
    } //-- void setType(int) 

    /**
     * Sets the value of field 'url'.
     * 
     * @param url the value of field 'url'.
     */
    public void setUrl(java.lang.String url)
    {
        this._url = url;
    } //-- void setUrl(java.lang.String) 

    /**
     * Sets the value of field 'windowStatus'.
     * 
     * @param windowStatus the value of field 'windowStatus'.
     */
    public void setWindowStatus(int windowStatus)
    {
        this._windowStatus = windowStatus;
        this._has_windowStatus = true;
    } //-- void setWindowStatus(int) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Portlet
     */
    public static org.light.portal.portlet.config.Portlet unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (org.light.portal.portlet.config.Portlet) Unmarshaller.unmarshal(org.light.portal.portlet.config.Portlet.class, reader);
    } //-- org.light.portlet.config.Portlet unmarshal(java.io.Reader) 

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
