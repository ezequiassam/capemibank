import {Component, OnInit} from '@angular/core';
import {Conta} from "./model/conta";
import {AppUserService} from "./app-user.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  conta: Conta = new Conta();
  title = 'Capemibank Web está em execução!';
  resumo = '';
  valor = 0;
  showDep = false;
  showSac = false;

  constructor(private appService: AppUserService) {
  }

  ngOnInit() {
    this.getConta();
  }

  getConta() {
    this.appService.find().subscribe(data => {
      this.conta = data;
    })
  }

  getSaldo() {
    this.appService.saldo(this.conta).subscribe(data => {
      this.resumo = `Seu Saldo é de ${data}`;
    })
  }

  depositar() {
    this.appService.deposito(this.conta, this.valor).subscribe(data => {
      this.resumo = `Deposito no valor de ${this.valor} foi realizado com sucesso!`;
      this.showDep = false;
      this.valor = 0;
    })
  }

  sacar() {
    this.appService.saque(this.conta, this.valor).subscribe(data => {
      this.resumo = `Saque no valor de ${this.valor} foi realizado com sucesso!`;
      this.showSac = false;
      this.valor = 0;
    })
  }
}
