import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IPacienteTurno } from 'app/shared/model/paciente-turno.model';
import { Principal } from 'app/core';
import { PacienteTurnoService } from './paciente-turno.service';

@Component({
    selector: 'jhi-paciente-turno',
    templateUrl: './paciente-turno.component.html'
})
export class PacienteTurnoComponent implements OnInit, OnDestroy {
    pacienteTurnos: IPacienteTurno[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private pacienteTurnoService: PacienteTurnoService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.pacienteTurnoService.query().subscribe(
            (res: HttpResponse<IPacienteTurno[]>) => {
                this.pacienteTurnos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInPacienteTurnos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IPacienteTurno) {
        return item.id;
    }

    registerChangeInPacienteTurnos() {
        this.eventSubscriber = this.eventManager.subscribe('pacienteTurnoListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
