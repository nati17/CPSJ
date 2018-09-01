import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IDigitalizacion } from 'app/shared/model/digitalizacion.model';
import { Principal } from 'app/core';
import { DigitalizacionService } from './digitalizacion.service';

@Component({
    selector: 'jhi-digitalizacion',
    templateUrl: './digitalizacion.component.html'
})
export class DigitalizacionComponent implements OnInit, OnDestroy {
    digitalizacions: IDigitalizacion[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private digitalizacionService: DigitalizacionService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.digitalizacionService.query().subscribe(
            (res: HttpResponse<IDigitalizacion[]>) => {
                this.digitalizacions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInDigitalizacions();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IDigitalizacion) {
        return item.id;
    }

    registerChangeInDigitalizacions() {
        this.eventSubscriber = this.eventManager.subscribe('digitalizacionListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
