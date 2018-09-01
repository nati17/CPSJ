/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CpsjTestModule } from '../../../test.module';
import { DiasDetailComponent } from 'app/entities/dias/dias-detail.component';
import { Dias } from 'app/shared/model/dias.model';

describe('Component Tests', () => {
    describe('Dias Management Detail Component', () => {
        let comp: DiasDetailComponent;
        let fixture: ComponentFixture<DiasDetailComponent>;
        const route = ({ data: of({ dias: new Dias(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [DiasDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DiasDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DiasDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.dias).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
