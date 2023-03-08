package com.project.bankproj.entity;

import com.project.bankproj.entity.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "transaction")
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID", strategy = GenerationType.UUID)

    private UUID id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type")
    private TransactionType type;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private Timestamp createdAt;

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