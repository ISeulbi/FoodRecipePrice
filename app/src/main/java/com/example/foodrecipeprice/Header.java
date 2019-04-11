package com.example.foodrecipeprice;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
class Header {
    @Element
    private String resultCode;

    @Element
    private String resultMsg;

    public String getResultCode() {
        return resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }
}
