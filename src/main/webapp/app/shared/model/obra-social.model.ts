import { IMedico } from 'app/shared/model//medico.model';

export interface IObraSocial {
    id?: number;
    codigoObraSocial?: string;
    nombreObraSocial?: string;
    medicos?: IMedico[];
}

export class ObraSocial implements IObraSocial {
    constructor(public id?: number, public codigoObraSocial?: string, public nombreObraSocial?: string, public medicos?: IMedico[]) {}
}
