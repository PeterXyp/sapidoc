package com.xyp.sapidoc.idoc.writer;

import com.xyp.sapidoc.idoc.annotation.IdocField;
import com.xyp.sapidoc.idoc.attribute.IdocFieldProperty;
import com.xyp.sapidoc.idoc.enumeration.TagEnum;
import com.xyp.sapidoc.idoc.reader.Line;
import com.xyp.sapidoc.idoc.reader.LineReader;
import com.xyp.sapidoc.idoc.util.PathUtil;
import com.xyp.sapidoc.idoc.util.ReflectionUtil;
import com.xyp.sapidoc.idoc.util.ResourceUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Yunpeng_Xu
 */
public class ClassGenerator {

    private LineReader reader;

    public ClassGenerator(LineReader reader) {
        this.reader = reader;
    }

    public void generateClass() throws Exception {
        parseRecordSection();
        parseSegmentSection();
    }

    private void parseRecordSection() throws Exception {
        String pkgName = "com.xyp.sapidoc.idoc.model";
        String targetDir = PathUtil.getJavaSrcDir();
        Set<String> imports = new HashSet<String>();
        imports.add(IdocField.class.getName());

        List<IdocFieldProperty> fields1 = parseControlRecord();
        writeClass("EDI_DC40", pkgName, targetDir, fields1, imports);
        List<IdocFieldProperty> fields2 = parseDataRecord();
        writeClass("EDI_DD40", pkgName, targetDir, fields2, imports);
        List<IdocFieldProperty> fields3 = parseStatusRecord();
        writeClass("EDI_DS40", pkgName, targetDir, fields3, imports);
    }

    private List<IdocFieldProperty> parseControlRecord() throws Exception {
        return parseIdocFields(TagEnum.CONTROL_RECORD);
    }

    private List<IdocFieldProperty> parseDataRecord() throws Exception {
        return parseIdocFields(TagEnum.DATA_RECORD);
    }

    private List<IdocFieldProperty> parseStatusRecord() throws Exception {
        return parseIdocFields(TagEnum.STATUS_RECORD);
    }

    private List<IdocFieldProperty> parseIdocFields(TagEnum tag) throws Exception {
        List<Line> lines = reader.readTagContent(tag);
        List<IdocFieldProperty> fields = new ArrayList<IdocFieldProperty>();
        IdocFieldProperty field = new IdocFieldProperty();
        for (Line line : lines) {
            if (line.isStructureTagLine()) {
                continue;
            }
            if (line.isBlankLine()) {
                fields.add(field);
                field = new IdocFieldProperty();
                continue;
            }
            String tagName = line.getLineTag();
            String value = line.getLineValue();
            ReflectionUtil.setProperty(field, tagName.toLowerCase(), value);
        }
        fields.add(field);
        return fields;
    }

    private void writeClass(String className, String pkgName, String targetDir, List<IdocFieldProperty> fields, Set<String> imports)
            throws IOException {
        ClassWriter classWriter = new ClassWriter(className, pkgName, targetDir);
        classWriter.addFields(fields);
        classWriter.addImport(IdocField.class.getName());
        classWriter.addImports(imports);
        classWriter.writerClass();
    }

    private void parseSegmentSection() {
    }

    public static void main(String[] args) throws Exception {
        LineReader reader = new LineReader(ResourceUtil.loadClasspathResource("ORDERS05.txt"));
        ClassGenerator cg = new ClassGenerator(reader);
        cg.generateClass();
    }
}
