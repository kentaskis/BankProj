package com.project.bankproj.repository;

import com.project.bankproj.entity.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgreementRepository extends JpaRepository<Agreement, Integer> {
    @Query("select a from Agreement a join a.product p join p.manager m left join a.account where p.manager.id = :managerId")
    List<Agreement> findAllAgreementsWhereManagerIdIs(@Param("managerId") int managerId);
}