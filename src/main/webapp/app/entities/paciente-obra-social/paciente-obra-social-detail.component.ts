import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPacienteObraSocial } from 'app/shared/model/paciente-obra-social.model';

@Component({
    selector: 'jhi-paciente-obra-social-detail',
    templateUrl: './paciente-obra-social-detail.component.html'
})
export class PacienteObraSocialDetailComponent implements OnInit {
    pacienteObraSocial: IPacienteObraSocial;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ pacienteObraSocial }) => {
            this.pacienteObraSocial = pacienteObraSocial;
        });
    }

    previousState() {
        window.history.back();
    }
}
