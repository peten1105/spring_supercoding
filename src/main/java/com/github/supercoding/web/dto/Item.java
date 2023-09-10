package com.github.supercoding.web.dto;

import com.github.supercoding.respository.items.ItemEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class Item {
    private String id;
    private String name;
    private String type;
    private Integer price;
    private Spec spec;
}