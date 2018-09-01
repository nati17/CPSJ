/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CpsjTestModule } from '../../../test.module';
import { ObservacionDetailComponent } from 'app/entities/observacion/observacion-detail.component';
import { Observacion } from 'app/shared/model/observacion.model';

describe('Component Tests', () => {
    describe('Observacion Management Detail Component', () => {
        let comp: ObservacionDetailComponent;
        let fixture: ComponentFixture<ObservacionDetailComponent>;
        const route = ({ data: of({ observacion: new Observacion(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [ObservacionDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ObservacionDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ObservacionDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.observacion).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
