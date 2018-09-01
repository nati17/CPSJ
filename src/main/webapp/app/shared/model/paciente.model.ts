import { Moment } from 'moment';

export interface IPaciente {
    id?: number;
    nombrePaciente?: string;
    apellidoPaciente?: string;
    direccionPaciente?: string;
    telefonoPaciente?: string;
    emailPaciente?: string;
    fechaNacPaciente?: Moment;
    generoPaciente?: string;
    pacienteObraSocialId?: number;
}

export class Paciente implements IPaciente {
    constructor(
        public id?: number,
        public nombrePaciente?: string,
        public apellidoPaciente?: string,
        public direccionPaciente?: string,
        public telefonoPaciente?: string,
        public emailPaciente?: string,
        public fechaNacPaciente?: Moment,
        public generoPaciente?: string,
        public pacienteObraSocialId?: number
    ) {}
}
