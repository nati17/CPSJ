import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDigitalizacion } from 'app/shared/model/digitalizacion.model';
import { DigitalizacionService } from './digitalizacion.service';

@Component({
    selector: 'jhi-digitalizacion-delete-dialog',
    templateUrl: './digitalizacion-delete-dialog.component.html'
})
export class DigitalizacionDeleteDialogComponent {
    digitalizacion: IDigitalizacion;

    constructor(
        private digitalizacionService: DigitalizacionService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.digitalizacionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'digitalizacionListModification',
                content: 'Deleted an digitalizacion'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-digitalizacion-delete-popup',
    template: ''
})
export class DigitalizacionDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ digitalizacion }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DigitalizacionDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.digitalizacion = digitalizacion;
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
