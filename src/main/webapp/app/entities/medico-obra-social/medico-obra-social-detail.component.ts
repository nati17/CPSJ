import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMedicoObraSocial } from 'app/shared/model/medico-obra-social.model';

@Component({
    selector: 'jhi-medico-obra-social-detail',
    templateUrl: './medico-obra-social-detail.component.html'
})
export class MedicoObraSocialDetailComponent implements OnInit {
    medicoObraSocial: IMedicoObraSocial;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ medicoObraSocial }) => {
            this.medicoObraSocial = medicoObraSocial;
        });
    }

    previousState() {
        window.history.back();
    }
}
