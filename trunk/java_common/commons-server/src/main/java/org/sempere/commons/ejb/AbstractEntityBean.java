/*
 * =============================================================================
 * Copyright by Benjamin Sempere,
 * All rights reserved.
 * =============================================================================
 * Author  : Benjamin Sempere
 * E-mail  : benjamin@sempere.org
 * Homepage: www.sempere.org
 * =============================================================================
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.sempere.commons.ejb;

import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import java.rmi.RemoteException;

/**
 * Base class for any entity beans.
 *
 * @author bsempere
 */
public abstract class AbstractEntityBean extends AbstractEnterpriseBean implements EntityBean {

    private static final long serialVersionUID = 8650200809496659545L;

    // *************************************************************************
    //
    // Constructors
    //
    // *************************************************************************

    public AbstractEntityBean() {
    }

    // ********************************************************************************
    //
    // Callback methods
    //
    // ********************************************************************************

    public void ejbActivate() {
        // Nothing to do here
    }

    public void ejbPassivate() {
        // Nothing to do here
    }

    public void ejbLoad() {
        // Nothing to do here
    }

    public void ejbStore() {
        // Nothing to do here
    }

    // ********************************************************************************
    //
    // Context methods
    //
    // ********************************************************************************

    public void setEntityContext(EntityContext context) {
        this.setContext(context);
    }

    public void unsetEntityContext() throws EJBException, RemoteException {
        this.setContext(null);
    }
}