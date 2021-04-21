package com.capgemini.bank.capemibank.service;

import com.capgemini.bank.capemibank.exception.ContaCorrenteNaoEncotradaException;
import com.capgemini.bank.capemibank.model.ContaCorrente;

public interface ContaCorrenteService {

    public Long saldoBancario(ContaCorrente contaCorrente) throws ContaCorrenteNaoEncotradaException;

    public ContaCorrente depositoBancario(ContaCorrente contaCorrente, Long valorDoDeposito) throws ContaCorrenteNaoEncotradaException;

    public ContaCorrente saqueBancario(ContaCorrente contaCorrente, Long valorDoSaque) throws ContaCorrenteNaoEncotradaException;
}
