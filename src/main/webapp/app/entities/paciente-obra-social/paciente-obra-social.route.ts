import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { PacienteObraSocial } from 'app/shared/model/paciente-obra-social.model';
import { PacienteObraSocialService } from './paciente-obra-social.service';
import { PacienteObraSocialComponent } from './paciente-obra-social.component';
import { PacienteObraSocialDetailComponent } from './paciente-obra-social-detail.component';
import { PacienteObraSocialUpdateComponent } from './paciente-obra-social-update.component';
import { PacienteObraSocialDeletePopupComponent } from './paciente-obra-social-delete-dialog.component';
import { IPacienteObraSocial } from 'app/shared/model/paciente-obra-social.model';

@Injectable({ providedIn: 'root' })
export class PacienteObraSocialResolve implements Resolve<IPacienteObraSocial> {
    constructor(private service: PacienteObraSocialService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((pacienteObraSocial: HttpResponse<PacienteObraSocial>) => pacienteObraSocial.body));
        }
        return of(new PacienteObraSocial());
    }
}

export const pacienteObraSocialRoute: Routes = [
    {
        path: 'paciente-obra-social',
        component: PacienteObraSocialComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.pacienteObraSocial.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'paciente-obra-social/:id/view',
        component: PacienteObraSocialDetailComponent,
        resolve: {
            pacienteObraSocial: PacienteObraSocialResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.pacienteObraSocial.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'paciente-obra-social/new',
        component: PacienteObraSocialUpdateComponent,
        resolve: {
            pacienteObraSocial: PacienteObraSocialResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.pacienteObraSocial.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'paciente-obra-social/:id/edit',
        component: PacienteObraSocialUpdateComponent,
        resolve: {
            pacienteObraSocial: PacienteObraSocialResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.pacienteObraSocial.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const pacienteObraSocialPopupRoute: Routes = [
    {
        path: 'paciente-obra-social/:id/delete',
        component: PacienteObraSocialDeletePopupComponent,
        resolve: {
            pacienteObraSocial: PacienteObraSocialResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.pacienteObraSocial.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
