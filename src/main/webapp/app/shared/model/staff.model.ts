export interface IStaff {
    id?: number;
    personal?: string;
}

export class Staff implements IStaff {
    constructor(public id?: number, public personal?: string) {}
}
