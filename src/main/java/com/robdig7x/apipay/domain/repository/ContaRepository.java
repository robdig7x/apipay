package com.robdig7x.apipay.domain.repository;

import com.robdig7x.apipay.domain.model.entity.Conta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    List<Conta> findAllByDataVencimentoBetweenAndDescricaoContaining(LocalDate startDate, LocalDate endDate, String descricao);

    Page<Conta> findAllByDataVencimentoBetweenAndDescricaoContaining(
            LocalDate startDate,
            LocalDate endDate,
            String descricao,
            Pageable pageable
    );

}