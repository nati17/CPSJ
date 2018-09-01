package com.cpsj.web.rest;

import com.cpsj.CpsjApp;

import com.cpsj.domain.MedicoEspecialidadDiagnostico;
import com.cpsj.repository.MedicoEspecialidadDiagnosticoRepository;
import com.cpsj.service.MedicoEspecialidadDiagnosticoService;
import com.cpsj.service.dto.MedicoEspecialidadDiagnosticoDTO;
import com.cpsj.service.mapper.MedicoEspecialidadDiagnosticoMapper;
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
 * Test class for the MedicoEspecialidadDiagnosticoResource REST controller.
 *
 * @see MedicoEspecialidadDiagnosticoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CpsjApp.class)
public class MedicoEspecialidadDiagnosticoResourceIntTest {

    private static final String DEFAULT_ID_MEDICO = "AAAAAAAAAA";
    private static final String UPDATED_ID_MEDICO = "BBBBBBBBBB";

    private static final String DEFAULT_ID_ESPECIALIDAD = "AAAAAAAAAA";
    private static final String UPDATED_ID_ESPECIALIDAD = "BBBBBBBBBB";

    private static final String DEFAULT_ID_DIAGNOSTICO = "AAAAAAAAAA";
    private static final String UPDATED_ID_DIAGNOSTICO = "BBBBBBBBBB";

    @Autowired
    private MedicoEspecialidadDiagnosticoRepository medicoEspecialidadDiagnosticoRepository;


    @Autowired
    private MedicoEspecialidadDiagnosticoMapper medicoEspecialidadDiagnosticoMapper;
    

    @Autowired
    private MedicoEspecialidadDiagnosticoService medicoEspecialidadDiagnosticoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMedicoEspecialidadDiagnosticoMockMvc;

    private MedicoEspecialidadDiagnostico medicoEspecialidadDiagnostico;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MedicoEspecialidadDiagnosticoResource medicoEspecialidadDiagnosticoResource = new MedicoEspecialidadDiagnosticoResource(medicoEspecialidadDiagnosticoService);
        this.restMedicoEspecialidadDiagnosticoMockMvc = MockMvcBuilders.standaloneSetup(medicoEspecialidadDiagnosticoResource)
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
    public static MedicoEspecialidadDiagnostico createEntity(EntityManager em) {
        MedicoEspecialidadDiagnostico medicoEspecialidadDiagnostico = new MedicoEspecialidadDiagnostico()
            .idMedico(DEFAULT_ID_MEDICO)
            .idEspecialidad(DEFAULT_ID_ESPECIALIDAD)
            .idDiagnostico(DEFAULT_ID_DIAGNOSTICO);
        return medicoEspecialidadDiagnostico;
    }

    @Before
    public void initTest() {
        medicoEspecialidadDiagnostico = createEntity(em);
    }

    @Test
    @Transactional
    public void createMedicoEspecialidadDiagnostico() throws Exception {
        int databaseSizeBeforeCreate = medicoEspecialidadDiagnosticoRepository.findAll().size();

        // Create the MedicoEspecialidadDiagnostico
        MedicoEspecialidadDiagnosticoDTO medicoEspecialidadDiagnosticoDTO = medicoEspecialidadDiagnosticoMapper.toDto(medicoEspecialidadDiagnostico);
        restMedicoEspecialidadDiagnosticoMockMvc.perform(post("/api/medico-especialidad-diagnosticos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medicoEspecialidadDiagnosticoDTO)))
            .andExpect(status().isCreated());

        // Validate the MedicoEspecialidadDiagnostico in the database
        List<MedicoEspecialidadDiagnostico> medicoEspecialidadDiagnosticoList = medicoEspecialidadDiagnosticoRepository.findAll();
        assertThat(medicoEspecialidadDiagnosticoList).hasSize(databaseSizeBeforeCreate + 1);
        MedicoEspecialidadDiagnostico testMedicoEspecialidadDiagnostico = medicoEspecialidadDiagnosticoList.get(medicoEspecialidadDiagnosticoList.size() - 1);
        assertThat(testMedicoEspecialidadDiagnostico.getIdMedico()).isEqualTo(DEFAULT_ID_MEDICO);
        assertThat(testMedicoEspecialidadDiagnostico.getIdEspecialidad()).isEqualTo(DEFAULT_ID_ESPECIALIDAD);
        assertThat(testMedicoEspecialidadDiagnostico.getIdDiagnostico()).isEqualTo(DEFAULT_ID_DIAGNOSTICO);
    }

    @Test
    @Transactional
    public void createMedicoEspecialidadDiagnosticoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = medicoEspecialidadDiagnosticoRepository.findAll().size();

        // Create the MedicoEspecialidadDiagnostico with an existing ID
        medicoEspecialidadDiagnostico.setId(1L);
        MedicoEspecialidadDiagnosticoDTO medicoEspecialidadDiagnosticoDTO = medicoEspecialidadDiagnosticoMapper.toDto(medicoEspecialidadDiagnostico);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMedicoEspecialidadDiagnosticoMockMvc.perform(post("/api/medico-especialidad-diagnosticos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medicoEspecialidadDiagnosticoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedicoEspecialidadDiagnostico in the database
        List<MedicoEspecialidadDiagnostico> medicoEspecialidadDiagnosticoList = medicoEspecialidadDiagnosticoRepository.findAll();
        assertThat(medicoEspecialidadDiagnosticoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMedicoEspecialidadDiagnosticos() throws Exception {
        // Initialize the database
        medicoEspecialidadDiagnosticoRepository.saveAndFlush(medicoEspecialidadDiagnostico);

        // Get all the medicoEspecialidadDiagnosticoList
        restMedicoEspecialidadDiagnosticoMockMvc.perform(get("/api/medico-especialidad-diagnosticos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(medicoEspecialidadDiagnostico.getId().intValue())))
            .andExpect(jsonPath("$.[*].idMedico").value(hasItem(DEFAULT_ID_MEDICO.toString())))
            .andExpect(jsonPath("$.[*].idEspecialidad").value(hasItem(DEFAULT_ID_ESPECIALIDAD.toString())))
            .andExpect(jsonPath("$.[*].idDiagnostico").value(hasItem(DEFAULT_ID_DIAGNOSTICO.toString())));
    }
    

    @Test
    @Transactional
    public void getMedicoEspecialidadDiagnostico() throws Exception {
        // Initialize the database
        medicoEspecialidadDiagnosticoRepository.saveAndFlush(medicoEspecialidadDiagnostico);

        // Get the medicoEspecialidadDiagnostico
        restMedicoEspecialidadDiagnosticoMockMvc.perform(get("/api/medico-especialidad-diagnosticos/{id}", medicoEspecialidadDiagnostico.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(medicoEspecialidadDiagnostico.getId().intValue()))
            .andExpect(jsonPath("$.idMedico").value(DEFAULT_ID_MEDICO.toString()))
            .andExpect(jsonPath("$.idEspecialidad").value(DEFAULT_ID_ESPECIALIDAD.toString()))
            .andExpect(jsonPath("$.idDiagnostico").value(DEFAULT_ID_DIAGNOSTICO.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingMedicoEspecialidadDiagnostico() throws Exception {
        // Get the medicoEspecialidadDiagnostico
        restMedicoEspecialidadDiagnosticoMockMvc.perform(get("/api/medico-especialidad-diagnosticos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMedicoEspecialidadDiagnostico() throws Exception {
        // Initialize the database
        medicoEspecialidadDiagnosticoRepository.saveAndFlush(medicoEspecialidadDiagnostico);

        int databaseSizeBeforeUpdate = medicoEspecialidadDiagnosticoRepository.findAll().size();

        // Update the medicoEspecialidadDiagnostico
        MedicoEspecialidadDiagnostico updatedMedicoEspecialidadDiagnostico = medicoEspecialidadDiagnosticoRepository.findById(medicoEspecialidadDiagnostico.getId()).get();
        // Disconnect from session so that the updates on updatedMedicoEspecialidadDiagnostico are not directly saved in db
        em.detach(updatedMedicoEspecialidadDiagnostico);
        updatedMedicoEspecialidadDiagnostico
            .idMedico(UPDATED_ID_MEDICO)
            .idEspecialidad(UPDATED_ID_ESPECIALIDAD)
            .idDiagnostico(UPDATED_ID_DIAGNOSTICO);
        MedicoEspecialidadDiagnosticoDTO medicoEspecialidadDiagnosticoDTO = medicoEspecialidadDiagnosticoMapper.toDto(updatedMedicoEspecialidadDiagnostico);

        restMedicoEspecialidadDiagnosticoMockMvc.perform(put("/api/medico-especialidad-diagnosticos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medicoEspecialidadDiagnosticoDTO)))
            .andExpect(status().isOk());

        // Validate the MedicoEspecialidadDiagnostico in the database
        List<MedicoEspecialidadDiagnostico> medicoEspecialidadDiagnosticoList = medicoEspecialidadDiagnosticoRepository.findAll();
        assertThat(medicoEspecialidadDiagnosticoList).hasSize(databaseSizeBeforeUpdate);
        MedicoEspecialidadDiagnostico testMedicoEspecialidadDiagnostico = medicoEspecialidadDiagnosticoList.get(medicoEspecialidadDiagnosticoList.size() - 1);
        assertThat(testMedicoEspecialidadDiagnostico.getIdMedico()).isEqualTo(UPDATED_ID_MEDICO);
        assertThat(testMedicoEspecialidadDiagnostico.getIdEspecialidad()).isEqualTo(UPDATED_ID_ESPECIALIDAD);
        assertThat(testMedicoEspecialidadDiagnostico.getIdDiagnostico()).isEqualTo(UPDATED_ID_DIAGNOSTICO);
    }

    @Test
    @Transactional
    public void updateNonExistingMedicoEspecialidadDiagnostico() throws Exception {
        int databaseSizeBeforeUpdate = medicoEspecialidadDiagnosticoRepository.findAll().size();

        // Create the MedicoEspecialidadDiagnostico
        MedicoEspecialidadDiagnosticoDTO medicoEspecialidadDiagnosticoDTO = medicoEspecialidadDiagnosticoMapper.toDto(medicoEspecialidadDiagnostico);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMedicoEspecialidadDiagnosticoMockMvc.perform(put("/api/medico-especialidad-diagnosticos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medicoEspecialidadDiagnosticoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedicoEspecialidadDiagnostico in the database
        List<MedicoEspecialidadDiagnostico> medicoEspecialidadDiagnosticoList = medicoEspecialidadDiagnosticoRepository.findAll();
        assertThat(medicoEspecialidadDiagnosticoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMedicoEspecialidadDiagnostico() throws Exception {
        // Initialize the database
        medicoEspecialidadDiagnosticoRepository.saveAndFlush(medicoEspecialidadDiagnostico);

        int databaseSizeBeforeDelete = medicoEspecialidadDiagnosticoRepository.findAll().size();

        // Get the medicoEspecialidadDiagnostico
        restMedicoEspecialidadDiagnosticoMockMvc.perform(delete("/api/medico-especialidad-diagnosticos/{id}", medicoEspecialidadDiagnostico.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MedicoEspecialidadDiagnostico> medicoEspecialidadDiagnosticoList = medicoEspecialidadDiagnosticoRepository.findAll();
        assertThat(medicoEspecialidadDiagnosticoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedicoEspecialidadDiagnostico.class);
        MedicoEspecialidadDiagnostico medicoEspecialidadDiagnostico1 = new MedicoEspecialidadDiagnostico();
        medicoEspecialidadDiagnostico1.setId(1L);
        MedicoEspecialidadDiagnostico medicoEspecialidadDiagnostico2 = new MedicoEspecialidadDiagnostico();
        medicoEspecialidadDiagnostico2.setId(medicoEspecialidadDiagnostico1.getId());
        assertThat(medicoEspecialidadDiagnostico1).isEqualTo(medicoEspecialidadDiagnostico2);
        medicoEspecialidadDiagnostico2.setId(2L);
        assertThat(medicoEspecialidadDiagnostico1).isNotEqualTo(medicoEspecialidadDiagnostico2);
        medicoEspecialidadDiagnostico1.setId(null);
        assertThat(medicoEspecialidadDiagnostico1).isNotEqualTo(medicoEspecialidadDiagnostico2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedicoEspecialidadDiagnosticoDTO.class);
        MedicoEspecialidadDiagnosticoDTO medicoEspecialidadDiagnosticoDTO1 = new MedicoEspecialidadDiagnosticoDTO();
        medicoEspecialidadDiagnosticoDTO1.setId(1L);
        MedicoEspecialidadDiagnosticoDTO medicoEspecialidadDiagnosticoDTO2 = new MedicoEspecialidadDiagnosticoDTO();
        assertThat(medicoEspecialidadDiagnosticoDTO1).isNotEqualTo(medicoEspecialidadDiagnosticoDTO2);
        medicoEspecialidadDiagnosticoDTO2.setId(medicoEspecialidadDiagnosticoDTO1.getId());
        assertThat(medicoEspecialidadDiagnosticoDTO1).isEqualTo(medicoEspecialidadDiagnosticoDTO2);
        medicoEspecialidadDiagnosticoDTO2.setId(2L);
        assertThat(medicoEspecialidadDiagnosticoDTO1).isNotEqualTo(medicoEspecialidadDiagnosticoDTO2);
        medicoEspecialidadDiagnosticoDTO1.setId(null);
        assertThat(medicoEspecialidadDiagnosticoDTO1).isNotEqualTo(medicoEspecialidadDiagnosticoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(medicoEspecialidadDiagnosticoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(medicoEspecialidadDiagnosticoMapper.fromId(null)).isNull();
    }
}
