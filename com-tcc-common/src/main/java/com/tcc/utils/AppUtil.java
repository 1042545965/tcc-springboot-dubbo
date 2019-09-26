package com.tcc.utils;

import java.text.ParseException;
import java.util.Collection;
import java.util.Map;

/**
 * @author dkzadmin
 *	2018-9-30 10:54:57
 */
public final class AppUtil 
{

    private AppUtil() {
    }

    // 判断对象非空
    public static boolean isNotNull(Object argObj) {
        return !isNull(argObj);
    }

    // 判断对象为空
    @SuppressWarnings("rawtypes")
    public static boolean isNull(Object argObj) 
    {
        if (argObj == null) 
        {
            return true;
        }

        if (argObj instanceof String) 
        {
            return ((String) argObj).trim().equals("") || ((String) argObj).trim().equals(" ") || ((String) argObj).trim().equals("null");
        }

        if (argObj instanceof Collection) 
        {

            return ((Collection) argObj).isEmpty();
        }

        if (argObj instanceof Map) 
        {

            return ((Map) argObj).isEmpty();
        }

        return false;
    }

    public static long parseLong(String str) 
    {
        if (isNull(str)) 
        {
            return 0;
        }
        long l;
        try {
            l = Long.parseLong(str);
        } catch (NumberFormatException e) {
            return 0;
        }
        return l;
    }
    
    public static void main(String[] args) throws ParseException {

    }
}
