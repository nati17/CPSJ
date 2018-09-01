/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CpsjTestModule } from '../../../test.module';
import { MontoConsultaPracticaDeleteDialogComponent } from 'app/entities/monto-consulta-practica/monto-consulta-practica-delete-dialog.component';
import { MontoConsultaPracticaService } from 'app/entities/monto-consulta-practica/monto-consulta-practica.service';

describe('Component Tests', () => {
    describe('MontoConsultaPractica Management Delete Component', () => {
        let comp: MontoConsultaPracticaDeleteDialogComponent;
        let fixture: ComponentFixture<MontoConsultaPracticaDeleteDialogComponent>;
        let service: MontoConsultaPracticaService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [MontoConsultaPracticaDeleteDialogComponent]
            })
                .overrideTemplate(MontoConsultaPracticaDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MontoConsultaPracticaDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MontoConsultaPracticaService);
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
