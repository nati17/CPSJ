import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CpsjSharedModule } from 'app/shared';
import {
    FichaComponent,
    FichaDetailComponent,
    FichaUpdateComponent,
    FichaDeletePopupComponent,
    FichaDeleteDialogComponent,
    fichaRoute,
    fichaPopupRoute
} from './';

const ENTITY_STATES = [...fichaRoute, ...fichaPopupRoute];

@NgModule({
    imports: [CpsjSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [FichaComponent, FichaDetailComponent, FichaUpdateComponent, FichaDeleteDialogComponent, FichaDeletePopupComponent],
    entryComponents: [FichaComponent, FichaUpdateComponent, FichaDeleteDialogComponent, FichaDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CpsjFichaModule {}
