package com.capgemini.bank.capemibank;

import com.capgemini.bank.capemibank.model.ContaCorrente;
import com.capgemini.bank.capemibank.model.HistoricoBancario;
import com.capgemini.bank.capemibank.model.OperacaoBancaria;
import com.capgemini.bank.capemibank.persistence.ContaCorrenteRepository;
import com.capgemini.bank.capemibank.service.ContaCorrenteService;
import com.capgemini.bank.capemibank.service.ContaCorrenteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
abstract class CapemibankApplicationTests {

    @MockBean
    protected ContaCorrenteRepository contaRepository;

    @Autowired
    protected ContaCorrenteService service;

    protected ContaCorrente contaCorrenteAbstract;
    protected ContaCorrente contaCorrenteMock;


    @BeforeEach
    void init() {
        contaCorrenteAbstract = new ContaCorrente();
        List<HistoricoBancario> hist = new ArrayList<>();
        hist.add(new HistoricoBancario(OperacaoBancaria.ABERTURA, new Date()));
        contaCorrenteAbstract.setHistoricoBancario(hist);

        when(contaRepository.findById(anyLong())).thenReturn(Optional.of(contaCorrenteAbstract));

        service = new ContaCorrenteServiceImpl(contaRepository);

        contaCorrenteMock = Mockito.mock(ContaCorrente.class);
    }

}
