/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CpsjTestModule } from '../../../test.module';
import { MontoConsultaPracticaComponent } from 'app/entities/monto-consulta-practica/monto-consulta-practica.component';
import { MontoConsultaPracticaService } from 'app/entities/monto-consulta-practica/monto-consulta-practica.service';
import { MontoConsultaPractica } from 'app/shared/model/monto-consulta-practica.model';

describe('Component Tests', () => {
    describe('MontoConsultaPractica Management Component', () => {
        let comp: MontoConsultaPracticaComponent;
        let fixture: ComponentFixture<MontoConsultaPracticaComponent>;
        let service: MontoConsultaPracticaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [MontoConsultaPracticaComponent],
                providers: []
            })
                .overrideTemplate(MontoConsultaPracticaComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MontoConsultaPracticaComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MontoConsultaPracticaService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new MontoConsultaPractica(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.montoConsultaPracticas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
