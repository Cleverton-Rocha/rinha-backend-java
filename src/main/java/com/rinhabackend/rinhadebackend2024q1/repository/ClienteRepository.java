package com.rinhabackend.rinhadebackend2024q1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import com.rinhabackend.rinhadebackend2024q1.domain.Cliente;

import jakarta.persistence.LockModeType;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  Optional<Cliente> findClienteById(Long id);
}
