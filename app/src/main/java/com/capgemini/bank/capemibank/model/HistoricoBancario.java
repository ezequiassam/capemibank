package com.capgemini.bank.capemibank.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "historicobancario")
public class HistoricoBancario {

    public HistoricoBancario() {
    }

    public HistoricoBancario(OperacaoBancaria operacaoBancaria, Date dataOperacao) {
        this.operacaoBancaria = operacaoBancaria;
        this.dataOperacao = dataOperacao;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private OperacaoBancaria operacaoBancaria;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.DATE)
    private Date dataOperacao;


    public Long getId() {
        return id;
    }

    public OperacaoBancaria getOperacaoBancaria() {
        return operacaoBancaria;
    }

    public void setOperacaoBancaria(OperacaoBancaria operacaoBancaria) {
        this.operacaoBancaria = operacaoBancaria;
    }

    public Date getDataOperacao() {
        return dataOperacao;
    }

    public void setDataOperacao(Date dataOperacao) {
        this.dataOperacao = dataOperacao;
    }
}
