export interface IMedicoCalendario {
    id?: number;
    idMedico?: string;
    idCalendario?: string;
    estado?: boolean;
}

export class MedicoCalendario implements IMedicoCalendario {
    constructor(public id?: number, public idMedico?: string, public idCalendario?: string, public estado?: boolean) {
        this.estado = false;
    }
}
