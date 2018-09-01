/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CpsjTestModule } from '../../../test.module';
import { MedicoEspecialidadDiagnosticoComponent } from 'app/entities/medico-especialidad-diagnostico/medico-especialidad-diagnostico.component';
import { MedicoEspecialidadDiagnosticoService } from 'app/entities/medico-especialidad-diagnostico/medico-especialidad-diagnostico.service';
import { MedicoEspecialidadDiagnostico } from 'app/shared/model/medico-especialidad-diagnostico.model';

describe('Component Tests', () => {
    describe('MedicoEspecialidadDiagnostico Management Component', () => {
        let comp: MedicoEspecialidadDiagnosticoComponent;
        let fixture: ComponentFixture<MedicoEspecialidadDiagnosticoComponent>;
        let service: MedicoEspecialidadDiagnosticoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [MedicoEspecialidadDiagnosticoComponent],
                providers: []
            })
                .overrideTemplate(MedicoEspecialidadDiagnosticoComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MedicoEspecialidadDiagnosticoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MedicoEspecialidadDiagnosticoService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new MedicoEspecialidadDiagnostico(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.medicoEspecialidadDiagnosticos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
