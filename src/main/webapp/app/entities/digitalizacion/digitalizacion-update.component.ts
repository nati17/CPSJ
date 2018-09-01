import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IDigitalizacion } from 'app/shared/model/digitalizacion.model';
import { DigitalizacionService } from './digitalizacion.service';

@Component({
    selector: 'jhi-digitalizacion-update',
    templateUrl: './digitalizacion-update.component.html'
})
export class DigitalizacionUpdateComponent implements OnInit {
    private _digitalizacion: IDigitalizacion;
    isSaving: boolean;

    constructor(private digitalizacionService: DigitalizacionService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ digitalizacion }) => {
            this.digitalizacion = digitalizacion;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.digitalizacion.id !== undefined) {
            this.subscribeToSaveResponse(this.digitalizacionService.update(this.digitalizacion));
        } else {
            this.subscribeToSaveResponse(this.digitalizacionService.create(this.digitalizacion));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDigitalizacion>>) {
        result.subscribe((res: HttpResponse<IDigitalizacion>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get digitalizacion() {
        return this._digitalizacion;
    }

    set digitalizacion(digitalizacion: IDigitalizacion) {
        this._digitalizacion = digitalizacion;
    }
}
