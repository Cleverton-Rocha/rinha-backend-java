package com.rinhabackend.rinhadebackend2024q1.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import com.rinhabackend.rinhadebackend2024q1.domain.Cliente;
import com.rinhabackend.rinhadebackend2024q1.domain.Transacao;
import com.rinhabackend.rinhadebackend2024q1.exception.ClienteLimitExceededException;
import com.rinhabackend.rinhadebackend2024q1.exception.ClienteNotFoundException;
import com.rinhabackend.rinhadebackend2024q1.model.ExtratoResponse;
import com.rinhabackend.rinhadebackend2024q1.model.ExtratoTransacao;
import com.rinhabackend.rinhadebackend2024q1.model.Saldo;
import com.rinhabackend.rinhadebackend2024q1.model.TransacaoRequest;
import com.rinhabackend.rinhadebackend2024q1.model.TransacaoResponse;
import com.rinhabackend.rinhadebackend2024q1.repository.ClienteRepository;
import com.rinhabackend.rinhadebackend2024q1.repository.TransacaoRepository;

import jakarta.transaction.Transactional;

@Service
public class RinhaService {

  @Autowired
  private ClienteRepository clienteRepository;
  @Autowired
  private TransacaoRepository transacaoRepository;

  @Transactional
  public List<Cliente> returnClientes() {
    return clienteRepository.findAll();
  }

  @Transactional
  public TransacaoResponse transacao(Long id, TransacaoRequest request) {
    Cliente cliente = clienteRepository.findClienteById(id)
        .orElseThrow(ClienteNotFoundException::new);

    char tipo = request.tipo().charAt(0);

    if (tipo == 'c') {
      cliente.realizarCredito(request.valor().intValue());
    }

    if (tipo == 'd') {
      if (cliente.getSaldo() - request.valor().intValue() < -cliente.getLimite()) {
        throw new ClienteLimitExceededException();
      }
      cliente.realizarDebito(request.valor().intValue());
    }

    Transacao transacao = criarTransacao(request, cliente, tipo);
    cliente.criarTransacao(transacao);
    clienteRepository.save(cliente);
    TransacaoResponse response = new TransacaoResponse(cliente.getLimite(), cliente.getSaldo());

    return response;
  }

  @Transactional
  public ExtratoResponse extrato(Long id) {
    Cliente cliente = clienteRepository.findById(id)
        .orElseThrow(ClienteNotFoundException::new);

    Saldo saldo = new Saldo(cliente.getSaldo(), LocalDateTime.now(), cliente.getLimite());
    List<ExtratoTransacao> transacoes = transacaoRepository
        .findByClienteIdOrderByRealizadaEmDesc(id, Limit.of(10)).stream()
        .map(t -> new ExtratoTransacao(
            t.getValor(), t.getTipo(), t.getDescricao(), t.getRealizadaEm()))
        .toList();

    ExtratoResponse extrato = new ExtratoResponse(saldo, transacoes);

    return extrato;
  }

  private Transacao criarTransacao(TransacaoRequest request, Cliente cliente, char tipo) {
    Transacao transacao = new Transacao();
    transacao.setCliente(cliente);
    transacao.setTipo(tipo);
    transacao.setValor(request.valor().intValue());
    transacao.setDescricao(request.descricao());
    transacao.setRealizadaEm(LocalDateTime.now());
    return transacao;
  }

}
