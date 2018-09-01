export interface IConsPractObservacion {
    id?: number;
    idConsPract?: string;
    idObservacion?: string;
}

export class ConsPractObservacion implements IConsPractObservacion {
    constructor(public id?: number, public idConsPract?: string, public idObservacion?: string) {}
}
