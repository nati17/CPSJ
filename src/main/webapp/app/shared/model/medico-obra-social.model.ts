export interface IMedicoObraSocial {
    id?: number;
    idMedico?: string;
    idOSocial?: string;
    observaciones?: string;
}

export class MedicoObraSocial implements IMedicoObraSocial {
    constructor(public id?: number, public idMedico?: string, public idOSocial?: string, public observaciones?: string) {}
}
