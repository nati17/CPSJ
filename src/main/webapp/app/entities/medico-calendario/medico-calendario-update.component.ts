import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IMedicoCalendario } from 'app/shared/model/medico-calendario.model';
import { MedicoCalendarioService } from './medico-calendario.service';

@Component({
    selector: 'jhi-medico-calendario-update',
    templateUrl: './medico-calendario-update.component.html'
})
export class MedicoCalendarioUpdateComponent implements OnInit {
    private _medicoCalendario: IMedicoCalendario;
    isSaving: boolean;

    constructor(private medicoCalendarioService: MedicoCalendarioService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ medicoCalendario }) => {
            this.medicoCalendario = medicoCalendario;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.medicoCalendario.id !== undefined) {
            this.subscribeToSaveResponse(this.medicoCalendarioService.update(this.medicoCalendario));
        } else {
            this.subscribeToSaveResponse(this.medicoCalendarioService.create(this.medicoCalendario));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IMedicoCalendario>>) {
        result.subscribe((res: HttpResponse<IMedicoCalendario>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get medicoCalendario() {
        return this._medicoCalendario;
    }

    set medicoCalendario(medicoCalendario: IMedicoCalendario) {
        this._medicoCalendario = medicoCalendario;
    }
}
