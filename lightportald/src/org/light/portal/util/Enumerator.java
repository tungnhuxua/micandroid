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

package org.light.portal.util;

/**
 * 
 * @author Jianmin Liu
 **/
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;


/**
 * Uitlity class to wraps an <code>Enumeration</code> around a
 * Collection, i.e. <code>Iterator</code> classes.
 */

public final class Enumerator implements Enumeration
{


    // Iterator over which the Enumeration takes place
    private Iterator iterator = null;


    /**
     * Returns an Enumeration over the specified Collection.
     *
     * @param collection Collection with values that should be enumerated
     */
    public Enumerator(Collection collection)
    {
        this(collection.iterator());
    }


    /**
     * Returns an Enumeration over the values of the
     * specified Iterator.
     *
     * @param iterator Iterator to be wrapped
     */
    public Enumerator(Iterator iterator)
    {
        super();
        this.iterator = iterator;
    }


    /**
     * Returns an Enumeration over the values of the specified Map.
     *
     * @param map Map with values that should be enumerated
     */
    public Enumerator(Map map)
    {
        this(map.values().iterator());
    }



    /**
     * Tests if this enumeration contains more elements.
     *
     * @return <code>true</code> if this enumeration contains at 
     *          least one more element to provide,
     *          <code>false</code> otherwise.
     */
    public boolean hasMoreElements()
    {
        return(iterator.hasNext());
    }


    /**
     * Returns the next element of this enumeration.
     *
     * @return the next element of this enumeration
     *
     * @exception NoSuchElementException if no more elements exist
     */
    public Object nextElement() throws NoSuchElementException {
        return(iterator.next());
    }


}
