export interface IPaciente {
    id?: number;
    nombrePaciente?: string;
    apellidoPaciente?: string;
    documentoPaciente?: string;
    direccionPaciente?: string;
    telefonoPaciente?: string;
    emailPaciente?: string;
    pacienteObraSocialId?: number;
}

export class Paciente implements IPaciente {
    constructor(
        public id?: number,
        public nombrePaciente?: string,
        public apellidoPaciente?: string,
        public documentoPaciente?: string,
        public direccionPaciente?: string,
        public telefonoPaciente?: string,
        public emailPaciente?: string,
        public pacienteObraSocialId?: number
    ) {}
}
