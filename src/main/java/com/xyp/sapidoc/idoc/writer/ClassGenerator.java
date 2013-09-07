package com.xyp.sapidoc.idoc.writer;

import com.xyp.sapidoc.idoc.annotation.IdocField;
import com.xyp.sapidoc.idoc.annotation.IdocSegment;
import com.xyp.sapidoc.idoc.attribute.IdocFieldProperty;
import com.xyp.sapidoc.idoc.attribute.IdocSegmentProperty;
import com.xyp.sapidoc.idoc.enumeration.StatusEnum;
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
        for (Line[] segment : segments) {
            IdocSegmentProperty _segment = parseSegment(segment);

            ClassWriter classWriter = new ClassWriter(_segment.getType(), pkgName, targetDir);
            classWriter.addAnnotation(IdocSegment.class, _segment.getClassAnnotation());
            classWriter.addFields(_segment.getFields());
            classWriter.addImports(_segment.getImportedClass());
            classWriter.writerClass();
        }
    }

    private IdocSegmentProperty parseSegment(Line[] segment) throws Exception {
        Line[] field = reader.findTag(TagEnum.FIELDS, segment[0], segment[1]);
        List<Line> fieldsContent = reader.readLine(field[0], field[1]);
        List<IdocFieldProperty> fields = parseIdocFields(fieldsContent);
        
        List<Line> segmentHeader = reader.readLine(segment[0].getLineNumber(), field[0].getLineNumber() - 1);

        IdocSegmentProperty _segment = new IdocSegmentProperty();
        _segment.setFields(fields);
        for (Line line : segmentHeader) {
            if ("BEGIN_SEGMENT".equals(line.getLineTag())) {
                _segment.setName(line.getLineValue());
            }
            String lineTag = line.getLineTag();
            String lineValue = line.getLineValue();
            switch (lineTag) {
                case ("BEGIN_SEGMENT"):
                    _segment.setName(lineValue);
                    break;
                case ("SEGMENTTYPE"):
                    _segment.setType(lineValue);
                    break;
                case ("QUALIFIED"):
                    _segment.setQualified(lineValue);
                    break;
                case ("LEVEL"):
                    _segment.setLevel(Integer.parseInt(lineValue));
                    break;
                case ("STATUS"):
                    lineValue = "".equals(lineValue) ? "DEFAULT" : lineValue;
                    _segment.setStatus(StatusEnum.valueOf(lineValue));
                    break;
                case ("LOOPMIN"):
                    _segment.setLoopMin(Integer.parseInt(lineValue));
                    break;
                case ("LOOPMAX"):
                    Long temp = Long.valueOf(lineValue);
                    if (temp > Integer.MAX_VALUE){
                        _segment.setLoopMax(Integer.MAX_VALUE);
                    } else {
                        _segment.setLoopMax(temp.intValue());
                    }
                    break;
            }
        }
        return _segment;
    }

    public static void main(String[] args) throws Exception {
        LineReader reader = new LineReader(ResourceUtil.loadClasspathResource("ORDERS05.txt"));
        ClassGenerator cg = new ClassGenerator(reader);
        cg.generateClass();
    }
}
