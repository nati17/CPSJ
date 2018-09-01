import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IMedicoObraSocial } from 'app/shared/model/medico-obra-social.model';
import { MedicoObraSocialService } from './medico-obra-social.service';

@Component({
    selector: 'jhi-medico-obra-social-update',
    templateUrl: './medico-obra-social-update.component.html'
})
export class MedicoObraSocialUpdateComponent implements OnInit {
    private _medicoObraSocial: IMedicoObraSocial;
    isSaving: boolean;

    constructor(private medicoObraSocialService: MedicoObraSocialService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ medicoObraSocial }) => {
            this.medicoObraSocial = medicoObraSocial;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.medicoObraSocial.id !== undefined) {
            this.subscribeToSaveResponse(this.medicoObraSocialService.update(this.medicoObraSocial));
        } else {
            this.subscribeToSaveResponse(this.medicoObraSocialService.create(this.medicoObraSocial));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IMedicoObraSocial>>) {
        result.subscribe((res: HttpResponse<IMedicoObraSocial>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get medicoObraSocial() {
        return this._medicoObraSocial;
    }

    set medicoObraSocial(medicoObraSocial: IMedicoObraSocial) {
        this._medicoObraSocial = medicoObraSocial;
    }
}
