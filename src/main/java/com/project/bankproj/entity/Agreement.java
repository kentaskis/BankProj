package com.project.bankproj.entity;

import com.project.bankproj.entity.enums.AccountProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="agreement")
public class Agreement {
    /**
     * id INT NOT NULL ID_SEQ.NEXTVAL,
     * 	account_id varchar(40) NOT NULL,
     * 	product_id INT NOT NULL,
     * 	status INT(1) NOT NULL,
     * 	interest_rate decimal(10,5) NULL
     * 	created_at TIMESTAMP NOT NULL,
     * 	updated_at TIMESTAMP NOT NULL,
     */

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AccountProductStatus status;

    @Column(name = "interest_rate")
    private float interestRate;

    @Column(name = "created_at")
    private int createdAt;

    @Column(name = "updated_at")
    private int updatedAt;

    @ManyToOne()
    @JoinColumn(name = "account_id",
            referencedColumnName = "id")
    private Account account;

    @ManyToOne()
    @JoinColumn(name = "product_id",
            referencedColumnName = "id")
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agreement agreement = (Agreement) o;
        return createdAt == agreement.createdAt && account.equals(agreement.account) && product.equals(agreement.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdAt, account, product);
    }

    @Override
    public String toString() {
        return "Agreement{" +
                "id=" + id +
                ", status=" + status +
                ", interestRate=" + interestRate +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", account=" + account +
                ", product=" + product +
                '}';
    }
}