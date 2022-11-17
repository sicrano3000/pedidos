package com.infnet.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infnet.projeto.model.Massa;

@Repository
public interface MassaRepository extends JpaRepository<Massa, Long> {

}
