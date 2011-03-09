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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 ** <CODE>StringUtils</CODE> hosts a couple of utility methods around
 ** strings.
 *
 * @author Jianmin Liu
 **/


public class StringUtil
{

    static java.util.BitSet dontNeedEncoding;
    static final int caseDiff = ('a' - 'A');

    /* The list of characters that are not encoded have been determined by
       referencing O'Reilly's "HTML: The Definitive Guide" (page 164). */

    static {
        dontNeedEncoding = new java.util.BitSet(256);
        int i;
        for (i = 'a'; i <= 'z'; i++)
        {
            dontNeedEncoding.set(i);
        }
        for (i = 'A'; i <= 'Z'; i++)
        {
            dontNeedEncoding.set(i);
        }
        for (i = '0'; i <= '9'; i++)
        {
            dontNeedEncoding.set(i);
        }
        dontNeedEncoding.set('-');
        dontNeedEncoding.set('_');
        dontNeedEncoding.set('.');
        dontNeedEncoding.set('*');
    }

    /**
     ** The operating system's line separator ('\n' on UNIX, '\r\n' on Windows)
     **/

    public static final String lineSeparator = System.getProperty("line.separator");

    /**
     ** Returns the name of the package of the specified class.
     ** The package will not include the common (short) name of the
     ** class or the file extension.
     **
     ** @param   aClass
     **          a class object
     **
     ** @return   its package
     **/

    public static String packageOf (Class aClass)
    {
        if (aClass == null)
        {
            throw (new IllegalArgumentException ("StringUtils: Argument \"aClass\" cannot be null."));
        }

        String result = "";

        int index = aClass.getName ().lastIndexOf (".");

        if (index >= 0)
        {
            result = aClass.getName ().substring (0, index);
        }

        return(result);
    }

    /**
     * Returns the short name of the specified class.
     * The name will not include the package name or file extension.
     *
     * @param   aClass
     *          a class object
     *
     * @return   its name
     */

    public static String nameOf (Class aClass)
    {
        if (aClass == null)
        {
            throw new IllegalArgumentException ("StringUtils: Argument \"aClass\" cannot be null.");
        }

        String className = aClass.getName ();

        int index = className.lastIndexOf (".");

        if (index >= 0)
        {
            className = className.substring (index + 1);
        }

        return(className);
    }

    /**
     * Returns a combination of two paths, inserting slashes as appropriate.
     *
     * @param   aRoot
     *          a root path
     * @param   aPath
     *          a path
     *
     * @return   the path
     */

    public static String pathOf (String aRoot, String aPath)
    {
        if (aPath == null)
        {
            throw new IllegalArgumentException ("StringUtils: Argument \"aPath\" cannot be null.");
        }

        String result = null;

        if (aPath.startsWith ("/")  ||
            aPath.startsWith ("\\") ||
            (aPath.length () >= 2 && aPath.charAt (1) == ':'))
        {
            result = aPath;
        }
        else
        {
            if (aRoot == null)
            {
                throw new IllegalArgumentException ("StringUtils: Argument \"aRoot\" cannot be null.");
            }

            StringBuffer temp = new StringBuffer (aRoot);

            if (! aRoot.endsWith ("/") &&
                ! aRoot.endsWith ("\\"))
            {
                temp.append ('/');
            }

            temp.append (aPath);

            result = temp.toString ();
        }

        return result.toString();
    }

    /**
     * Returns a <CODE>Boolean</CODE> object that corresponds the given value.
     * A value of <CODE>true</CODE> or <CODE>yes</CODE> corresponds to
     * <CODE>Boolean.TRUE</CODE> and a value of <CODE>false</CODE> or
     * <CODE>no</CODE> corresponds to <CODE>Boolean.FALSE</CODE>.
     * The comparions is case-insensitive, but for performance reasons,
     * lower-case values of <CODE>true</CODE> and <CODE>false</CODE>
     * should be used.
     *
     * @param   aValue
     *          to value to convert
     *
     * @return   the boolean value
     */
    public static Boolean booleanOf (String aValue)
    {
        Boolean result = null;

        if (aValue != null)
        {
            if (aValue == "true" ||
                aValue == "yes" ||
                aValue.equalsIgnoreCase ("true") ||
                aValue.equalsIgnoreCase ("yes"))
            {
                result = Boolean.TRUE;
            }
            else if (aValue == "false" ||
                     aValue == "no" ||
                     aValue.equalsIgnoreCase ("false") ||
                     aValue.equalsIgnoreCase ("no"))
            {
                result = Boolean.FALSE;
            }
        }

        return(result);
    }

    /**
     * Replace all occurrences of a pattern within a string by a replacement
     *
     * @param source  The string that should be searched
     * @param pattern  The pattern that should be replaced
     * @param replace  The replacement that should be inserted instead of the pattern
     *
     * @return  The updated source string
     */
    public static String replace(String source, String pattern, String replace)
    {
        if (source == null || source.length() == 0 ||
            pattern == null || pattern.length() == 0)
        {
            return source;
        }

        int k = source.indexOf(pattern);

        if (k == -1)
        {
            return source;
        }

        StringBuffer out = new StringBuffer();
        int i=0, l=pattern.length();

        while (k != -1)
        {
            out.append(source.substring(i,k));

            if (replace != null) {
                out.append(replace);
            }

            i = k+l;
            k = source.indexOf(pattern, i);
        }
        out.append(source.substring(i));
        return out.toString();
    }

    public static void newLine(StringBuffer buffer, int indent)
    {
        buffer.append(StringUtil.lineSeparator);
        indent(buffer, indent);
    }

    public static void indent(StringBuffer buffer, int indent)
    {
        for (int i=0; i<indent; i++) buffer.append(' ');
    }

    public static String encode(String s)
    {
        int maxBytesPerChar = 10;
        StringBuffer out = new StringBuffer(s.length());
        java.io.ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream(maxBytesPerChar);
        java.io.OutputStreamWriter writer = new java.io.OutputStreamWriter(buf);

        for (int i = 0; i < s.length(); i++)
        {
            int c = (int)s.charAt(i);
            if (dontNeedEncoding.get(c))
            {
                out.append((char)c);
            }
            else
            {
                // convert to external encoding before hex conversion
                try
                {
                    writer.write(c);
                    writer.flush();
                }
                catch (java.io.IOException e)
                {
                    buf.reset();
                    continue;
                }
                byte[] ba = buf.toByteArray();
                for (int j = 0; j < ba.length; j++)
                {
                    out.append('x');
                    char ch = Character.forDigit((ba[j] >> 4) & 0xF, 16);
                    // converting to use uppercase letter as part of
                    // the hex value if ch is a letter.
                    if (Character.isLetter(ch))
                    {
                        ch -= caseDiff;
                    }
                    out.append(ch);
                    ch = Character.forDigit(ba[j] & 0xF, 16);
                    if (Character.isLetter(ch))
                    {
                        ch -= caseDiff;
                    }
                    out.append(ch);
                }
                buf.reset();
            }
        }

        return out.toString();
    }

    public static String decode(String s)
    {
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<s.length(); i++)
        {
            char c = s.charAt(i);
            switch (c)
            {
                case '%':
                    if (((s.charAt(i+1)>='0') && (s.charAt(i+1)<='9')) &&
                        ((s.charAt(i+2)>='0') && (s.charAt(i+2)<='9')))
                    {
                        try
                        {
                            sb.append((char)Integer.parseInt(s.substring(i+1, i+3), 16));
                        }
                        catch (java.lang.NumberFormatException e)
                        {
                            throw new java.lang.IllegalArgumentException();
                        }
                        i += 2;
                        break;
                    }
                default:
                    sb.append(c);
                    break;
            }
        }
        // Undo conversion to external encoding
        String result = sb.toString();
        try
        {
            byte[] inputBytes = result.getBytes("8859_1");
            result = new String(inputBytes);
        }
        catch (java.io.UnsupportedEncodingException e)
        {
            // The system should always have 8859_1
        }
        return result;
    }

    public static String[] copy(String[] source) 
    {
        if (source == null)
            return null;
        int length = source.length;
        String[] result = new String[length];
        System.arraycopy(source, 0, result, 0, length);
        return result;    
    }

    public static Map copyParameters(Map parameters)
    { 
        Map result = new HashMap(parameters);
        for (Iterator iter = result.entrySet().iterator(); iter.hasNext();) {
            Map.Entry entry = (Map.Entry)iter.next();
            if (!(entry.getKey() instanceof String)) {
                throw new IllegalArgumentException("Parameter map keys must not be null and of type java.lang.String.");
            }
            try {
                entry.setValue(copy((String[]) entry.getValue()));
            } catch (ClassCastException ex) {                
                throw new IllegalArgumentException("Parameter map values must not be null and of type java.lang.String[].");
            }
        }
        return result;
    }
    
    public static boolean isEmpty(String value)
    {
    	return org.apache.commons.lang.StringUtils.isEmpty(value);
    }
    
    public static int distance(String a,String b){		
		if(StringUtil.isEmpty(a) && StringUtil.isEmpty(b)) return 0;
		if(StringUtil.isEmpty(a) && !StringUtil.isEmpty(b)) return b.length();
		if(!StringUtil.isEmpty(a) && StringUtil.isEmpty(b)) return a.length();
		return countDistance(a,b);
	}
	public static int distanceIgnoreCase(String a,String b){		
		if(StringUtil.isEmpty(a) && StringUtil.isEmpty(b)) return 0;
		if(StringUtil.isEmpty(a) && !StringUtil.isEmpty(b)) return b.length();
		if(!StringUtil.isEmpty(a) && StringUtil.isEmpty(b)) return a.length();
		return countDistance(a.toLowerCase(),b.toLowerCase());
	}
	
	private static int countDistance(String x, String y) {
	    int m = x.length();
	    int n = y.length();

	    int[][] T = new int[m + 1][n + 1];

	    T[0][0] = 0;
	    for (int j = 0; j < n; j++) {
	      T[0][j + 1] = T[0][j] + ins(y, j);
	    }
	    for (int i = 0; i < m; i++) {
	      T[i + 1][0] = T[i][0] + del(x, i);
	      for (int j = 0; j < n; j++) {
	        T[i + 1][j + 1] =  min(
	            T[i][j] + sub(x, i, y, j),
	            T[i][j + 1] + del(x, i),
	            T[i + 1][j] + ins(y, j)
	        );
	      }
	    }

	    return T[m][n];
	}
	
	private static int sub(String x, int xi, String y, int yi) {
		return x.charAt(xi) == y.charAt(yi) ? 0 : 1;
	}
	
	private static int ins(String x, int xi) {
		return 1;
	}
	
	private static int del(String x, int xi) {
		return 1;
	}
	  
	private static int min(int a,int b,int c){
		return (a<b) ? ((a<c) ? a : c) : ((b<c) ? b : c);
	}
}
