package com.github.supercoding.respository.items;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@ToString
@Builder
public class ItemEntity {
    private Integer id;
    private String name;
    private String type;
    private Integer price;
    private Integer storeId;
    private Integer stock;
    private String cpu;
    private String capacity;

    public ItemEntity(Integer id, String name, String type, Integer price, String cpu, String capacity) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.cpu = cpu;
        this.capacity = capacity;
        this.storeId = null;
        this.stock = 0;
    }
}
