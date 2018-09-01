export interface ICalendario {
    id?: number;
    idCalendario?: string;
    anioCalendario?: string;
    mesCalendario?: string;
}

export class Calendario implements ICalendario {
    constructor(public id?: number, public idCalendario?: string, public anioCalendario?: string, public mesCalendario?: string) {}
}
