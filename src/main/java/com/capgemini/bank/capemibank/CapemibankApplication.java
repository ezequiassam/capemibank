package com.capgemini.bank.capemibank;

import com.capgemini.bank.capemibank.model.ContaCorrente;
import com.capgemini.bank.capemibank.model.HistoricoBancario;
import com.capgemini.bank.capemibank.model.OperacaoBancaria;
import com.capgemini.bank.capemibank.persistence.ContaCorrenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class CapemibankApplication {

    @Autowired
    private ContaCorrenteRepository contaRepository;

    public static void main(String[] args) {
        SpringApplication.run(CapemibankApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setHistoricoBancario(Arrays.asList(new HistoricoBancario(OperacaoBancaria.ABERTURA, Calendar.getInstance().getTime())));
        contaRepository.save(contaCorrente);
    }

   /* @Bean
    CommandLineRunner init(ContaCorrenteRepository contaCorrenteRepository) {
        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setHistoricoBancario(Arrays.asList(new HistoricoBancario(OperacaoBancaria.ABERTURA, Calendar.getInstance().getTime())));
        contaCorrenteRepository.save(contaCorrente);
        return args -> {
            contaCorrenteRepository.findById(contaCorrente.getId()).get();
        };

    }*/

}
