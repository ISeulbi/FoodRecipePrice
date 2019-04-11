package com.example.foodrecipeprice.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class LostGoodsDetailResponse {

    @Element
    private BodyForLostGoodDetail body;

    public BodyForLostGoodDetail getBody() {
        return body;
    }
}
