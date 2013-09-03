package com.xyp.sapidoc.idoc.enumeration;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Yunpeng_Xu
 */
public enum TagEnum {

    FIELDS("FIELDS"),
    
    RECORD_SECTION("RECORD_SECTION"),
    CONTROL_RECORD("CONTROL_RECORD"),
    DATA_RECORD("DATA_RECORD"),
    STATUS_RECORD("STATUS_RECORD"),
    
    SEGMENT_SECTION("SEGMENT_SECTION"),
    IDOC("IDOC"),
    SEGMENT("SEGMENT"),
    GROUP("GROUP"),
    ;
    
    private String tag;

    private TagEnum(String tag) {
        this.tag = tag;
    }
    

    public String getTagBegin() {
        return "BEGIN_" + tag;
    }

    public String getTagEnd() {
        return "END_" + tag;
    }
    
    public static Set<String> getAllTags(){
        Set<String> tags = new HashSet<String>();
        TagEnum[] tagEnums = TagEnum.values();
        for (TagEnum tagEnum : tagEnums) {
            tags.add(tagEnum.getTagBegin());
            tags.add(tagEnum.getTagEnd());
        }
        return tags;
    }
}
