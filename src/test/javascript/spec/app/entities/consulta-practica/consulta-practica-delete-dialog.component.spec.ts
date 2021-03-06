/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CpsjTestModule } from '../../../test.module';
import { ConsultaPracticaDeleteDialogComponent } from 'app/entities/consulta-practica/consulta-practica-delete-dialog.component';
import { ConsultaPracticaService } from 'app/entities/consulta-practica/consulta-practica.service';

describe('Component Tests', () => {
    describe('ConsultaPractica Management Delete Component', () => {
        let comp: ConsultaPracticaDeleteDialogComponent;
        let fixture: ComponentFixture<ConsultaPracticaDeleteDialogComponent>;
        let service: ConsultaPracticaService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [ConsultaPracticaDeleteDialogComponent]
            })
                .overrideTemplate(ConsultaPracticaDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ConsultaPracticaDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConsultaPracticaService);
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
