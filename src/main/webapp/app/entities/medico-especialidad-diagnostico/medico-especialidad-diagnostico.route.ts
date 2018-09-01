import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { MedicoEspecialidadDiagnostico } from 'app/shared/model/medico-especialidad-diagnostico.model';
import { MedicoEspecialidadDiagnosticoService } from './medico-especialidad-diagnostico.service';
import { MedicoEspecialidadDiagnosticoComponent } from './medico-especialidad-diagnostico.component';
import { MedicoEspecialidadDiagnosticoDetailComponent } from './medico-especialidad-diagnostico-detail.component';
import { MedicoEspecialidadDiagnosticoUpdateComponent } from './medico-especialidad-diagnostico-update.component';
import { MedicoEspecialidadDiagnosticoDeletePopupComponent } from './medico-especialidad-diagnostico-delete-dialog.component';
import { IMedicoEspecialidadDiagnostico } from 'app/shared/model/medico-especialidad-diagnostico.model';

@Injectable({ providedIn: 'root' })
export class MedicoEspecialidadDiagnosticoResolve implements Resolve<IMedicoEspecialidadDiagnostico> {
    constructor(private service: MedicoEspecialidadDiagnosticoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(
                    map((medicoEspecialidadDiagnostico: HttpResponse<MedicoEspecialidadDiagnostico>) => medicoEspecialidadDiagnostico.body)
                );
        }
        return of(new MedicoEspecialidadDiagnostico());
    }
}

export const medicoEspecialidadDiagnosticoRoute: Routes = [
    {
        path: 'medico-especialidad-diagnostico',
        component: MedicoEspecialidadDiagnosticoComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.medicoEspecialidadDiagnostico.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'medico-especialidad-diagnostico/:id/view',
        component: MedicoEspecialidadDiagnosticoDetailComponent,
        resolve: {
            medicoEspecialidadDiagnostico: MedicoEspecialidadDiagnosticoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.medicoEspecialidadDiagnostico.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'medico-especialidad-diagnostico/new',
        component: MedicoEspecialidadDiagnosticoUpdateComponent,
        resolve: {
            medicoEspecialidadDiagnostico: MedicoEspecialidadDiagnosticoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.medicoEspecialidadDiagnostico.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'medico-especialidad-diagnostico/:id/edit',
        component: MedicoEspecialidadDiagnosticoUpdateComponent,
        resolve: {
            medicoEspecialidadDiagnostico: MedicoEspecialidadDiagnosticoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.medicoEspecialidadDiagnostico.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const medicoEspecialidadDiagnosticoPopupRoute: Routes = [
    {
        path: 'medico-especialidad-diagnostico/:id/delete',
        component: MedicoEspecialidadDiagnosticoDeletePopupComponent,
        resolve: {
            medicoEspecialidadDiagnostico: MedicoEspecialidadDiagnosticoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cpsjApp.medicoEspecialidadDiagnostico.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
