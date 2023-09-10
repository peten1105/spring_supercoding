package com.github.supercoding.repository.items;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

@Repository
public interface ElectronicStoreItemJpaRepository extends JpaRepository<ItemEntity, Integer> {

    List<ItemEntity> findItemEntitiesByTypeIn(List<String> types);

    List<ItemEntity> findItemEntitiesByPriceLessThanEqualOrderByPriceAsc(Integer maxValue);
    Page<ItemEntity> findAllByTypeIn(List<String> types, Pageable pageable);

}
