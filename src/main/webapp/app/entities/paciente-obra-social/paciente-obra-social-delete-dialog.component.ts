import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPacienteObraSocial } from 'app/shared/model/paciente-obra-social.model';
import { PacienteObraSocialService } from './paciente-obra-social.service';

@Component({
    selector: 'jhi-paciente-obra-social-delete-dialog',
    templateUrl: './paciente-obra-social-delete-dialog.component.html'
})
export class PacienteObraSocialDeleteDialogComponent {
    pacienteObraSocial: IPacienteObraSocial;

    constructor(
        private pacienteObraSocialService: PacienteObraSocialService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.pacienteObraSocialService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'pacienteObraSocialListModification',
                content: 'Deleted an pacienteObraSocial'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-paciente-obra-social-delete-popup',
    template: ''
})
export class PacienteObraSocialDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ pacienteObraSocial }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PacienteObraSocialDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.pacienteObraSocial = pacienteObraSocial;
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
