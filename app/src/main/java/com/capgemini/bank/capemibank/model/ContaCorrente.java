package com.capgemini.bank.capemibank.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contacorrente")
public class ContaCorrente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double saldo = 0D;

    @Column(unique = true)
    private String numeroConta;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<HistoricoBancario> historicoBancario = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public List<HistoricoBancario> getHistoricoBancario() {
        return historicoBancario;
    }

    public void setHistoricoBancario(List<HistoricoBancario> historicoBancario) {
        this.historicoBancario = historicoBancario;
    }
}
