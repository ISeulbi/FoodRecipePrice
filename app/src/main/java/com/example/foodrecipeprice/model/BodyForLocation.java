package com.example.foodrecipeprice.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(strict = false)
public class BodyForLocation {
       @ElementList
       private List<LocationDetail> items;

        public List<LocationDetail> getItems() {
            return items;
    }
}
