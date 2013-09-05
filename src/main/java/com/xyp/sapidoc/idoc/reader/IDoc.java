package com.xyp.sapidoc.idoc.reader;

/**
 *
 * @author Yunpeng_Xu
 */
public class IDoc {

    private String name;
    private Line startLine;
    private Line endLine;

    public IDoc() {
    }

    public IDoc(String name, Line startLine, Line endLine) {
        this.name = name;
        this.startLine = startLine;
        this.endLine = endLine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Line getStartLine() {
        return startLine;
    }

    public void setStartLine(Line startLine) {
        this.startLine = startLine;
    }

    public Line getEndLine() {
        return endLine;
    }

    public void setEndLine(Line endLine) {
        this.endLine = endLine;
    }
}
