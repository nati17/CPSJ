/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { CpsjTestModule } from '../../../test.module';
import { MontoConsultaPracticaUpdateComponent } from 'app/entities/monto-consulta-practica/monto-consulta-practica-update.component';
import { MontoConsultaPracticaService } from 'app/entities/monto-consulta-practica/monto-consulta-practica.service';
import { MontoConsultaPractica } from 'app/shared/model/monto-consulta-practica.model';

describe('Component Tests', () => {
    describe('MontoConsultaPractica Management Update Component', () => {
        let comp: MontoConsultaPracticaUpdateComponent;
        let fixture: ComponentFixture<MontoConsultaPracticaUpdateComponent>;
        let service: MontoConsultaPracticaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [MontoConsultaPracticaUpdateComponent]
            })
                .overrideTemplate(MontoConsultaPracticaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MontoConsultaPracticaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MontoConsultaPracticaService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new MontoConsultaPractica(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.montoConsultaPractica = entity;
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
                    const entity = new MontoConsultaPractica();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.montoConsultaPractica = entity;
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
