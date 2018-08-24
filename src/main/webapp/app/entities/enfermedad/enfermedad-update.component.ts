import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IEnfermedad } from 'app/shared/model/enfermedad.model';
import { EnfermedadService } from './enfermedad.service';
import { IAntecedentesPersonales } from 'app/shared/model/antecedentes-personales.model';
import { AntecedentesPersonalesService } from 'app/entities/antecedentes-personales';

@Component({
    selector: 'jhi-enfermedad-update',
    templateUrl: './enfermedad-update.component.html'
})
export class EnfermedadUpdateComponent implements OnInit {
    private _enfermedad: IEnfermedad;
    isSaving: boolean;

    antecedentespersonales: IAntecedentesPersonales[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private enfermedadService: EnfermedadService,
        private antecedentesPersonalesService: AntecedentesPersonalesService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ enfermedad }) => {
            this.enfermedad = enfermedad;
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
        if (this.enfermedad.id !== undefined) {
            this.subscribeToSaveResponse(this.enfermedadService.update(this.enfermedad));
        } else {
            this.subscribeToSaveResponse(this.enfermedadService.create(this.enfermedad));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEnfermedad>>) {
        result.subscribe((res: HttpResponse<IEnfermedad>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get enfermedad() {
        return this._enfermedad;
    }

    set enfermedad(enfermedad: IEnfermedad) {
        this._enfermedad = enfermedad;
    }
}
