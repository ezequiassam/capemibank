package com.capgemini.bank.capemibank.controller;

import com.capgemini.bank.capemibank.exception.ContaCorrenteNaoEncotradaException;
import com.capgemini.bank.capemibank.model.ContaCorrente;
import com.capgemini.bank.capemibank.persistence.ContaCorrenteRepository;
import com.capgemini.bank.capemibank.service.ContaCorrenteService;
import com.capgemini.bank.capemibank.service.ContaCorrenteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/capemibank/conta")
public class ContaCorrenteController {

    @Autowired
    private ContaCorrenteRepository repository;

    @Autowired
    private ContaCorrenteService service = new ContaCorrenteServiceImpl(repository);


    @GetMapping(produces = "application/json")
    @ResponseBody
    public ResponseEntity<ContaCorrente> find() {
        ContaCorrente contaCorrente = service.findContaCorrente();
        return new ResponseEntity<>(contaCorrente, HttpStatus.OK);
    }

    @PostMapping(value = "/saldo", produces = "application/json")
    public ResponseEntity<Long> saldo(@RequestBody ContaCorrente contaCorrente) throws ContaCorrenteNaoEncotradaException {
        Long saldoBancario = service.saldoBancario(contaCorrente);
        return new ResponseEntity<>(saldoBancario, HttpStatus.OK);
    }

    @PutMapping(value = "/depositar/{valor}", produces = "application/json")
    public ResponseEntity<ContaCorrente> depositar(@PathVariable("valor") Long valorDeposito, @RequestBody ContaCorrente contaCorrente) throws ContaCorrenteNaoEncotradaException {
        ContaCorrente conta = service.depositoBancario(contaCorrente, valorDeposito);
        return new ResponseEntity<>(conta, HttpStatus.CREATED);
    }

    @PutMapping(value = "/saque/{valor}", produces = "application/json")
    public ResponseEntity<ContaCorrente> sacar(@PathVariable("valor") Long valorSaque, @RequestBody ContaCorrente contaCorrente) throws ContaCorrenteNaoEncotradaException {
        ContaCorrente conta = service.saqueBancario(contaCorrente, valorSaque);
        return new ResponseEntity<>(conta, HttpStatus.CREATED);
    }


    @ExceptionHandler({ContaCorrenteNaoEncotradaException.class})
    public ResponseEntity<Error> handleContaCorrenteNaoEncotradaException(ContaCorrenteNaoEncotradaException e) {
        return new ResponseEntity<>(new Error(e), HttpStatus.NOT_FOUND);
    }

    class Error {
        private final List<ModelMap> errors;

        public Error(Exception err) {
            this.errors = getModelMap(err);
        }

        public Error(List<ModelMap> err) {
            this.errors = err;
        }

        private List<ModelMap> getModelMap(Exception error) {
            List<ModelMap> list = new ArrayList<>();
            ModelMap map = new ModelMap();
            map.put("defaultMessage", error.getMessage());
            map.put("tipo", true);
            list.add(map);
            return list;
        }

        public List<ModelMap> getErrors() {
            return errors;
        }
    }
}
