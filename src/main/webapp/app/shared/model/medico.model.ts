import { IFicha } from 'app/shared/model//ficha.model';
import { IObraSocial } from 'app/shared/model//obra-social.model';
import { IEspecialidad } from 'app/shared/model//especialidad.model';
import { IDias } from 'app/shared/model//dias.model';

export interface IMedico {
    id?: number;
    codigoMedico?: string;
    nombreMedico?: string;
    apellidoMedico?: string;
    direccionMedico?: string;
    telefonoMedico?: string;
    emailMedico?: string;
    matriculaMedico?: string;
    horarioIMedico?: string;
    horarioEMedico?: string;
    porcentaje?: string;
    fichas?: IFicha[];
    obrasocials?: IObraSocial[];
    especialidads?: IEspecialidad[];
    dias?: IDias[];
    imagenMedico?: File;
}

export class Medico implements IMedico {
    constructor(
        public id?: number,
        public codigoMedico?: string,
        public nombreMedico?: string,
        public apellidoMedico?: string,
        public direccionMedico?: string,
        public telefonoMedico?: string,
        public emailMedico?: string,
        public matriculaMedico?: string,
        public horarioIMedico?: string,
        public horarioEMedico?: string,
        public porcentaje?: string,
        public fichas?: IFicha[],
        public obrasocials?: IObraSocial[],
        public especialidads?: IEspecialidad[],
        public dias?: IDias[],
        public imagenMedico?: File
    ) {}
}
