import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CpsjSharedModule } from 'app/shared';
import {
    PacienteTurnoComponent,
    PacienteTurnoDetailComponent,
    PacienteTurnoUpdateComponent,
    PacienteTurnoDeletePopupComponent,
    PacienteTurnoDeleteDialogComponent,
    pacienteTurnoRoute,
    pacienteTurnoPopupRoute
} from './';

const ENTITY_STATES = [...pacienteTurnoRoute, ...pacienteTurnoPopupRoute];

@NgModule({
    imports: [CpsjSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PacienteTurnoComponent,
        PacienteTurnoDetailComponent,
        PacienteTurnoUpdateComponent,
        PacienteTurnoDeleteDialogComponent,
        PacienteTurnoDeletePopupComponent
    ],
    entryComponents: [
        PacienteTurnoComponent,
        PacienteTurnoUpdateComponent,
        PacienteTurnoDeleteDialogComponent,
        PacienteTurnoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CpsjPacienteTurnoModule {}
