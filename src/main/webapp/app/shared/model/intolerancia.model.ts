export const enum IntoleranciasEnum {
    GLUTEN = 'GLUTEN',
    LACTOSA = 'LACTOSA',
    OTROS = 'OTROS'
}

export interface IIntolerancia {
    id?: number;
    valor?: IntoleranciasEnum;
}

export class Intolerancia implements IIntolerancia {
    constructor(public id?: number, public valor?: IntoleranciasEnum) {}
}
