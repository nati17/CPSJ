import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IFicha } from 'app/shared/model/ficha.model';
import { Principal } from 'app/core';
import { FichaService } from './ficha.service';

@Component({
    selector: 'jhi-ficha',
    templateUrl: './ficha.component.html'
})
export class FichaComponent implements OnInit, OnDestroy {
    fichas: IFicha[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private fichaService: FichaService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.fichaService.query().subscribe(
            (res: HttpResponse<IFicha[]>) => {
                this.fichas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInFichas();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IFicha) {
        return item.id;
    }

    registerChangeInFichas() {
        this.eventSubscriber = this.eventManager.subscribe('fichaListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
