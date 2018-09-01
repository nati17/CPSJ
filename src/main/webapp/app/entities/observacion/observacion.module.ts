import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CpsjSharedModule } from 'app/shared';
import {
    ObservacionComponent,
    ObservacionDetailComponent,
    ObservacionUpdateComponent,
    ObservacionDeletePopupComponent,
    ObservacionDeleteDialogComponent,
    observacionRoute,
    observacionPopupRoute
} from './';

const ENTITY_STATES = [...observacionRoute, ...observacionPopupRoute];

@NgModule({
    imports: [CpsjSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ObservacionComponent,
        ObservacionDetailComponent,
        ObservacionUpdateComponent,
        ObservacionDeleteDialogComponent,
        ObservacionDeletePopupComponent
    ],
    entryComponents: [ObservacionComponent, ObservacionUpdateComponent, ObservacionDeleteDialogComponent, ObservacionDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CpsjObservacionModule {}
