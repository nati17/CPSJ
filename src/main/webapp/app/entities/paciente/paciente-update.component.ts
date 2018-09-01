import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IPaciente } from 'app/shared/model/paciente.model';
import { PacienteService } from './paciente.service';
import { IObraSocial } from 'app/shared/model/obra-social.model';
import { ObraSocialService } from 'app/entities/obra-social';

@Component({
    selector: 'jhi-paciente-update',
    templateUrl: './paciente-update.component.html'
})
export class PacienteUpdateComponent implements OnInit {
    private _paciente: IPaciente;
    isSaving: boolean;

    pacienteobrasocials: IObraSocial[];
    fechaNacPacienteDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private pacienteService: PacienteService,
        private obraSocialService: ObraSocialService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ paciente }) => {
            this.paciente = paciente;
        });
        this.obraSocialService.query({ filter: 'paciente-is-null' }).subscribe(
            (res: HttpResponse<IObraSocial[]>) => {
                if (!this.paciente.pacienteObraSocialId) {
                    this.pacienteobrasocials = res.body;
                } else {
                    this.obraSocialService.find(this.paciente.pacienteObraSocialId).subscribe(
                        (subRes: HttpResponse<IObraSocial>) => {
                            this.pacienteobrasocials = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.paciente.id !== undefined) {
            this.subscribeToSaveResponse(this.pacienteService.update(this.paciente));
        } else {
            this.subscribeToSaveResponse(this.pacienteService.create(this.paciente));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPaciente>>) {
        result.subscribe((res: HttpResponse<IPaciente>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get paciente() {
        return this._paciente;
    }

    set paciente(paciente: IPaciente) {
        this._paciente = paciente;
    }
}
