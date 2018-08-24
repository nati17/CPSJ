import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { CpsjCodigoPostalModule } from './codigo-postal/codigo-postal.module';
import { CpsjProvinciaModule } from './provincia/provincia.module';
import { CpsjObraSocialModule } from './obra-social/obra-social.module';
import { CpsjMedicoModule } from './medico/medico.module';
import { CpsjEspecialidadModule } from './especialidad/especialidad.module';
import { CpsjPacienteModule } from './paciente/paciente.module';
import { CpsjEnfermedadModule } from './enfermedad/enfermedad.module';
import { CpsjAlergiaModule } from './alergia/alergia.module';
import { CpsjIntoleranciaModule } from './intolerancia/intolerancia.module';
import { CpsjRegimenModule } from './regimen/regimen.module';
import { CpsjBebidaModule } from './bebida/bebida.module';
import { CpsjEjercicioModule } from './ejercicio/ejercicio.module';
import { CpsjAntecedentesPersonalesModule } from './antecedentes-personales/antecedentes-personales.module';
import { CpsjAntecedentesFamiliaresModule } from './antecedentes-familiares/antecedentes-familiares.module';
import { CpsjConsultaModule } from './consulta/consulta.module';
import { CpsjFichaModule } from './ficha/ficha.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        CpsjCodigoPostalModule,
        CpsjProvinciaModule,
        CpsjObraSocialModule,
        CpsjMedicoModule,
        CpsjEspecialidadModule,
        CpsjPacienteModule,
        CpsjEnfermedadModule,
        CpsjAlergiaModule,
        CpsjIntoleranciaModule,
        CpsjRegimenModule,
        CpsjBebidaModule,
        CpsjEjercicioModule,
        CpsjAntecedentesPersonalesModule,
        CpsjAntecedentesFamiliaresModule,
        CpsjConsultaModule,
        CpsjFichaModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CpsjEntityModule {}
