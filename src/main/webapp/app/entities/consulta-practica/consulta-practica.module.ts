import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CpsjSharedModule } from 'app/shared';
import {
    ConsultaPracticaComponent,
    ConsultaPracticaDetailComponent,
    ConsultaPracticaUpdateComponent,
    ConsultaPracticaDeletePopupComponent,
    ConsultaPracticaDeleteDialogComponent,
    consultaPracticaRoute,
    consultaPracticaPopupRoute
} from './';

const ENTITY_STATES = [...consultaPracticaRoute, ...consultaPracticaPopupRoute];

@NgModule({
    imports: [CpsjSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ConsultaPracticaComponent,
        ConsultaPracticaDetailComponent,
        ConsultaPracticaUpdateComponent,
        ConsultaPracticaDeleteDialogComponent,
        ConsultaPracticaDeletePopupComponent
    ],
    entryComponents: [
        ConsultaPracticaComponent,
        ConsultaPracticaUpdateComponent,
        ConsultaPracticaDeleteDialogComponent,
        ConsultaPracticaDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CpsjConsultaPracticaModule {}
