import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ConsultaPractica } from 'app/shared/model/consulta-practica.model';
import { ConsultaPracticaService } from './consulta-practica.service';
import { ConsultaPracticaComponent } from './consulta-practica.component';
import { ConsultaPracticaDetailComponent } from './consulta-practica-detail.component';
import { ConsultaPracticaUpdateComponent } from './consulta-practica-update.component';
import { ConsultaPracticaDeletePopupComponent } from './consulta-practica-delete-dialog.component';
import { IConsultaPractica } from 'app/shared/model/consulta-practica.model';

@Injectable({ providedIn: 'root' })
export class ConsultaPracticaResolve implements Resolve<IConsultaPractica> {
    constructor(private service: ConsultaPracticaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((consultaPractica: HttpResponse<ConsultaPractica>) => consultaPractica.body));
        }
        return of(new ConsultaPractica());
    }
}

export const consultaPracticaRoute: Routes = [
    {
        path: 'consulta-practica',
        component: ConsultaPracticaComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.consultaPractica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'consulta-practica/:id/view',
        component: ConsultaPracticaDetailComponent,
        resolve: {
            consultaPractica: ConsultaPracticaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.consultaPractica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'consulta-practica/new',
        component: ConsultaPracticaUpdateComponent,
        resolve: {
            consultaPractica: ConsultaPracticaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.consultaPractica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'consulta-practica/:id/edit',
        component: ConsultaPracticaUpdateComponent,
        resolve: {
            consultaPractica: ConsultaPracticaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.consultaPractica.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const consultaPracticaPopupRoute: Routes = [
    {
        path: 'consulta-practica/:id/delete',
        component: ConsultaPracticaDeletePopupComponent,
        resolve: {
            consultaPractica: ConsultaPracticaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.consultaPractica.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
