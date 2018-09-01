import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDias } from 'app/shared/model/dias.model';

@Component({
    selector: 'jhi-dias-detail',
    templateUrl: './dias-detail.component.html'
})
export class DiasDetailComponent implements OnInit {
    dias: IDias;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ dias }) => {
            this.dias = dias;
        });
    }

    previousState() {
        window.history.back();
    }
}
