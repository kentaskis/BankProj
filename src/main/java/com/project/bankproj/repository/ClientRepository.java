package com.project.bankproj.repository;

import com.project.bankproj.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

    @Query("SELECT  c  " +
            "from Client c " +
            "left join c.accounts a " +
            "left join a.creditTransactions " +
            "left join a.debitTransactions " +
            "group by c.id " +
            "having count(a.id) > :transactionCount")
    List<Client> getAllWhereTransactionCountMoreThen(@Param("transactionCount") int transactionCount);
}