import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CpsjSharedModule } from 'app/shared';
import {
    MedicoCalendarioComponent,
    MedicoCalendarioDetailComponent,
    MedicoCalendarioUpdateComponent,
    MedicoCalendarioDeletePopupComponent,
    MedicoCalendarioDeleteDialogComponent,
    medicoCalendarioRoute,
    medicoCalendarioPopupRoute
} from './';

const ENTITY_STATES = [...medicoCalendarioRoute, ...medicoCalendarioPopupRoute];

@NgModule({
    imports: [CpsjSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MedicoCalendarioComponent,
        MedicoCalendarioDetailComponent,
        MedicoCalendarioUpdateComponent,
        MedicoCalendarioDeleteDialogComponent,
        MedicoCalendarioDeletePopupComponent
    ],
    entryComponents: [
        MedicoCalendarioComponent,
        MedicoCalendarioUpdateComponent,
        MedicoCalendarioDeleteDialogComponent,
        MedicoCalendarioDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CpsjMedicoCalendarioModule {}
