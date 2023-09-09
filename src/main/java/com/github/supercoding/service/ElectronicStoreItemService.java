package com.github.supercoding.service;


import com.github.supercoding.respository.ElectronicStoreItemRepository;
import com.github.supercoding.respository.ItemEntity;
import com.github.supercoding.web.dto.Item;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ElectronicStoreItemService {
    private ElectronicStoreItemRepository electronicStoreItemRepository;

    public ElectronicStoreItemService(ElectronicStoreItemRepository electronicStoreItemRepository) {
        this.electronicStoreItemRepository = electronicStoreItemRepository;
    }

    public List<Item> findAll() {
        List<ItemEntity> itemEntities = electronicStoreItemRepository.findAllItems();
        return itemEntities.stream().map(Item::new).collect(Collectors.toList());
    }


}
