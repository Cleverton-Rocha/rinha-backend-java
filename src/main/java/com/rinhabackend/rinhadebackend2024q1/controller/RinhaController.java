package com.rinhabackend.rinhadebackend2024q1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rinhabackend.rinhadebackend2024q1.domain.Cliente;
import com.rinhabackend.rinhadebackend2024q1.model.ExtratoResponse;
import com.rinhabackend.rinhadebackend2024q1.model.TransacaoRequest;
import com.rinhabackend.rinhadebackend2024q1.model.TransacaoResponse;
import com.rinhabackend.rinhadebackend2024q1.service.RinhaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class RinhaController {

  @Autowired
  private RinhaService rinhaService;

  @GetMapping
  public ResponseEntity<List<Cliente>> allClientes() {
    List<Cliente> clientes = rinhaService.returnClientes();

    return ResponseEntity.ok(clientes);
  }

  @PostMapping("/{id}/transacoes")
  public ResponseEntity<TransacaoResponse> transacao(@PathVariable Long id,
      @RequestBody @Valid TransacaoRequest request) {
    TransacaoResponse transacao = rinhaService.transacao(id, request);

    return ResponseEntity.ok(transacao);
  }

  @GetMapping("/{id}/extrato")
  public ResponseEntity<ExtratoResponse> extrato(@PathVariable Long id) {
    ExtratoResponse extrato = rinhaService.extrato(id);

    return ResponseEntity.ok(extrato);
  }

}
