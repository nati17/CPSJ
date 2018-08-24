export interface ICodigoPostal {
    id?: number;
    codigo?: string;
    nombreCiudad?: string;
    provinciaNombreProvincia?: string;
    provinciaId?: number;
}

export class CodigoPostal implements ICodigoPostal {
    constructor(
        public id?: number,
        public codigo?: string,
        public nombreCiudad?: string,
        public provinciaNombreProvincia?: string,
        public provinciaId?: number
    ) {}
}
