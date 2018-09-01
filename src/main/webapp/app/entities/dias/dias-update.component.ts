import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IDias } from 'app/shared/model/dias.model';
import { DiasService } from './dias.service';

@Component({
    selector: 'jhi-dias-update',
    templateUrl: './dias-update.component.html'
})
export class DiasUpdateComponent implements OnInit {
    private _dias: IDias;
    isSaving: boolean;

    constructor(private diasService: DiasService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ dias }) => {
            this.dias = dias;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.dias.id !== undefined) {
            this.subscribeToSaveResponse(this.diasService.update(this.dias));
        } else {
            this.subscribeToSaveResponse(this.diasService.create(this.dias));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDias>>) {
        result.subscribe((res: HttpResponse<IDias>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get dias() {
        return this._dias;
    }

    set dias(dias: IDias) {
        this._dias = dias;
    }
}
