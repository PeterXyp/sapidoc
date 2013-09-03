package com.xyp.sapidoc.idoc.reader;

import com.xyp.sapidoc.idoc.enumeration.TagEnum;
import com.xyp.sapidoc.idoc.util.PathUtil;
import com.xyp.sapidoc.idoc.util.ResourceUtil;
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

    /**
     * line number - line value line number starts from 1
     */
    private List<String> content = new ArrayList<String>();
    private List<Integer> lineNumbers = new ArrayList<Integer>();
    private List<String> lineTags = new ArrayList<String>();

    public LineReader(InputStream is) throws IOException {
        this(new InputStreamReader(is));
    }

    public LineReader(Reader reader) throws IOException {
        BufferedReader br = new BufferedReader(reader);
        String line = null;
        int lineNumber = 0;
        while ((line = br.readLine()) != null) {
            content.add(line);
            String lineTag = getLineTag(line);
            if (lineTag != null) {
                lineTags.add(lineTag);
                lineNumbers.add(lineNumber);
            }
            lineNumber++;
        }
        br.close();
    }

    private String getLineTag(String line) {
        if ("".equals(line)) {
            return null;
        }
        String lineTrim = line.trim();
        StringTokenizer st = new StringTokenizer(lineTrim);
        String lineTag = st.nextToken();

        Set<String> allTags = TagEnum.getAllTags();
        if (allTags.contains(lineTag)) {
            return lineTag;
        }
        return null;
    }

    public List<String> readTagContent(TagEnum tag) {
        String tagBegin = tag.getTagBegin();
        String tagEnd = tag.getTagEnd();
        int beginIndex = 0;
        int endIndex = 0;
        int tempEndIndex;
        for (int i = 0; i < lineTags.size(); i++) {
            if (lineTags.get(i).equals(tagBegin)) {
                beginIndex = i;
                for (int j = beginIndex + 1; j < lineTags.size(); j++) {
                    if (lineTags.get(j).equals(tagEnd)) {
                        tempEndIndex = j;
                    }
                }
            }
        }
        return null;
    }

    public void findTag(TagEnum tag, int fromIndex) {
        List<String> tempLineTags = lineTags.subList(fromIndex, lineTags.size());
        int beginIndex = tempLineTags.indexOf(tag.getTagBegin());
        int tempEndIndex = tempLineTags.indexOf(tag.getTagEnd());
        int endIndex = tempEndIndex;
        int nestTagNum = 0;
        for (int i = beginIndex + 1; i < tempEndIndex; i++) {
            if (tempLineTags.get(i).equals(tag.getTagBegin())) {
                nestTagNum++;
            }
        }
        if (nestTagNum != 0) {
            for (int i = tempEndIndex + 1; i < tempLineTags.size(); i++) {
                if (tempLineTags.get(i).equals(tag.getTagEnd())) {
                    nestTagNum--;
                    if (nestTagNum == 0) {
                        endIndex = i;
                        break;
                    }
                }
            }
        }

        System.out.println(lineNumbers.get(beginIndex));
        System.out.println(lineNumbers.get(endIndex));
    }

    public List<String> readContent() {
        return content;
    }

    public List<Integer> getLineNumbers() {
        return lineNumbers;
    }

    public List<String> getLineTags() {
        return lineTags;
    }
//    public static void main(String[] args) throws IOException {
//        LineReader reader = new LineReader(ResourceUtil.loadClasspathResource("ORDERS05.txt"));
//        FileWriter fw = new FileWriter(PathUtil.getResourceDir() + "ORDERS05_structure.txt");
//        int len = reader.getLineNumbers().size();
//        List<Integer> lineNumbers = reader.getLineNumbers();
//        List<String> lineTags = reader.getLineTags();
//        for (int i = 0; i < len; i++) {
//            int lineNumber = lineNumbers.get(i);
//            String lineTag = lineTags.get(i);
//            fw.write(String.format("%1$04d", lineNumber) + "    " + lineTag + "\n");
//        }
//        fw.flush();
//        fw.close();
//    }
}