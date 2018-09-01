import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Observacion } from 'app/shared/model/observacion.model';
import { ObservacionService } from './observacion.service';
import { ObservacionComponent } from './observacion.component';
import { ObservacionDetailComponent } from './observacion-detail.component';
import { ObservacionUpdateComponent } from './observacion-update.component';
import { ObservacionDeletePopupComponent } from './observacion-delete-dialog.component';
import { IObservacion } from 'app/shared/model/observacion.model';

@Injectable({ providedIn: 'root' })
export class ObservacionResolve implements Resolve<IObservacion> {
    constructor(private service: ObservacionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((observacion: HttpResponse<Observacion>) => observacion.body));
        }
        return of(new Observacion());
    }
}

export const observacionRoute: Routes = [
    {
        path: 'observacion',
        component: ObservacionComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.observacion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'observacion/:id/view',
        component: ObservacionDetailComponent,
        resolve: {
            observacion: ObservacionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.observacion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'observacion/new',
        component: ObservacionUpdateComponent,
        resolve: {
            observacion: ObservacionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.observacion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'observacion/:id/edit',
        component: ObservacionUpdateComponent,
        resolve: {
            observacion: ObservacionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.observacion.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const observacionPopupRoute: Routes = [
    {
        path: 'observacion/:id/delete',
        component: ObservacionDeletePopupComponent,
        resolve: {
            observacion: ObservacionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.observacion.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
