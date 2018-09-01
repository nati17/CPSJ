import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IPacienteObraSocial } from 'app/shared/model/paciente-obra-social.model';
import { Principal } from 'app/core';
import { PacienteObraSocialService } from './paciente-obra-social.service';

@Component({
    selector: 'jhi-paciente-obra-social',
    templateUrl: './paciente-obra-social.component.html'
})
export class PacienteObraSocialComponent implements OnInit, OnDestroy {
    pacienteObraSocials: IPacienteObraSocial[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private pacienteObraSocialService: PacienteObraSocialService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.pacienteObraSocialService.query().subscribe(
            (res: HttpResponse<IPacienteObraSocial[]>) => {
                this.pacienteObraSocials = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInPacienteObraSocials();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IPacienteObraSocial) {
        return item.id;
    }

    registerChangeInPacienteObraSocials() {
        this.eventSubscriber = this.eventManager.subscribe('pacienteObraSocialListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
