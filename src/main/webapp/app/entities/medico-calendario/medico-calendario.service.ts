import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMedicoCalendario } from 'app/shared/model/medico-calendario.model';

type EntityResponseType = HttpResponse<IMedicoCalendario>;
type EntityArrayResponseType = HttpResponse<IMedicoCalendario[]>;

@Injectable({ providedIn: 'root' })
export class MedicoCalendarioService {
    private resourceUrl = SERVER_API_URL + 'api/medico-calendarios';

    constructor(private http: HttpClient) {}

    create(medicoCalendario: IMedicoCalendario): Observable<EntityResponseType> {
        return this.http.post<IMedicoCalendario>(this.resourceUrl, medicoCalendario, { observe: 'response' });
    }

    update(medicoCalendario: IMedicoCalendario): Observable<EntityResponseType> {
        return this.http.put<IMedicoCalendario>(this.resourceUrl, medicoCalendario, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IMedicoCalendario>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IMedicoCalendario[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
