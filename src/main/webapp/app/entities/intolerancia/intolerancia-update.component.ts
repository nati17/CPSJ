import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IIntolerancia } from 'app/shared/model/intolerancia.model';
import { IntoleranciaService } from './intolerancia.service';
import { IAntecedentesPersonales } from 'app/shared/model/antecedentes-personales.model';
import { AntecedentesPersonalesService } from 'app/entities/antecedentes-personales';

@Component({
    selector: 'jhi-intolerancia-update',
    templateUrl: './intolerancia-update.component.html'
})
export class IntoleranciaUpdateComponent implements OnInit {
    private _intolerancia: IIntolerancia;
    isSaving: boolean;

    antecedentespersonales: IAntecedentesPersonales[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private intoleranciaService: IntoleranciaService,
        private antecedentesPersonalesService: AntecedentesPersonalesService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ intolerancia }) => {
            this.intolerancia = intolerancia;
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
        if (this.intolerancia.id !== undefined) {
            this.subscribeToSaveResponse(this.intoleranciaService.update(this.intolerancia));
        } else {
            this.subscribeToSaveResponse(this.intoleranciaService.create(this.intolerancia));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IIntolerancia>>) {
        result.subscribe((res: HttpResponse<IIntolerancia>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get intolerancia() {
        return this._intolerancia;
    }

    set intolerancia(intolerancia: IIntolerancia) {
        this._intolerancia = intolerancia;
    }
}
