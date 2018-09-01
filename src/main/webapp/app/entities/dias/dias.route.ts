import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Dias } from 'app/shared/model/dias.model';
import { DiasService } from './dias.service';
import { DiasComponent } from './dias.component';
import { DiasDetailComponent } from './dias-detail.component';
import { DiasUpdateComponent } from './dias-update.component';
import { DiasDeletePopupComponent } from './dias-delete-dialog.component';
import { IDias } from 'app/shared/model/dias.model';

@Injectable({ providedIn: 'root' })
export class DiasResolve implements Resolve<IDias> {
    constructor(private service: DiasService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((dias: HttpResponse<Dias>) => dias.body));
        }
        return of(new Dias());
    }
}

export const diasRoute: Routes = [
    {
        path: 'dias',
        component: DiasComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.dias.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'dias/:id/view',
        component: DiasDetailComponent,
        resolve: {
            dias: DiasResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.dias.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'dias/new',
        component: DiasUpdateComponent,
        resolve: {
            dias: DiasResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.dias.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'dias/:id/edit',
        component: DiasUpdateComponent,
        resolve: {
            dias: DiasResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.dias.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const diasPopupRoute: Routes = [
    {
        path: 'dias/:id/delete',
        component: DiasDeletePopupComponent,
        resolve: {
            dias: DiasResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.dias.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
