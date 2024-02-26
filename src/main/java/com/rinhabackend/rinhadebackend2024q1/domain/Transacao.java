package com.rinhabackend.rinhadebackend2024q1.domain;

import java.time.LocalDateTime;

import com.rinhabackend.rinhadebackend2024q1.model.TipoTransacao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "transacao")
@Entity
@Data
public class Transacao {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "id_cliente")
  private Cliente cliente;

  @Column(nullable = false)
  private int valor;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TipoTransacao tipo;

  @Column(length = 10)
  private String descricao;

  @Column(name = "realizada_em", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private LocalDateTime realizadaEm;
}
