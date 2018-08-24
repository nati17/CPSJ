import { IAntecedentesPersonales } from 'app/shared/model//antecedentes-personales.model';

export interface IBebida {
    id?: number;
    valor?: string;
    values?: IAntecedentesPersonales[];
}

export class Bebida implements IBebida {
    constructor(public id?: number, public valor?: string, public values?: IAntecedentesPersonales[]) {}
}
