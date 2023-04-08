package com.project.bankproj.entity;

import com.project.bankproj.entity.converter.RoleSetConverter;
import com.project.bankproj.entity.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID", strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Convert(converter = RoleSetConverter.class)
    private Set<Role> roles;

    @OneToOne()
    @JoinColumn(name = "manager_id",
            referencedColumnName = "id")
    private Manager manager;

    @OneToOne()
    @JoinColumn(name = "client_id",
            referencedColumnName = "id")
    private Client client;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && login.equals(user.login) && password.equals(user.password) && roles.equals(user.roles);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, roles, manager, client);
    }
}