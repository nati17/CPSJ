import { IEnfermedad } from 'app/shared/model//enfermedad.model';
import { IAlergia } from 'app/shared/model//alergia.model';
import { IIntolerancia } from 'app/shared/model//intolerancia.model';
import { IRegimen } from 'app/shared/model//regimen.model';
import { IBebida } from 'app/shared/model//bebida.model';
import { IEjercicio } from 'app/shared/model//ejercicio.model';

export interface IAntecedentesPersonales {
    id?: number;
    tabaco?: boolean;
    tecafe?: boolean;
    enfermedades?: IEnfermedad[];
    alergias?: IAlergia[];
    intolerancias?: IIntolerancia[];
    regimenes?: IRegimen[];
    bebidas?: IBebida[];
    ejercicios?: IEjercicio[];
}

export class AntecedentesPersonales implements IAntecedentesPersonales {
    constructor(
        public id?: number,
        public tabaco?: boolean,
        public tecafe?: boolean,
        public enfermedades?: IEnfermedad[],
        public alergias?: IAlergia[],
        public intolerancias?: IIntolerancia[],
        public regimenes?: IRegimen[],
        public bebidas?: IBebida[],
        public ejercicios?: IEjercicio[]
    ) {
        this.tabaco = false;
        this.tecafe = false;
    }
}
