/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { MedicoEspecialidadDiagnosticoService } from 'app/entities/medico-especialidad-diagnostico/medico-especialidad-diagnostico.service';
import { MedicoEspecialidadDiagnostico } from 'app/shared/model/medico-especialidad-diagnostico.model';
import { SERVER_API_URL } from 'app/app.constants';

describe('Service Tests', () => {
    describe('MedicoEspecialidadDiagnostico Service', () => {
        let injector: TestBed;
        let service: MedicoEspecialidadDiagnosticoService;
        let httpMock: HttpTestingController;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(MedicoEspecialidadDiagnosticoService);
            httpMock = injector.get(HttpTestingController);
        });

        describe('Service methods', () => {
            it('should call correct URL', () => {
                service.find(123).subscribe(() => {});

                const req = httpMock.expectOne({ method: 'GET' });

                const resourceUrl = SERVER_API_URL + 'api/medico-especialidad-diagnosticos';
                expect(req.request.url).toEqual(resourceUrl + '/' + 123);
            });

            it('should create a MedicoEspecialidadDiagnostico', () => {
                service.create(new MedicoEspecialidadDiagnostico(null)).subscribe(received => {
                    expect(received.body.id).toEqual(null);
                });

                const req = httpMock.expectOne({ method: 'POST' });
                req.flush({ id: null });
            });

            it('should update a MedicoEspecialidadDiagnostico', () => {
                service.update(new MedicoEspecialidadDiagnostico(123)).subscribe(received => {
                    expect(received.body.id).toEqual(123);
                });

                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush({ id: 123 });
            });

            it('should return a MedicoEspecialidadDiagnostico', () => {
                service.find(123).subscribe(received => {
                    expect(received.body.id).toEqual(123);
                });

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush({ id: 123 });
            });

            it('should return a list of MedicoEspecialidadDiagnostico', () => {
                service.query(null).subscribe(received => {
                    expect(received.body[0].id).toEqual(123);
                });

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush([new MedicoEspecialidadDiagnostico(123)]);
            });

            it('should delete a MedicoEspecialidadDiagnostico', () => {
                service.delete(123).subscribe(received => {
                    expect(received.url).toContain('/' + 123);
                });

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush(null);
            });

            it('should propagate not found response', () => {
                service.find(123).subscribe(null, (_error: any) => {
                    expect(_error.status).toEqual(404);
                });

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush('Invalid request parameters', {
                    status: 404,
                    statusText: 'Bad Request'
                });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
