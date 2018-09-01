import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ConsPractObservacion } from 'app/shared/model/cons-pract-observacion.model';
import { ConsPractObservacionService } from './cons-pract-observacion.service';
import { ConsPractObservacionComponent } from './cons-pract-observacion.component';
import { ConsPractObservacionDetailComponent } from './cons-pract-observacion-detail.component';
import { ConsPractObservacionUpdateComponent } from './cons-pract-observacion-update.component';
import { ConsPractObservacionDeletePopupComponent } from './cons-pract-observacion-delete-dialog.component';
import { IConsPractObservacion } from 'app/shared/model/cons-pract-observacion.model';

@Injectable({ providedIn: 'root' })
export class ConsPractObservacionResolve implements Resolve<IConsPractObservacion> {
    constructor(private service: ConsPractObservacionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((consPractObservacion: HttpResponse<ConsPractObservacion>) => consPractObservacion.body));
        }
        return of(new ConsPractObservacion());
    }
}

export const consPractObservacionRoute: Routes = [
    {
        path: 'cons-pract-observacion',
        component: ConsPractObservacionComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.consPractObservacion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cons-pract-observacion/:id/view',
        component: ConsPractObservacionDetailComponent,
        resolve: {
            consPractObservacion: ConsPractObservacionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.consPractObservacion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cons-pract-observacion/new',
        component: ConsPractObservacionUpdateComponent,
        resolve: {
            consPractObservacion: ConsPractObservacionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.consPractObservacion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cons-pract-observacion/:id/edit',
        component: ConsPractObservacionUpdateComponent,
        resolve: {
            consPractObservacion: ConsPractObservacionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.consPractObservacion.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const consPractObservacionPopupRoute: Routes = [
    {
        path: 'cons-pract-observacion/:id/delete',
        component: ConsPractObservacionDeletePopupComponent,
        resolve: {
            consPractObservacion: ConsPractObservacionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.consPractObservacion.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
