/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { CpsjTestModule } from '../../../test.module';
import { MedicoCalendarioUpdateComponent } from 'app/entities/medico-calendario/medico-calendario-update.component';
import { MedicoCalendarioService } from 'app/entities/medico-calendario/medico-calendario.service';
import { MedicoCalendario } from 'app/shared/model/medico-calendario.model';

describe('Component Tests', () => {
    describe('MedicoCalendario Management Update Component', () => {
        let comp: MedicoCalendarioUpdateComponent;
        let fixture: ComponentFixture<MedicoCalendarioUpdateComponent>;
        let service: MedicoCalendarioService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [MedicoCalendarioUpdateComponent]
            })
                .overrideTemplate(MedicoCalendarioUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MedicoCalendarioUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MedicoCalendarioService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new MedicoCalendario(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.medicoCalendario = entity;
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
                    const entity = new MedicoCalendario();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.medicoCalendario = entity;
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
