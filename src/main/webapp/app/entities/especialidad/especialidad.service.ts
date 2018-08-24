import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEspecialidad } from 'app/shared/model/especialidad.model';

type EntityResponseType = HttpResponse<IEspecialidad>;
type EntityArrayResponseType = HttpResponse<IEspecialidad[]>;

@Injectable({ providedIn: 'root' })
export class EspecialidadService {
    private resourceUrl = SERVER_API_URL + 'api/especialidads';

    constructor(private http: HttpClient) {}

    create(especialidad: IEspecialidad): Observable<EntityResponseType> {
        return this.http.post<IEspecialidad>(this.resourceUrl, especialidad, { observe: 'response' });
    }

    update(especialidad: IEspecialidad): Observable<EntityResponseType> {
        return this.http.put<IEspecialidad>(this.resourceUrl, especialidad, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IEspecialidad>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEspecialidad[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
