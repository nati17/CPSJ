import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CpsjSharedModule } from 'app/shared';
import {
    MedicoObraSocialComponent,
    MedicoObraSocialDetailComponent,
    MedicoObraSocialUpdateComponent,
    MedicoObraSocialDeletePopupComponent,
    MedicoObraSocialDeleteDialogComponent,
    medicoObraSocialRoute,
    medicoObraSocialPopupRoute
} from './';

const ENTITY_STATES = [...medicoObraSocialRoute, ...medicoObraSocialPopupRoute];

@NgModule({
    imports: [CpsjSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MedicoObraSocialComponent,
        MedicoObraSocialDetailComponent,
        MedicoObraSocialUpdateComponent,
        MedicoObraSocialDeleteDialogComponent,
        MedicoObraSocialDeletePopupComponent
    ],
    entryComponents: [
        MedicoObraSocialComponent,
        MedicoObraSocialUpdateComponent,
        MedicoObraSocialDeleteDialogComponent,
        MedicoObraSocialDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CpsjMedicoObraSocialModule {}
