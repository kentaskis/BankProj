package com.project.bankproj.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;
import java.util.UUID;

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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "com.project.bankproj.generator.UuidTimeSequenceGenerator")
    private UUID id;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return createdAt == that.createdAt && Objects.equals(debitAccountId, that.debitAccountId) && Objects.equals(creditAccountId, that.creditAccountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdAt, debitAccountId, creditAccountId);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", debitAccountId=" + debitAccountId +
                ", creditAccountId=" + creditAccountId +
                '}';
    }
}