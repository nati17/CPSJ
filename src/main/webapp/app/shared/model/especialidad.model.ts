import { IMedico } from 'app/shared/model//medico.model';

export interface IEspecialidad {
    id?: number;
    codigoEspecialidad?: string;
    nombreEspecialidad?: string;
    descripcionEspecialidad?: string;
    medicos?: IMedico[];
}

export class Especialidad implements IEspecialidad {
    constructor(
        public id?: number,
        public codigoEspecialidad?: string,
        public nombreEspecialidad?: string,
        public descripcionEspecialidad?: string,
        public medicos?: IMedico[]
    ) {}
}
