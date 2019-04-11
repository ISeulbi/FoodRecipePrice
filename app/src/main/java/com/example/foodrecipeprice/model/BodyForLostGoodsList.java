package com.example.foodrecipeprice.model;

import com.example.foodrecipeprice.model.LostGoods;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root
public class BodyForLostGoodsList {
        @ElementList(name = "items")
        private List<LostGoods> items;

        @Element
        private int numOfRows;

        @Element
        private int pageNo;

        @Element
        private int totalCount;

        public List<LostGoods> getItems() {
            return items;
        }

        public int getNumOfRows() {
            return numOfRows;
        }

        public int getPageNo() {
            return pageNo;
        }

        public int getTotalCount() {
            return totalCount;
        }

}
