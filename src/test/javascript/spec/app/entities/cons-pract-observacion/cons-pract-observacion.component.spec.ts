/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CpsjTestModule } from '../../../test.module';
import { ConsPractObservacionComponent } from 'app/entities/cons-pract-observacion/cons-pract-observacion.component';
import { ConsPractObservacionService } from 'app/entities/cons-pract-observacion/cons-pract-observacion.service';
import { ConsPractObservacion } from 'app/shared/model/cons-pract-observacion.model';

describe('Component Tests', () => {
    describe('ConsPractObservacion Management Component', () => {
        let comp: ConsPractObservacionComponent;
        let fixture: ComponentFixture<ConsPractObservacionComponent>;
        let service: ConsPractObservacionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [ConsPractObservacionComponent],
                providers: []
            })
                .overrideTemplate(ConsPractObservacionComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ConsPractObservacionComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConsPractObservacionService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ConsPractObservacion(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.consPractObservacions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
