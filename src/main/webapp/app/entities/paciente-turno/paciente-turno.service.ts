import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPacienteTurno } from 'app/shared/model/paciente-turno.model';

type EntityResponseType = HttpResponse<IPacienteTurno>;
type EntityArrayResponseType = HttpResponse<IPacienteTurno[]>;

@Injectable({ providedIn: 'root' })
export class PacienteTurnoService {
    private resourceUrl = SERVER_API_URL + 'api/paciente-turnos';

    constructor(private http: HttpClient) {}

    create(pacienteTurno: IPacienteTurno): Observable<EntityResponseType> {
        return this.http.post<IPacienteTurno>(this.resourceUrl, pacienteTurno, { observe: 'response' });
    }

    update(pacienteTurno: IPacienteTurno): Observable<EntityResponseType> {
        return this.http.put<IPacienteTurno>(this.resourceUrl, pacienteTurno, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPacienteTurno>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPacienteTurno[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
