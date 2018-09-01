export interface IObservacion {
    id?: number;
    idObservacion?: string;
    descripcionObservacion?: string;
}

export class Observacion implements IObservacion {
    constructor(public id?: number, public idObservacion?: string, public descripcionObservacion?: string) {}
}
