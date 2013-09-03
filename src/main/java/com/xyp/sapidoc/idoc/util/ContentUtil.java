
package com.xyp.sapidoc.idoc.util;

/**
 *
 * @author Yunpeng_Xu
 */
public class ContentUtil {
    
    private static final String TAB = "    ";
    private static final String CRLF = "\n";
    
    public static String appendTAB(int num) {
        StringBuilder tabs = new StringBuilder();
        for (int i = 0; i < num; i++) {
            tabs.append(TAB);
        }
        return tabs.toString();
    }
    public static String appendCRLF(int num) {
        StringBuilder crlfs = new StringBuilder();
        for (int i = 0; i < num; i++) {
            crlfs.append(CRLF);
        }
        return crlfs.toString();
    }
}
