import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IConsulta } from 'app/shared/model/consulta.model';
import { ConsultaService } from './consulta.service';

@Component({
    selector: 'jhi-consulta-update',
    templateUrl: './consulta-update.component.html'
})
export class ConsultaUpdateComponent implements OnInit {
    private _consulta: IConsulta;
    isSaving: boolean;
    fechaConsultaDp: any;

    constructor(private consultaService: ConsultaService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ consulta }) => {
            this.consulta = consulta;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.consulta.id !== undefined) {
            this.subscribeToSaveResponse(this.consultaService.update(this.consulta));
        } else {
            this.subscribeToSaveResponse(this.consultaService.create(this.consulta));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IConsulta>>) {
        result.subscribe((res: HttpResponse<IConsulta>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get consulta() {
        return this._consulta;
    }

    set consulta(consulta: IConsulta) {
        this._consulta = consulta;
    }
}
