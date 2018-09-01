/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CpsjTestModule } from '../../../test.module';
import { PacienteObraSocialComponent } from 'app/entities/paciente-obra-social/paciente-obra-social.component';
import { PacienteObraSocialService } from 'app/entities/paciente-obra-social/paciente-obra-social.service';
import { PacienteObraSocial } from 'app/shared/model/paciente-obra-social.model';

describe('Component Tests', () => {
    describe('PacienteObraSocial Management Component', () => {
        let comp: PacienteObraSocialComponent;
        let fixture: ComponentFixture<PacienteObraSocialComponent>;
        let service: PacienteObraSocialService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [PacienteObraSocialComponent],
                providers: []
            })
                .overrideTemplate(PacienteObraSocialComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PacienteObraSocialComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PacienteObraSocialService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new PacienteObraSocial(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.pacienteObraSocials[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
