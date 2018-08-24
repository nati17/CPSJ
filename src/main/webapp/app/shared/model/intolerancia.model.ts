import { IAntecedentesPersonales } from 'app/shared/model//antecedentes-personales.model';

export interface IIntolerancia {
    id?: number;
    valor?: string;
    values?: IAntecedentesPersonales[];
}

export class Intolerancia implements IIntolerancia {
    constructor(public id?: number, public valor?: string, public values?: IAntecedentesPersonales[]) {}
}
