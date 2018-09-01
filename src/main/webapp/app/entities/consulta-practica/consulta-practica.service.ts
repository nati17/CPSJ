import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IConsultaPractica } from 'app/shared/model/consulta-practica.model';

type EntityResponseType = HttpResponse<IConsultaPractica>;
type EntityArrayResponseType = HttpResponse<IConsultaPractica[]>;

@Injectable({ providedIn: 'root' })
export class ConsultaPracticaService {
    private resourceUrl = SERVER_API_URL + 'api/consulta-practicas';

    constructor(private http: HttpClient) {}

    create(consultaPractica: IConsultaPractica): Observable<EntityResponseType> {
        return this.http.post<IConsultaPractica>(this.resourceUrl, consultaPractica, { observe: 'response' });
    }

    update(consultaPractica: IConsultaPractica): Observable<EntityResponseType> {
        return this.http.put<IConsultaPractica>(this.resourceUrl, consultaPractica, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IConsultaPractica>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IConsultaPractica[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
