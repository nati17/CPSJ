import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMedicoObraSocial } from 'app/shared/model/medico-obra-social.model';
import { MedicoObraSocialService } from './medico-obra-social.service';

@Component({
    selector: 'jhi-medico-obra-social-delete-dialog',
    templateUrl: './medico-obra-social-delete-dialog.component.html'
})
export class MedicoObraSocialDeleteDialogComponent {
    medicoObraSocial: IMedicoObraSocial;

    constructor(
        private medicoObraSocialService: MedicoObraSocialService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.medicoObraSocialService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'medicoObraSocialListModification',
                content: 'Deleted an medicoObraSocial'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-medico-obra-social-delete-popup',
    template: ''
})
export class MedicoObraSocialDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ medicoObraSocial }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MedicoObraSocialDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.medicoObraSocial = medicoObraSocial;
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
