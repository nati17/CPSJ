import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { MontoConsultaPractica } from 'app/shared/model/monto-consulta-practica.model';
import { MontoConsultaPracticaService } from './monto-consulta-practica.service';
import { MontoConsultaPracticaComponent } from './monto-consulta-practica.component';
import { MontoConsultaPracticaDetailComponent } from './monto-consulta-practica-detail.component';
import { MontoConsultaPracticaUpdateComponent } from './monto-consulta-practica-update.component';
import { MontoConsultaPracticaDeletePopupComponent } from './monto-consulta-practica-delete-dialog.component';
import { IMontoConsultaPractica } from 'app/shared/model/monto-consulta-practica.model';

@Injectable({ providedIn: 'root' })
export class MontoConsultaPracticaResolve implements Resolve<IMontoConsultaPractica> {
    constructor(private service: MontoConsultaPracticaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(map((montoConsultaPractica: HttpResponse<MontoConsultaPractica>) => montoConsultaPractica.body));
        }
        return of(new MontoConsultaPractica());
    }
}

export const montoConsultaPracticaRoute: Routes = [
    {
        path: 'monto-consulta-practica',
        component: MontoConsultaPracticaComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.montoConsultaPractica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'monto-consulta-practica/:id/view',
        component: MontoConsultaPracticaDetailComponent,
        resolve: {
            montoConsultaPractica: MontoConsultaPracticaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.montoConsultaPractica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'monto-consulta-practica/new',
        component: MontoConsultaPracticaUpdateComponent,
        resolve: {
            montoConsultaPractica: MontoConsultaPracticaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.montoConsultaPractica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'monto-consulta-practica/:id/edit',
        component: MontoConsultaPracticaUpdateComponent,
        resolve: {
            montoConsultaPractica: MontoConsultaPracticaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.montoConsultaPractica.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const montoConsultaPracticaPopupRoute: Routes = [
    {
        path: 'monto-consulta-practica/:id/delete',
        component: MontoConsultaPracticaDeletePopupComponent,
        resolve: {
            montoConsultaPractica: MontoConsultaPracticaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.montoConsultaPractica.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
