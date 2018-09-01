/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { CpsjTestModule } from '../../../test.module';
import { ConsultaPracticaUpdateComponent } from 'app/entities/consulta-practica/consulta-practica-update.component';
import { ConsultaPracticaService } from 'app/entities/consulta-practica/consulta-practica.service';
import { ConsultaPractica } from 'app/shared/model/consulta-practica.model';

describe('Component Tests', () => {
    describe('ConsultaPractica Management Update Component', () => {
        let comp: ConsultaPracticaUpdateComponent;
        let fixture: ComponentFixture<ConsultaPracticaUpdateComponent>;
        let service: ConsultaPracticaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [ConsultaPracticaUpdateComponent]
            })
                .overrideTemplate(ConsultaPracticaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ConsultaPracticaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConsultaPracticaService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ConsultaPractica(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.consultaPractica = entity;
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
                    const entity = new ConsultaPractica();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.consultaPractica = entity;
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
