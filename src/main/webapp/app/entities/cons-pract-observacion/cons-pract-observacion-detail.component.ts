import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IConsPractObservacion } from 'app/shared/model/cons-pract-observacion.model';

@Component({
    selector: 'jhi-cons-pract-observacion-detail',
    templateUrl: './cons-pract-observacion-detail.component.html'
})
export class ConsPractObservacionDetailComponent implements OnInit {
    consPractObservacion: IConsPractObservacion;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ consPractObservacion }) => {
            this.consPractObservacion = consPractObservacion;
        });
    }

    previousState() {
        window.history.back();
    }
}
