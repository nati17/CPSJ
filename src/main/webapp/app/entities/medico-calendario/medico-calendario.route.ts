import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { MedicoCalendario } from 'app/shared/model/medico-calendario.model';
import { MedicoCalendarioService } from './medico-calendario.service';
import { MedicoCalendarioComponent } from './medico-calendario.component';
import { MedicoCalendarioDetailComponent } from './medico-calendario-detail.component';
import { MedicoCalendarioUpdateComponent } from './medico-calendario-update.component';
import { MedicoCalendarioDeletePopupComponent } from './medico-calendario-delete-dialog.component';
import { IMedicoCalendario } from 'app/shared/model/medico-calendario.model';

@Injectable({ providedIn: 'root' })
export class MedicoCalendarioResolve implements Resolve<IMedicoCalendario> {
    constructor(private service: MedicoCalendarioService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((medicoCalendario: HttpResponse<MedicoCalendario>) => medicoCalendario.body));
        }
        return of(new MedicoCalendario());
    }
}

export const medicoCalendarioRoute: Routes = [
    {
        path: 'medico-calendario',
        component: MedicoCalendarioComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.medicoCalendario.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'medico-calendario/:id/view',
        component: MedicoCalendarioDetailComponent,
        resolve: {
            medicoCalendario: MedicoCalendarioResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.medicoCalendario.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'medico-calendario/new',
        component: MedicoCalendarioUpdateComponent,
        resolve: {
            medicoCalendario: MedicoCalendarioResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.medicoCalendario.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'medico-calendario/:id/edit',
        component: MedicoCalendarioUpdateComponent,
        resolve: {
            medicoCalendario: MedicoCalendarioResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.medicoCalendario.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const medicoCalendarioPopupRoute: Routes = [
    {
        path: 'medico-calendario/:id/delete',
        component: MedicoCalendarioDeletePopupComponent,
        resolve: {
            medicoCalendario: MedicoCalendarioResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.medicoCalendario.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
