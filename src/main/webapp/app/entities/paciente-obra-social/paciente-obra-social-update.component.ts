import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IPacienteObraSocial } from 'app/shared/model/paciente-obra-social.model';
import { PacienteObraSocialService } from './paciente-obra-social.service';

@Component({
    selector: 'jhi-paciente-obra-social-update',
    templateUrl: './paciente-obra-social-update.component.html'
})
export class PacienteObraSocialUpdateComponent implements OnInit {
    private _pacienteObraSocial: IPacienteObraSocial;
    isSaving: boolean;

    constructor(private pacienteObraSocialService: PacienteObraSocialService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ pacienteObraSocial }) => {
            this.pacienteObraSocial = pacienteObraSocial;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.pacienteObraSocial.id !== undefined) {
            this.subscribeToSaveResponse(this.pacienteObraSocialService.update(this.pacienteObraSocial));
        } else {
            this.subscribeToSaveResponse(this.pacienteObraSocialService.create(this.pacienteObraSocial));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPacienteObraSocial>>) {
        result.subscribe((res: HttpResponse<IPacienteObraSocial>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get pacienteObraSocial() {
        return this._pacienteObraSocial;
    }

    set pacienteObraSocial(pacienteObraSocial: IPacienteObraSocial) {
        this._pacienteObraSocial = pacienteObraSocial;
    }
}
