package com.cpsj.web.rest;

import com.cpsj.CpsjApp;

import com.cpsj.domain.Calendario;
import com.cpsj.repository.CalendarioRepository;
import com.cpsj.service.CalendarioService;
import com.cpsj.service.dto.CalendarioDTO;
import com.cpsj.service.mapper.CalendarioMapper;
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
 * Test class for the CalendarioResource REST controller.
 *
 * @see CalendarioResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CpsjApp.class)
public class CalendarioResourceIntTest {

    private static final String DEFAULT_ID_CALENDARIO = "AAAAAAAAAA";
    private static final String UPDATED_ID_CALENDARIO = "BBBBBBBBBB";

    private static final String DEFAULT_ANIO_CALENDARIO = "AAAAAAAAAA";
    private static final String UPDATED_ANIO_CALENDARIO = "BBBBBBBBBB";

    private static final String DEFAULT_MES_CALENDARIO = "AAAAAAAAAA";
    private static final String UPDATED_MES_CALENDARIO = "BBBBBBBBBB";

    @Autowired
    private CalendarioRepository calendarioRepository;


    @Autowired
    private CalendarioMapper calendarioMapper;
    

    @Autowired
    private CalendarioService calendarioService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCalendarioMockMvc;

    private Calendario calendario;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CalendarioResource calendarioResource = new CalendarioResource(calendarioService);
        this.restCalendarioMockMvc = MockMvcBuilders.standaloneSetup(calendarioResource)
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
    public static Calendario createEntity(EntityManager em) {
        Calendario calendario = new Calendario()
            .idCalendario(DEFAULT_ID_CALENDARIO)
            .anioCalendario(DEFAULT_ANIO_CALENDARIO)
            .mesCalendario(DEFAULT_MES_CALENDARIO);
        return calendario;
    }

    @Before
    public void initTest() {
        calendario = createEntity(em);
    }

    @Test
    @Transactional
    public void createCalendario() throws Exception {
        int databaseSizeBeforeCreate = calendarioRepository.findAll().size();

        // Create the Calendario
        CalendarioDTO calendarioDTO = calendarioMapper.toDto(calendario);
        restCalendarioMockMvc.perform(post("/api/calendarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(calendarioDTO)))
            .andExpect(status().isCreated());

        // Validate the Calendario in the database
        List<Calendario> calendarioList = calendarioRepository.findAll();
        assertThat(calendarioList).hasSize(databaseSizeBeforeCreate + 1);
        Calendario testCalendario = calendarioList.get(calendarioList.size() - 1);
        assertThat(testCalendario.getIdCalendario()).isEqualTo(DEFAULT_ID_CALENDARIO);
        assertThat(testCalendario.getAnioCalendario()).isEqualTo(DEFAULT_ANIO_CALENDARIO);
        assertThat(testCalendario.getMesCalendario()).isEqualTo(DEFAULT_MES_CALENDARIO);
    }

    @Test
    @Transactional
    public void createCalendarioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = calendarioRepository.findAll().size();

        // Create the Calendario with an existing ID
        calendario.setId(1L);
        CalendarioDTO calendarioDTO = calendarioMapper.toDto(calendario);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCalendarioMockMvc.perform(post("/api/calendarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(calendarioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Calendario in the database
        List<Calendario> calendarioList = calendarioRepository.findAll();
        assertThat(calendarioList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCalendarios() throws Exception {
        // Initialize the database
        calendarioRepository.saveAndFlush(calendario);

        // Get all the calendarioList
        restCalendarioMockMvc.perform(get("/api/calendarios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(calendario.getId().intValue())))
            .andExpect(jsonPath("$.[*].idCalendario").value(hasItem(DEFAULT_ID_CALENDARIO.toString())))
            .andExpect(jsonPath("$.[*].anioCalendario").value(hasItem(DEFAULT_ANIO_CALENDARIO.toString())))
            .andExpect(jsonPath("$.[*].mesCalendario").value(hasItem(DEFAULT_MES_CALENDARIO.toString())));
    }
    

    @Test
    @Transactional
    public void getCalendario() throws Exception {
        // Initialize the database
        calendarioRepository.saveAndFlush(calendario);

        // Get the calendario
        restCalendarioMockMvc.perform(get("/api/calendarios/{id}", calendario.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(calendario.getId().intValue()))
            .andExpect(jsonPath("$.idCalendario").value(DEFAULT_ID_CALENDARIO.toString()))
            .andExpect(jsonPath("$.anioCalendario").value(DEFAULT_ANIO_CALENDARIO.toString()))
            .andExpect(jsonPath("$.mesCalendario").value(DEFAULT_MES_CALENDARIO.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCalendario() throws Exception {
        // Get the calendario
        restCalendarioMockMvc.perform(get("/api/calendarios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCalendario() throws Exception {
        // Initialize the database
        calendarioRepository.saveAndFlush(calendario);

        int databaseSizeBeforeUpdate = calendarioRepository.findAll().size();

        // Update the calendario
        Calendario updatedCalendario = calendarioRepository.findById(calendario.getId()).get();
        // Disconnect from session so that the updates on updatedCalendario are not directly saved in db
        em.detach(updatedCalendario);
        updatedCalendario
            .idCalendario(UPDATED_ID_CALENDARIO)
            .anioCalendario(UPDATED_ANIO_CALENDARIO)
            .mesCalendario(UPDATED_MES_CALENDARIO);
        CalendarioDTO calendarioDTO = calendarioMapper.toDto(updatedCalendario);

        restCalendarioMockMvc.perform(put("/api/calendarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(calendarioDTO)))
            .andExpect(status().isOk());

        // Validate the Calendario in the database
        List<Calendario> calendarioList = calendarioRepository.findAll();
        assertThat(calendarioList).hasSize(databaseSizeBeforeUpdate);
        Calendario testCalendario = calendarioList.get(calendarioList.size() - 1);
        assertThat(testCalendario.getIdCalendario()).isEqualTo(UPDATED_ID_CALENDARIO);
        assertThat(testCalendario.getAnioCalendario()).isEqualTo(UPDATED_ANIO_CALENDARIO);
        assertThat(testCalendario.getMesCalendario()).isEqualTo(UPDATED_MES_CALENDARIO);
    }

    @Test
    @Transactional
    public void updateNonExistingCalendario() throws Exception {
        int databaseSizeBeforeUpdate = calendarioRepository.findAll().size();

        // Create the Calendario
        CalendarioDTO calendarioDTO = calendarioMapper.toDto(calendario);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCalendarioMockMvc.perform(put("/api/calendarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(calendarioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Calendario in the database
        List<Calendario> calendarioList = calendarioRepository.findAll();
        assertThat(calendarioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCalendario() throws Exception {
        // Initialize the database
        calendarioRepository.saveAndFlush(calendario);

        int databaseSizeBeforeDelete = calendarioRepository.findAll().size();

        // Get the calendario
        restCalendarioMockMvc.perform(delete("/api/calendarios/{id}", calendario.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Calendario> calendarioList = calendarioRepository.findAll();
        assertThat(calendarioList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Calendario.class);
        Calendario calendario1 = new Calendario();
        calendario1.setId(1L);
        Calendario calendario2 = new Calendario();
        calendario2.setId(calendario1.getId());
        assertThat(calendario1).isEqualTo(calendario2);
        calendario2.setId(2L);
        assertThat(calendario1).isNotEqualTo(calendario2);
        calendario1.setId(null);
        assertThat(calendario1).isNotEqualTo(calendario2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CalendarioDTO.class);
        CalendarioDTO calendarioDTO1 = new CalendarioDTO();
        calendarioDTO1.setId(1L);
        CalendarioDTO calendarioDTO2 = new CalendarioDTO();
        assertThat(calendarioDTO1).isNotEqualTo(calendarioDTO2);
        calendarioDTO2.setId(calendarioDTO1.getId());
        assertThat(calendarioDTO1).isEqualTo(calendarioDTO2);
        calendarioDTO2.setId(2L);
        assertThat(calendarioDTO1).isNotEqualTo(calendarioDTO2);
        calendarioDTO1.setId(null);
        assertThat(calendarioDTO1).isNotEqualTo(calendarioDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(calendarioMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(calendarioMapper.fromId(null)).isNull();
    }
}
