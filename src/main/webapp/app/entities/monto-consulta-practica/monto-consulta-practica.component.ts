import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IMontoConsultaPractica } from 'app/shared/model/monto-consulta-practica.model';
import { Principal } from 'app/core';
import { MontoConsultaPracticaService } from './monto-consulta-practica.service';

@Component({
    selector: 'jhi-monto-consulta-practica',
    templateUrl: './monto-consulta-practica.component.html'
})
export class MontoConsultaPracticaComponent implements OnInit, OnDestroy {
    montoConsultaPracticas: IMontoConsultaPractica[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private montoConsultaPracticaService: MontoConsultaPracticaService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.montoConsultaPracticaService.query().subscribe(
            (res: HttpResponse<IMontoConsultaPractica[]>) => {
                this.montoConsultaPracticas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInMontoConsultaPracticas();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IMontoConsultaPractica) {
        return item.id;
    }

    registerChangeInMontoConsultaPracticas() {
        this.eventSubscriber = this.eventManager.subscribe('montoConsultaPracticaListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
