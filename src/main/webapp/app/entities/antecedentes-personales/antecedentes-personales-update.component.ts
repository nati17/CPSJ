import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IAntecedentesPersonales } from 'app/shared/model/antecedentes-personales.model';
import { AntecedentesPersonalesService } from './antecedentes-personales.service';

@Component({
    selector: 'jhi-antecedentes-personales-update',
    templateUrl: './antecedentes-personales-update.component.html'
})
export class AntecedentesPersonalesUpdateComponent implements OnInit {
    private _antecedentesPersonales: IAntecedentesPersonales;
    isSaving: boolean;

    constructor(private antecedentesPersonalesService: AntecedentesPersonalesService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ antecedentesPersonales }) => {
            this.antecedentesPersonales = antecedentesPersonales;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.antecedentesPersonales.id !== undefined) {
            this.subscribeToSaveResponse(this.antecedentesPersonalesService.update(this.antecedentesPersonales));
        } else {
            this.subscribeToSaveResponse(this.antecedentesPersonalesService.create(this.antecedentesPersonales));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAntecedentesPersonales>>) {
        result.subscribe(
            (res: HttpResponse<IAntecedentesPersonales>) => this.onSaveSuccess(),
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
    get antecedentesPersonales() {
        return this._antecedentesPersonales;
    }

    set antecedentesPersonales(antecedentesPersonales: IAntecedentesPersonales) {
        this._antecedentesPersonales = antecedentesPersonales;
    }
}
