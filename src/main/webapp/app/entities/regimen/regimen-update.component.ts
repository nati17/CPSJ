import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IRegimen } from 'app/shared/model/regimen.model';
import { RegimenService } from './regimen.service';
import { IAntecedentesPersonales } from 'app/shared/model/antecedentes-personales.model';
import { AntecedentesPersonalesService } from 'app/entities/antecedentes-personales';

@Component({
    selector: 'jhi-regimen-update',
    templateUrl: './regimen-update.component.html'
})
export class RegimenUpdateComponent implements OnInit {
    private _regimen: IRegimen;
    isSaving: boolean;

    antecedentespersonales: IAntecedentesPersonales[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private regimenService: RegimenService,
        private antecedentesPersonalesService: AntecedentesPersonalesService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ regimen }) => {
            this.regimen = regimen;
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
        if (this.regimen.id !== undefined) {
            this.subscribeToSaveResponse(this.regimenService.update(this.regimen));
        } else {
            this.subscribeToSaveResponse(this.regimenService.create(this.regimen));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRegimen>>) {
        result.subscribe((res: HttpResponse<IRegimen>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get regimen() {
        return this._regimen;
    }

    set regimen(regimen: IRegimen) {
        this._regimen = regimen;
    }
}
