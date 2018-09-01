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
import { CpsjTurnoModule } from './turno/turno.module';
import { CpsjConsultaPracticaModule } from './consulta-practica/consulta-practica.module';
import { CpsjEmpresaModule } from './empresa/empresa.module';
import { CpsjSucursalModule } from './sucursal/sucursal.module';
import { CpsjPersonalModule } from './personal/personal.module';
import { CpsjDiagnosticoModule } from './diagnostico/diagnostico.module';
import { CpsjMedicoEspecialidadDiagnosticoModule } from './medico-especialidad-diagnostico/medico-especialidad-diagnostico.module';
import { CpsjMedicoObraSocialModule } from './medico-obra-social/medico-obra-social.module';
import { CpsjPacienteObraSocialModule } from './paciente-obra-social/paciente-obra-social.module';
import { CpsjCalendarioModule } from './calendario/calendario.module';
import { CpsjPacienteTurnoModule } from './paciente-turno/paciente-turno.module';
import { CpsjMedicoCalendarioModule } from './medico-calendario/medico-calendario.module';
import { CpsjMontoConsultaPracticaModule } from './monto-consulta-practica/monto-consulta-practica.module';
import { CpsjObservacionModule } from './observacion/observacion.module';
import { CpsjConsPractObservacionModule } from './cons-pract-observacion/cons-pract-observacion.module';
import { CpsjDigitalizacionModule } from './digitalizacion/digitalizacion.module';
import { CpsjDiasModule } from './dias/dias.module';
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
        CpsjTurnoModule,
        CpsjConsultaPracticaModule,
        CpsjEmpresaModule,
        CpsjSucursalModule,
        CpsjPersonalModule,
        CpsjDiagnosticoModule,
        CpsjMedicoEspecialidadDiagnosticoModule,
        CpsjMedicoObraSocialModule,
        CpsjPacienteObraSocialModule,
        CpsjCalendarioModule,
        CpsjPacienteTurnoModule,
        CpsjMedicoCalendarioModule,
        CpsjMontoConsultaPracticaModule,
        CpsjObservacionModule,
        CpsjConsPractObservacionModule,
        CpsjDigitalizacionModule,
        CpsjDiasModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CpsjEntityModule {}
