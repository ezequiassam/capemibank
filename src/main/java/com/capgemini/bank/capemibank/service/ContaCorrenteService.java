package com.capgemini.bank.capemibank.service;

import com.capgemini.bank.capemibank.exception.ContaCorrenteNaoEncotradaException;
import com.capgemini.bank.capemibank.model.ContaCorrente;

public interface ContaCorrenteService {

    public ContaCorrente findContaCorrente() throws ContaCorrenteNaoEncotradaException;

    public Double saldoBancario(ContaCorrente contaCorrente) throws ContaCorrenteNaoEncotradaException;

    public ContaCorrente depositoBancario(ContaCorrente contaCorrente, Double valorDoDeposito) throws ContaCorrenteNaoEncotradaException;

    public ContaCorrente saqueBancario(ContaCorrente contaCorrente, Double valorDoSaque) throws ContaCorrenteNaoEncotradaException;
}
