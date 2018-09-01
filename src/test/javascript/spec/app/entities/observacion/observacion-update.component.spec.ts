/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { CpsjTestModule } from '../../../test.module';
import { ObservacionUpdateComponent } from 'app/entities/observacion/observacion-update.component';
import { ObservacionService } from 'app/entities/observacion/observacion.service';
import { Observacion } from 'app/shared/model/observacion.model';

describe('Component Tests', () => {
    describe('Observacion Management Update Component', () => {
        let comp: ObservacionUpdateComponent;
        let fixture: ComponentFixture<ObservacionUpdateComponent>;
        let service: ObservacionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [ObservacionUpdateComponent]
            })
                .overrideTemplate(ObservacionUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ObservacionUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ObservacionService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Observacion(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.observacion = entity;
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
                    const entity = new Observacion();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.observacion = entity;
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
