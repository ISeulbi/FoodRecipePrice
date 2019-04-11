package com.example.foodrecipeprice.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root
public class BodyForLostGoodDetail {
    @Element
    public LostGoodsDetail item;

    public LostGoodsDetail getItem() {
        return item;
    }

    public void setItem(LostGoodsDetail item) {
        this.item = item;
    }
}
