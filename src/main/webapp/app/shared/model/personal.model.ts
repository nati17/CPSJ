export interface IPersonal {
    id?: number;
    nombrePersonal?: string;
    apellidoPersonal?: string;
    direccionPersonal?: string;
    telefonoPersonal?: string;
    emailPersonal?: string;
    cargoPersonal?: string;
}

export class Personal implements IPersonal {
    constructor(
        public id?: number,
        public nombrePersonal?: string,
        public apellidoPersonal?: string,
        public direccionPersonal?: string,
        public telefonoPersonal?: string,
        public emailPersonal?: string,
        public cargoPersonal?: string
    ) {}
}
