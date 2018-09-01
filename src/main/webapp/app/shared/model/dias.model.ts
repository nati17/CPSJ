export const enum DiasSemanaEnum {
    LUNES = 'LUNES',
    MARTES = 'MARTES',
    MIERCOLES = 'MIERCOLES',
    JUEVES = 'JUEVES',
    VIERNES = 'VIERNES',
    SABADO = 'SABADO',
    DOMINGO = 'DOMINGO'
}

export interface IDias {
    id?: number;
    valor?: DiasSemanaEnum;
}

export class Dias implements IDias {
    constructor(public id?: number, public valor?: DiasSemanaEnum) {}
}
