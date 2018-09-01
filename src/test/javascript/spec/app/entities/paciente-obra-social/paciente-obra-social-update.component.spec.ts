/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { CpsjTestModule } from '../../../test.module';
import { PacienteObraSocialUpdateComponent } from 'app/entities/paciente-obra-social/paciente-obra-social-update.component';
import { PacienteObraSocialService } from 'app/entities/paciente-obra-social/paciente-obra-social.service';
import { PacienteObraSocial } from 'app/shared/model/paciente-obra-social.model';

describe('Component Tests', () => {
    describe('PacienteObraSocial Management Update Component', () => {
        let comp: PacienteObraSocialUpdateComponent;
        let fixture: ComponentFixture<PacienteObraSocialUpdateComponent>;
        let service: PacienteObraSocialService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [PacienteObraSocialUpdateComponent]
            })
                .overrideTemplate(PacienteObraSocialUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PacienteObraSocialUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PacienteObraSocialService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new PacienteObraSocial(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.pacienteObraSocial = entity;
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
                    const entity = new PacienteObraSocial();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.pacienteObraSocial = entity;
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
