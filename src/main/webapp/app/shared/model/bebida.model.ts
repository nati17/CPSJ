export const enum BebidasEnum {
    NO = 'NO',
    SISIEMPRE = 'SISIEMPRE',
    SIAVECES = 'SIAVECES'
}

export interface IBebida {
    id?: number;
    valor?: BebidasEnum;
}

export class Bebida implements IBebida {
    constructor(public id?: number, public valor?: BebidasEnum) {}
}
