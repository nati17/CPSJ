import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDigitalizacion } from 'app/shared/model/digitalizacion.model';

@Component({
    selector: 'jhi-digitalizacion-detail',
    templateUrl: './digitalizacion-detail.component.html'
})
export class DigitalizacionDetailComponent implements OnInit {
    digitalizacion: IDigitalizacion;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ digitalizacion }) => {
            this.digitalizacion = digitalizacion;
        });
    }

    previousState() {
        window.history.back();
    }
}
