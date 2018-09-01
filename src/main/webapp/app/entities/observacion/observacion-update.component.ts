import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IObservacion } from 'app/shared/model/observacion.model';
import { ObservacionService } from './observacion.service';

@Component({
    selector: 'jhi-observacion-update',
    templateUrl: './observacion-update.component.html'
})
export class ObservacionUpdateComponent implements OnInit {
    private _observacion: IObservacion;
    isSaving: boolean;

    constructor(private observacionService: ObservacionService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ observacion }) => {
            this.observacion = observacion;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.observacion.id !== undefined) {
            this.subscribeToSaveResponse(this.observacionService.update(this.observacion));
        } else {
            this.subscribeToSaveResponse(this.observacionService.create(this.observacion));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IObservacion>>) {
        result.subscribe((res: HttpResponse<IObservacion>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get observacion() {
        return this._observacion;
    }

    set observacion(observacion: IObservacion) {
        this._observacion = observacion;
    }
}
