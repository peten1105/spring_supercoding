package com.github.supercoding.web.controller;

import com.github.supercoding.service.ElectronicStoreItemService;
import com.github.supercoding.web.dto.BuyOrder;
import com.github.supercoding.web.dto.Item;
import com.github.supercoding.web.dto.ItemBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ElectronicStoreController {

    private final ElectronicStoreItemService electronicStoreItemService;

    @GetMapping("/items")
    public List<Item> findAllItem() {
        return electronicStoreItemService.findAll();
    }

    @PostMapping("/items")
    public String registerItem(@RequestBody ItemBody itemBody) {
        Integer itemID = electronicStoreItemService.saveItem(itemBody);
        return "ID: " + itemID;
    }

    @GetMapping("/items/{id}")
    public Item findItemById(@PathVariable String id){
        return electronicStoreItemService.findItemById(id);
    }

    @GetMapping("/items-query")
    public Item findItemByQueryId(@RequestParam("id") String id ){
        return electronicStoreItemService.findItemById(id);
    }

    @GetMapping("/items-queries")
    public List<Item> findItemByQueryIds(@RequestParam("id") List<String> ids ){
        return electronicStoreItemService.findItemsByIds(ids);
    }

    @DeleteMapping("/items/{id}")
    public String deleteItemById(@PathVariable String id) {
        electronicStoreItemService.deleteItem(id);
        return "Object with id =" + id + "has been deleted";
    }

    @PutMapping("/items/{id}")
    public Item updateItem(@PathVariable String id, @RequestBody ItemBody itemBody) {
        return electronicStoreItemService.updateItem(id, itemBody);
    }

    @PostMapping("/items/buy")
    public String buyItem(@RequestBody BuyOrder buyOrder){
        Integer OrderItemNums = electronicStoreItemService.buyItems(buyOrder);
        return "요청하신 Item 중 " + OrderItemNums + "개를 구매 하였습니다.";
    }

}
