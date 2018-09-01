export interface IPacienteObraSocial {
    id?: number;
    idPaciente?: string;
    idOSocial?: string;
    nroAfiliado?: string;
    planAfiliado?: string;
    vigencia?: string;
    estado?: boolean;
}

export class PacienteObraSocial implements IPacienteObraSocial {
    constructor(
        public id?: number,
        public idPaciente?: string,
        public idOSocial?: string,
        public nroAfiliado?: string,
        public planAfiliado?: string,
        public vigencia?: string,
        public estado?: boolean
    ) {
        this.estado = false;
    }
}
