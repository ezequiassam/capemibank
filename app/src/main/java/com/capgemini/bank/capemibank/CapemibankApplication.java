package com.capgemini.bank.capemibank;

import com.capgemini.bank.capemibank.model.ContaCorrente;
import com.capgemini.bank.capemibank.model.HistoricoBancario;
import com.capgemini.bank.capemibank.model.OperacaoBancaria;
import com.capgemini.bank.capemibank.persistence.ContaCorrenteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class CapemibankApplication {

    public static void main(String[] args) {
        SpringApplication.run(CapemibankApplication.class, args);
    }

    @Bean
    CommandLineRunner init(ContaCorrenteRepository contaCorrenteRepository) {
        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setHistoricoBancario(Arrays.asList(new HistoricoBancario(OperacaoBancaria.ABERTURA, new Date())));
        contaCorrenteRepository.save(contaCorrente);
        return args -> {
            contaCorrenteRepository.findById(contaCorrente.getId()).get();
        };

    }

}
