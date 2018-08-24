import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IEspecialidad } from 'app/shared/model/especialidad.model';
import { EspecialidadService } from './especialidad.service';
import { IMedico } from 'app/shared/model/medico.model';
import { MedicoService } from 'app/entities/medico';

@Component({
    selector: 'jhi-especialidad-update',
    templateUrl: './especialidad-update.component.html'
})
export class EspecialidadUpdateComponent implements OnInit {
    private _especialidad: IEspecialidad;
    isSaving: boolean;

    medicos: IMedico[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private especialidadService: EspecialidadService,
        private medicoService: MedicoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ especialidad }) => {
            this.especialidad = especialidad;
        });
        this.medicoService.query().subscribe(
            (res: HttpResponse<IMedico[]>) => {
                this.medicos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.especialidad.id !== undefined) {
            this.subscribeToSaveResponse(this.especialidadService.update(this.especialidad));
        } else {
            this.subscribeToSaveResponse(this.especialidadService.create(this.especialidad));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEspecialidad>>) {
        result.subscribe((res: HttpResponse<IEspecialidad>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackMedicoById(index: number, item: IMedico) {
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
    get especialidad() {
        return this._especialidad;
    }

    set especialidad(especialidad: IEspecialidad) {
        this._especialidad = especialidad;
    }
}
