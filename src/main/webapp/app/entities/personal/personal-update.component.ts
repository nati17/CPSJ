import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IPersonal } from 'app/shared/model/personal.model';
import { PersonalService } from './personal.service';

@Component({
    selector: 'jhi-personal-update',
    templateUrl: './personal-update.component.html'
})
export class PersonalUpdateComponent implements OnInit {
    private _personal: IPersonal;
    isSaving: boolean;

    constructor(private personalService: PersonalService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ personal }) => {
            this.personal = personal;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.personal.id !== undefined) {
            this.subscribeToSaveResponse(this.personalService.update(this.personal));
        } else {
            this.subscribeToSaveResponse(this.personalService.create(this.personal));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPersonal>>) {
        result.subscribe((res: HttpResponse<IPersonal>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get personal() {
        return this._personal;
    }

    set personal(personal: IPersonal) {
        this._personal = personal;
    }
}
