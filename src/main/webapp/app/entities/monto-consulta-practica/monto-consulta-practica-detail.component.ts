import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMontoConsultaPractica } from 'app/shared/model/monto-consulta-practica.model';

@Component({
    selector: 'jhi-monto-consulta-practica-detail',
    templateUrl: './monto-consulta-practica-detail.component.html'
})
export class MontoConsultaPracticaDetailComponent implements OnInit {
    montoConsultaPractica: IMontoConsultaPractica;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ montoConsultaPractica }) => {
            this.montoConsultaPractica = montoConsultaPractica;
        });
    }

    previousState() {
        window.history.back();
    }
}
