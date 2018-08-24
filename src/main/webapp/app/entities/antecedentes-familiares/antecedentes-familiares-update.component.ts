import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IAntecedentesFamiliares } from 'app/shared/model/antecedentes-familiares.model';
import { AntecedentesFamiliaresService } from './antecedentes-familiares.service';

@Component({
    selector: 'jhi-antecedentes-familiares-update',
    templateUrl: './antecedentes-familiares-update.component.html'
})
export class AntecedentesFamiliaresUpdateComponent implements OnInit {
    private _antecedentesFamiliares: IAntecedentesFamiliares;
    isSaving: boolean;

    constructor(private antecedentesFamiliaresService: AntecedentesFamiliaresService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ antecedentesFamiliares }) => {
            this.antecedentesFamiliares = antecedentesFamiliares;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.antecedentesFamiliares.id !== undefined) {
            this.subscribeToSaveResponse(this.antecedentesFamiliaresService.update(this.antecedentesFamiliares));
        } else {
            this.subscribeToSaveResponse(this.antecedentesFamiliaresService.create(this.antecedentesFamiliares));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAntecedentesFamiliares>>) {
        result.subscribe(
            (res: HttpResponse<IAntecedentesFamiliares>) => this.onSaveSuccess(),
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
    get antecedentesFamiliares() {
        return this._antecedentesFamiliares;
    }

    set antecedentesFamiliares(antecedentesFamiliares: IAntecedentesFamiliares) {
        this._antecedentesFamiliares = antecedentesFamiliares;
    }
}
