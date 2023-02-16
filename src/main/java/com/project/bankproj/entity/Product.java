package com.project.bankproj.entity;

import com.project.bankproj.entity.enums.Currencies;
import com.project.bankproj.entity.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    /**
     * 	id INT NOT NULL ID_SEQ.NEXTVAL,
     * 	managet_id INT NOT NULL,
     * 	name varchar(70) NOT NULL,
     * 	status INT(1) NOT NULL,
     * 	curency_code INT(2) NOT NULL,
     * 	interest_rate INT(1) NOT NULL,
     * 	limit INT NOT NULL,
     * 	created_at TIMESTAMP NOT NULL,
     * 	updated_at TIMESTAMP NOT NULL,
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency_code")
    private Currencies currency;

    @Column(name = "interest_rate")
    private int interestRate;

    @Column(name = "limit")
    private int limit;

    @Column(name = "created_at")
    private int createdAt;

    @Column(name = "updated_at")
    private int updatedAt;

    @ManyToOne()
    @JoinColumn(name = "managet_id",
            referencedColumnName = "id")
    private Manager manager;
}