/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CpsjTestModule } from '../../../test.module';
import { MedicoObraSocialComponent } from 'app/entities/medico-obra-social/medico-obra-social.component';
import { MedicoObraSocialService } from 'app/entities/medico-obra-social/medico-obra-social.service';
import { MedicoObraSocial } from 'app/shared/model/medico-obra-social.model';

describe('Component Tests', () => {
    describe('MedicoObraSocial Management Component', () => {
        let comp: MedicoObraSocialComponent;
        let fixture: ComponentFixture<MedicoObraSocialComponent>;
        let service: MedicoObraSocialService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [MedicoObraSocialComponent],
                providers: []
            })
                .overrideTemplate(MedicoObraSocialComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MedicoObraSocialComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MedicoObraSocialService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new MedicoObraSocial(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.medicoObraSocials[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
