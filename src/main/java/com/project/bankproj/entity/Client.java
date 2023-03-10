package com.project.bankproj.entity;

import com.project.bankproj.entity.enums.ClientStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
public class Client {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID", strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private ClientStatus status;

    @Column(name = "tax_code")
    private String taxCode;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToOne()
    @JoinColumn(name = "manager_id",
            referencedColumnName = "id")
    private Manager manager;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private Set<Account> accounts = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return taxCode.equals(client.taxCode) && email.equals(client.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taxCode, email);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", status=" + status +
                ", taxCode='" + taxCode + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}