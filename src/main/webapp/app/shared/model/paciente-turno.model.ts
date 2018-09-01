export interface IPacienteTurno {
    id?: number;
    idPaciente?: string;
    idTurno?: string;
    observacionesTurno?: string;
}

export class PacienteTurno implements IPacienteTurno {
    constructor(public id?: number, public idPaciente?: string, public idTurno?: string, public observacionesTurno?: string) {}
}
