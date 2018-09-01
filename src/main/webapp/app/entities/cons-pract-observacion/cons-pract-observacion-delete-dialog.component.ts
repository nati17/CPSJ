import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IConsPractObservacion } from 'app/shared/model/cons-pract-observacion.model';
import { ConsPractObservacionService } from './cons-pract-observacion.service';

@Component({
    selector: 'jhi-cons-pract-observacion-delete-dialog',
    templateUrl: './cons-pract-observacion-delete-dialog.component.html'
})
export class ConsPractObservacionDeleteDialogComponent {
    consPractObservacion: IConsPractObservacion;

    constructor(
        private consPractObservacionService: ConsPractObservacionService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.consPractObservacionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'consPractObservacionListModification',
                content: 'Deleted an consPractObservacion'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-cons-pract-observacion-delete-popup',
    template: ''
})
export class ConsPractObservacionDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ consPractObservacion }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ConsPractObservacionDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.consPractObservacion = consPractObservacion;
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
