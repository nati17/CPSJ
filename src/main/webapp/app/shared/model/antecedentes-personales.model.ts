export const enum EnfermedadesEnum {
    ASMA = 'ASMA',
    DIABETES = 'DIABETES',
    HIPERTENSION = 'HIPERTENSION',
    OBESIDAD = 'OBESIDAD',
    INSUFCARDIACA = 'INSUFCARDIACA',
    INSUFRENAL = 'INSUFRENAL',
    OTROS = 'OTROS'
}

export const enum AlergiasEnum {
    SALICILATOS = 'SALICILATOS',
    PIRAZOLONAS = 'PIRAZOLONAS',
    PENISILINA = 'PENISILINA',
    OTROS = 'OTROS'
}

export const enum IntoleranciasEnum {
    GLUTEN = 'GLUTEN',
    LACTOSA = 'LACTOSA',
    OTROS = 'OTROS'
}

export const enum RegimenesEnum {
    NO = 'NO',
    HIPOGLUCIDOS = 'HIPOGLUCIDOS',
    HIPOLIPIDOS = 'HIPOLIPIDOS',
    HIPOSODICOS = 'HIPOSODICOS',
    HIPERGLUCIDOS = 'HIPERGLUCIDOS',
    HIPERLIPIDOS = 'HIPERLIPIDOS'
}

export const enum BebidasEnum {
    NO = 'NO',
    SISIEMPRE = 'SISIEMPRE',
    SIAVECES = 'SIAVECES'
}

export const enum EjerciciosEnum {
    NO = 'NO',
    UNDIA = 'UNDIA',
    TRESDIAS = 'TRESDIAS',
    TODOSLOSDIAS = 'TODOSLOSDIAS'
}

export interface IAntecedentesPersonales {
    id?: number;
    enfermedad?: EnfermedadesEnum;
    alergia?: AlergiasEnum;
    intolerancia?: IntoleranciasEnum;
    regimen?: RegimenesEnum;
    bebida?: BebidasEnum;
    ejercicio?: EjerciciosEnum;
    tabaco?: boolean;
    tecafe?: boolean;
}

export class AntecedentesPersonales implements IAntecedentesPersonales {
    constructor(
        public id?: number,
        public enfermedad?: EnfermedadesEnum,
        public alergia?: AlergiasEnum,
        public intolerancia?: IntoleranciasEnum,
        public regimen?: RegimenesEnum,
        public bebida?: BebidasEnum,
        public ejercicio?: EjerciciosEnum,
        public tabaco?: boolean,
        public tecafe?: boolean
    ) {
        this.tabaco = false;
        this.tecafe = false;
    }
}
