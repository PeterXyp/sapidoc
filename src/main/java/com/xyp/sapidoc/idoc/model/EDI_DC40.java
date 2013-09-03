package com.xyp.sapidoc.idoc.model;

import com.xyp.sapidoc.idoc.annotation.IdocField;

public class EDI_DC40 {

    @IdocField(name = "TABNAM", length = 10, field_pos = 1, character_first = 1, character_last = 10)
    private String TABNAM;
    @IdocField(name = "MANDT", length = 3, field_pos = 2, character_first = 11, character_last = 13)
    private String MANDT;
    @IdocField(name = "DOCNUM", length = 16, field_pos = 3, character_first = 14, character_last = 29)
    private String DOCNUM;
    @IdocField(name = "DOCREL", length = 4, field_pos = 4, character_first = 30, character_last = 33)
    private String DOCREL;
    @IdocField(name = "STATUS", length = 2, field_pos = 5, character_first = 34, character_last = 35)
    private String STATUS;
    @IdocField(name = "DIRECT", length = 1, field_pos = 6, character_first = 36, character_last = 36)
    private String DIRECT;
    @IdocField(name = "OUTMOD", length = 1, field_pos = 7, character_first = 37, character_last = 37)
    private String OUTMOD;
    @IdocField(name = "EXPRSS", length = 1, field_pos = 8, character_first = 38, character_last = 38)
    private String EXPRSS;
    @IdocField(name = "TEST", length = 1, field_pos = 9, character_first = 39, character_last = 39)
    private String TEST;
    @IdocField(name = "IDOCTYP", length = 30, field_pos = 10, character_first = 40, character_last = 69)
    private String IDOCTYP;
    @IdocField(name = "CIMTYP", length = 30, field_pos = 11, character_first = 70, character_last = 99)
    private String CIMTYP;
    @IdocField(name = "MESTYP", length = 30, field_pos = 12, character_first = 100, character_last = 129)
    private String MESTYP;
    @IdocField(name = "MESCOD", length = 3, field_pos = 13, character_first = 130, character_last = 132)
    private String MESCOD;
    @IdocField(name = "MESFCT", length = 3, field_pos = 14, character_first = 133, character_last = 135)
    private String MESFCT;
    @IdocField(name = "STD", length = 1, field_pos = 15, character_first = 136, character_last = 136)
    private String STD;
    @IdocField(name = "STDVRS", length = 6, field_pos = 16, character_first = 137, character_last = 142)
    private String STDVRS;
    @IdocField(name = "STDMES", length = 6, field_pos = 17, character_first = 143, character_last = 148)
    private String STDMES;
    @IdocField(name = "SNDPOR", length = 10, field_pos = 18, character_first = 149, character_last = 158)
    private String SNDPOR;
    @IdocField(name = "SNDPRT", length = 2, field_pos = 19, character_first = 159, character_last = 160)
    private String SNDPRT;
    @IdocField(name = "SNDPFC", length = 2, field_pos = 20, character_first = 161, character_last = 162)
    private String SNDPFC;
    @IdocField(name = "SNDPRN", length = 10, field_pos = 21, character_first = 163, character_last = 172)
    private String SNDPRN;
    @IdocField(name = "SNDSAD", length = 21, field_pos = 22, character_first = 173, character_last = 193)
    private String SNDSAD;
    @IdocField(name = "SNDLAD", length = 70, field_pos = 23, character_first = 194, character_last = 263)
    private String SNDLAD;
    @IdocField(name = "RCVPOR", length = 10, field_pos = 24, character_first = 264, character_last = 273)
    private String RCVPOR;
    @IdocField(name = "RCVPRT", length = 2, field_pos = 25, character_first = 274, character_last = 275)
    private String RCVPRT;
    @IdocField(name = "RCVPFC", length = 2, field_pos = 26, character_first = 276, character_last = 277)
    private String RCVPFC;
    @IdocField(name = "RCVPRN", length = 10, field_pos = 27, character_first = 278, character_last = 287)
    private String RCVPRN;
    @IdocField(name = "RCVSAD", length = 21, field_pos = 28, character_first = 288, character_last = 308)
    private String RCVSAD;
    @IdocField(name = "RCVLAD", length = 70, field_pos = 29, character_first = 309, character_last = 378)
    private String RCVLAD;
    @IdocField(name = "CREDAT", length = 8, field_pos = 30, character_first = 379, character_last = 386)
    private String CREDAT;
    @IdocField(name = "CRETIM", length = 6, field_pos = 31, character_first = 387, character_last = 392)
    private String CRETIM;
    @IdocField(name = "REFINT", length = 14, field_pos = 32, character_first = 393, character_last = 406)
    private String REFINT;
    @IdocField(name = "REFGRP", length = 14, field_pos = 33, character_first = 407, character_last = 420)
    private String REFGRP;
    @IdocField(name = "REFMES", length = 14, field_pos = 34, character_first = 421, character_last = 434)
    private String REFMES;
    @IdocField(name = "ARCKEY", length = 70, field_pos = 35, character_first = 435, character_last = 504)
    private String ARCKEY;
    @IdocField(name = "SERIAL", length = 20, field_pos = 36, character_first = 505, character_last = 524)
    private String SERIAL;


    public void setTABNAM(String TABNAM) {
        this.TABNAM = TABNAM;
    }

    public void setMANDT(String MANDT) {
        this.MANDT = MANDT;
    }

    public void setDOCNUM(String DOCNUM) {
        this.DOCNUM = DOCNUM;
    }

    public void setDOCREL(String DOCREL) {
        this.DOCREL = DOCREL;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public void setDIRECT(String DIRECT) {
        this.DIRECT = DIRECT;
    }

    public void setOUTMOD(String OUTMOD) {
        this.OUTMOD = OUTMOD;
    }

    public void setEXPRSS(String EXPRSS) {
        this.EXPRSS = EXPRSS;
    }

    public void setTEST(String TEST) {
        this.TEST = TEST;
    }

    public void setIDOCTYP(String IDOCTYP) {
        this.IDOCTYP = IDOCTYP;
    }

    public void setCIMTYP(String CIMTYP) {
        this.CIMTYP = CIMTYP;
    }

    public void setMESTYP(String MESTYP) {
        this.MESTYP = MESTYP;
    }

    public void setMESCOD(String MESCOD) {
        this.MESCOD = MESCOD;
    }

    public void setMESFCT(String MESFCT) {
        this.MESFCT = MESFCT;
    }

    public void setSTD(String STD) {
        this.STD = STD;
    }

    public void setSTDVRS(String STDVRS) {
        this.STDVRS = STDVRS;
    }

    public void setSTDMES(String STDMES) {
        this.STDMES = STDMES;
    }

    public void setSNDPOR(String SNDPOR) {
        this.SNDPOR = SNDPOR;
    }

    public void setSNDPRT(String SNDPRT) {
        this.SNDPRT = SNDPRT;
    }

    public void setSNDPFC(String SNDPFC) {
        this.SNDPFC = SNDPFC;
    }

    public void setSNDPRN(String SNDPRN) {
        this.SNDPRN = SNDPRN;
    }

    public void setSNDSAD(String SNDSAD) {
        this.SNDSAD = SNDSAD;
    }

    public void setSNDLAD(String SNDLAD) {
        this.SNDLAD = SNDLAD;
    }

    public void setRCVPOR(String RCVPOR) {
        this.RCVPOR = RCVPOR;
    }

    public void setRCVPRT(String RCVPRT) {
        this.RCVPRT = RCVPRT;
    }

    public void setRCVPFC(String RCVPFC) {
        this.RCVPFC = RCVPFC;
    }

    public void setRCVPRN(String RCVPRN) {
        this.RCVPRN = RCVPRN;
    }

    public void setRCVSAD(String RCVSAD) {
        this.RCVSAD = RCVSAD;
    }

    public void setRCVLAD(String RCVLAD) {
        this.RCVLAD = RCVLAD;
    }

    public void setCREDAT(String CREDAT) {
        this.CREDAT = CREDAT;
    }

    public void setCRETIM(String CRETIM) {
        this.CRETIM = CRETIM;
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

    public void setSERIAL(String SERIAL) {
        this.SERIAL = SERIAL;
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

    public String getDOCREL() {
        return DOCREL;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public String getDIRECT() {
        return DIRECT;
    }

    public String getOUTMOD() {
        return OUTMOD;
    }

    public String getEXPRSS() {
        return EXPRSS;
    }

    public String getTEST() {
        return TEST;
    }

    public String getIDOCTYP() {
        return IDOCTYP;
    }

    public String getCIMTYP() {
        return CIMTYP;
    }

    public String getMESTYP() {
        return MESTYP;
    }

    public String getMESCOD() {
        return MESCOD;
    }

    public String getMESFCT() {
        return MESFCT;
    }

    public String getSTD() {
        return STD;
    }

    public String getSTDVRS() {
        return STDVRS;
    }

    public String getSTDMES() {
        return STDMES;
    }

    public String getSNDPOR() {
        return SNDPOR;
    }

    public String getSNDPRT() {
        return SNDPRT;
    }

    public String getSNDPFC() {
        return SNDPFC;
    }

    public String getSNDPRN() {
        return SNDPRN;
    }

    public String getSNDSAD() {
        return SNDSAD;
    }

    public String getSNDLAD() {
        return SNDLAD;
    }

    public String getRCVPOR() {
        return RCVPOR;
    }

    public String getRCVPRT() {
        return RCVPRT;
    }

    public String getRCVPFC() {
        return RCVPFC;
    }

    public String getRCVPRN() {
        return RCVPRN;
    }

    public String getRCVSAD() {
        return RCVSAD;
    }

    public String getRCVLAD() {
        return RCVLAD;
    }

    public String getCREDAT() {
        return CREDAT;
    }

    public String getCRETIM() {
        return CRETIM;
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

    public String getSERIAL() {
        return SERIAL;
    }
}