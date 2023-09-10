package com.github.supercoding.web.dto;


import com.github.supercoding.repository.items.ItemEntity;
import com.github.supercoding.repository.storeSales.StoreSales;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class StoreInfo {
    private Integer id;
    private String storeName;
    private Integer amount;
    private List<String> itemNames;

    public StoreInfo(StoreSales storeSales) {
        this.id = storeSales.getId();
        this.storeName = storeSales.getStoreName();
        this.amount = storeSales.getAmount();
        this.itemNames = storeSales.getItemEntities().stream().map(ItemEntity::getName).collect(Collectors.toList());
    }
}