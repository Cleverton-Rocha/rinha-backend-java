package com.rinhabackend.rinhadebackend2024q1.repository;

import java.util.List;

import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rinhabackend.rinhadebackend2024q1.domain.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
  List<Transacao> findByClienteIdOrderByRealizadaEmDesc(Long id, Limit limit);
}
