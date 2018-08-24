import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CpsjSharedModule } from 'app/shared';
import {
    PacienteComponent,
    PacienteDetailComponent,
    PacienteUpdateComponent,
    PacienteDeletePopupComponent,
    PacienteDeleteDialogComponent,
    pacienteRoute,
    pacientePopupRoute
} from './';

const ENTITY_STATES = [...pacienteRoute, ...pacientePopupRoute];

@NgModule({
    imports: [CpsjSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PacienteComponent,
        PacienteDetailComponent,
        PacienteUpdateComponent,
        PacienteDeleteDialogComponent,
        PacienteDeletePopupComponent
    ],
    entryComponents: [PacienteComponent, PacienteUpdateComponent, PacienteDeleteDialogComponent, PacienteDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CpsjPacienteModule {}
