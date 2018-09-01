import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CpsjSharedModule } from 'app/shared';
import {
    DigitalizacionComponent,
    DigitalizacionDetailComponent,
    DigitalizacionUpdateComponent,
    DigitalizacionDeletePopupComponent,
    DigitalizacionDeleteDialogComponent,
    digitalizacionRoute,
    digitalizacionPopupRoute
} from './';

const ENTITY_STATES = [...digitalizacionRoute, ...digitalizacionPopupRoute];

@NgModule({
    imports: [CpsjSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DigitalizacionComponent,
        DigitalizacionDetailComponent,
        DigitalizacionUpdateComponent,
        DigitalizacionDeleteDialogComponent,
        DigitalizacionDeletePopupComponent
    ],
    entryComponents: [
        DigitalizacionComponent,
        DigitalizacionUpdateComponent,
        DigitalizacionDeleteDialogComponent,
        DigitalizacionDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CpsjDigitalizacionModule {}
