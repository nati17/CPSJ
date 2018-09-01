import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMedicoEspecialidadDiagnostico } from 'app/shared/model/medico-especialidad-diagnostico.model';
import { MedicoEspecialidadDiagnosticoService } from './medico-especialidad-diagnostico.service';

@Component({
    selector: 'jhi-medico-especialidad-diagnostico-delete-dialog',
    templateUrl: './medico-especialidad-diagnostico-delete-dialog.component.html'
})
export class MedicoEspecialidadDiagnosticoDeleteDialogComponent {
    medicoEspecialidadDiagnostico: IMedicoEspecialidadDiagnostico;

    constructor(
        private medicoEspecialidadDiagnosticoService: MedicoEspecialidadDiagnosticoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.medicoEspecialidadDiagnosticoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'medicoEspecialidadDiagnosticoListModification',
                content: 'Deleted an medicoEspecialidadDiagnostico'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-medico-especialidad-diagnostico-delete-popup',
    template: ''
})
export class MedicoEspecialidadDiagnosticoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ medicoEspecialidadDiagnostico }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MedicoEspecialidadDiagnosticoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.medicoEspecialidadDiagnostico = medicoEspecialidadDiagnostico;
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
