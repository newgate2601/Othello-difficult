package com.example.Othellodifficult.repository;

import com.example.Othellodifficult.entity.DifficultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DifficultRepository extends JpaRepository<DifficultEntity, Long> {
}
