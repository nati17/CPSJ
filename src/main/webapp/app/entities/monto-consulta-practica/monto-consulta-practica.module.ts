import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CpsjSharedModule } from 'app/shared';
import {
    MontoConsultaPracticaComponent,
    MontoConsultaPracticaDetailComponent,
    MontoConsultaPracticaUpdateComponent,
    MontoConsultaPracticaDeletePopupComponent,
    MontoConsultaPracticaDeleteDialogComponent,
    montoConsultaPracticaRoute,
    montoConsultaPracticaPopupRoute
} from './';

const ENTITY_STATES = [...montoConsultaPracticaRoute, ...montoConsultaPracticaPopupRoute];

@NgModule({
    imports: [CpsjSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MontoConsultaPracticaComponent,
        MontoConsultaPracticaDetailComponent,
        MontoConsultaPracticaUpdateComponent,
        MontoConsultaPracticaDeleteDialogComponent,
        MontoConsultaPracticaDeletePopupComponent
    ],
    entryComponents: [
        MontoConsultaPracticaComponent,
        MontoConsultaPracticaUpdateComponent,
        MontoConsultaPracticaDeleteDialogComponent,
        MontoConsultaPracticaDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CpsjMontoConsultaPracticaModule {}
