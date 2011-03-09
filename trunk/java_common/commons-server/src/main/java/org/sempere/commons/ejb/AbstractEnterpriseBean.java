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

import org.sempere.commons.SystemException;

import javax.ejb.EJBContext;
import javax.ejb.EnterpriseBean;

/**
 * Base class for any enterprise beans.
 *
 * @author bsempere
 */
public abstract class AbstractEnterpriseBean implements EnterpriseBean {

    private static final long serialVersionUID = -7190796073750926196L;

    // EJB context reference
    protected EJBContext context;

    // *************************************************************************
    //
    // Constructors
    //
    // *************************************************************************

    public AbstractEnterpriseBean() {
    }

    // ********************************************************************************
    //
    // Callback methods
    //
    // ********************************************************************************

    public void ejbRemove() {
        // Nothing to do here
    }

    // ********************************************************************************
    //
    // Context methods
    //
    // ********************************************************************************

    /**
     * Rollbacks the current transaction in a JTA mode
     */
    public void rollbackJTA() {
        this.context.setRollbackOnly();
    }

    /**
     * Rollbacks the current transaction in a non JTA mode
     */
    public void rollbackNonJTA() {
        try {
            this.context.getUserTransaction().rollback();
        } catch (Exception e) {
            throw new SystemException("Cannot mark the current transaction as roolback.", e);
        }
    }

    public void setContext(EJBContext context) {
        this.context = context;
    }
}