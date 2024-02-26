package com.rinhabackend.rinhadebackend2024q1.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "cliente")
@Entity
@Data
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;

  @Column(nullable = false)
  private int limite;

  @Column(nullable = false)
  private int saldo;

  @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
  private List<Transacao> transacoes;

  public void realizarCredito(Integer valor) {
    this.saldo += valor;
  }

  public void realizarDebito(Integer valor) {
    this.saldo -= valor;
  }

  public void criarTransacao(Transacao transacao) {
    this.transacoes.add(transacao);
  }

}
