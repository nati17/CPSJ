/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CpsjTestModule } from '../../../test.module';
import { PacienteTurnoDeleteDialogComponent } from 'app/entities/paciente-turno/paciente-turno-delete-dialog.component';
import { PacienteTurnoService } from 'app/entities/paciente-turno/paciente-turno.service';

describe('Component Tests', () => {
    describe('PacienteTurno Management Delete Component', () => {
        let comp: PacienteTurnoDeleteDialogComponent;
        let fixture: ComponentFixture<PacienteTurnoDeleteDialogComponent>;
        let service: PacienteTurnoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [PacienteTurnoDeleteDialogComponent]
            })
                .overrideTemplate(PacienteTurnoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PacienteTurnoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PacienteTurnoService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it(
                'Should call delete service on confirmDelete',
                inject(
                    [],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });
});
