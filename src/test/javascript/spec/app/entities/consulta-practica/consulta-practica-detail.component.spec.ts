/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CpsjTestModule } from '../../../test.module';
import { ConsultaPracticaDetailComponent } from 'app/entities/consulta-practica/consulta-practica-detail.component';
import { ConsultaPractica } from 'app/shared/model/consulta-practica.model';

describe('Component Tests', () => {
    describe('ConsultaPractica Management Detail Component', () => {
        let comp: ConsultaPracticaDetailComponent;
        let fixture: ComponentFixture<ConsultaPracticaDetailComponent>;
        const route = ({ data: of({ consultaPractica: new ConsultaPractica(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [ConsultaPracticaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ConsultaPracticaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ConsultaPracticaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.consultaPractica).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
