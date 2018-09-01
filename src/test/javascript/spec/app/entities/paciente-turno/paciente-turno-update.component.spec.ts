/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { CpsjTestModule } from '../../../test.module';
import { PacienteTurnoUpdateComponent } from 'app/entities/paciente-turno/paciente-turno-update.component';
import { PacienteTurnoService } from 'app/entities/paciente-turno/paciente-turno.service';
import { PacienteTurno } from 'app/shared/model/paciente-turno.model';

describe('Component Tests', () => {
    describe('PacienteTurno Management Update Component', () => {
        let comp: PacienteTurnoUpdateComponent;
        let fixture: ComponentFixture<PacienteTurnoUpdateComponent>;
        let service: PacienteTurnoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [PacienteTurnoUpdateComponent]
            })
                .overrideTemplate(PacienteTurnoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PacienteTurnoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PacienteTurnoService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new PacienteTurno(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.pacienteTurno = entity;
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
                    const entity = new PacienteTurno();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.pacienteTurno = entity;
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
