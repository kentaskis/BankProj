package com.project.bankproj.repository;

import com.project.bankproj.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {
    @Query("SELECT m,c FROM Manager m JOIN m.clients c WHERE c.firstName IS NOT NULL")
    List<Manager> findAllWhichContainsClient();
}