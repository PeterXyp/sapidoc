package com.xyp.sapidoc.idoc.reader;

import com.xyp.sapidoc.idoc.enumeration.TagEnum;
import com.xyp.sapidoc.idoc.util.PathUtil;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 *
 * @author Yunpeng_Xu
 */
public class LineReader {

    private List<Line> content = new ArrayList<Line>();
    private List<Line> structureLine = new ArrayList<Line>();
    private Set<String> allStructureTags = TagEnum.getAllTags();

    public LineReader(InputStream is) throws IOException {
        this(new InputStreamReader(is));
    }

    public LineReader(Reader reader) throws IOException {
        BufferedReader br = new BufferedReader(reader);
        String lineStr = null;
        int lineNumber = 1;
        while ((lineStr = br.readLine()) != null) {
            Line line = parseLine(lineStr, lineNumber++);
            content.add(line);
        }
        br.close();
    }

    private Line parseLine(String lineStr, int lineNumber) {
        if ("".equals(lineStr)) {
            return new Line(lineNumber);
        }
        StringTokenizer st = new StringTokenizer(lineStr);
        String lineTag = st.nextToken();
        String lineValue = "";
        while (st.hasMoreTokens()) {
            lineValue += (st.nextToken() + " ");
        }
        if (!"".equals(lineValue)) {
            lineValue = lineValue.substring(0, lineValue.length() - 1);
        }
        Line line = new Line(lineNumber, lineTag, lineValue);
        if (allStructureTags.contains(lineTag)) {
            structureLine.add(line);
        }
        return line;
    }

    public void generateStructure() throws IOException {
        FileWriter fw = new FileWriter(PathUtil.getResourceDir() + "ORDERS05_structure_1.txt");
        String formatter = "%04d     %-30s %15s\n";
        for (Line line : structureLine) {
            int lineNumber = line.getLineNumber();
            String lineTag = line.getLineTag();
            String lineValue = line.getLineValue() == null ? "" : line.getLineValue();
            String lineStr = String.format(formatter, lineNumber, lineTag, lineValue);
            fw.write(lineStr);
        }
        fw.flush();
        fw.close();
    }

    public List<Line> readTagContent(TagEnum tag) {
        Line[] result = findTag(tag, 1);
        Line beginLine = result[0];
        Line endLine = result[1];
        int fromIndex = beginLine.getLineNumber() - 1;
        int toIndex = endLine.getLineNumber();
        return content.subList(fromIndex, toIndex);
    }

    public Line[] findTag(TagEnum tag, int fromLineNumber) {
        Line fromLine = findLine(fromLineNumber);
        int fromIndex = structureLine.indexOf(fromLine);
        int toIndex = structureLine.size();
        Line beginLine = null;
        Line endLine = null;
        Line tempEndLine = null;
        int beginIndex = 0;
        int tempEndIndex = 0;
        int nestedTags = 0;
        for (int i = fromIndex; i < toIndex; i++) {
            Line temp = structureLine.get(i);
            if (temp.getLineTag().equals(tag.getTagBegin())) {
                beginLine = temp;
                beginIndex = i;
                break;
            }
        }
        for (int j = beginIndex; j < toIndex; j++) {
            Line temp = structureLine.get(j);
            if (temp.getLineTag().equals(tag.getTagEnd())) {
                tempEndLine = temp;
                tempEndIndex = j;
                break;
            }
        }
        for (int k = beginIndex + 1; k < tempEndIndex; k++) {
            Line temp = structureLine.get(k);
            if (temp.getLineTag().equals(tag.getTagBegin())) {
                nestedTags++;
            }
        }
        if (nestedTags == 0) {
            endLine = tempEndLine;
        } else {
            for (int m = tempEndIndex + 1; m < toIndex; m++) {
                Line temp = structureLine.get(m);
                if (temp.getLineTag().equals(tag.getTagBegin())) {
                    nestedTags++;
                }
                if (temp.getLineTag().equals(tag.getTagEnd())) {
                    nestedTags--;
                    if (nestedTags == 0) {
                        endLine = temp;
                        break;
                    }
                }
            }
        }
        Line[] result = new Line[2];
        result[0] = beginLine;
        result[1] = endLine;
        return result;
    }

    private Line findLine(int fromLineNumber) {
        for (Line line : structureLine) {
            if (line.getLineNumber() == fromLineNumber) {
                return line;
            }
        }
        return null;
    }

    public List<Line> readContent() {
        return content;
    }
}