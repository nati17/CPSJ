import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CpsjSharedModule } from 'app/shared';
import {
    SucursalComponent,
    SucursalDetailComponent,
    SucursalUpdateComponent,
    SucursalDeletePopupComponent,
    SucursalDeleteDialogComponent,
    sucursalRoute,
    sucursalPopupRoute
} from './';

const ENTITY_STATES = [...sucursalRoute, ...sucursalPopupRoute];

@NgModule({
    imports: [CpsjSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SucursalComponent,
        SucursalDetailComponent,
        SucursalUpdateComponent,
        SucursalDeleteDialogComponent,
        SucursalDeletePopupComponent
    ],
    entryComponents: [SucursalComponent, SucursalUpdateComponent, SucursalDeleteDialogComponent, SucursalDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CpsjSucursalModule {}
