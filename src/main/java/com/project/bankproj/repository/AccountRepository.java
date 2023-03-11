package com.project.bankproj.repository;

import com.project.bankproj.entity.Account;
import com.project.bankproj.entity.enums.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    List<Account> findAccountByStatus(AccountStatus status);
}