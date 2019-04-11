package com.example.foodrecipeprice.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class LostGoodsResponse {
    @Element
    private Header header;
    @Element
    private BodyForLostGoodsList body;

    public Header getHeader() {
        return header;
    }

    public BodyForLostGoodsList getBody() {
        return body;
    }
}

