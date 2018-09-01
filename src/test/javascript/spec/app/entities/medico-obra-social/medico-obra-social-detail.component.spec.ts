/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CpsjTestModule } from '../../../test.module';
import { MedicoObraSocialDetailComponent } from 'app/entities/medico-obra-social/medico-obra-social-detail.component';
import { MedicoObraSocial } from 'app/shared/model/medico-obra-social.model';

describe('Component Tests', () => {
    describe('MedicoObraSocial Management Detail Component', () => {
        let comp: MedicoObraSocialDetailComponent;
        let fixture: ComponentFixture<MedicoObraSocialDetailComponent>;
        const route = ({ data: of({ medicoObraSocial: new MedicoObraSocial(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [MedicoObraSocialDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(MedicoObraSocialDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MedicoObraSocialDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.medicoObraSocial).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
