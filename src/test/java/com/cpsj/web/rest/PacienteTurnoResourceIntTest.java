package com.cpsj.web.rest;

import com.cpsj.CpsjApp;

import com.cpsj.domain.PacienteTurno;
import com.cpsj.repository.PacienteTurnoRepository;
import com.cpsj.service.PacienteTurnoService;
import com.cpsj.service.dto.PacienteTurnoDTO;
import com.cpsj.service.mapper.PacienteTurnoMapper;
import com.cpsj.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static com.cpsj.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the PacienteTurnoResource REST controller.
 *
 * @see PacienteTurnoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CpsjApp.class)
public class PacienteTurnoResourceIntTest {

    private static final String DEFAULT_ID_PACIENTE = "AAAAAAAAAA";
    private static final String UPDATED_ID_PACIENTE = "BBBBBBBBBB";

    private static final String DEFAULT_ID_TURNO = "AAAAAAAAAA";
    private static final String UPDATED_ID_TURNO = "BBBBBBBBBB";

    private static final String DEFAULT_OBSERVACIONES_TURNO = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVACIONES_TURNO = "BBBBBBBBBB";

    @Autowired
    private PacienteTurnoRepository pacienteTurnoRepository;


    @Autowired
    private PacienteTurnoMapper pacienteTurnoMapper;
    

    @Autowired
    private PacienteTurnoService pacienteTurnoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPacienteTurnoMockMvc;

    private PacienteTurno pacienteTurno;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PacienteTurnoResource pacienteTurnoResource = new PacienteTurnoResource(pacienteTurnoService);
        this.restPacienteTurnoMockMvc = MockMvcBuilders.standaloneSetup(pacienteTurnoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PacienteTurno createEntity(EntityManager em) {
        PacienteTurno pacienteTurno = new PacienteTurno()
            .idPaciente(DEFAULT_ID_PACIENTE)
            .idTurno(DEFAULT_ID_TURNO)
            .observacionesTurno(DEFAULT_OBSERVACIONES_TURNO);
        return pacienteTurno;
    }

    @Before
    public void initTest() {
        pacienteTurno = createEntity(em);
    }

    @Test
    @Transactional
    public void createPacienteTurno() throws Exception {
        int databaseSizeBeforeCreate = pacienteTurnoRepository.findAll().size();

        // Create the PacienteTurno
        PacienteTurnoDTO pacienteTurnoDTO = pacienteTurnoMapper.toDto(pacienteTurno);
        restPacienteTurnoMockMvc.perform(post("/api/paciente-turnos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pacienteTurnoDTO)))
            .andExpect(status().isCreated());

        // Validate the PacienteTurno in the database
        List<PacienteTurno> pacienteTurnoList = pacienteTurnoRepository.findAll();
        assertThat(pacienteTurnoList).hasSize(databaseSizeBeforeCreate + 1);
        PacienteTurno testPacienteTurno = pacienteTurnoList.get(pacienteTurnoList.size() - 1);
        assertThat(testPacienteTurno.getIdPaciente()).isEqualTo(DEFAULT_ID_PACIENTE);
        assertThat(testPacienteTurno.getIdTurno()).isEqualTo(DEFAULT_ID_TURNO);
        assertThat(testPacienteTurno.getObservacionesTurno()).isEqualTo(DEFAULT_OBSERVACIONES_TURNO);
    }

    @Test
    @Transactional
    public void createPacienteTurnoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pacienteTurnoRepository.findAll().size();

        // Create the PacienteTurno with an existing ID
        pacienteTurno.setId(1L);
        PacienteTurnoDTO pacienteTurnoDTO = pacienteTurnoMapper.toDto(pacienteTurno);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPacienteTurnoMockMvc.perform(post("/api/paciente-turnos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pacienteTurnoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PacienteTurno in the database
        List<PacienteTurno> pacienteTurnoList = pacienteTurnoRepository.findAll();
        assertThat(pacienteTurnoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPacienteTurnos() throws Exception {
        // Initialize the database
        pacienteTurnoRepository.saveAndFlush(pacienteTurno);

        // Get all the pacienteTurnoList
        restPacienteTurnoMockMvc.perform(get("/api/paciente-turnos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pacienteTurno.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPaciente").value(hasItem(DEFAULT_ID_PACIENTE.toString())))
            .andExpect(jsonPath("$.[*].idTurno").value(hasItem(DEFAULT_ID_TURNO.toString())))
            .andExpect(jsonPath("$.[*].observacionesTurno").value(hasItem(DEFAULT_OBSERVACIONES_TURNO.toString())));
    }
    

    @Test
    @Transactional
    public void getPacienteTurno() throws Exception {
        // Initialize the database
        pacienteTurnoRepository.saveAndFlush(pacienteTurno);

        // Get the pacienteTurno
        restPacienteTurnoMockMvc.perform(get("/api/paciente-turnos/{id}", pacienteTurno.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pacienteTurno.getId().intValue()))
            .andExpect(jsonPath("$.idPaciente").value(DEFAULT_ID_PACIENTE.toString()))
            .andExpect(jsonPath("$.idTurno").value(DEFAULT_ID_TURNO.toString()))
            .andExpect(jsonPath("$.observacionesTurno").value(DEFAULT_OBSERVACIONES_TURNO.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingPacienteTurno() throws Exception {
        // Get the pacienteTurno
        restPacienteTurnoMockMvc.perform(get("/api/paciente-turnos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePacienteTurno() throws Exception {
        // Initialize the database
        pacienteTurnoRepository.saveAndFlush(pacienteTurno);

        int databaseSizeBeforeUpdate = pacienteTurnoRepository.findAll().size();

        // Update the pacienteTurno
        PacienteTurno updatedPacienteTurno = pacienteTurnoRepository.findById(pacienteTurno.getId()).get();
        // Disconnect from session so that the updates on updatedPacienteTurno are not directly saved in db
        em.detach(updatedPacienteTurno);
        updatedPacienteTurno
            .idPaciente(UPDATED_ID_PACIENTE)
            .idTurno(UPDATED_ID_TURNO)
            .observacionesTurno(UPDATED_OBSERVACIONES_TURNO);
        PacienteTurnoDTO pacienteTurnoDTO = pacienteTurnoMapper.toDto(updatedPacienteTurno);

        restPacienteTurnoMockMvc.perform(put("/api/paciente-turnos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pacienteTurnoDTO)))
            .andExpect(status().isOk());

        // Validate the PacienteTurno in the database
        List<PacienteTurno> pacienteTurnoList = pacienteTurnoRepository.findAll();
        assertThat(pacienteTurnoList).hasSize(databaseSizeBeforeUpdate);
        PacienteTurno testPacienteTurno = pacienteTurnoList.get(pacienteTurnoList.size() - 1);
        assertThat(testPacienteTurno.getIdPaciente()).isEqualTo(UPDATED_ID_PACIENTE);
        assertThat(testPacienteTurno.getIdTurno()).isEqualTo(UPDATED_ID_TURNO);
        assertThat(testPacienteTurno.getObservacionesTurno()).isEqualTo(UPDATED_OBSERVACIONES_TURNO);
    }

    @Test
    @Transactional
    public void updateNonExistingPacienteTurno() throws Exception {
        int databaseSizeBeforeUpdate = pacienteTurnoRepository.findAll().size();

        // Create the PacienteTurno
        PacienteTurnoDTO pacienteTurnoDTO = pacienteTurnoMapper.toDto(pacienteTurno);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPacienteTurnoMockMvc.perform(put("/api/paciente-turnos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pacienteTurnoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PacienteTurno in the database
        List<PacienteTurno> pacienteTurnoList = pacienteTurnoRepository.findAll();
        assertThat(pacienteTurnoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePacienteTurno() throws Exception {
        // Initialize the database
        pacienteTurnoRepository.saveAndFlush(pacienteTurno);

        int databaseSizeBeforeDelete = pacienteTurnoRepository.findAll().size();

        // Get the pacienteTurno
        restPacienteTurnoMockMvc.perform(delete("/api/paciente-turnos/{id}", pacienteTurno.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PacienteTurno> pacienteTurnoList = pacienteTurnoRepository.findAll();
        assertThat(pacienteTurnoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PacienteTurno.class);
        PacienteTurno pacienteTurno1 = new PacienteTurno();
        pacienteTurno1.setId(1L);
        PacienteTurno pacienteTurno2 = new PacienteTurno();
        pacienteTurno2.setId(pacienteTurno1.getId());
        assertThat(pacienteTurno1).isEqualTo(pacienteTurno2);
        pacienteTurno2.setId(2L);
        assertThat(pacienteTurno1).isNotEqualTo(pacienteTurno2);
        pacienteTurno1.setId(null);
        assertThat(pacienteTurno1).isNotEqualTo(pacienteTurno2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PacienteTurnoDTO.class);
        PacienteTurnoDTO pacienteTurnoDTO1 = new PacienteTurnoDTO();
        pacienteTurnoDTO1.setId(1L);
        PacienteTurnoDTO pacienteTurnoDTO2 = new PacienteTurnoDTO();
        assertThat(pacienteTurnoDTO1).isNotEqualTo(pacienteTurnoDTO2);
        pacienteTurnoDTO2.setId(pacienteTurnoDTO1.getId());
        assertThat(pacienteTurnoDTO1).isEqualTo(pacienteTurnoDTO2);
        pacienteTurnoDTO2.setId(2L);
        assertThat(pacienteTurnoDTO1).isNotEqualTo(pacienteTurnoDTO2);
        pacienteTurnoDTO1.setId(null);
        assertThat(pacienteTurnoDTO1).isNotEqualTo(pacienteTurnoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(pacienteTurnoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(pacienteTurnoMapper.fromId(null)).isNull();
    }
}
