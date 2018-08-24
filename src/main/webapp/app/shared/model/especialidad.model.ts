import { IMedico } from 'app/shared/model//medico.model';

export interface IEspecialidad {
    id?: number;
    codigoEspecilidad?: string;
    nombreEspecialidad?: string;
    medicos?: IMedico[];
}

export class Especialidad implements IEspecialidad {
    constructor(public id?: number, public codigoEspecilidad?: string, public nombreEspecialidad?: string, public medicos?: IMedico[]) {}
}
