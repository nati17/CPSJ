export const enum RegimenesEnum {
    NO = 'NO',
    HIPOGLUCIDOS = 'HIPOGLUCIDOS',
    HIPOLIPIDOS = 'HIPOLIPIDOS',
    HIPOSODICOS = 'HIPOSODICOS',
    HIPERGLUCIDOS = 'HIPERGLUCIDOS',
    HIPERLIPIDOS = 'HIPERLIPIDOS'
}

export interface IRegimen {
    id?: number;
    valor?: RegimenesEnum;
}

export class Regimen implements IRegimen {
    constructor(public id?: number, public valor?: RegimenesEnum) {}
}
