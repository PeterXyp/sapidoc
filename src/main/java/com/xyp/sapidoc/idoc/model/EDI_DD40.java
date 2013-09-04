package com.xyp.sapidoc.idoc.model;

import com.xyp.sapidoc.idoc.annotation.IdocField;

public class EDI_DD40 {

    @IdocField(name = "SEGNAM", length = 30, field_pos = 1, character_first = 1, character_last = 30)
    private String SEGNAM;
    @IdocField(name = "MANDT", length = 3, field_pos = 2, character_first = 31, character_last = 33)
    private String MANDT;
    @IdocField(name = "DOCNUM", length = 16, field_pos = 3, character_first = 34, character_last = 49)
    private String DOCNUM;
    @IdocField(name = "SEGNUM", length = 6, field_pos = 4, character_first = 50, character_last = 55)
    private String SEGNUM;
    @IdocField(name = "PSGNUM", length = 6, field_pos = 5, character_first = 56, character_last = 61)
    private String PSGNUM;
    @IdocField(name = "HLEVEL", length = 2, field_pos = 6, character_first = 62, character_last = 63)
    private String HLEVEL;
    @IdocField(name = "SDATA", length = 1000, field_pos = 7, character_first = 64, character_last = 1063)
    private String SDATA;


    public void setSEGNAM(String SEGNAM) {
        this.SEGNAM = SEGNAM;
    }

    public void setMANDT(String MANDT) {
        this.MANDT = MANDT;
    }

    public void setDOCNUM(String DOCNUM) {
        this.DOCNUM = DOCNUM;
    }

    public void setSEGNUM(String SEGNUM) {
        this.SEGNUM = SEGNUM;
    }

    public void setPSGNUM(String PSGNUM) {
        this.PSGNUM = PSGNUM;
    }

    public void setHLEVEL(String HLEVEL) {
        this.HLEVEL = HLEVEL;
    }

    public void setSDATA(String SDATA) {
        this.SDATA = SDATA;
    }

    public String getSEGNAM() {
        return SEGNAM;
    }

    public String getMANDT() {
        return MANDT;
    }

    public String getDOCNUM() {
        return DOCNUM;
    }

    public String getSEGNUM() {
        return SEGNUM;
    }

    public String getPSGNUM() {
        return PSGNUM;
    }

    public String getHLEVEL() {
        return HLEVEL;
    }

    public String getSDATA() {
        return SDATA;
    }
}