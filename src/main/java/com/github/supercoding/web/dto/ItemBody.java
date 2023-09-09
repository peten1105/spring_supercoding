package com.github.supercoding.web.dto;

public class ItemBody {

    private String name;
    private String type;
    private Integer price;
    private Spec spec;

    public String getName() {
        return name;
    }

    public ItemBody() {
    }

    public String getType() {
        return type;
    }

    public Integer getPrice() {
        return price;
    }

    public Spec getSpec() {
        return spec;
    }
}
