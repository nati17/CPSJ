import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IDias } from 'app/shared/model/dias.model';
import { Principal } from 'app/core';
import { DiasService } from './dias.service';

@Component({
    selector: 'jhi-dias',
    templateUrl: './dias.component.html'
})
export class DiasComponent implements OnInit, OnDestroy {
    dias: IDias[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private diasService: DiasService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.diasService.query().subscribe(
            (res: HttpResponse<IDias[]>) => {
                this.dias = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInDias();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IDias) {
        return item.id;
    }

    registerChangeInDias() {
        this.eventSubscriber = this.eventManager.subscribe('diasListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
