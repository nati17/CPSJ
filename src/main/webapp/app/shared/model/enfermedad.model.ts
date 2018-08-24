import { IAntecedentesPersonales } from 'app/shared/model//antecedentes-personales.model';

export interface IEnfermedad {
    id?: number;
    valor?: string;
    values?: IAntecedentesPersonales[];
}

export class Enfermedad implements IEnfermedad {
    constructor(public id?: number, public valor?: string, public values?: IAntecedentesPersonales[]) {}
}
