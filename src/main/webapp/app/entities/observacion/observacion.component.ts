import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IObservacion } from 'app/shared/model/observacion.model';
import { Principal } from 'app/core';
import { ObservacionService } from './observacion.service';

@Component({
    selector: 'jhi-observacion',
    templateUrl: './observacion.component.html'
})
export class ObservacionComponent implements OnInit, OnDestroy {
    observacions: IObservacion[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private observacionService: ObservacionService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.observacionService.query().subscribe(
            (res: HttpResponse<IObservacion[]>) => {
                this.observacions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInObservacions();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IObservacion) {
        return item.id;
    }

    registerChangeInObservacions() {
        this.eventSubscriber = this.eventManager.subscribe('observacionListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
