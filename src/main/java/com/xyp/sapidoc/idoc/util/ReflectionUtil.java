package com.xyp.sapidoc.idoc.util;

import java.lang.reflect.Method;

/**
 *
 * @author Yunpeng_Xu
 */
public class ReflectionUtil {

    public static void setProperty(Object obj, String fieldName, String fieldValue) throws Exception{
        String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
        Class clazz = obj.getClass();
        Method setter;
        try {
            setter = clazz.getDeclaredMethod(setterName, String.class);
            setter.invoke(obj, fieldValue);
        } catch (NoSuchMethodException ex) {
            setter = clazz.getDeclaredMethod(setterName, int.class);
            setter.invoke(obj, Integer.parseInt(fieldValue));
        }
    }
}
