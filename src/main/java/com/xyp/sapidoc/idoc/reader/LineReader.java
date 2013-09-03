package com.xyp.sapidoc.idoc.reader;

import com.xyp.sapidoc.idoc.enumeration.TagEnum;
import com.xyp.sapidoc.idoc.util.ResourceUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
    /**
     * line number - tag value
     */
    private Map<Integer, String> tags = new LinkedHashMap<Integer, String>();

    public LineReader(InputStream is) throws IOException {
        this(new InputStreamReader(is));
    }

    public LineReader(Reader reader) throws IOException {
        BufferedReader br = new BufferedReader(reader);
        String line = null;
        int lineNumber = 0;
        while ((line = br.readLine()) != null) {
            content.add(line);
            if (isTagLine(line)) {
                tags.put(lineNumber, line);
            }
            lineNumber++;
        }
        br.close();
    }

    public List<String> readTagContent(TagEnum tag){
        return null;
    }
    
    public void close() {
        content = null;
        tags = null;
    }

    private Boolean isTagLine(String line) {
        if ("".equals(line)) {
            return false;
        }
        String lineTrim = line.trim();
        StringTokenizer st = new StringTokenizer(lineTrim);
        String lineTag = st.nextToken();

        Set<String> allTags = TagEnum.getAllTags();
        if (allTags.contains(lineTag)) {
            return true;
        }
        return false;
    }

    public List<String> getContent() {
        return content;
    }

    public Map<Integer, String> getTags() {
        return tags;
    }
}