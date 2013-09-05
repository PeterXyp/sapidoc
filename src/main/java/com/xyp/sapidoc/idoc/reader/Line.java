package com.xyp.sapidoc.idoc.reader;

import com.xyp.sapidoc.idoc.enumeration.TagEnum;

/**
 *
 * @author Yunpeng_Xu
 */
public class Line {

    private int lineNumber;//from 1
    private String lineTag;
    private String lineValue;

    public Line(int lineNumber) {
        this(lineNumber, "", "");
    }

    public Line(int lineNumber, String lineTag, String lineValue) {
        this.lineNumber = lineNumber;
        this.lineTag = lineTag;
        this.lineValue = lineValue;
    }

    public boolean isBlankLine(){
        return "".equals(lineTag);
    }
    
    public boolean isStructureTagLine(){
        return TagEnum.getAllTags().contains(this.getLineTag());
    }
    
    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getLineTag() {
        return lineTag;
    }

    public void setLineTag(String lineTag) {
        this.lineTag = lineTag;
    }

    public String getLineValue() {
        return lineValue;
    }

    public void setLineValue(String lineValue) {
        this.lineValue = lineValue;
    }

    @Override
    public String toString() {
        return "Line{" + "lineNumber=" + lineNumber + ", lineTag=" + lineTag + ", lineValue=" + lineValue + "}";
    }
}
