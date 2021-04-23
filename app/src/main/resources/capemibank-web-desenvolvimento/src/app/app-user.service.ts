import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Conta} from "./model/conta";

@Injectable({
  providedIn: 'root'
})
export class AppUserService {

  private routerURL: string;
  private header = {
    headers: {
      'Access-Control-Allow-Origin': '*',
    }

  };

  constructor(private http: HttpClient) {
    this.routerURL = "http://localhost:8080"
  }

  public find(): Observable<Conta> {
    return this.http.get<Conta>(this.routerURL)
  }

  public saldo(conta: Conta) {
    return this.http.post(`${this.routerURL}/saldo`, conta);
  }

  public deposito(conta: Conta, valor: number) {
    return this.http.post(`${this.routerURL}/depositar/${valor}`, conta);
  }

  public saque(conta: Conta, valor: number) {
    return this.http.post(`${this.routerURL}/saque/${valor}`, conta);
  }
}
