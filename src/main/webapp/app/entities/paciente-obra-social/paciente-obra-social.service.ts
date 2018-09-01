import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPacienteObraSocial } from 'app/shared/model/paciente-obra-social.model';

type EntityResponseType = HttpResponse<IPacienteObraSocial>;
type EntityArrayResponseType = HttpResponse<IPacienteObraSocial[]>;

@Injectable({ providedIn: 'root' })
export class PacienteObraSocialService {
    private resourceUrl = SERVER_API_URL + 'api/paciente-obra-socials';

    constructor(private http: HttpClient) {}

    create(pacienteObraSocial: IPacienteObraSocial): Observable<EntityResponseType> {
        return this.http.post<IPacienteObraSocial>(this.resourceUrl, pacienteObraSocial, { observe: 'response' });
    }

    update(pacienteObraSocial: IPacienteObraSocial): Observable<EntityResponseType> {
        return this.http.put<IPacienteObraSocial>(this.resourceUrl, pacienteObraSocial, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPacienteObraSocial>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPacienteObraSocial[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
