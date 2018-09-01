package com.cpsj.web.rest;

import com.cpsj.CpsjApp;

import com.cpsj.domain.MontoConsultaPractica;
import com.cpsj.repository.MontoConsultaPracticaRepository;
import com.cpsj.service.MontoConsultaPracticaService;
import com.cpsj.service.dto.MontoConsultaPracticaDTO;
import com.cpsj.service.mapper.MontoConsultaPracticaMapper;
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
 * Test class for the MontoConsultaPracticaResource REST controller.
 *
 * @see MontoConsultaPracticaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CpsjApp.class)
public class MontoConsultaPracticaResourceIntTest {

    private static final String DEFAULT_ID_MONTO_CONS_PRACT = "AAAAAAAAAA";
    private static final String UPDATED_ID_MONTO_CONS_PRACT = "BBBBBBBBBB";

    private static final String DEFAULT_MONTO = "AAAAAAAAAA";
    private static final String UPDATED_MONTO = "BBBBBBBBBB";

    @Autowired
    private MontoConsultaPracticaRepository montoConsultaPracticaRepository;


    @Autowired
    private MontoConsultaPracticaMapper montoConsultaPracticaMapper;
    

    @Autowired
    private MontoConsultaPracticaService montoConsultaPracticaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMontoConsultaPracticaMockMvc;

    private MontoConsultaPractica montoConsultaPractica;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MontoConsultaPracticaResource montoConsultaPracticaResource = new MontoConsultaPracticaResource(montoConsultaPracticaService);
        this.restMontoConsultaPracticaMockMvc = MockMvcBuilders.standaloneSetup(montoConsultaPracticaResource)
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
    public static MontoConsultaPractica createEntity(EntityManager em) {
        MontoConsultaPractica montoConsultaPractica = new MontoConsultaPractica()
            .idMontoConsPract(DEFAULT_ID_MONTO_CONS_PRACT)
            .monto(DEFAULT_MONTO);
        return montoConsultaPractica;
    }

    @Before
    public void initTest() {
        montoConsultaPractica = createEntity(em);
    }

    @Test
    @Transactional
    public void createMontoConsultaPractica() throws Exception {
        int databaseSizeBeforeCreate = montoConsultaPracticaRepository.findAll().size();

        // Create the MontoConsultaPractica
        MontoConsultaPracticaDTO montoConsultaPracticaDTO = montoConsultaPracticaMapper.toDto(montoConsultaPractica);
        restMontoConsultaPracticaMockMvc.perform(post("/api/monto-consulta-practicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(montoConsultaPracticaDTO)))
            .andExpect(status().isCreated());

        // Validate the MontoConsultaPractica in the database
        List<MontoConsultaPractica> montoConsultaPracticaList = montoConsultaPracticaRepository.findAll();
        assertThat(montoConsultaPracticaList).hasSize(databaseSizeBeforeCreate + 1);
        MontoConsultaPractica testMontoConsultaPractica = montoConsultaPracticaList.get(montoConsultaPracticaList.size() - 1);
        assertThat(testMontoConsultaPractica.getIdMontoConsPract()).isEqualTo(DEFAULT_ID_MONTO_CONS_PRACT);
        assertThat(testMontoConsultaPractica.getMonto()).isEqualTo(DEFAULT_MONTO);
    }

    @Test
    @Transactional
    public void createMontoConsultaPracticaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = montoConsultaPracticaRepository.findAll().size();

        // Create the MontoConsultaPractica with an existing ID
        montoConsultaPractica.setId(1L);
        MontoConsultaPracticaDTO montoConsultaPracticaDTO = montoConsultaPracticaMapper.toDto(montoConsultaPractica);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMontoConsultaPracticaMockMvc.perform(post("/api/monto-consulta-practicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(montoConsultaPracticaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MontoConsultaPractica in the database
        List<MontoConsultaPractica> montoConsultaPracticaList = montoConsultaPracticaRepository.findAll();
        assertThat(montoConsultaPracticaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMontoConsultaPracticas() throws Exception {
        // Initialize the database
        montoConsultaPracticaRepository.saveAndFlush(montoConsultaPractica);

        // Get all the montoConsultaPracticaList
        restMontoConsultaPracticaMockMvc.perform(get("/api/monto-consulta-practicas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(montoConsultaPractica.getId().intValue())))
            .andExpect(jsonPath("$.[*].idMontoConsPract").value(hasItem(DEFAULT_ID_MONTO_CONS_PRACT.toString())))
            .andExpect(jsonPath("$.[*].monto").value(hasItem(DEFAULT_MONTO.toString())));
    }
    

    @Test
    @Transactional
    public void getMontoConsultaPractica() throws Exception {
        // Initialize the database
        montoConsultaPracticaRepository.saveAndFlush(montoConsultaPractica);

        // Get the montoConsultaPractica
        restMontoConsultaPracticaMockMvc.perform(get("/api/monto-consulta-practicas/{id}", montoConsultaPractica.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(montoConsultaPractica.getId().intValue()))
            .andExpect(jsonPath("$.idMontoConsPract").value(DEFAULT_ID_MONTO_CONS_PRACT.toString()))
            .andExpect(jsonPath("$.monto").value(DEFAULT_MONTO.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingMontoConsultaPractica() throws Exception {
        // Get the montoConsultaPractica
        restMontoConsultaPracticaMockMvc.perform(get("/api/monto-consulta-practicas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMontoConsultaPractica() throws Exception {
        // Initialize the database
        montoConsultaPracticaRepository.saveAndFlush(montoConsultaPractica);

        int databaseSizeBeforeUpdate = montoConsultaPracticaRepository.findAll().size();

        // Update the montoConsultaPractica
        MontoConsultaPractica updatedMontoConsultaPractica = montoConsultaPracticaRepository.findById(montoConsultaPractica.getId()).get();
        // Disconnect from session so that the updates on updatedMontoConsultaPractica are not directly saved in db
        em.detach(updatedMontoConsultaPractica);
        updatedMontoConsultaPractica
            .idMontoConsPract(UPDATED_ID_MONTO_CONS_PRACT)
            .monto(UPDATED_MONTO);
        MontoConsultaPracticaDTO montoConsultaPracticaDTO = montoConsultaPracticaMapper.toDto(updatedMontoConsultaPractica);

        restMontoConsultaPracticaMockMvc.perform(put("/api/monto-consulta-practicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(montoConsultaPracticaDTO)))
            .andExpect(status().isOk());

        // Validate the MontoConsultaPractica in the database
        List<MontoConsultaPractica> montoConsultaPracticaList = montoConsultaPracticaRepository.findAll();
        assertThat(montoConsultaPracticaList).hasSize(databaseSizeBeforeUpdate);
        MontoConsultaPractica testMontoConsultaPractica = montoConsultaPracticaList.get(montoConsultaPracticaList.size() - 1);
        assertThat(testMontoConsultaPractica.getIdMontoConsPract()).isEqualTo(UPDATED_ID_MONTO_CONS_PRACT);
        assertThat(testMontoConsultaPractica.getMonto()).isEqualTo(UPDATED_MONTO);
    }

    @Test
    @Transactional
    public void updateNonExistingMontoConsultaPractica() throws Exception {
        int databaseSizeBeforeUpdate = montoConsultaPracticaRepository.findAll().size();

        // Create the MontoConsultaPractica
        MontoConsultaPracticaDTO montoConsultaPracticaDTO = montoConsultaPracticaMapper.toDto(montoConsultaPractica);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMontoConsultaPracticaMockMvc.perform(put("/api/monto-consulta-practicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(montoConsultaPracticaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MontoConsultaPractica in the database
        List<MontoConsultaPractica> montoConsultaPracticaList = montoConsultaPracticaRepository.findAll();
        assertThat(montoConsultaPracticaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMontoConsultaPractica() throws Exception {
        // Initialize the database
        montoConsultaPracticaRepository.saveAndFlush(montoConsultaPractica);

        int databaseSizeBeforeDelete = montoConsultaPracticaRepository.findAll().size();

        // Get the montoConsultaPractica
        restMontoConsultaPracticaMockMvc.perform(delete("/api/monto-consulta-practicas/{id}", montoConsultaPractica.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MontoConsultaPractica> montoConsultaPracticaList = montoConsultaPracticaRepository.findAll();
        assertThat(montoConsultaPracticaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MontoConsultaPractica.class);
        MontoConsultaPractica montoConsultaPractica1 = new MontoConsultaPractica();
        montoConsultaPractica1.setId(1L);
        MontoConsultaPractica montoConsultaPractica2 = new MontoConsultaPractica();
        montoConsultaPractica2.setId(montoConsultaPractica1.getId());
        assertThat(montoConsultaPractica1).isEqualTo(montoConsultaPractica2);
        montoConsultaPractica2.setId(2L);
        assertThat(montoConsultaPractica1).isNotEqualTo(montoConsultaPractica2);
        montoConsultaPractica1.setId(null);
        assertThat(montoConsultaPractica1).isNotEqualTo(montoConsultaPractica2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MontoConsultaPracticaDTO.class);
        MontoConsultaPracticaDTO montoConsultaPracticaDTO1 = new MontoConsultaPracticaDTO();
        montoConsultaPracticaDTO1.setId(1L);
        MontoConsultaPracticaDTO montoConsultaPracticaDTO2 = new MontoConsultaPracticaDTO();
        assertThat(montoConsultaPracticaDTO1).isNotEqualTo(montoConsultaPracticaDTO2);
        montoConsultaPracticaDTO2.setId(montoConsultaPracticaDTO1.getId());
        assertThat(montoConsultaPracticaDTO1).isEqualTo(montoConsultaPracticaDTO2);
        montoConsultaPracticaDTO2.setId(2L);
        assertThat(montoConsultaPracticaDTO1).isNotEqualTo(montoConsultaPracticaDTO2);
        montoConsultaPracticaDTO1.setId(null);
        assertThat(montoConsultaPracticaDTO1).isNotEqualTo(montoConsultaPracticaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(montoConsultaPracticaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(montoConsultaPracticaMapper.fromId(null)).isNull();
    }
}
