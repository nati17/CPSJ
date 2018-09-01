import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CpsjSharedModule } from 'app/shared';
import {
    MedicoEspecialidadDiagnosticoComponent,
    MedicoEspecialidadDiagnosticoDetailComponent,
    MedicoEspecialidadDiagnosticoUpdateComponent,
    MedicoEspecialidadDiagnosticoDeletePopupComponent,
    MedicoEspecialidadDiagnosticoDeleteDialogComponent,
    medicoEspecialidadDiagnosticoRoute,
    medicoEspecialidadDiagnosticoPopupRoute
} from './';

const ENTITY_STATES = [...medicoEspecialidadDiagnosticoRoute, ...medicoEspecialidadDiagnosticoPopupRoute];

@NgModule({
    imports: [CpsjSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MedicoEspecialidadDiagnosticoComponent,
        MedicoEspecialidadDiagnosticoDetailComponent,
        MedicoEspecialidadDiagnosticoUpdateComponent,
        MedicoEspecialidadDiagnosticoDeleteDialogComponent,
        MedicoEspecialidadDiagnosticoDeletePopupComponent
    ],
    entryComponents: [
        MedicoEspecialidadDiagnosticoComponent,
        MedicoEspecialidadDiagnosticoUpdateComponent,
        MedicoEspecialidadDiagnosticoDeleteDialogComponent,
        MedicoEspecialidadDiagnosticoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CpsjMedicoEspecialidadDiagnosticoModule {}
