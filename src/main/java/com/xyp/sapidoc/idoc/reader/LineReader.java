package com.xyp.sapidoc.idoc.reader;

import com.xyp.sapidoc.idoc.enumeration.TagEnum;
import com.xyp.sapidoc.idoc.exception.IDocException;
import com.xyp.sapidoc.idoc.exception.TagNotFoundException;
import com.xyp.sapidoc.idoc.util.AssertUtil;
import com.xyp.sapidoc.idoc.util.MessageUtil;
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
        String formatter = "%04d     %04d     %-30s %15s\n";
        for (int i = 0; i < structureLine.size(); i++) {
            Line line = structureLine.get(i);
            int lineNumber = line.getLineNumber();
            String lineTag = line.getLineTag();
            String lineValue = line.getLineValue() == null ? "" : line.getLineValue();
            String lineStr = String.format(formatter, i, lineNumber, lineTag, lineValue);
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

    public Line readLine(int lineNumber) {
        int index = lineNumber - 1;
        return content.get(index);
    }

    public List<Line> readLine(int fromLineNumber, int toLineNumber) {
        int fromIndex = fromLineNumber - 1;
        int toIndex = toLineNumber;
        return content.subList(fromIndex, toIndex);
    }
    
    public Line readLine(Line line) {
        int index = line.getLineNumber() - 1;
        return content.get(index);
    }

    public List<Line> readLine(Line from, Line to) {
        int fromIndex = from.getLineNumber() - 1;
        int toIndex = to.getLineNumber();
        return content.subList(fromIndex, toIndex);
    }

    /**
     * default from line number 1 to last line number in structure line
     *
     * @param tag
     * @return start line and end line
     */
    public Line[] findTag(TagEnum tag) {
        int fromLineNumber = 1;
        int toLineNumber = getTotalLineNumber();
        return findTag(tag, fromLineNumber, toLineNumber);
    }

    public Line[] findTag(TagEnum tag, Line fromLine) {
        int fromLineNumber = fromLine.getLineNumber();
        int toLineNumber = getTotalLineNumber();
        return findTag(tag, fromLineNumber, toLineNumber);
    }
    
    public Line[] findTag(TagEnum tag, int fromLineNumber) {
        int toLineNumber = getTotalLineNumber();
        return findTag(tag, fromLineNumber, toLineNumber);
    }

    public Line[] findTag(TagEnum tag, Line fromLine, Line toLine) {
        int fromLineNumber = fromLine.getLineNumber();
        int toLineNumber = toLine.getLineNumber();
        return findTag(tag, fromLineNumber, toLineNumber);
    }
    
    /**
     * @param tag
     * @param fromLineNumber
     * @return start line and end line
     */
    public Line[] findTag(TagEnum tag, int fromLineNumber, int toLineNumber) {
        Line[] result = null;
        int fromIndex = 0;
        int toIndex = 0;

        fromIndex = findStructureIndexByLineNumber(fromLineNumber, true);
        toIndex = findStructureIndexByLineNumber(toLineNumber, false);

        if (fromIndex >= toIndex || fromIndex < 0 || toIndex >= structureLine.size()) {
            return result;
        }
        Line beginLine = null;
        Line endLine = null;
        Line tempEndLine = null;
        int beginIndex = 0;
        int tempEndIndex = 0;
        int nestedTags = 0;
        for (int i = fromIndex; i <= toIndex; i++) {
            Line temp = structureLine.get(i);
            if (temp.getLineTag().equals(tag.getTagBegin())) {
                beginLine = temp;
                beginIndex = i;
                break;
            }
        }
        AssertUtil.assertNotNull(beginLine, new TagNotFoundException(MessageUtil.BEGIN_TAG_NOT_FOUND));

        for (int j = beginIndex + 1; j <= toIndex; j++) {
            Line temp = structureLine.get(j);
            if (temp.getLineTag().equals(tag.getTagEnd())) {
                tempEndLine = temp;
                tempEndIndex = j;
                break;
            }
        }
        AssertUtil.assertNotNull(tempEndLine, new TagNotFoundException(MessageUtil.END_TAG_NOT_FOUND));

        for (int k = beginIndex + 1; k < tempEndIndex; k++) {
            Line temp = structureLine.get(k);
            if (temp.getLineTag().equals(tag.getTagBegin())) {
                nestedTags++;
            }
        }
        if (nestedTags == 0) {
            endLine = tempEndLine;
        } else {
            for (int m = tempEndIndex + 1; m <= toIndex; m++) {
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
        AssertUtil.assertZero(nestedTags, new IDocException(MessageUtil.IDOC_SCHEMA_ERROR));

        result = new Line[2];
        result[0] = beginLine;
        result[1] = endLine;
        return result;
    }

    public List<Line[]> findTags(TagEnum tag) {
        int fromLineNumber = 1;
        int toLineNumber = getTotalLineNumber();
        return findTags(tag, fromLineNumber, toLineNumber);
    }

    public List<Line[]> findTags(TagEnum tag, int fromLineNumber) {
        int toLineNumber = getTotalLineNumber();
        return findTags(tag, fromLineNumber, toLineNumber);
    }

    public List<Line[]> findTags(TagEnum tag, int fromLineNumber, int toLineNumber) {
        List<Line[]> results = new ArrayList<Line[]>();
        Line[] result = findTag(tag, fromLineNumber, toLineNumber);
        while (result != null) {
            results.add(result);
            try {
                result = findTag(tag, result[1].getLineNumber() + 1, toLineNumber);
            } catch (Exception ex) {
                result = null;
            }
        }
        return results;
    }

    public int findStructureIndexByLineNumber(int lineNumber, boolean moveBefore) {
        lineNumber = lineNumber <= 0 ? 1 : lineNumber;
        lineNumber = lineNumber > getTotalLineNumber() ? getTotalLineNumber() : lineNumber;
        int index = -1;
        for (int i = 0; i < structureLine.size(); i++) {
            Line temp = structureLine.get(i);
            if (temp.getLineNumber() == lineNumber) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            for (int i = 0; i < structureLine.size(); i++) {
                Line temp = structureLine.get(i);
                if (temp.getLineNumber() > lineNumber) {
                    if (moveBefore) {
                        index = i - 1;
                    } else {
                        index = i;
                    }
                    break;
                }
            }
        }
        return index;
    }

    public List<Line> readContent() {
        return content;
    }

    public int getTotalLineNumber() {
        return content.size();
    }
}