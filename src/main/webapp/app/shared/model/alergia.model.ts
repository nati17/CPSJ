import { IAntecedentesPersonales } from 'app/shared/model//antecedentes-personales.model';

export interface IAlergia {
    id?: number;
    valor?: string;
    values?: IAntecedentesPersonales[];
}

export class Alergia implements IAlergia {
    constructor(public id?: number, public valor?: string, public values?: IAntecedentesPersonales[]) {}
}
