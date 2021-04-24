package com.capgemini.bank.capemibank;

import com.capgemini.bank.capemibank.model.ContaCorrente;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ContaCorrenteServiceTest extends CapemibankApplicationTests {


    @Test
    public void findContaCorrente() {
        when(contaRepository.findAll()).thenReturn(Arrays.asList(contaCorrenteAbstract));
        service.findContaCorrente();
        verify(contaRepository).findById(1L);
    }

    @Test
    public void saldoBancario() {
        Double saldoBancario = service.saldoBancario(contaCorrenteMock);
        verify(contaRepository).findById(anyLong());
        assertEquals(0.0, saldoBancario.doubleValue());

        contaCorrenteAbstract.setSaldo(10.0);
        saldoBancario = service.saldoBancario(contaCorrenteMock);
        assertEquals(10.0, saldoBancario.doubleValue());

        contaCorrenteAbstract.setSaldo(5.0);
        saldoBancario = service.saldoBancario(contaCorrenteMock);
        assertEquals(5.0, saldoBancario.doubleValue());
    }

    @Test
    public void depositoBancario() {
        ContaCorrente contaRetornada = service.depositoBancario(contaCorrenteMock, 305.6);
        verify(contaRepository).findById(anyLong());
        verify(contaRepository).save(contaCorrenteAbstract);
        assertEquals(305.6, contaRetornada.getSaldo().doubleValue());

        contaRetornada = service.depositoBancario(contaCorrenteMock, 100.0);
        assertEquals(405.6, contaRetornada.getSaldo().doubleValue());
    }

    @Test
    public void saqueBancario() {
        contaCorrenteAbstract.setSaldo(3001.0);
        ContaCorrente contaRetornada = service.saqueBancario(contaCorrenteMock, 1000.50);
        verify(contaRepository).findById(anyLong());
        verify(contaRepository).save(contaCorrenteAbstract);
        assertEquals(2000.50, contaRetornada.getSaldo().doubleValue());

        contaRetornada = service.saqueBancario(contaCorrenteMock, 500.0);
        assertEquals(1500.50, contaRetornada.getSaldo().doubleValue());
    }
}
