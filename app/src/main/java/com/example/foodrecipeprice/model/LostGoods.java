package com.example.foodrecipeprice.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "items")
public class LostGoods {

    @Element(name = "atcId")
    private String atcId;
    @Element(name = "lstPlace")
    private String lstPlace;
    @Element(name = "lstPrdtNm")
    private String lstPrdtNm;
    @Element(name = "lstSbjt")
    private String lstSbjt;
    @Element(name = "lstYmd")
    private String lstYmd;
    @Element(name = "prdtClNm")
    private String prdtClNm;
    @Element(name = "rnum")
    private int rnum;

    public String getAtcId() {
        return atcId;
    }

    public String getLstPlace() {
        return lstPlace;
    }

    public String getLstPrdtNm() {
        return lstPrdtNm;
    }

    public String getLstSbjt() {
        return lstSbjt;
    }

    public String getLstYmd() {
        return lstYmd;
    }

    public String getPrdtClNm() {
        return prdtClNm;
    }

    public int getRnum() {
        return rnum;
    }
}
