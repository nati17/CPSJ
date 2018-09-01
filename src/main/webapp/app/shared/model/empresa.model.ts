export interface IEmpresa {
    id?: number;
    nombreEmpresa?: string;
    direccionEmpresa?: string;
    telefonoEmpresa?: string;
    emailEmpresa?: string;
    sucursalNombreSucursal?: string;
    sucursalId?: number;
}

export class Empresa implements IEmpresa {
    constructor(
        public id?: number,
        public nombreEmpresa?: string,
        public direccionEmpresa?: string,
        public telefonoEmpresa?: string,
        public emailEmpresa?: string,
        public sucursalNombreSucursal?: string,
        public sucursalId?: number
    ) {}
}
