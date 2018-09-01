/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { CpsjTestModule } from '../../../test.module';
import { MedicoObraSocialUpdateComponent } from 'app/entities/medico-obra-social/medico-obra-social-update.component';
import { MedicoObraSocialService } from 'app/entities/medico-obra-social/medico-obra-social.service';
import { MedicoObraSocial } from 'app/shared/model/medico-obra-social.model';

describe('Component Tests', () => {
    describe('MedicoObraSocial Management Update Component', () => {
        let comp: MedicoObraSocialUpdateComponent;
        let fixture: ComponentFixture<MedicoObraSocialUpdateComponent>;
        let service: MedicoObraSocialService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [MedicoObraSocialUpdateComponent]
            })
                .overrideTemplate(MedicoObraSocialUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MedicoObraSocialUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MedicoObraSocialService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new MedicoObraSocial(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.medicoObraSocial = entity;
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
                    const entity = new MedicoObraSocial();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.medicoObraSocial = entity;
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
