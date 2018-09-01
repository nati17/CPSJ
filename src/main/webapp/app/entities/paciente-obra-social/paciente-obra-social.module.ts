import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CpsjSharedModule } from 'app/shared';
import {
    PacienteObraSocialComponent,
    PacienteObraSocialDetailComponent,
    PacienteObraSocialUpdateComponent,
    PacienteObraSocialDeletePopupComponent,
    PacienteObraSocialDeleteDialogComponent,
    pacienteObraSocialRoute,
    pacienteObraSocialPopupRoute
} from './';

const ENTITY_STATES = [...pacienteObraSocialRoute, ...pacienteObraSocialPopupRoute];

@NgModule({
    imports: [CpsjSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PacienteObraSocialComponent,
        PacienteObraSocialDetailComponent,
        PacienteObraSocialUpdateComponent,
        PacienteObraSocialDeleteDialogComponent,
        PacienteObraSocialDeletePopupComponent
    ],
    entryComponents: [
        PacienteObraSocialComponent,
        PacienteObraSocialUpdateComponent,
        PacienteObraSocialDeleteDialogComponent,
        PacienteObraSocialDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CpsjPacienteObraSocialModule {}
