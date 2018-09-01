/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CpsjTestModule } from '../../../test.module';
import { MedicoCalendarioComponent } from 'app/entities/medico-calendario/medico-calendario.component';
import { MedicoCalendarioService } from 'app/entities/medico-calendario/medico-calendario.service';
import { MedicoCalendario } from 'app/shared/model/medico-calendario.model';

describe('Component Tests', () => {
    describe('MedicoCalendario Management Component', () => {
        let comp: MedicoCalendarioComponent;
        let fixture: ComponentFixture<MedicoCalendarioComponent>;
        let service: MedicoCalendarioService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [MedicoCalendarioComponent],
                providers: []
            })
                .overrideTemplate(MedicoCalendarioComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MedicoCalendarioComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MedicoCalendarioService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new MedicoCalendario(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.medicoCalendarios[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
