package com.xyp.sapidoc.idoc.model;

import com.xyp.sapidoc.idoc.annotation.IdocField;
import com.xyp.sapidoc.idoc.attribute.IdocFieldProperty;
import com.xyp.sapidoc.idoc.enumeration.TagEnum;
import com.xyp.sapidoc.idoc.util.PathUtil;
import com.xyp.sapidoc.idoc.util.ResourceUtil;
import com.xyp.sapidoc.idoc.writer.ClassWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yunpeng_Xu
 */
public class ModelUtil {

    public static List<IdocFieldProperty> parseFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(ResourceUtil.loadClasspathResource(fileName)));
        String line = null;
        List<IdocFieldProperty> fields = new ArrayList<IdocFieldProperty>();
        String[] props = new String[IdocFieldProperty.AttrSize];
        int propPosition = 0;
        while ((line = br.readLine()) != null) {
            if (skipLine(line)) {
                continue;
            }
            StringTokenizer st = new StringTokenizer(line);
            StringBuilder fieldValue = new StringBuilder();
            st.nextToken();//skip first token
            while (st.hasMoreTokens()) {
                fieldValue.append(st.nextToken()).append(" ");
            }
            props[propPosition++] = fieldValue.substring(0, fieldValue.length() - 1);
            if (propPosition == IdocFieldProperty.AttrSize) {
                IdocFieldProperty field = new IdocFieldProperty(props);
                fields.add(field);
                propPosition = 0;
                props = new String[IdocFieldProperty.AttrSize];
            }
        }
        br.close();
        return fields;
    }

    private static boolean skipLine(String line) {
        line = line.trim();
        Set<String> ignoreLines = new HashSet<String>();
        ignoreLines.add("");
        ignoreLines.add(TagEnum.CONTROL_RECORD.getTagBegin());
        ignoreLines.add(TagEnum.DATA_RECORD.getTagBegin());
        ignoreLines.add(TagEnum.STATUS_RECORD.getTagBegin());
        ignoreLines.add(TagEnum.FIELDS.getTagBegin());
        ignoreLines.add(TagEnum.CONTROL_RECORD.getTagEnd());
        ignoreLines.add(TagEnum.DATA_RECORD.getTagEnd());
        ignoreLines.add(TagEnum.STATUS_RECORD.getTagEnd());
        ignoreLines.add(TagEnum.FIELDS.getTagEnd());
        if (ignoreLines.contains(line)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        String className = "EDI_DC40";
        String pkgName = "com.xyp.sapidoc.idoc.model";
        String targetDir = PathUtil.getJavaSrcDir();
        String fileName = "EDI_DC40";
        ClassWriter classWriter = new ClassWriter(className, pkgName, targetDir);
        classWriter.addFields(parseFile(fileName));
        classWriter.addImport(IdocField.class.getName());
        classWriter.writerClass();
    }
}