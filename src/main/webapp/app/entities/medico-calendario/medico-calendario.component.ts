import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IMedicoCalendario } from 'app/shared/model/medico-calendario.model';
import { Principal } from 'app/core';
import { MedicoCalendarioService } from './medico-calendario.service';

@Component({
    selector: 'jhi-medico-calendario',
    templateUrl: './medico-calendario.component.html'
})
export class MedicoCalendarioComponent implements OnInit, OnDestroy {
    medicoCalendarios: IMedicoCalendario[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private medicoCalendarioService: MedicoCalendarioService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.medicoCalendarioService.query().subscribe(
            (res: HttpResponse<IMedicoCalendario[]>) => {
                this.medicoCalendarios = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInMedicoCalendarios();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IMedicoCalendario) {
        return item.id;
    }

    registerChangeInMedicoCalendarios() {
        this.eventSubscriber = this.eventManager.subscribe('medicoCalendarioListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
