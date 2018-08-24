import { IAntecedentesPersonales } from 'app/shared/model//antecedentes-personales.model';

export interface IRegimen {
    id?: number;
    valor?: string;
    values?: IAntecedentesPersonales[];
}

export class Regimen implements IRegimen {
    constructor(public id?: number, public valor?: string, public values?: IAntecedentesPersonales[]) {}
}
