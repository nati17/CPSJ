/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CpsjTestModule } from '../../../test.module';
import { PacienteTurnoDetailComponent } from 'app/entities/paciente-turno/paciente-turno-detail.component';
import { PacienteTurno } from 'app/shared/model/paciente-turno.model';

describe('Component Tests', () => {
    describe('PacienteTurno Management Detail Component', () => {
        let comp: PacienteTurnoDetailComponent;
        let fixture: ComponentFixture<PacienteTurnoDetailComponent>;
        const route = ({ data: of({ pacienteTurno: new PacienteTurno(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [PacienteTurnoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PacienteTurnoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PacienteTurnoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.pacienteTurno).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
