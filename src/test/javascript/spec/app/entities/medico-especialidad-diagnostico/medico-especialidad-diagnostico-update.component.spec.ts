/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { CpsjTestModule } from '../../../test.module';
import { MedicoEspecialidadDiagnosticoUpdateComponent } from 'app/entities/medico-especialidad-diagnostico/medico-especialidad-diagnostico-update.component';
import { MedicoEspecialidadDiagnosticoService } from 'app/entities/medico-especialidad-diagnostico/medico-especialidad-diagnostico.service';
import { MedicoEspecialidadDiagnostico } from 'app/shared/model/medico-especialidad-diagnostico.model';

describe('Component Tests', () => {
    describe('MedicoEspecialidadDiagnostico Management Update Component', () => {
        let comp: MedicoEspecialidadDiagnosticoUpdateComponent;
        let fixture: ComponentFixture<MedicoEspecialidadDiagnosticoUpdateComponent>;
        let service: MedicoEspecialidadDiagnosticoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [MedicoEspecialidadDiagnosticoUpdateComponent]
            })
                .overrideTemplate(MedicoEspecialidadDiagnosticoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MedicoEspecialidadDiagnosticoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MedicoEspecialidadDiagnosticoService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new MedicoEspecialidadDiagnostico(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.medicoEspecialidadDiagnostico = entity;
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
                    const entity = new MedicoEspecialidadDiagnostico();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.medicoEspecialidadDiagnostico = entity;
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
