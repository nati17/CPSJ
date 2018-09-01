import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { MedicoObraSocial } from 'app/shared/model/medico-obra-social.model';
import { MedicoObraSocialService } from './medico-obra-social.service';
import { MedicoObraSocialComponent } from './medico-obra-social.component';
import { MedicoObraSocialDetailComponent } from './medico-obra-social-detail.component';
import { MedicoObraSocialUpdateComponent } from './medico-obra-social-update.component';
import { MedicoObraSocialDeletePopupComponent } from './medico-obra-social-delete-dialog.component';
import { IMedicoObraSocial } from 'app/shared/model/medico-obra-social.model';

@Injectable({ providedIn: 'root' })
export class MedicoObraSocialResolve implements Resolve<IMedicoObraSocial> {
    constructor(private service: MedicoObraSocialService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((medicoObraSocial: HttpResponse<MedicoObraSocial>) => medicoObraSocial.body));
        }
        return of(new MedicoObraSocial());
    }
}

export const medicoObraSocialRoute: Routes = [
    {
        path: 'medico-obra-social',
        component: MedicoObraSocialComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.medicoObraSocial.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'medico-obra-social/:id/view',
        component: MedicoObraSocialDetailComponent,
        resolve: {
            medicoObraSocial: MedicoObraSocialResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.medicoObraSocial.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'medico-obra-social/new',
        component: MedicoObraSocialUpdateComponent,
        resolve: {
            medicoObraSocial: MedicoObraSocialResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.medicoObraSocial.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'medico-obra-social/:id/edit',
        component: MedicoObraSocialUpdateComponent,
        resolve: {
            medicoObraSocial: MedicoObraSocialResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.medicoObraSocial.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const medicoObraSocialPopupRoute: Routes = [
    {
        path: 'medico-obra-social/:id/delete',
        component: MedicoObraSocialDeletePopupComponent,
        resolve: {
            medicoObraSocial: MedicoObraSocialResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.medicoObraSocial.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
