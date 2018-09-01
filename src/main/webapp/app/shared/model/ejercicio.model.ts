export const enum EjerciciosEnum {
    NO = 'NO',
    UNDIA = 'UNDIA',
    TRESDIAS = 'TRESDIAS',
    TODOSLOSDIAS = 'TODOSLOSDIAS'
}

export interface IEjercicio {
    id?: number;
    valor?: EjerciciosEnum;
}

export class Ejercicio implements IEjercicio {
    constructor(public id?: number, public valor?: EjerciciosEnum) {}
}
