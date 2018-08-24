import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IFicha } from 'app/shared/model/ficha.model';
import { FichaService } from './ficha.service';
import { IMedico } from 'app/shared/model/medico.model';
import { MedicoService } from 'app/entities/medico';

@Component({
    selector: 'jhi-ficha-update',
    templateUrl: './ficha-update.component.html'
})
export class FichaUpdateComponent implements OnInit {
    private _ficha: IFicha;
    isSaving: boolean;

    medicos: IMedico[];
    fechaIngresoDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private fichaService: FichaService,
        private medicoService: MedicoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ ficha }) => {
            this.ficha = ficha;
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
        if (this.ficha.id !== undefined) {
            this.subscribeToSaveResponse(this.fichaService.update(this.ficha));
        } else {
            this.subscribeToSaveResponse(this.fichaService.create(this.ficha));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IFicha>>) {
        result.subscribe((res: HttpResponse<IFicha>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get ficha() {
        return this._ficha;
    }

    set ficha(ficha: IFicha) {
        this._ficha = ficha;
    }
}
