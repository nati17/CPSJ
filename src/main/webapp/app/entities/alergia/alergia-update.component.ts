import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IAlergia } from 'app/shared/model/alergia.model';
import { AlergiaService } from './alergia.service';
import { IAntecedentesPersonales } from 'app/shared/model/antecedentes-personales.model';
import { AntecedentesPersonalesService } from 'app/entities/antecedentes-personales';

@Component({
    selector: 'jhi-alergia-update',
    templateUrl: './alergia-update.component.html'
})
export class AlergiaUpdateComponent implements OnInit {
    private _alergia: IAlergia;
    isSaving: boolean;

    antecedentespersonales: IAntecedentesPersonales[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private alergiaService: AlergiaService,
        private antecedentesPersonalesService: AntecedentesPersonalesService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ alergia }) => {
            this.alergia = alergia;
        });
        this.antecedentesPersonalesService.query().subscribe(
            (res: HttpResponse<IAntecedentesPersonales[]>) => {
                this.antecedentespersonales = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.alergia.id !== undefined) {
            this.subscribeToSaveResponse(this.alergiaService.update(this.alergia));
        } else {
            this.subscribeToSaveResponse(this.alergiaService.create(this.alergia));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAlergia>>) {
        result.subscribe((res: HttpResponse<IAlergia>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackAntecedentesPersonalesById(index: number, item: IAntecedentesPersonales) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
    get alergia() {
        return this._alergia;
    }

    set alergia(alergia: IAlergia) {
        this._alergia = alergia;
    }
}
