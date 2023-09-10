package com.github.supercoding.repository.storeSales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreSalesJpaRepository extends JpaRepository<StoreSales, Integer> {
}
