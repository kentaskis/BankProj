package com.project.bankproj.entity;

import com.project.bankproj.entity.enums.ManagerStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "manager")
public class Manager {
    /**
     * id INT NOT NULL ID_SEQ.NEXTVAL,
     * 	first_name varchar(50) NOT NULL,
     * 	last_name varchar(50) NOT NULL,
     * 	status INT(1) NOT NULL,
     * 	created_at TIMESTAMP NOT NULL,
     * 	updated_at TIMESTAMP NOT NULL,
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private ManagerStatus status;

    @Column(name = "created_at")
    public Timestamp createdAt;

    @Column(name = "updated_at")
    public Timestamp updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manager manager = (Manager) o;
        return createdAt == manager.createdAt && firstName.equals(manager.firstName) && lastName.equals(manager.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, createdAt);
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}