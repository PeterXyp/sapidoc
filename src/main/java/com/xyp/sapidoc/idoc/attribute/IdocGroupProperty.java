package com.xyp.sapidoc.idoc.attribute;

import com.xyp.sapidoc.idoc.enumeration.StatusEnum;
import java.util.List;

/**
 *
 * @author Yunpeng_Xu
 */
public class IdocGroupProperty {
    
    private String name;
    private int level;
    private StatusEnum status;
    private int loopMin;
    private long loopMax;
    private List<IdocSegmentProperty> segments;

    public IdocGroupProperty() {
    }

    public IdocGroupProperty(String name, int level, StatusEnum status, int loopMin, long loopMax, List<IdocSegmentProperty> segments) {
        this.name = name;
        this.level = level;
        this.status = status;
        this.loopMin = loopMin;
        this.loopMax = loopMax;
        this.segments = segments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public long getLoopMax() {
        return loopMax;
    }

    public void setLoopMax(long loopMax) {
        this.loopMax = loopMax;
    }

    public List<IdocSegmentProperty> getSegments() {
        return segments;
    }

    public void setSegments(List<IdocSegmentProperty> segments) {
        this.segments = segments;
    }

    @Override
    public String toString() {
        return "IdocGroupProperty{" + "name=" + name + ", level=" + level + ", status=" + status + ", loopMin=" + loopMin + ", loopMax=" + loopMax + ", segments=" + segments + '}';
    }
}
