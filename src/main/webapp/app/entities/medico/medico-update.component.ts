import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IMedico } from 'app/shared/model/medico.model';
import { MedicoService } from './medico.service';
import { IObraSocial } from 'app/shared/model/obra-social.model';
import { ObraSocialService } from 'app/entities/obra-social';
import { IEspecialidad } from 'app/shared/model/especialidad.model';
import { EspecialidadService } from 'app/entities/especialidad';

@Component({
    selector: 'jhi-medico-update',
    templateUrl: './medico-update.component.html'
})
export class MedicoUpdateComponent implements OnInit {
    private _medico: IMedico;
    isSaving: boolean;

    obrasocials: IObraSocial[];

    especialidads: IEspecialidad[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private medicoService: MedicoService,
        private obraSocialService: ObraSocialService,
        private especialidadService: EspecialidadService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ medico }) => {
            this.medico = medico;
        });
        this.obraSocialService.query().subscribe(
            (res: HttpResponse<IObraSocial[]>) => {
                this.obrasocials = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.especialidadService.query().subscribe(
            (res: HttpResponse<IEspecialidad[]>) => {
                this.especialidads = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.medico.id !== undefined) {
            this.subscribeToSaveResponse(this.medicoService.update(this.medico));
        } else {
            this.subscribeToSaveResponse(this.medicoService.create(this.medico));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IMedico>>) {
        result.subscribe((res: HttpResponse<IMedico>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackObraSocialById(index: number, item: IObraSocial) {
        return item.id;
    }

    trackEspecialidadById(index: number, item: IEspecialidad) {
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
    get medico() {
        return this._medico;
    }

    set medico(medico: IMedico) {
        this._medico = medico;
    }
}
