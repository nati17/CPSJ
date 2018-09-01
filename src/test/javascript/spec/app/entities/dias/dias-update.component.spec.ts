/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { CpsjTestModule } from '../../../test.module';
import { DiasUpdateComponent } from 'app/entities/dias/dias-update.component';
import { DiasService } from 'app/entities/dias/dias.service';
import { Dias } from 'app/shared/model/dias.model';

describe('Component Tests', () => {
    describe('Dias Management Update Component', () => {
        let comp: DiasUpdateComponent;
        let fixture: ComponentFixture<DiasUpdateComponent>;
        let service: DiasService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [DiasUpdateComponent]
            })
                .overrideTemplate(DiasUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DiasUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DiasService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Dias(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.dias = entity;
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
                    const entity = new Dias();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.dias = entity;
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
