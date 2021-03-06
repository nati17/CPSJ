import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CpsjSharedModule } from 'app/shared';
import {
    CalendarioComponent,
    CalendarioDetailComponent,
    CalendarioUpdateComponent,
    CalendarioDeletePopupComponent,
    CalendarioDeleteDialogComponent,
    calendarioRoute,
    calendarioPopupRoute
} from './';

const ENTITY_STATES = [...calendarioRoute, ...calendarioPopupRoute];

@NgModule({
    imports: [CpsjSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CalendarioComponent,
        CalendarioDetailComponent,
        CalendarioUpdateComponent,
        CalendarioDeleteDialogComponent,
        CalendarioDeletePopupComponent
    ],
    entryComponents: [CalendarioComponent, CalendarioUpdateComponent, CalendarioDeleteDialogComponent, CalendarioDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CpsjCalendarioModule {}
