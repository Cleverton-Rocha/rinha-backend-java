package com.rinhabackend.rinhadebackend2024q1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rinhabackend.rinhadebackend2024q1.domain.Cliente;
import com.rinhabackend.rinhadebackend2024q1.exception.ClienteLimitExceededException;
import com.rinhabackend.rinhadebackend2024q1.exception.ClienteNotFoundException;
import com.rinhabackend.rinhadebackend2024q1.model.TipoTransacao;
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
    Optional<Cliente> clienteExists = clienteRepository.findById(id);

    if (clienteExists.isEmpty())
      throw new ClienteNotFoundException();

    Cliente cliente = clienteExists.get();

    TipoTransacao tipo = request.tipo();

    if (tipo == TipoTransacao.c) {
      cliente.realizarCredito(request.valor().intValue());
    }

    if (tipo == TipoTransacao.d) {
      if (cliente.getSaldo() - request.valor() < -cliente.getLimite()) {
        throw new ClienteLimitExceededException();
      }
      cliente.realizarDebito(request.valor().intValue());
    }

    TransacaoResponse response = new TransacaoResponse(cliente.getLimite(), cliente.getSaldo());

    return response;
  }

}
