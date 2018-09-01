import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMedicoCalendario } from 'app/shared/model/medico-calendario.model';
import { MedicoCalendarioService } from './medico-calendario.service';

@Component({
    selector: 'jhi-medico-calendario-delete-dialog',
    templateUrl: './medico-calendario-delete-dialog.component.html'
})
export class MedicoCalendarioDeleteDialogComponent {
    medicoCalendario: IMedicoCalendario;

    constructor(
        private medicoCalendarioService: MedicoCalendarioService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.medicoCalendarioService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'medicoCalendarioListModification',
                content: 'Deleted an medicoCalendario'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-medico-calendario-delete-popup',
    template: ''
})
export class MedicoCalendarioDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ medicoCalendario }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MedicoCalendarioDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.medicoCalendario = medicoCalendario;
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
