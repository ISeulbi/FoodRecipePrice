package com.example.foodrecipeprice.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item")
public class LocationDetail {
    @Element(name = "cdNm")
    private String cdNm;
    @Element(name = "commCd")
    private String commCd;
    @Element(name = "commGrpCd")
    private String commGrpCd;
    @Element(name = "grpNm")
    private String grpNm;

    public String getCdNm() {
        return cdNm;
    }

    public void setCdNm(String cdNm) {
        this.cdNm = cdNm;
    }

    public String getCommCd() {
        return commCd;
    }

    public void setCommCd(String commCd) {
        this.commCd = commCd;
    }

    public String getCommGrpCd() {
        return commGrpCd;
    }

    public void setCommGrpCd(String commGrpCd) {
        this.commGrpCd = commGrpCd;
    }

    public String getGrpNm() {
        return grpNm;
    }

    public void setGrpNm(String grpNm) {
        this.grpNm = grpNm;
    }
}
