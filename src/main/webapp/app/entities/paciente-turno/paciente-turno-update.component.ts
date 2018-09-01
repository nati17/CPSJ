import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IPacienteTurno } from 'app/shared/model/paciente-turno.model';
import { PacienteTurnoService } from './paciente-turno.service';

@Component({
    selector: 'jhi-paciente-turno-update',
    templateUrl: './paciente-turno-update.component.html'
})
export class PacienteTurnoUpdateComponent implements OnInit {
    private _pacienteTurno: IPacienteTurno;
    isSaving: boolean;

    constructor(private pacienteTurnoService: PacienteTurnoService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ pacienteTurno }) => {
            this.pacienteTurno = pacienteTurno;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.pacienteTurno.id !== undefined) {
            this.subscribeToSaveResponse(this.pacienteTurnoService.update(this.pacienteTurno));
        } else {
            this.subscribeToSaveResponse(this.pacienteTurnoService.create(this.pacienteTurno));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPacienteTurno>>) {
        result.subscribe((res: HttpResponse<IPacienteTurno>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get pacienteTurno() {
        return this._pacienteTurno;
    }

    set pacienteTurno(pacienteTurno: IPacienteTurno) {
        this._pacienteTurno = pacienteTurno;
    }
}
