import { IMedico } from 'app/shared/model//medico.model';

export interface IObraSocial {
    id?: number;
    codigoObraSocial?: string;
    nombreOSocial?: string;
    direecionOSocial?: string;
    telefonoOSocial?: string;
    emailOSocial?: string;
    pacienteObraSocialId?: number;
    medicos?: IMedico[];
}

export class ObraSocial implements IObraSocial {
    constructor(
        public id?: number,
        public codigoObraSocial?: string,
        public nombreOSocial?: string,
        public direecionOSocial?: string,
        public telefonoOSocial?: string,
        public emailOSocial?: string,
        public pacienteObraSocialId?: number,
        public medicos?: IMedico[]
    ) {}
}
