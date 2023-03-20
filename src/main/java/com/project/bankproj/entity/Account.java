package com.project.bankproj.entity;

import com.project.bankproj.entity.enums.AccountStatus;
import com.project.bankproj.entity.enums.AccountType;
import com.project.bankproj.entity.enums.CurrencyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "account")
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID", strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private AccountType type;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private AccountStatus status;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "currency_code")
    private CurrencyType currency;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToOne()
    @JoinColumn(name = "client_id",
            referencedColumnName = "id")
    private Client client;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "debitAccountId")
    private Set<Transaction> debitTransactions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creditAccountId")
    private Set<Transaction> creditTransactions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && currency == account.currency && Objects.equals(client, account.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, currency, client);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", balance=" + balance +
                ", currency=" + currency +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", client=" + client +
                ", debitTransactions=" + debitTransactions +
                '}';
    }
}