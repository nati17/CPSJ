/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { CpsjTestModule } from '../../../test.module';
import { DigitalizacionUpdateComponent } from 'app/entities/digitalizacion/digitalizacion-update.component';
import { DigitalizacionService } from 'app/entities/digitalizacion/digitalizacion.service';
import { Digitalizacion } from 'app/shared/model/digitalizacion.model';

describe('Component Tests', () => {
    describe('Digitalizacion Management Update Component', () => {
        let comp: DigitalizacionUpdateComponent;
        let fixture: ComponentFixture<DigitalizacionUpdateComponent>;
        let service: DigitalizacionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [DigitalizacionUpdateComponent]
            })
                .overrideTemplate(DigitalizacionUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DigitalizacionUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DigitalizacionService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Digitalizacion(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.digitalizacion = entity;
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
                    const entity = new Digitalizacion();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.digitalizacion = entity;
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
