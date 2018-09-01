import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDigitalizacion } from 'app/shared/model/digitalizacion.model';

type EntityResponseType = HttpResponse<IDigitalizacion>;
type EntityArrayResponseType = HttpResponse<IDigitalizacion[]>;

@Injectable({ providedIn: 'root' })
export class DigitalizacionService {
    private resourceUrl = SERVER_API_URL + 'api/digitalizacions';

    constructor(private http: HttpClient) {}

    create(digitalizacion: IDigitalizacion): Observable<EntityResponseType> {
        return this.http.post<IDigitalizacion>(this.resourceUrl, digitalizacion, { observe: 'response' });
    }

    update(digitalizacion: IDigitalizacion): Observable<EntityResponseType> {
        return this.http.put<IDigitalizacion>(this.resourceUrl, digitalizacion, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IDigitalizacion>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IDigitalizacion[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
