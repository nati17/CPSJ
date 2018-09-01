import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CpsjSharedModule } from 'app/shared';
import {
    DiasComponent,
    DiasDetailComponent,
    DiasUpdateComponent,
    DiasDeletePopupComponent,
    DiasDeleteDialogComponent,
    diasRoute,
    diasPopupRoute
} from './';

const ENTITY_STATES = [...diasRoute, ...diasPopupRoute];

@NgModule({
    imports: [CpsjSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [DiasComponent, DiasDetailComponent, DiasUpdateComponent, DiasDeleteDialogComponent, DiasDeletePopupComponent],
    entryComponents: [DiasComponent, DiasUpdateComponent, DiasDeleteDialogComponent, DiasDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CpsjDiasModule {}
