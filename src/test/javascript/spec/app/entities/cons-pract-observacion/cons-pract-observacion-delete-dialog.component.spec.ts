/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CpsjTestModule } from '../../../test.module';
import { ConsPractObservacionDeleteDialogComponent } from 'app/entities/cons-pract-observacion/cons-pract-observacion-delete-dialog.component';
import { ConsPractObservacionService } from 'app/entities/cons-pract-observacion/cons-pract-observacion.service';

describe('Component Tests', () => {
    describe('ConsPractObservacion Management Delete Component', () => {
        let comp: ConsPractObservacionDeleteDialogComponent;
        let fixture: ComponentFixture<ConsPractObservacionDeleteDialogComponent>;
        let service: ConsPractObservacionService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [ConsPractObservacionDeleteDialogComponent]
            })
                .overrideTemplate(ConsPractObservacionDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ConsPractObservacionDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConsPractObservacionService);
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
