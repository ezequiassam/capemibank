import {HistoricoBancario} from "./historico-bancario";

export class Conta {
  id: number = 0;
  saldo: number = 0;
  historicoBancario: HistoricoBancario[] = [];
}
