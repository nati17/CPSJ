export const enum EnfermedadesEnum {
    ASMA = 'ASMA',
    DIABETES = 'DIABETES',
    HIPERTENSION = 'HIPERTENSION',
    OBESIDAD = 'OBESIDAD',
    INSUFCARDIACA = 'INSUFCARDIACA',
    INSUFRENAL = 'INSUFRENAL',
    OTROS = 'OTROS'
}

export interface IEnfermedad {
    id?: number;
    valor?: EnfermedadesEnum;
}

export class Enfermedad implements IEnfermedad {
    constructor(public id?: number, public valor?: EnfermedadesEnum) {}
}
