/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CpsjTestModule } from '../../../test.module';
import { MedicoObraSocialDeleteDialogComponent } from 'app/entities/medico-obra-social/medico-obra-social-delete-dialog.component';
import { MedicoObraSocialService } from 'app/entities/medico-obra-social/medico-obra-social.service';

describe('Component Tests', () => {
    describe('MedicoObraSocial Management Delete Component', () => {
        let comp: MedicoObraSocialDeleteDialogComponent;
        let fixture: ComponentFixture<MedicoObraSocialDeleteDialogComponent>;
        let service: MedicoObraSocialService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [MedicoObraSocialDeleteDialogComponent]
            })
                .overrideTemplate(MedicoObraSocialDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MedicoObraSocialDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MedicoObraSocialService);
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
