/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CpsjTestModule } from '../../../test.module';
import { MedicoCalendarioDetailComponent } from 'app/entities/medico-calendario/medico-calendario-detail.component';
import { MedicoCalendario } from 'app/shared/model/medico-calendario.model';

describe('Component Tests', () => {
    describe('MedicoCalendario Management Detail Component', () => {
        let comp: MedicoCalendarioDetailComponent;
        let fixture: ComponentFixture<MedicoCalendarioDetailComponent>;
        const route = ({ data: of({ medicoCalendario: new MedicoCalendario(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [MedicoCalendarioDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(MedicoCalendarioDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MedicoCalendarioDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.medicoCalendario).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
