import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CpsjSharedModule } from 'app/shared';
import {
    ConsPractObservacionComponent,
    ConsPractObservacionDetailComponent,
    ConsPractObservacionUpdateComponent,
    ConsPractObservacionDeletePopupComponent,
    ConsPractObservacionDeleteDialogComponent,
    consPractObservacionRoute,
    consPractObservacionPopupRoute
} from './';

const ENTITY_STATES = [...consPractObservacionRoute, ...consPractObservacionPopupRoute];

@NgModule({
    imports: [CpsjSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ConsPractObservacionComponent,
        ConsPractObservacionDetailComponent,
        ConsPractObservacionUpdateComponent,
        ConsPractObservacionDeleteDialogComponent,
        ConsPractObservacionDeletePopupComponent
    ],
    entryComponents: [
        ConsPractObservacionComponent,
        ConsPractObservacionUpdateComponent,
        ConsPractObservacionDeleteDialogComponent,
        ConsPractObservacionDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CpsjConsPractObservacionModule {}
