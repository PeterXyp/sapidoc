package com.xyp.sapidoc.idoc.attribute;

import com.xyp.sapidoc.idoc.util.ContentUtil;

/**
 *
 * @author Yunpeng_Xu
 */
public class IdocFieldProperty {

    public static final int AttrSize = 7;
    
    private String name;
    private String text;
    private String type;
    private int length;
    private int field_pos;
    private int character_first;
    private int character_last;

    public IdocFieldProperty() {
    }

    public IdocFieldProperty(String name, String text, String type, int length, int field_pos, int character_first, int character_last) {
        this.name = name;
        this.text = text;
        this.type = type;
        this.length = length;
        this.field_pos = field_pos;
        this.character_first = character_first;
        this.character_last = character_last;
    }
    
    public IdocFieldProperty(String props[]) {
        this.name = props[0];
        this.text = props[1];
        this.type = String.class.getSimpleName();
        this.length = Integer.parseInt(props[3]);
        this.field_pos = Integer.parseInt(props[4]);
        this.character_first = Integer.parseInt(props[5]);
        this.character_last = Integer.parseInt(props[6]);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getField_pos() {
        return field_pos;
    }

    public void setField_pos(int field_pos) {
        this.field_pos = field_pos;
    }

    public int getCharacter_first() {
        return character_first;
    }

    public void setCharacter_first(int character_first) {
        this.character_first = character_first;
    }

    public int getCharacter_last() {
        return character_last;
    }

    public void setCharacter_last(int character_last) {
        this.character_last = character_last;
    }
    
    
    public String getFieldDeclaration(){
        StringBuilder sb = new StringBuilder();
        //add annotation
        sb.append(ContentUtil.appendTAB(1))
                .append("@IdocField")
                .append("(")
                .append("name = ").append("\"").append(getName()).append("\",")
                .append(" length = ").append(getLength()).append(",")
                .append(" field_pos = ").append(getField_pos()).append(",")
                .append(" character_first = ").append(getCharacter_first()).append(",")
                .append(" character_last = ").append(getCharacter_last())
                .append(")")
                .append(ContentUtil.appendCRLF(1));
        //add field declaration
        sb.append(ContentUtil.appendTAB(1))
                .append("private String ").append(getName()).append(";")
                .append(ContentUtil.appendCRLF(1));
        return sb.toString();
    }
    
    public String getGetter(){
        String fieldUpperCase = getName().substring(0, 1).toUpperCase() + getName().substring(1, getName().length());
        StringBuilder sb = new StringBuilder();
        sb.append(ContentUtil.appendCRLF(2))
                .append(ContentUtil.appendTAB(1))
                .append("public String get").append(fieldUpperCase).append("() {")
                .append(ContentUtil.appendCRLF(1)).append(ContentUtil.appendTAB(2))
                .append("return ").append(getName()).append(";")
                .append(ContentUtil.appendCRLF(1)).append(ContentUtil.appendTAB(1))
                .append("}");
        return sb.toString();
    }
    
    public String getSetter(){
        String fieldUpperCase = getName().substring(0, 1).toUpperCase() + getName().substring(1, getName().length());
        StringBuilder sb = new StringBuilder();
        sb.append(ContentUtil.appendCRLF(2))
                .append(ContentUtil.appendTAB(1))
                .append("public void set").append(fieldUpperCase).append("(String ").append(getName()).append(") {")
                .append(ContentUtil.appendCRLF(1)).append(ContentUtil.appendTAB(2))
                .append("this.").append(getName()).append(" = ").append(getName()).append(";")
                .append(ContentUtil.appendCRLF(1)).append(ContentUtil.appendTAB(1))
                .append("}");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "IdocFieldProperty{" + "name=" + name + ", text=" + text + ", type=" + type + ", length=" + length + ", field_pos=" + field_pos + ", character_first=" + character_first + ", character_last=" + character_last + '}';
    }
}
