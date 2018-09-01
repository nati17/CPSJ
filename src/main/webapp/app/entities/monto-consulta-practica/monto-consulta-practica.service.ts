import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMontoConsultaPractica } from 'app/shared/model/monto-consulta-practica.model';

type EntityResponseType = HttpResponse<IMontoConsultaPractica>;
type EntityArrayResponseType = HttpResponse<IMontoConsultaPractica[]>;

@Injectable({ providedIn: 'root' })
export class MontoConsultaPracticaService {
    private resourceUrl = SERVER_API_URL + 'api/monto-consulta-practicas';

    constructor(private http: HttpClient) {}

    create(montoConsultaPractica: IMontoConsultaPractica): Observable<EntityResponseType> {
        return this.http.post<IMontoConsultaPractica>(this.resourceUrl, montoConsultaPractica, { observe: 'response' });
    }

    update(montoConsultaPractica: IMontoConsultaPractica): Observable<EntityResponseType> {
        return this.http.put<IMontoConsultaPractica>(this.resourceUrl, montoConsultaPractica, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IMontoConsultaPractica>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IMontoConsultaPractica[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
