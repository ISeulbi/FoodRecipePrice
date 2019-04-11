package com.example.foodrecipeprice.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(strict = false)
public class LocationCodeResponse {
    @Element
    private BodyForLocation body;

    public BodyForLocation getBody() {
        return body;
    }
}
