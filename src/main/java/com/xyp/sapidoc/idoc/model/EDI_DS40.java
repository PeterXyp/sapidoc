package com.xyp.sapidoc.idoc.model;

import com.xyp.sapidoc.idoc.annotation.IdocField;

public class EDI_DS40 {

    @IdocField(name = "TABNAM", length = 10, field_pos = 1, character_first = 1, character_last = 10)
    private String TABNAM;
    @IdocField(name = "MANDT", length = 3, field_pos = 2, character_first = 11, character_last = 13)
    private String MANDT;
    @IdocField(name = "DOCNUM", length = 16, field_pos = 3, character_first = 14, character_last = 29)
    private String DOCNUM;
    @IdocField(name = "LOGDAT", length = 8, field_pos = 4, character_first = 30, character_last = 37)
    private String LOGDAT;
    @IdocField(name = "LOGTIM", length = 6, field_pos = 5, character_first = 38, character_last = 43)
    private String LOGTIM;
    @IdocField(name = "STATUS", length = 2, field_pos = 6, character_first = 44, character_last = 45)
    private String STATUS;
    @IdocField(name = "STAMQU", length = 3, field_pos = 7, character_first = 46, character_last = 48)
    private String STAMQU;
    @IdocField(name = "STAMID", length = 20, field_pos = 8, character_first = 49, character_last = 68)
    private String STAMID;
    @IdocField(name = "STAMNO", length = 3, field_pos = 9, character_first = 69, character_last = 71)
    private String STAMNO;
    @IdocField(name = "STATYP", length = 1, field_pos = 10, character_first = 72, character_last = 72)
    private String STATYP;
    @IdocField(name = "STAPA1", length = 50, field_pos = 11, character_first = 73, character_last = 122)
    private String STAPA1;
    @IdocField(name = "STAPA2", length = 50, field_pos = 12, character_first = 123, character_last = 172)
    private String STAPA2;
    @IdocField(name = "STAPA3", length = 50, field_pos = 13, character_first = 173, character_last = 222)
    private String STAPA3;
    @IdocField(name = "STAPA4", length = 50, field_pos = 14, character_first = 223, character_last = 272)
    private String STAPA4;
    @IdocField(name = "STATXT", length = 70, field_pos = 15, character_first = 273, character_last = 342)
    private String STATXT;
    @IdocField(name = "UNAME", length = 12, field_pos = 16, character_first = 343, character_last = 354)
    private String UNAME;
    @IdocField(name = "REPID", length = 30, field_pos = 17, character_first = 355, character_last = 384)
    private String REPID;
    @IdocField(name = "ROUTID", length = 30, field_pos = 18, character_first = 385, character_last = 414)
    private String ROUTID;
    @IdocField(name = "SEGNUM", length = 6, field_pos = 19, character_first = 415, character_last = 420)
    private String SEGNUM;
    @IdocField(name = "SEGFLD", length = 30, field_pos = 20, character_first = 421, character_last = 450)
    private String SEGFLD;
    @IdocField(name = "REFINT", length = 14, field_pos = 21, character_first = 451, character_last = 464)
    private String REFINT;
    @IdocField(name = "REFGRP", length = 14, field_pos = 22, character_first = 465, character_last = 478)
    private String REFGRP;
    @IdocField(name = "REFMES", length = 14, field_pos = 23, character_first = 479, character_last = 492)
    private String REFMES;
    @IdocField(name = "ARCKEY", length = 70, field_pos = 24, character_first = 493, character_last = 562)
    private String ARCKEY;


    public void setTABNAM(String TABNAM) {
        this.TABNAM = TABNAM;
    }

    public void setMANDT(String MANDT) {
        this.MANDT = MANDT;
    }

    public void setDOCNUM(String DOCNUM) {
        this.DOCNUM = DOCNUM;
    }

    public void setLOGDAT(String LOGDAT) {
        this.LOGDAT = LOGDAT;
    }

    public void setLOGTIM(String LOGTIM) {
        this.LOGTIM = LOGTIM;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public void setSTAMQU(String STAMQU) {
        this.STAMQU = STAMQU;
    }

    public void setSTAMID(String STAMID) {
        this.STAMID = STAMID;
    }

    public void setSTAMNO(String STAMNO) {
        this.STAMNO = STAMNO;
    }

    public void setSTATYP(String STATYP) {
        this.STATYP = STATYP;
    }

    public void setSTAPA1(String STAPA1) {
        this.STAPA1 = STAPA1;
    }

    public void setSTAPA2(String STAPA2) {
        this.STAPA2 = STAPA2;
    }

    public void setSTAPA3(String STAPA3) {
        this.STAPA3 = STAPA3;
    }

    public void setSTAPA4(String STAPA4) {
        this.STAPA4 = STAPA4;
    }

    public void setSTATXT(String STATXT) {
        this.STATXT = STATXT;
    }

    public void setUNAME(String UNAME) {
        this.UNAME = UNAME;
    }

    public void setREPID(String REPID) {
        this.REPID = REPID;
    }

    public void setROUTID(String ROUTID) {
        this.ROUTID = ROUTID;
    }

    public void setSEGNUM(String SEGNUM) {
        this.SEGNUM = SEGNUM;
    }

    public void setSEGFLD(String SEGFLD) {
        this.SEGFLD = SEGFLD;
    }

    public void setREFINT(String REFINT) {
        this.REFINT = REFINT;
    }

    public void setREFGRP(String REFGRP) {
        this.REFGRP = REFGRP;
    }

    public void setREFMES(String REFMES) {
        this.REFMES = REFMES;
    }

    public void setARCKEY(String ARCKEY) {
        this.ARCKEY = ARCKEY;
    }

    public String getTABNAM() {
        return TABNAM;
    }

    public String getMANDT() {
        return MANDT;
    }

    public String getDOCNUM() {
        return DOCNUM;
    }

    public String getLOGDAT() {
        return LOGDAT;
    }

    public String getLOGTIM() {
        return LOGTIM;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public String getSTAMQU() {
        return STAMQU;
    }

    public String getSTAMID() {
        return STAMID;
    }

    public String getSTAMNO() {
        return STAMNO;
    }

    public String getSTATYP() {
        return STATYP;
    }

    public String getSTAPA1() {
        return STAPA1;
    }

    public String getSTAPA2() {
        return STAPA2;
    }

    public String getSTAPA3() {
        return STAPA3;
    }

    public String getSTAPA4() {
        return STAPA4;
    }

    public String getSTATXT() {
        return STATXT;
    }

    public String getUNAME() {
        return UNAME;
    }

    public String getREPID() {
        return REPID;
    }

    public String getROUTID() {
        return ROUTID;
    }

    public String getSEGNUM() {
        return SEGNUM;
    }

    public String getSEGFLD() {
        return SEGFLD;
    }

    public String getREFINT() {
        return REFINT;
    }

    public String getREFGRP() {
        return REFGRP;
    }

    public String getREFMES() {
        return REFMES;
    }

    public String getARCKEY() {
        return ARCKEY;
    }
}