package com.cpsj.web.rest;

import com.cpsj.CpsjApp;

import com.cpsj.domain.MedicoCalendario;
import com.cpsj.repository.MedicoCalendarioRepository;
import com.cpsj.service.MedicoCalendarioService;
import com.cpsj.service.dto.MedicoCalendarioDTO;
import com.cpsj.service.mapper.MedicoCalendarioMapper;
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
 * Test class for the MedicoCalendarioResource REST controller.
 *
 * @see MedicoCalendarioResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CpsjApp.class)
public class MedicoCalendarioResourceIntTest {

    private static final String DEFAULT_ID_MEDICO = "AAAAAAAAAA";
    private static final String UPDATED_ID_MEDICO = "BBBBBBBBBB";

    private static final String DEFAULT_ID_CALENDARIO = "AAAAAAAAAA";
    private static final String UPDATED_ID_CALENDARIO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ESTADO = false;
    private static final Boolean UPDATED_ESTADO = true;

    @Autowired
    private MedicoCalendarioRepository medicoCalendarioRepository;


    @Autowired
    private MedicoCalendarioMapper medicoCalendarioMapper;
    

    @Autowired
    private MedicoCalendarioService medicoCalendarioService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMedicoCalendarioMockMvc;

    private MedicoCalendario medicoCalendario;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MedicoCalendarioResource medicoCalendarioResource = new MedicoCalendarioResource(medicoCalendarioService);
        this.restMedicoCalendarioMockMvc = MockMvcBuilders.standaloneSetup(medicoCalendarioResource)
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
    public static MedicoCalendario createEntity(EntityManager em) {
        MedicoCalendario medicoCalendario = new MedicoCalendario()
            .idMedico(DEFAULT_ID_MEDICO)
            .idCalendario(DEFAULT_ID_CALENDARIO)
            .estado(DEFAULT_ESTADO);
        return medicoCalendario;
    }

    @Before
    public void initTest() {
        medicoCalendario = createEntity(em);
    }

    @Test
    @Transactional
    public void createMedicoCalendario() throws Exception {
        int databaseSizeBeforeCreate = medicoCalendarioRepository.findAll().size();

        // Create the MedicoCalendario
        MedicoCalendarioDTO medicoCalendarioDTO = medicoCalendarioMapper.toDto(medicoCalendario);
        restMedicoCalendarioMockMvc.perform(post("/api/medico-calendarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medicoCalendarioDTO)))
            .andExpect(status().isCreated());

        // Validate the MedicoCalendario in the database
        List<MedicoCalendario> medicoCalendarioList = medicoCalendarioRepository.findAll();
        assertThat(medicoCalendarioList).hasSize(databaseSizeBeforeCreate + 1);
        MedicoCalendario testMedicoCalendario = medicoCalendarioList.get(medicoCalendarioList.size() - 1);
        assertThat(testMedicoCalendario.getIdMedico()).isEqualTo(DEFAULT_ID_MEDICO);
        assertThat(testMedicoCalendario.getIdCalendario()).isEqualTo(DEFAULT_ID_CALENDARIO);
        assertThat(testMedicoCalendario.isEstado()).isEqualTo(DEFAULT_ESTADO);
    }

    @Test
    @Transactional
    public void createMedicoCalendarioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = medicoCalendarioRepository.findAll().size();

        // Create the MedicoCalendario with an existing ID
        medicoCalendario.setId(1L);
        MedicoCalendarioDTO medicoCalendarioDTO = medicoCalendarioMapper.toDto(medicoCalendario);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMedicoCalendarioMockMvc.perform(post("/api/medico-calendarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medicoCalendarioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedicoCalendario in the database
        List<MedicoCalendario> medicoCalendarioList = medicoCalendarioRepository.findAll();
        assertThat(medicoCalendarioList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMedicoCalendarios() throws Exception {
        // Initialize the database
        medicoCalendarioRepository.saveAndFlush(medicoCalendario);

        // Get all the medicoCalendarioList
        restMedicoCalendarioMockMvc.perform(get("/api/medico-calendarios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(medicoCalendario.getId().intValue())))
            .andExpect(jsonPath("$.[*].idMedico").value(hasItem(DEFAULT_ID_MEDICO.toString())))
            .andExpect(jsonPath("$.[*].idCalendario").value(hasItem(DEFAULT_ID_CALENDARIO.toString())))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.booleanValue())));
    }
    

    @Test
    @Transactional
    public void getMedicoCalendario() throws Exception {
        // Initialize the database
        medicoCalendarioRepository.saveAndFlush(medicoCalendario);

        // Get the medicoCalendario
        restMedicoCalendarioMockMvc.perform(get("/api/medico-calendarios/{id}", medicoCalendario.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(medicoCalendario.getId().intValue()))
            .andExpect(jsonPath("$.idMedico").value(DEFAULT_ID_MEDICO.toString()))
            .andExpect(jsonPath("$.idCalendario").value(DEFAULT_ID_CALENDARIO.toString()))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingMedicoCalendario() throws Exception {
        // Get the medicoCalendario
        restMedicoCalendarioMockMvc.perform(get("/api/medico-calendarios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMedicoCalendario() throws Exception {
        // Initialize the database
        medicoCalendarioRepository.saveAndFlush(medicoCalendario);

        int databaseSizeBeforeUpdate = medicoCalendarioRepository.findAll().size();

        // Update the medicoCalendario
        MedicoCalendario updatedMedicoCalendario = medicoCalendarioRepository.findById(medicoCalendario.getId()).get();
        // Disconnect from session so that the updates on updatedMedicoCalendario are not directly saved in db
        em.detach(updatedMedicoCalendario);
        updatedMedicoCalendario
            .idMedico(UPDATED_ID_MEDICO)
            .idCalendario(UPDATED_ID_CALENDARIO)
            .estado(UPDATED_ESTADO);
        MedicoCalendarioDTO medicoCalendarioDTO = medicoCalendarioMapper.toDto(updatedMedicoCalendario);

        restMedicoCalendarioMockMvc.perform(put("/api/medico-calendarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medicoCalendarioDTO)))
            .andExpect(status().isOk());

        // Validate the MedicoCalendario in the database
        List<MedicoCalendario> medicoCalendarioList = medicoCalendarioRepository.findAll();
        assertThat(medicoCalendarioList).hasSize(databaseSizeBeforeUpdate);
        MedicoCalendario testMedicoCalendario = medicoCalendarioList.get(medicoCalendarioList.size() - 1);
        assertThat(testMedicoCalendario.getIdMedico()).isEqualTo(UPDATED_ID_MEDICO);
        assertThat(testMedicoCalendario.getIdCalendario()).isEqualTo(UPDATED_ID_CALENDARIO);
        assertThat(testMedicoCalendario.isEstado()).isEqualTo(UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void updateNonExistingMedicoCalendario() throws Exception {
        int databaseSizeBeforeUpdate = medicoCalendarioRepository.findAll().size();

        // Create the MedicoCalendario
        MedicoCalendarioDTO medicoCalendarioDTO = medicoCalendarioMapper.toDto(medicoCalendario);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMedicoCalendarioMockMvc.perform(put("/api/medico-calendarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medicoCalendarioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedicoCalendario in the database
        List<MedicoCalendario> medicoCalendarioList = medicoCalendarioRepository.findAll();
        assertThat(medicoCalendarioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMedicoCalendario() throws Exception {
        // Initialize the database
        medicoCalendarioRepository.saveAndFlush(medicoCalendario);

        int databaseSizeBeforeDelete = medicoCalendarioRepository.findAll().size();

        // Get the medicoCalendario
        restMedicoCalendarioMockMvc.perform(delete("/api/medico-calendarios/{id}", medicoCalendario.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MedicoCalendario> medicoCalendarioList = medicoCalendarioRepository.findAll();
        assertThat(medicoCalendarioList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedicoCalendario.class);
        MedicoCalendario medicoCalendario1 = new MedicoCalendario();
        medicoCalendario1.setId(1L);
        MedicoCalendario medicoCalendario2 = new MedicoCalendario();
        medicoCalendario2.setId(medicoCalendario1.getId());
        assertThat(medicoCalendario1).isEqualTo(medicoCalendario2);
        medicoCalendario2.setId(2L);
        assertThat(medicoCalendario1).isNotEqualTo(medicoCalendario2);
        medicoCalendario1.setId(null);
        assertThat(medicoCalendario1).isNotEqualTo(medicoCalendario2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedicoCalendarioDTO.class);
        MedicoCalendarioDTO medicoCalendarioDTO1 = new MedicoCalendarioDTO();
        medicoCalendarioDTO1.setId(1L);
        MedicoCalendarioDTO medicoCalendarioDTO2 = new MedicoCalendarioDTO();
        assertThat(medicoCalendarioDTO1).isNotEqualTo(medicoCalendarioDTO2);
        medicoCalendarioDTO2.setId(medicoCalendarioDTO1.getId());
        assertThat(medicoCalendarioDTO1).isEqualTo(medicoCalendarioDTO2);
        medicoCalendarioDTO2.setId(2L);
        assertThat(medicoCalendarioDTO1).isNotEqualTo(medicoCalendarioDTO2);
        medicoCalendarioDTO1.setId(null);
        assertThat(medicoCalendarioDTO1).isNotEqualTo(medicoCalendarioDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(medicoCalendarioMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(medicoCalendarioMapper.fromId(null)).isNull();
    }
}
