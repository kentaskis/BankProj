package com.project.bankproj.entity;

import com.project.bankproj.entity.enums.AccountProductStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountProduct {
    /**
     * id INT NOT NULL ID_SEQ.NEXTVAL,
     * 	account_id varchar(40) NOT NULL,
     * 	product_id INT NOT NULL,
     * 	status INT(1) NOT NULL,
     * 	created_at TIMESTAMP NOT NULL,
     * 	updated_at TIMESTAMP NOT NULL,
     * 	removed_at TIMESTAMP NOT NULL,
     */
    private int id;
    private String accountId;
    private Product product;
    private AccountProductStatus status;
    private int createdAt;
    private int updatedAt;
    private int removedAt;
}