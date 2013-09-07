package com.xyp.sapidoc.idoc.attribute;

import com.xyp.sapidoc.idoc.annotation.IdocField;
import com.xyp.sapidoc.idoc.annotation.IdocSegment;
import com.xyp.sapidoc.idoc.enumeration.StatusEnum;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Yunpeng_Xu
 */
public class IdocSegmentProperty {
    
    private String name;
    private String type;
    private String qualified;
    private int level;
    private StatusEnum status;
    private int loopMin;
    private int loopMax;
    private List<IdocFieldProperty> fields;

    public IdocSegmentProperty() {
        this.qualified = "";
        this.status = StatusEnum.DEFAULT;
    }

    public IdocSegmentProperty(String name, String type, String qualified, int level, StatusEnum status, int loopMin, int loopMax, List<IdocFieldProperty> fields) {
        this.name = name;
        this.type = type;
        this.qualified = qualified;
        this.level = level;
        this.status = status;
        this.loopMin = loopMin;
        this.loopMax = loopMax;
        this.fields = fields;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQualified() {
        return qualified;
    }

    public void setQualified(String qualified) {
        this.qualified = qualified;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public int getLoopMin() {
        return loopMin;
    }

    public void setLoopMin(int loopMin) {
        this.loopMin = loopMin;
    }

    public int getLoopMax() {
        return loopMax;
    }

    public void setLoopMax(int loopMax) {
        this.loopMax = loopMax;
    }

    public List<IdocFieldProperty> getFields() {
        return fields;
    }

    public void setFields(List<IdocFieldProperty> fields) {
        this.fields = fields;
    }
    
    public String getClassAnnotation(){
        StringBuilder sb = new StringBuilder();
        sb.append("@IdocSegment(")
                .append("name = \"").append(name).append("\",")
                .append(" type = ").append(type).append(".class,")
                .append(" qualified = \"").append(qualified).append("\",")
                .append(" level = ").append(level).append(",")
                .append(" status = StatusEnum.").append(status.toString()).append(",")
                .append(" loopMin = ").append(loopMin).append(",")
                .append(" loopMax = ").append(loopMax)
                .append(")");
        return sb.toString();
    }
    
    public Set<String> getImportedClass(){
        Set<String> imports = new HashSet<String>();
        imports.add(IdocField.class.getName());
        imports.add(IdocSegment.class.getName());
        imports.add(StatusEnum.class.getName());
        return imports;
    }
    
    @Override
    public String toString() {
        return "IdocSegmentProperty{" + "name=" + name + ", type=" + type + ", qualified=" + qualified + ", level=" + level + ", status=" + status + ", loopMin=" + loopMin + ", loopMax=" + loopMax + ", fields=" + fields + '}';
    }
}
