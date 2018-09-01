/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { CpsjTestModule } from '../../../test.module';
import { ConsPractObservacionUpdateComponent } from 'app/entities/cons-pract-observacion/cons-pract-observacion-update.component';
import { ConsPractObservacionService } from 'app/entities/cons-pract-observacion/cons-pract-observacion.service';
import { ConsPractObservacion } from 'app/shared/model/cons-pract-observacion.model';

describe('Component Tests', () => {
    describe('ConsPractObservacion Management Update Component', () => {
        let comp: ConsPractObservacionUpdateComponent;
        let fixture: ComponentFixture<ConsPractObservacionUpdateComponent>;
        let service: ConsPractObservacionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [ConsPractObservacionUpdateComponent]
            })
                .overrideTemplate(ConsPractObservacionUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ConsPractObservacionUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConsPractObservacionService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ConsPractObservacion(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.consPractObservacion = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ConsPractObservacion();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.consPractObservacion = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
