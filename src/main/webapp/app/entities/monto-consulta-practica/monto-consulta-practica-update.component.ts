import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IMontoConsultaPractica } from 'app/shared/model/monto-consulta-practica.model';
import { MontoConsultaPracticaService } from './monto-consulta-practica.service';

@Component({
    selector: 'jhi-monto-consulta-practica-update',
    templateUrl: './monto-consulta-practica-update.component.html'
})
export class MontoConsultaPracticaUpdateComponent implements OnInit {
    private _montoConsultaPractica: IMontoConsultaPractica;
    isSaving: boolean;

    constructor(private montoConsultaPracticaService: MontoConsultaPracticaService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ montoConsultaPractica }) => {
            this.montoConsultaPractica = montoConsultaPractica;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.montoConsultaPractica.id !== undefined) {
            this.subscribeToSaveResponse(this.montoConsultaPracticaService.update(this.montoConsultaPractica));
        } else {
            this.subscribeToSaveResponse(this.montoConsultaPracticaService.create(this.montoConsultaPractica));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IMontoConsultaPractica>>) {
        result.subscribe(
            (res: HttpResponse<IMontoConsultaPractica>) => this.onSaveSuccess(),
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
    get montoConsultaPractica() {
        return this._montoConsultaPractica;
    }

    set montoConsultaPractica(montoConsultaPractica: IMontoConsultaPractica) {
        this._montoConsultaPractica = montoConsultaPractica;
    }
}
