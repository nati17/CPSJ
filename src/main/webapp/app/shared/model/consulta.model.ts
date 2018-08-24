import { Moment } from 'moment';

export interface IConsulta {
    id?: number;
    fechaConsulta?: Moment;
    observacionesConsulta?: string;
}

export class Consulta implements IConsulta {
    constructor(public id?: number, public fechaConsulta?: Moment, public observacionesConsulta?: string) {}
}
