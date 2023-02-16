package com.project.bankproj.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "transaction")
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    /**
     *	id varchar(40) NOT NULL,
     * 	debit_account_id varchar(40) NOT NULL,
     * 	credit_account_id varchar(40) NOT NULL,
     * 	type INT(1) NOT NULL,
     * 	amount DECIMAL(12.4) NOT NULL,
     * 	fee DECIMAL(12.4) NOT NULL,
     * 	description varchar(255) NOT NULL,
     * 	created_at TIMESTAMP NOT NULL,
     */

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private int type;

    @Column(name = "amount")
    private float amount;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private int createdAt;

    @ManyToOne()
    @JoinColumn(name = "debit_account_id",
            referencedColumnName = "id")
    private Account debitAccountId;

    @ManyToOne()
    @JoinColumn(name = "credit_account_id",
            referencedColumnName = "id")
    private Account creditAccountId;
}