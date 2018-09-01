import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPacienteTurno } from 'app/shared/model/paciente-turno.model';

@Component({
    selector: 'jhi-paciente-turno-detail',
    templateUrl: './paciente-turno-detail.component.html'
})
export class PacienteTurnoDetailComponent implements OnInit {
    pacienteTurno: IPacienteTurno;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ pacienteTurno }) => {
            this.pacienteTurno = pacienteTurno;
        });
    }

    previousState() {
        window.history.back();
    }
}
