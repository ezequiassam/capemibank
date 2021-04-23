package com.capgemini.bank.capemibank.service;

import com.capgemini.bank.capemibank.exception.ContaCorrenteNaoEncotradaException;
import com.capgemini.bank.capemibank.model.ContaCorrente;
import com.capgemini.bank.capemibank.model.HistoricoBancario;
import com.capgemini.bank.capemibank.model.OperacaoBancaria;
import com.capgemini.bank.capemibank.persistence.ContaCorrenteRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ContaCorrenteServiceImpl implements ContaCorrenteService {

    private ContaCorrenteRepository provider;

    public ContaCorrenteServiceImpl(ContaCorrenteRepository provider) {
        this.provider = provider;
    }


    @Override
    public ContaCorrente findContaCorrente() throws ContaCorrenteNaoEncotradaException {
        Optional<ContaCorrente> providerById = provider.findById(1L);
        if (providerById.isPresent()){
            return providerById.get();
        }
        Iterable<ContaCorrente> contasCorrentes = provider.findAll();
        if (contasCorrentes == null || !contasCorrentes.iterator().hasNext()) {
            throw new ContaCorrenteNaoEncotradaException("Nenhuma conta corrente foi localizada");
        }
        return contasCorrentes.iterator().next();
    }

    @Override
    public Double saldoBancario(ContaCorrente contaCorrente) throws ContaCorrenteNaoEncotradaException {
        ContaCorrente conta = getConta(contaCorrente);
        registrarOperacao(conta, OperacaoBancaria.SALDO);
        provider.save(conta);
        return conta.getSaldo();
    }

    @Override
    public ContaCorrente depositoBancario(ContaCorrente contaCorrente, Double valorDoDeposito) throws ContaCorrenteNaoEncotradaException {
        ContaCorrente conta = getConta(contaCorrente);
        conta.setSaldo(conta.getSaldo() + valorDoDeposito);
        conta = registrarOperacao(conta, OperacaoBancaria.DEPOSITO);
        provider.save(conta);
        return conta;
    }

    @Override
    public ContaCorrente saqueBancario(ContaCorrente contaCorrente, Double valorDoSaque) throws ContaCorrenteNaoEncotradaException {
        ContaCorrente conta = getConta(contaCorrente);
        Double saldoRestante = conta.getSaldo() - valorDoSaque;
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
