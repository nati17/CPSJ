/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CpsjTestModule } from '../../../test.module';
import { DigitalizacionDetailComponent } from 'app/entities/digitalizacion/digitalizacion-detail.component';
import { Digitalizacion } from 'app/shared/model/digitalizacion.model';

describe('Component Tests', () => {
    describe('Digitalizacion Management Detail Component', () => {
        let comp: DigitalizacionDetailComponent;
        let fixture: ComponentFixture<DigitalizacionDetailComponent>;
        const route = ({ data: of({ digitalizacion: new Digitalizacion(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [DigitalizacionDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DigitalizacionDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DigitalizacionDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.digitalizacion).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
