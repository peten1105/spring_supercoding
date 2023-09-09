package com.github.supercoding.web.dto;

public class BuyOrder {
    private Integer itemId;
    private Integer itemNums;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getItemNums() {
        return itemNums;
    }

    public void setItemNums(Integer itemNums) {
        this.itemNums = itemNums;
    }

    public BuyOrder() {
    }
}
