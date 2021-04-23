package com.capgemini.bank.capemibank;

import com.capgemini.bank.capemibank.model.ContaCorrente;
import com.capgemini.bank.capemibank.model.HistoricoBancario;
import com.capgemini.bank.capemibank.model.OperacaoBancaria;
import com.capgemini.bank.capemibank.persistence.ContaCorrenteRepository;
import com.capgemini.bank.capemibank.service.ContaCorrenteService;
import com.capgemini.bank.capemibank.service.ContaCorrenteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
abstract class CapemibankApplicationTests {

    @MockBean
    protected static ContaCorrenteRepository contaRepository;

    protected ContaCorrenteService service;
    protected ContaCorrente contaCorrenteAbstract;
    protected ContaCorrente contaCorrenteMock;


    @BeforeEach
    void init() {
        contaCorrenteAbstract = new ContaCorrente();
        contaCorrenteAbstract.setHistoricoBancario(Arrays.asList(new HistoricoBancario(OperacaoBancaria.ABERTURA, new Date())));

        when(contaRepository.findById(anyLong())).thenReturn(Optional.of(contaCorrenteAbstract));

        service = new ContaCorrenteServiceImpl(contaRepository);

        contaCorrenteMock = Mockito.mock(ContaCorrente.class);
    }

}
