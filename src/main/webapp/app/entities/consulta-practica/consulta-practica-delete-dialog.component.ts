import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IConsultaPractica } from 'app/shared/model/consulta-practica.model';
import { ConsultaPracticaService } from './consulta-practica.service';

@Component({
    selector: 'jhi-consulta-practica-delete-dialog',
    templateUrl: './consulta-practica-delete-dialog.component.html'
})
export class ConsultaPracticaDeleteDialogComponent {
    consultaPractica: IConsultaPractica;

    constructor(
        private consultaPracticaService: ConsultaPracticaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.consultaPracticaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'consultaPracticaListModification',
                content: 'Deleted an consultaPractica'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-consulta-practica-delete-popup',
    template: ''
})
export class ConsultaPracticaDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ consultaPractica }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ConsultaPracticaDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.consultaPractica = consultaPractica;
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
