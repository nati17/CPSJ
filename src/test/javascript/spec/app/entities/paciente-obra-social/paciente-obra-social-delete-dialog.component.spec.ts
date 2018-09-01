/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CpsjTestModule } from '../../../test.module';
import { PacienteObraSocialDeleteDialogComponent } from 'app/entities/paciente-obra-social/paciente-obra-social-delete-dialog.component';
import { PacienteObraSocialService } from 'app/entities/paciente-obra-social/paciente-obra-social.service';

describe('Component Tests', () => {
    describe('PacienteObraSocial Management Delete Component', () => {
        let comp: PacienteObraSocialDeleteDialogComponent;
        let fixture: ComponentFixture<PacienteObraSocialDeleteDialogComponent>;
        let service: PacienteObraSocialService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [PacienteObraSocialDeleteDialogComponent]
            })
                .overrideTemplate(PacienteObraSocialDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PacienteObraSocialDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PacienteObraSocialService);
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
