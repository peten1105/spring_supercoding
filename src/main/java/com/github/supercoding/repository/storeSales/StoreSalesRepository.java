package com.github.supercoding.repository.storeSales;


import org.springframework.stereotype.Repository;

@Repository
public interface StoreSalesRepository {

    StoreSales findSotreSalesById(Integer storeId);
    void updateSalesAmount(Integer storeId, Integer stock);

}
