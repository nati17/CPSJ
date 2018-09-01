import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IMedicoEspecialidadDiagnostico } from 'app/shared/model/medico-especialidad-diagnostico.model';
import { Principal } from 'app/core';
import { MedicoEspecialidadDiagnosticoService } from './medico-especialidad-diagnostico.service';

@Component({
    selector: 'jhi-medico-especialidad-diagnostico',
    templateUrl: './medico-especialidad-diagnostico.component.html'
})
export class MedicoEspecialidadDiagnosticoComponent implements OnInit, OnDestroy {
    medicoEspecialidadDiagnosticos: IMedicoEspecialidadDiagnostico[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private medicoEspecialidadDiagnosticoService: MedicoEspecialidadDiagnosticoService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.medicoEspecialidadDiagnosticoService.query().subscribe(
            (res: HttpResponse<IMedicoEspecialidadDiagnostico[]>) => {
                this.medicoEspecialidadDiagnosticos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInMedicoEspecialidadDiagnosticos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IMedicoEspecialidadDiagnostico) {
        return item.id;
    }

    registerChangeInMedicoEspecialidadDiagnosticos() {
        this.eventSubscriber = this.eventManager.subscribe('medicoEspecialidadDiagnosticoListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
