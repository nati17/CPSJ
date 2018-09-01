import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IConsultaPractica } from 'app/shared/model/consulta-practica.model';

@Component({
    selector: 'jhi-consulta-practica-detail',
    templateUrl: './consulta-practica-detail.component.html'
})
export class ConsultaPracticaDetailComponent implements OnInit {
    consultaPractica: IConsultaPractica;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ consultaPractica }) => {
            this.consultaPractica = consultaPractica;
        });
    }

    previousState() {
        window.history.back();
    }
}
