package com.xyp.sapidoc.idoc.util;

/**
 *
 * @author Yunpeng_Xu
 */
public class PathUtil {

    public static String getProjectDir() {
        String targetPath = PathUtil.class.getClassLoader().getResource("").getPath();
        String projectDir = targetPath.substring(0, targetPath.indexOf("target"));
        return projectDir;
    }

    public static String getJavaSrcDir() {
        return getProjectDir() + "src/main/java/";
    }

    public static String getResourceDir() {
        return getProjectDir() + "src/main/resources/";
    }
}
