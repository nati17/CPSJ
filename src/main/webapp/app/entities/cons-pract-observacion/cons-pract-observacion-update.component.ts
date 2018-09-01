import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IConsPractObservacion } from 'app/shared/model/cons-pract-observacion.model';
import { ConsPractObservacionService } from './cons-pract-observacion.service';

@Component({
    selector: 'jhi-cons-pract-observacion-update',
    templateUrl: './cons-pract-observacion-update.component.html'
})
export class ConsPractObservacionUpdateComponent implements OnInit {
    private _consPractObservacion: IConsPractObservacion;
    isSaving: boolean;

    constructor(private consPractObservacionService: ConsPractObservacionService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ consPractObservacion }) => {
            this.consPractObservacion = consPractObservacion;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.consPractObservacion.id !== undefined) {
            this.subscribeToSaveResponse(this.consPractObservacionService.update(this.consPractObservacion));
        } else {
            this.subscribeToSaveResponse(this.consPractObservacionService.create(this.consPractObservacion));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IConsPractObservacion>>) {
        result.subscribe(
            (res: HttpResponse<IConsPractObservacion>) => this.onSaveSuccess(),
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
    get consPractObservacion() {
        return this._consPractObservacion;
    }

    set consPractObservacion(consPractObservacion: IConsPractObservacion) {
        this._consPractObservacion = consPractObservacion;
    }
}
