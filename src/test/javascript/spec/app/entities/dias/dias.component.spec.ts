/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CpsjTestModule } from '../../../test.module';
import { DiasComponent } from 'app/entities/dias/dias.component';
import { DiasService } from 'app/entities/dias/dias.service';
import { Dias } from 'app/shared/model/dias.model';

describe('Component Tests', () => {
    describe('Dias Management Component', () => {
        let comp: DiasComponent;
        let fixture: ComponentFixture<DiasComponent>;
        let service: DiasService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CpsjTestModule],
                declarations: [DiasComponent],
                providers: []
            })
                .overrideTemplate(DiasComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DiasComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DiasService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Dias(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.dias[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
