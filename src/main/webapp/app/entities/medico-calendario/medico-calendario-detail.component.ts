import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMedicoCalendario } from 'app/shared/model/medico-calendario.model';

@Component({
    selector: 'jhi-medico-calendario-detail',
    templateUrl: './medico-calendario-detail.component.html'
})
export class MedicoCalendarioDetailComponent implements OnInit {
    medicoCalendario: IMedicoCalendario;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ medicoCalendario }) => {
            this.medicoCalendario = medicoCalendario;
        });
    }

    previousState() {
        window.history.back();
    }
}
