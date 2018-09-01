/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CpsjTestModule } from '../../../test.module';
import { MontoConsultaPracticaDetailComponent } from 'app/entities/monto-consulta-practica/monto-consulta-practica-detail.component';
import { MontoConsultaPractica } from 'app/shared/model/monto-consulta-practica.model';

describe('Component Tests', () => {
    describe('MontoConsultaPractica Management Detail Component', () => {
        let comp: MontoConsultaPracticaDetailComponent;
        let fixture: ComponentFixture<MontoConsultaPracticaDetailComponent>;
        const route = ({ data: of({ montoConsultaPractica: new MontoConsultaPractica(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [MontoConsultaPracticaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(MontoConsultaPracticaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MontoConsultaPracticaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.montoConsultaPractica).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
