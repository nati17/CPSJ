import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMontoConsultaPractica } from 'app/shared/model/monto-consulta-practica.model';
import { MontoConsultaPracticaService } from './monto-consulta-practica.service';

@Component({
    selector: 'jhi-monto-consulta-practica-delete-dialog',
    templateUrl: './monto-consulta-practica-delete-dialog.component.html'
})
export class MontoConsultaPracticaDeleteDialogComponent {
    montoConsultaPractica: IMontoConsultaPractica;

    constructor(
        private montoConsultaPracticaService: MontoConsultaPracticaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.montoConsultaPracticaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'montoConsultaPracticaListModification',
                content: 'Deleted an montoConsultaPractica'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-monto-consulta-practica-delete-popup',
    template: ''
})
export class MontoConsultaPracticaDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ montoConsultaPractica }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MontoConsultaPracticaDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.montoConsultaPractica = montoConsultaPractica;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
