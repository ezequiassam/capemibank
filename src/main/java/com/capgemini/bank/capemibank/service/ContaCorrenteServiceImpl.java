package com.capgemini.bank.capemibank.service;

import com.capgemini.bank.capemibank.exception.ContaCorrenteNaoEncotradaException;
import com.capgemini.bank.capemibank.model.ContaCorrente;
import com.capgemini.bank.capemibank.model.HistoricoBancario;
import com.capgemini.bank.capemibank.model.OperacaoBancaria;
import com.capgemini.bank.capemibank.persistence.ContaCorrenteRepository;

import java.util.Date;
import java.util.Optional;

public class ContaCorrenteServiceImpl implements ContaCorrenteService {

    private ContaCorrenteRepository provider;

    public ContaCorrenteServiceImpl(ContaCorrenteRepository provider) {
        this.provider = provider;
    }

    @Override
    public Long saldoBancario(ContaCorrente contaCorrente) throws ContaCorrenteNaoEncotradaException {
        ContaCorrente conta = getConta(contaCorrente);
        registrarOperacao(conta, OperacaoBancaria.SALDO);
        return conta.getSaldo();
    }

    @Override
    public ContaCorrente depositoBancario(ContaCorrente contaCorrente, Long valorDoDeposito) throws ContaCorrenteNaoEncotradaException {
        ContaCorrente conta = getConta(contaCorrente);
        conta.setSaldo(valorDoDeposito);
        conta = registrarOperacao(conta, OperacaoBancaria.DEPOSITO);
        provider.save(conta);
        return conta;
    }

    @Override
    public ContaCorrente saqueBancario(ContaCorrente contaCorrente, Long valorDoSaque) throws ContaCorrenteNaoEncotradaException {
        ContaCorrente conta = getConta(contaCorrente);
        Long saldoRestante = valorDoSaque - conta.getSaldo();
        conta.setSaldo(saldoRestante);
        conta = registrarOperacao(conta, OperacaoBancaria.SAQUE);
        provider.save(conta);
        return conta;
    }

    private ContaCorrente getConta(ContaCorrente contaCorrente) throws ContaCorrenteNaoEncotradaException {
        Optional<ContaCorrente> optional = provider.findById(contaCorrente.getId());
        return optional.orElseThrow(() -> new ContaCorrenteNaoEncotradaException("Não foi possivel encontrar a conta com id=" + contaCorrente.getId()));
    }

    private ContaCorrente registrarOperacao(ContaCorrente contaCorrente, OperacaoBancaria operacaoBancaria) {
        contaCorrente.getHistoricoBancario().add(new HistoricoBancario(operacaoBancaria, new Date()));
        return contaCorrente;
    }
}
