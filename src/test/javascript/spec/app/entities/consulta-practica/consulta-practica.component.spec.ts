/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CpsjTestModule } from '../../../test.module';
import { ConsultaPracticaComponent } from 'app/entities/consulta-practica/consulta-practica.component';
import { ConsultaPracticaService } from 'app/entities/consulta-practica/consulta-practica.service';
import { ConsultaPractica } from 'app/shared/model/consulta-practica.model';

describe('Component Tests', () => {
    describe('ConsultaPractica Management Component', () => {
        let comp: ConsultaPracticaComponent;
        let fixture: ComponentFixture<ConsultaPracticaComponent>;
        let service: ConsultaPracticaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [ConsultaPracticaComponent],
                providers: []
            })
                .overrideTemplate(ConsultaPracticaComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ConsultaPracticaComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConsultaPracticaService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ConsultaPractica(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.consultaPracticas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
