/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CpsjTestModule } from '../../../test.module';
import { DigitalizacionComponent } from 'app/entities/digitalizacion/digitalizacion.component';
import { DigitalizacionService } from 'app/entities/digitalizacion/digitalizacion.service';
import { Digitalizacion } from 'app/shared/model/digitalizacion.model';

describe('Component Tests', () => {
    describe('Digitalizacion Management Component', () => {
        let comp: DigitalizacionComponent;
        let fixture: ComponentFixture<DigitalizacionComponent>;
        let service: DigitalizacionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [DigitalizacionComponent],
                providers: []
            })
                .overrideTemplate(DigitalizacionComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DigitalizacionComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DigitalizacionService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Digitalizacion(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.digitalizacions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
