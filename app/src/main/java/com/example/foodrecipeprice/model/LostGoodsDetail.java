package com.example.foodrecipeprice.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item", strict = false)
public class LostGoodsDetail {
    @Element
    private String atcId;
    @Element
    private String lstFilePathImg;
    @Element
    private String lstHor;
    @Element
    private String lstLctNm;
    @Element
    private String lstPlace;
    @Element
    private String lstPlaceSeNm;
    @Element
    private String lstPrdtNm;
    @Element
    private String lstSbjt;
    @Element
    private String lstSteNm;
    @Element
    private String lstYmd;
    @Element
    private String orgId;
    @Element
    private String orgNm;
    @Element
    private String prdtClNm;
    @Element
    private String tel;
    @Element
    private String uniq;

    public String getAtcId() {
        return atcId;
    }

    public String getLstFilePathImg() {
        return lstFilePathImg;
    }

    public String getLstHor() {
        return lstHor;
    }

    public String getLstLctNm() {
        return lstLctNm;
    }

    public String getLstPlace() {
        return lstPlace;
    }

    public String getLstPlaceSeNm() {
        return lstPlaceSeNm;
    }

    public String getLstPrdtNm() {
        return lstPrdtNm;
    }

    public String getLstSbjt() {
        return lstSbjt;
    }

    public String getLstSteNm() {
        return lstSteNm;
    }

    public String getLstYmd() {
        return lstYmd;
    }

    public String getOrgId() {
        return orgId;
    }

    public String getOrgNm() {
        return orgNm;
    }

    public String getPrdtClNm() {
        return prdtClNm;
    }

    public String getTel() {
        return tel;
    }

    public String getUniq() {
        return uniq;
    }
}
