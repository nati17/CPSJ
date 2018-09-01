import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IConsultaPractica } from 'app/shared/model/consulta-practica.model';
import { Principal } from 'app/core';
import { ConsultaPracticaService } from './consulta-practica.service';

@Component({
    selector: 'jhi-consulta-practica',
    templateUrl: './consulta-practica.component.html'
})
export class ConsultaPracticaComponent implements OnInit, OnDestroy {
    consultaPracticas: IConsultaPractica[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private consultaPracticaService: ConsultaPracticaService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.consultaPracticaService.query().subscribe(
            (res: HttpResponse<IConsultaPractica[]>) => {
                this.consultaPracticas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInConsultaPracticas();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IConsultaPractica) {
        return item.id;
    }

    registerChangeInConsultaPracticas() {
        this.eventSubscriber = this.eventManager.subscribe('consultaPracticaListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
