package com.xyp.sapidoc.idoc.writer;

import com.xyp.sapidoc.idoc.annotation.IdocField;
import com.xyp.sapidoc.idoc.attribute.IdocFieldProperty;
import com.xyp.sapidoc.idoc.enumeration.TagEnum;
import com.xyp.sapidoc.idoc.reader.IDoc;
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
    private String pkgName = "com.xyp.sapidoc.idoc.model.gen";
    private String targetDir = PathUtil.getJavaSrcDir();
    private static Set<String> imports = new HashSet<String>();

    static {
        imports.add(IdocField.class.getName());
    }

    public ClassGenerator(LineReader reader) {
        this.reader = reader;
    }

    public void generateClass() throws Exception {
        parseRecordSection();
        parseSegmentSection();
    }

    private void parseRecordSection() throws Exception {
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
        return parseIdocFields(lines);
    }

    private List<IdocFieldProperty> parseIdocFields(List<Line> lines) throws Exception {
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

    private void parseSegmentSection() throws Exception {
        List<IDoc> idocs = parseIdoc();
        for (IDoc idoc : idocs) {
            parseSegments(idoc);
//            parseGroups(idoc);
        }
    }

    private List<IDoc> parseIdoc() {
        List<IDoc> idocs = new ArrayList<IDoc>();
        List<Line[]> results = reader.findTags(TagEnum.IDOC);
        for (Line[] line : results) {
            Line from = line[0];
            Line to = line[1];
            IDoc idoc = new IDoc(from.getLineValue(), from, to);
            idocs.add(idoc);
        }
        return idocs;
    }

    private void parseSegments(IDoc idoc) throws Exception {
        int fromLine = idoc.getStartLine().getLineNumber();
        int endLine = idoc.getEndLine().getLineNumber();
        List<Line[]> segments = reader.findTags(TagEnum.SEGMENT, fromLine, endLine);
        int i = 0;
        for (Line[] segment : segments) {
            Line[] fields = reader.findTag(TagEnum.FIELDS, segment[0].getLineNumber(), segment[1].getLineNumber());
            List<Line> fieldsContent = reader.readLine(fields[0], fields[1]);
            List<IdocFieldProperty> _fields = parseIdocFields(fieldsContent);
            String className = reader.readLine(new Line(segment[0].getLineNumber() + 1)).getLineValue();
            writeClass(className, pkgName, targetDir, _fields, imports);
            i++;
        }
        System.out.println(i);
    }

    public static void main(String[] args) throws Exception {
        LineReader reader = new LineReader(ResourceUtil.loadClasspathResource("ORDERS05.txt"));
        ClassGenerator cg = new ClassGenerator(reader);
        cg.generateClass();
    }
}
