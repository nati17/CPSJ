import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPacienteTurno } from 'app/shared/model/paciente-turno.model';
import { PacienteTurnoService } from './paciente-turno.service';

@Component({
    selector: 'jhi-paciente-turno-delete-dialog',
    templateUrl: './paciente-turno-delete-dialog.component.html'
})
export class PacienteTurnoDeleteDialogComponent {
    pacienteTurno: IPacienteTurno;

    constructor(
        private pacienteTurnoService: PacienteTurnoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.pacienteTurnoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'pacienteTurnoListModification',
                content: 'Deleted an pacienteTurno'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-paciente-turno-delete-popup',
    template: ''
})
export class PacienteTurnoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ pacienteTurno }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PacienteTurnoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.pacienteTurno = pacienteTurno;
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
