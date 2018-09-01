/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CpsjTestModule } from '../../../test.module';
import { ConsPractObservacionDetailComponent } from 'app/entities/cons-pract-observacion/cons-pract-observacion-detail.component';
import { ConsPractObservacion } from 'app/shared/model/cons-pract-observacion.model';

describe('Component Tests', () => {
    describe('ConsPractObservacion Management Detail Component', () => {
        let comp: ConsPractObservacionDetailComponent;
        let fixture: ComponentFixture<ConsPractObservacionDetailComponent>;
        const route = ({ data: of({ consPractObservacion: new ConsPractObservacion(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [ConsPractObservacionDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ConsPractObservacionDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ConsPractObservacionDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.consPractObservacion).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
