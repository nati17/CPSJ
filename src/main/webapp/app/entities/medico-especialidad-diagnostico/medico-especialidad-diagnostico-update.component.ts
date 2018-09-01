import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IMedicoEspecialidadDiagnostico } from 'app/shared/model/medico-especialidad-diagnostico.model';
import { MedicoEspecialidadDiagnosticoService } from './medico-especialidad-diagnostico.service';

@Component({
    selector: 'jhi-medico-especialidad-diagnostico-update',
    templateUrl: './medico-especialidad-diagnostico-update.component.html'
})
export class MedicoEspecialidadDiagnosticoUpdateComponent implements OnInit {
    private _medicoEspecialidadDiagnostico: IMedicoEspecialidadDiagnostico;
    isSaving: boolean;

    constructor(
        private medicoEspecialidadDiagnosticoService: MedicoEspecialidadDiagnosticoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ medicoEspecialidadDiagnostico }) => {
            this.medicoEspecialidadDiagnostico = medicoEspecialidadDiagnostico;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.medicoEspecialidadDiagnostico.id !== undefined) {
            this.subscribeToSaveResponse(this.medicoEspecialidadDiagnosticoService.update(this.medicoEspecialidadDiagnostico));
        } else {
            this.subscribeToSaveResponse(this.medicoEspecialidadDiagnosticoService.create(this.medicoEspecialidadDiagnostico));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IMedicoEspecialidadDiagnostico>>) {
        result.subscribe(
            (res: HttpResponse<IMedicoEspecialidadDiagnostico>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get medicoEspecialidadDiagnostico() {
        return this._medicoEspecialidadDiagnostico;
    }

    set medicoEspecialidadDiagnostico(medicoEspecialidadDiagnostico: IMedicoEspecialidadDiagnostico) {
        this._medicoEspecialidadDiagnostico = medicoEspecialidadDiagnostico;
    }
}
