/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CpsjTestModule } from '../../../test.module';
import { PacienteTurnoComponent } from 'app/entities/paciente-turno/paciente-turno.component';
import { PacienteTurnoService } from 'app/entities/paciente-turno/paciente-turno.service';
import { PacienteTurno } from 'app/shared/model/paciente-turno.model';

describe('Component Tests', () => {
    describe('PacienteTurno Management Component', () => {
        let comp: PacienteTurnoComponent;
        let fixture: ComponentFixture<PacienteTurnoComponent>;
        let service: PacienteTurnoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [PacienteTurnoComponent],
                providers: []
            })
                .overrideTemplate(PacienteTurnoComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PacienteTurnoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PacienteTurnoService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new PacienteTurno(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.pacienteTurnos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
