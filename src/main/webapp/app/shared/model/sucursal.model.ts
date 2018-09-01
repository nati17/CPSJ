export interface ISucursal {
    id?: number;
    nombreSucursal?: string;
    numeroSucursal?: number;
}

export class Sucursal implements ISucursal {
    constructor(public id?: number, public nombreSucursal?: string, public numeroSucursal?: number) {}
}
