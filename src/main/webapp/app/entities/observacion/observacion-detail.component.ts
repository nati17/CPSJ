import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IObservacion } from 'app/shared/model/observacion.model';

@Component({
    selector: 'jhi-observacion-detail',
    templateUrl: './observacion-detail.component.html'
})
export class ObservacionDetailComponent implements OnInit {
    observacion: IObservacion;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ observacion }) => {
            this.observacion = observacion;
        });
    }

    previousState() {
        window.history.back();
    }
}
