import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMedicoObraSocial } from 'app/shared/model/medico-obra-social.model';

type EntityResponseType = HttpResponse<IMedicoObraSocial>;
type EntityArrayResponseType = HttpResponse<IMedicoObraSocial[]>;

@Injectable({ providedIn: 'root' })
export class MedicoObraSocialService {
    private resourceUrl = SERVER_API_URL + 'api/medico-obra-socials';

    constructor(private http: HttpClient) {}

    create(medicoObraSocial: IMedicoObraSocial): Observable<EntityResponseType> {
        return this.http.post<IMedicoObraSocial>(this.resourceUrl, medicoObraSocial, { observe: 'response' });
    }

    update(medicoObraSocial: IMedicoObraSocial): Observable<EntityResponseType> {
        return this.http.put<IMedicoObraSocial>(this.resourceUrl, medicoObraSocial, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IMedicoObraSocial>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IMedicoObraSocial[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
