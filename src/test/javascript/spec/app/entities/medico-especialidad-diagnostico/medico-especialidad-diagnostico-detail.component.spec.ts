/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CpsjTestModule } from '../../../test.module';
import { MedicoEspecialidadDiagnosticoDetailComponent } from 'app/entities/medico-especialidad-diagnostico/medico-especialidad-diagnostico-detail.component';
import { MedicoEspecialidadDiagnostico } from 'app/shared/model/medico-especialidad-diagnostico.model';

describe('Component Tests', () => {
    describe('MedicoEspecialidadDiagnostico Management Detail Component', () => {
        let comp: MedicoEspecialidadDiagnosticoDetailComponent;
        let fixture: ComponentFixture<MedicoEspecialidadDiagnosticoDetailComponent>;
        const route = ({ data: of({ medicoEspecialidadDiagnostico: new MedicoEspecialidadDiagnostico(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [MedicoEspecialidadDiagnosticoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(MedicoEspecialidadDiagnosticoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MedicoEspecialidadDiagnosticoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.medicoEspecialidadDiagnostico).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
