/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CpsjTestModule } from '../../../test.module';
import { PacienteObraSocialDetailComponent } from 'app/entities/paciente-obra-social/paciente-obra-social-detail.component';
import { PacienteObraSocial } from 'app/shared/model/paciente-obra-social.model';

describe('Component Tests', () => {
    describe('PacienteObraSocial Management Detail Component', () => {
        let comp: PacienteObraSocialDetailComponent;
        let fixture: ComponentFixture<PacienteObraSocialDetailComponent>;
        const route = ({ data: of({ pacienteObraSocial: new PacienteObraSocial(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [PacienteObraSocialDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PacienteObraSocialDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PacienteObraSocialDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.pacienteObraSocial).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
