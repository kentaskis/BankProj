package com.project.bankproj.repository;

import com.project.bankproj.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("select p from Product p join p.agreements a group by p.id having count(a.id)>:agreementCount")
    List<Product> findAllProductsWhereAgreementsQuantityMoreThan(@Param("agreementCount") int agreementCount);
}