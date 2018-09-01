import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMedicoEspecialidadDiagnostico } from 'app/shared/model/medico-especialidad-diagnostico.model';

@Component({
    selector: 'jhi-medico-especialidad-diagnostico-detail',
    templateUrl: './medico-especialidad-diagnostico-detail.component.html'
})
export class MedicoEspecialidadDiagnosticoDetailComponent implements OnInit {
    medicoEspecialidadDiagnostico: IMedicoEspecialidadDiagnostico;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ medicoEspecialidadDiagnostico }) => {
            this.medicoEspecialidadDiagnostico = medicoEspecialidadDiagnostico;
        });
    }

    previousState() {
        window.history.back();
    }
}
