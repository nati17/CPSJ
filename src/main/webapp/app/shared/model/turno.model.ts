import { Moment } from 'moment';

export interface ITurno {
    id?: number;
    fechaTurno?: Moment;
    horaTurno?: string;
    duracion?: string;
}

export class Turno implements ITurno {
    constructor(public id?: number, public fechaTurno?: Moment, public horaTurno?: string, public duracion?: string) {}
}
