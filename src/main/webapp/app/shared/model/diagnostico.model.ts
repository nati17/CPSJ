export interface IDiagnostico {
    id?: number;
    idDiagnostico?: string;
    codigoDiagnostico?: string;
    nombreDiagnostico?: string;
    descripcionDiagnostico?: string;
}

export class Diagnostico implements IDiagnostico {
    constructor(
        public id?: number,
        public idDiagnostico?: string,
        public codigoDiagnostico?: string,
        public nombreDiagnostico?: string,
        public descripcionDiagnostico?: string
    ) {}
}
