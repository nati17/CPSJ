import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMedicoEspecialidadDiagnostico } from 'app/shared/model/medico-especialidad-diagnostico.model';

type EntityResponseType = HttpResponse<IMedicoEspecialidadDiagnostico>;
type EntityArrayResponseType = HttpResponse<IMedicoEspecialidadDiagnostico[]>;

@Injectable({ providedIn: 'root' })
export class MedicoEspecialidadDiagnosticoService {
    private resourceUrl = SERVER_API_URL + 'api/medico-especialidad-diagnosticos';

    constructor(private http: HttpClient) {}

    create(medicoEspecialidadDiagnostico: IMedicoEspecialidadDiagnostico): Observable<EntityResponseType> {
        return this.http.post<IMedicoEspecialidadDiagnostico>(this.resourceUrl, medicoEspecialidadDiagnostico, { observe: 'response' });
    }

    update(medicoEspecialidadDiagnostico: IMedicoEspecialidadDiagnostico): Observable<EntityResponseType> {
        return this.http.put<IMedicoEspecialidadDiagnostico>(this.resourceUrl, medicoEspecialidadDiagnostico, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IMedicoEspecialidadDiagnostico>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IMedicoEspecialidadDiagnostico[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
