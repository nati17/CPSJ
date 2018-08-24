import { IFicha } from 'app/shared/model//ficha.model';
import { IObraSocial } from 'app/shared/model//obra-social.model';
import { IEspecialidad } from 'app/shared/model//especialidad.model';

export interface IMedico {
    id?: number;
    codigoMedico?: string;
    nombreMedico?: string;
    direccionMedico?: string;
    telefonoMedico?: string;
    emailMedico?: string;
    fichas?: IFicha[];
    obrasocials?: IObraSocial[];
    especialidads?: IEspecialidad[];
}

export class Medico implements IMedico {
    constructor(
        public id?: number,
        public codigoMedico?: string,
        public nombreMedico?: string,
        public direccionMedico?: string,
        public telefonoMedico?: string,
        public emailMedico?: string,
        public fichas?: IFicha[],
        public obrasocials?: IObraSocial[],
        public especialidads?: IEspecialidad[]
    ) {}
}
