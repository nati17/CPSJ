export interface IDigitalizacion {
    id?: number;
    idDigitalizacion?: string;
    descripcionDigitalizacion?: string;
    elementoDigitalizacion?: string;
}

export class Digitalizacion implements IDigitalizacion {
    constructor(
        public id?: number,
        public idDigitalizacion?: string,
        public descripcionDigitalizacion?: string,
        public elementoDigitalizacion?: string
    ) {}
}
