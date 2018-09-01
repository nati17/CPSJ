export const enum AlergiasEnum {
    SALICILATOS = 'SALICILATOS',
    PIRAZOLONAS = 'PIRAZOLONAS',
    PENISILINA = 'PENISILINA',
    OTROS = 'OTROS'
}

export interface IAlergia {
    id?: number;
    valor?: AlergiasEnum;
}

export class Alergia implements IAlergia {
    constructor(public id?: number, public valor?: AlergiasEnum) {}
}
