import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CpsjSharedModule } from 'app/shared';
import {
    ObraSocialComponent,
    ObraSocialDetailComponent,
    ObraSocialUpdateComponent,
    ObraSocialDeletePopupComponent,
    ObraSocialDeleteDialogComponent,
    obraSocialRoute,
    obraSocialPopupRoute
} from './';

const ENTITY_STATES = [...obraSocialRoute, ...obraSocialPopupRoute];

@NgModule({
    imports: [CpsjSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ObraSocialComponent,
        ObraSocialDetailComponent,
        ObraSocialUpdateComponent,
        ObraSocialDeleteDialogComponent,
        ObraSocialDeletePopupComponent
    ],
    entryComponents: [ObraSocialComponent, ObraSocialUpdateComponent, ObraSocialDeleteDialogComponent, ObraSocialDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CpsjObraSocialModule {}
