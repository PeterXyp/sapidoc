package com.xyp.sapidoc.idoc.util;

/**
 *
 * @author Yunpeng_Xu
 */
public class AssertUtil {

    public static void assertNotNull(Object obj, RuntimeException ex){
        if (obj == null) {
            throw ex;
        }
    }
    public static void assertZero(int i, RuntimeException ex){
        if (i != 0) {
            throw ex;
        }
    }
}
