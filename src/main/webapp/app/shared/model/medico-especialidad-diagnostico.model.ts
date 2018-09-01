export interface IMedicoEspecialidadDiagnostico {
    id?: number;
    idMedico?: string;
    idEspecialidad?: string;
    idDiagnostico?: string;
}

export class MedicoEspecialidadDiagnostico implements IMedicoEspecialidadDiagnostico {
    constructor(public id?: number, public idMedico?: string, public idEspecialidad?: string, public idDiagnostico?: string) {}
}
