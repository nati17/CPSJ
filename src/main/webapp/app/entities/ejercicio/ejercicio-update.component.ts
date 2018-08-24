import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IEjercicio } from 'app/shared/model/ejercicio.model';
import { EjercicioService } from './ejercicio.service';
import { IAntecedentesPersonales } from 'app/shared/model/antecedentes-personales.model';
import { AntecedentesPersonalesService } from 'app/entities/antecedentes-personales';

@Component({
    selector: 'jhi-ejercicio-update',
    templateUrl: './ejercicio-update.component.html'
})
export class EjercicioUpdateComponent implements OnInit {
    private _ejercicio: IEjercicio;
    isSaving: boolean;

    antecedentespersonales: IAntecedentesPersonales[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private ejercicioService: EjercicioService,
        private antecedentesPersonalesService: AntecedentesPersonalesService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ ejercicio }) => {
            this.ejercicio = ejercicio;
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
        if (this.ejercicio.id !== undefined) {
            this.subscribeToSaveResponse(this.ejercicioService.update(this.ejercicio));
        } else {
            this.subscribeToSaveResponse(this.ejercicioService.create(this.ejercicio));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEjercicio>>) {
        result.subscribe((res: HttpResponse<IEjercicio>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get ejercicio() {
        return this._ejercicio;
    }

    set ejercicio(ejercicio: IEjercicio) {
        this._ejercicio = ejercicio;
    }
}
