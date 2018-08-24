export interface IAntecedentesFamiliares {
    id?: number;
    vivoAF?: boolean;
    causaMuerteAF?: string;
}

export class AntecedentesFamiliares implements IAntecedentesFamiliares {
    constructor(public id?: number, public vivoAF?: boolean, public causaMuerteAF?: string) {
        this.vivoAF = false;
    }
}
