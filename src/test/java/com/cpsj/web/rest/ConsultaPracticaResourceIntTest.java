package com.cpsj.web.rest;

import com.cpsj.CpsjApp;

import com.cpsj.domain.ConsultaPractica;
import com.cpsj.repository.ConsultaPracticaRepository;
import com.cpsj.service.ConsultaPracticaService;
import com.cpsj.service.dto.ConsultaPracticaDTO;
import com.cpsj.service.mapper.ConsultaPracticaMapper;
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
 * Test class for the ConsultaPracticaResource REST controller.
 *
 * @see ConsultaPracticaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CpsjApp.class)
public class ConsultaPracticaResourceIntTest {

    private static final String DEFAULT_ATTRIBUTE = "AAAAAAAAAA";
    private static final String UPDATED_ATTRIBUTE = "BBBBBBBBBB";

    @Autowired
    private ConsultaPracticaRepository consultaPracticaRepository;


    @Autowired
    private ConsultaPracticaMapper consultaPracticaMapper;
    

    @Autowired
    private ConsultaPracticaService consultaPracticaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restConsultaPracticaMockMvc;

    private ConsultaPractica consultaPractica;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ConsultaPracticaResource consultaPracticaResource = new ConsultaPracticaResource(consultaPracticaService);
        this.restConsultaPracticaMockMvc = MockMvcBuilders.standaloneSetup(consultaPracticaResource)
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
    public static ConsultaPractica createEntity(EntityManager em) {
        ConsultaPractica consultaPractica = new ConsultaPractica()
            .attribute(DEFAULT_ATTRIBUTE);
        return consultaPractica;
    }

    @Before
    public void initTest() {
        consultaPractica = createEntity(em);
    }

    @Test
    @Transactional
    public void createConsultaPractica() throws Exception {
        int databaseSizeBeforeCreate = consultaPracticaRepository.findAll().size();

        // Create the ConsultaPractica
        ConsultaPracticaDTO consultaPracticaDTO = consultaPracticaMapper.toDto(consultaPractica);
        restConsultaPracticaMockMvc.perform(post("/api/consulta-practicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consultaPracticaDTO)))
            .andExpect(status().isCreated());

        // Validate the ConsultaPractica in the database
        List<ConsultaPractica> consultaPracticaList = consultaPracticaRepository.findAll();
        assertThat(consultaPracticaList).hasSize(databaseSizeBeforeCreate + 1);
        ConsultaPractica testConsultaPractica = consultaPracticaList.get(consultaPracticaList.size() - 1);
        assertThat(testConsultaPractica.getAttribute()).isEqualTo(DEFAULT_ATTRIBUTE);
    }

    @Test
    @Transactional
    public void createConsultaPracticaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = consultaPracticaRepository.findAll().size();

        // Create the ConsultaPractica with an existing ID
        consultaPractica.setId(1L);
        ConsultaPracticaDTO consultaPracticaDTO = consultaPracticaMapper.toDto(consultaPractica);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConsultaPracticaMockMvc.perform(post("/api/consulta-practicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consultaPracticaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ConsultaPractica in the database
        List<ConsultaPractica> consultaPracticaList = consultaPracticaRepository.findAll();
        assertThat(consultaPracticaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllConsultaPracticas() throws Exception {
        // Initialize the database
        consultaPracticaRepository.saveAndFlush(consultaPractica);

        // Get all the consultaPracticaList
        restConsultaPracticaMockMvc.perform(get("/api/consulta-practicas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(consultaPractica.getId().intValue())))
            .andExpect(jsonPath("$.[*].attribute").value(hasItem(DEFAULT_ATTRIBUTE.toString())));
    }
    

    @Test
    @Transactional
    public void getConsultaPractica() throws Exception {
        // Initialize the database
        consultaPracticaRepository.saveAndFlush(consultaPractica);

        // Get the consultaPractica
        restConsultaPracticaMockMvc.perform(get("/api/consulta-practicas/{id}", consultaPractica.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(consultaPractica.getId().intValue()))
            .andExpect(jsonPath("$.attribute").value(DEFAULT_ATTRIBUTE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingConsultaPractica() throws Exception {
        // Get the consultaPractica
        restConsultaPracticaMockMvc.perform(get("/api/consulta-practicas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConsultaPractica() throws Exception {
        // Initialize the database
        consultaPracticaRepository.saveAndFlush(consultaPractica);

        int databaseSizeBeforeUpdate = consultaPracticaRepository.findAll().size();

        // Update the consultaPractica
        ConsultaPractica updatedConsultaPractica = consultaPracticaRepository.findById(consultaPractica.getId()).get();
        // Disconnect from session so that the updates on updatedConsultaPractica are not directly saved in db
        em.detach(updatedConsultaPractica);
        updatedConsultaPractica
            .attribute(UPDATED_ATTRIBUTE);
        ConsultaPracticaDTO consultaPracticaDTO = consultaPracticaMapper.toDto(updatedConsultaPractica);

        restConsultaPracticaMockMvc.perform(put("/api/consulta-practicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consultaPracticaDTO)))
            .andExpect(status().isOk());

        // Validate the ConsultaPractica in the database
        List<ConsultaPractica> consultaPracticaList = consultaPracticaRepository.findAll();
        assertThat(consultaPracticaList).hasSize(databaseSizeBeforeUpdate);
        ConsultaPractica testConsultaPractica = consultaPracticaList.get(consultaPracticaList.size() - 1);
        assertThat(testConsultaPractica.getAttribute()).isEqualTo(UPDATED_ATTRIBUTE);
    }

    @Test
    @Transactional
    public void updateNonExistingConsultaPractica() throws Exception {
        int databaseSizeBeforeUpdate = consultaPracticaRepository.findAll().size();

        // Create the ConsultaPractica
        ConsultaPracticaDTO consultaPracticaDTO = consultaPracticaMapper.toDto(consultaPractica);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restConsultaPracticaMockMvc.perform(put("/api/consulta-practicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consultaPracticaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ConsultaPractica in the database
        List<ConsultaPractica> consultaPracticaList = consultaPracticaRepository.findAll();
        assertThat(consultaPracticaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteConsultaPractica() throws Exception {
        // Initialize the database
        consultaPracticaRepository.saveAndFlush(consultaPractica);

        int databaseSizeBeforeDelete = consultaPracticaRepository.findAll().size();

        // Get the consultaPractica
        restConsultaPracticaMockMvc.perform(delete("/api/consulta-practicas/{id}", consultaPractica.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ConsultaPractica> consultaPracticaList = consultaPracticaRepository.findAll();
        assertThat(consultaPracticaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConsultaPractica.class);
        ConsultaPractica consultaPractica1 = new ConsultaPractica();
        consultaPractica1.setId(1L);
        ConsultaPractica consultaPractica2 = new ConsultaPractica();
        consultaPractica2.setId(consultaPractica1.getId());
        assertThat(consultaPractica1).isEqualTo(consultaPractica2);
        consultaPractica2.setId(2L);
        assertThat(consultaPractica1).isNotEqualTo(consultaPractica2);
        consultaPractica1.setId(null);
        assertThat(consultaPractica1).isNotEqualTo(consultaPractica2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConsultaPracticaDTO.class);
        ConsultaPracticaDTO consultaPracticaDTO1 = new ConsultaPracticaDTO();
        consultaPracticaDTO1.setId(1L);
        ConsultaPracticaDTO consultaPracticaDTO2 = new ConsultaPracticaDTO();
        assertThat(consultaPracticaDTO1).isNotEqualTo(consultaPracticaDTO2);
        consultaPracticaDTO2.setId(consultaPracticaDTO1.getId());
        assertThat(consultaPracticaDTO1).isEqualTo(consultaPracticaDTO2);
        consultaPracticaDTO2.setId(2L);
        assertThat(consultaPracticaDTO1).isNotEqualTo(consultaPracticaDTO2);
        consultaPracticaDTO1.setId(null);
        assertThat(consultaPracticaDTO1).isNotEqualTo(consultaPracticaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(consultaPracticaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(consultaPracticaMapper.fromId(null)).isNull();
    }
}
