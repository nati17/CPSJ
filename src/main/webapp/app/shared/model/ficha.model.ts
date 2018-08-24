import { Moment } from 'moment';

export interface IFicha {
    id?: number;
    fechaIngreso?: Moment;
    medicoNombreMedico?: string;
    medicoId?: number;
}

export class Ficha implements IFicha {
    constructor(public id?: number, public fechaIngreso?: Moment, public medicoNombreMedico?: string, public medicoId?: number) {}
}
