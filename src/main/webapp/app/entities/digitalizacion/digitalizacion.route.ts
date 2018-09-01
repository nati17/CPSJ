import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Digitalizacion } from 'app/shared/model/digitalizacion.model';
import { DigitalizacionService } from './digitalizacion.service';
import { DigitalizacionComponent } from './digitalizacion.component';
import { DigitalizacionDetailComponent } from './digitalizacion-detail.component';
import { DigitalizacionUpdateComponent } from './digitalizacion-update.component';
import { DigitalizacionDeletePopupComponent } from './digitalizacion-delete-dialog.component';
import { IDigitalizacion } from 'app/shared/model/digitalizacion.model';

@Injectable({ providedIn: 'root' })
export class DigitalizacionResolve implements Resolve<IDigitalizacion> {
    constructor(private service: DigitalizacionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((digitalizacion: HttpResponse<Digitalizacion>) => digitalizacion.body));
        }
        return of(new Digitalizacion());
    }
}

export const digitalizacionRoute: Routes = [
    {
        path: 'digitalizacion',
        component: DigitalizacionComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.digitalizacion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'digitalizacion/:id/view',
        component: DigitalizacionDetailComponent,
        resolve: {
            digitalizacion: DigitalizacionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.digitalizacion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'digitalizacion/new',
        component: DigitalizacionUpdateComponent,
        resolve: {
            digitalizacion: DigitalizacionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.digitalizacion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'digitalizacion/:id/edit',
        component: DigitalizacionUpdateComponent,
        resolve: {
            digitalizacion: DigitalizacionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.digitalizacion.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const digitalizacionPopupRoute: Routes = [
    {
        path: 'digitalizacion/:id/delete',
        component: DigitalizacionDeletePopupComponent,
        resolve: {
            digitalizacion: DigitalizacionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.digitalizacion.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
