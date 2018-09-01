import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { PacienteTurno } from 'app/shared/model/paciente-turno.model';
import { PacienteTurnoService } from './paciente-turno.service';
import { PacienteTurnoComponent } from './paciente-turno.component';
import { PacienteTurnoDetailComponent } from './paciente-turno-detail.component';
import { PacienteTurnoUpdateComponent } from './paciente-turno-update.component';
import { PacienteTurnoDeletePopupComponent } from './paciente-turno-delete-dialog.component';
import { IPacienteTurno } from 'app/shared/model/paciente-turno.model';

@Injectable({ providedIn: 'root' })
export class PacienteTurnoResolve implements Resolve<IPacienteTurno> {
    constructor(private service: PacienteTurnoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((pacienteTurno: HttpResponse<PacienteTurno>) => pacienteTurno.body));
        }
        return of(new PacienteTurno());
    }
}

export const pacienteTurnoRoute: Routes = [
    {
        path: 'paciente-turno',
        component: PacienteTurnoComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.pacienteTurno.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'paciente-turno/:id/view',
        component: PacienteTurnoDetailComponent,
        resolve: {
            pacienteTurno: PacienteTurnoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.pacienteTurno.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'paciente-turno/new',
        component: PacienteTurnoUpdateComponent,
        resolve: {
            pacienteTurno: PacienteTurnoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.pacienteTurno.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'paciente-turno/:id/edit',
        component: PacienteTurnoUpdateComponent,
        resolve: {
            pacienteTurno: PacienteTurnoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.pacienteTurno.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const pacienteTurnoPopupRoute: Routes = [
    {
        path: 'paciente-turno/:id/delete',
        component: PacienteTurnoDeletePopupComponent,
        resolve: {
            pacienteTurno: PacienteTurnoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.pacienteTurno.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
