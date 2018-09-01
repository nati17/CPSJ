export interface IMontoConsultaPractica {
    id?: number;
    idMontoConsPract?: string;
    monto?: string;
}

export class MontoConsultaPractica implements IMontoConsultaPractica {
    constructor(public id?: number, public idMontoConsPract?: string, public monto?: string) {}
}
