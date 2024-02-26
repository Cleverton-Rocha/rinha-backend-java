package com.rinhabackend.rinhadebackend2024q1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rinhabackend.rinhadebackend2024q1.domain.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
