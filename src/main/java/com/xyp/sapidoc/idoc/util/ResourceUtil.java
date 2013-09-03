package com.xyp.sapidoc.idoc.util;

import java.io.InputStream;

/**
 *
 * @author Peter Xu
 */
public class ResourceUtil {

    /**
     * load classpath resource
     * eg: file 'book.xml' in package com.xml  then loadClasspathResource("com/xml/book.xml")
     * @param file
     * @return 
     */
    public static InputStream loadClasspathResource(String file) {
        return getClassLoader().getResourceAsStream(file);
    }
    
    public static ClassLoader getClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Exception e) {
            cl = ResourceUtil.class.getClassLoader();
        }
        return cl;
    }
}
