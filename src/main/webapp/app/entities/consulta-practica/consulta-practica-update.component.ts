import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IConsultaPractica } from 'app/shared/model/consulta-practica.model';
import { ConsultaPracticaService } from './consulta-practica.service';

@Component({
    selector: 'jhi-consulta-practica-update',
    templateUrl: './consulta-practica-update.component.html'
})
export class ConsultaPracticaUpdateComponent implements OnInit {
    private _consultaPractica: IConsultaPractica;
    isSaving: boolean;

    constructor(private consultaPracticaService: ConsultaPracticaService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ consultaPractica }) => {
            this.consultaPractica = consultaPractica;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.consultaPractica.id !== undefined) {
            this.subscribeToSaveResponse(this.consultaPracticaService.update(this.consultaPractica));
        } else {
            this.subscribeToSaveResponse(this.consultaPracticaService.create(this.consultaPractica));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IConsultaPractica>>) {
        result.subscribe((res: HttpResponse<IConsultaPractica>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get consultaPractica() {
        return this._consultaPractica;
    }

    set consultaPractica(consultaPractica: IConsultaPractica) {
        this._consultaPractica = consultaPractica;
    }
}
