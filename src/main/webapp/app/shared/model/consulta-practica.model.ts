export interface IConsultaPractica {
    id?: number;
    attribute?: string;
}

export class ConsultaPractica implements IConsultaPractica {
    constructor(public id?: number, public attribute?: string) {}
}
