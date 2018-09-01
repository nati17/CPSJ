package com.cpsj.web.rest;

import com.cpsj.CpsjApp;

import com.cpsj.domain.ConsPractObservacion;
import com.cpsj.repository.ConsPractObservacionRepository;
import com.cpsj.service.ConsPractObservacionService;
import com.cpsj.service.dto.ConsPractObservacionDTO;
import com.cpsj.service.mapper.ConsPractObservacionMapper;
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
 * Test class for the ConsPractObservacionResource REST controller.
 *
 * @see ConsPractObservacionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CpsjApp.class)
public class ConsPractObservacionResourceIntTest {

    private static final String DEFAULT_ID_CONS_PRACT = "AAAAAAAAAA";
    private static final String UPDATED_ID_CONS_PRACT = "BBBBBBBBBB";

    private static final String DEFAULT_ID_OBSERVACION = "AAAAAAAAAA";
    private static final String UPDATED_ID_OBSERVACION = "BBBBBBBBBB";

    @Autowired
    private ConsPractObservacionRepository consPractObservacionRepository;


    @Autowired
    private ConsPractObservacionMapper consPractObservacionMapper;
    

    @Autowired
    private ConsPractObservacionService consPractObservacionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restConsPractObservacionMockMvc;

    private ConsPractObservacion consPractObservacion;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ConsPractObservacionResource consPractObservacionResource = new ConsPractObservacionResource(consPractObservacionService);
        this.restConsPractObservacionMockMvc = MockMvcBuilders.standaloneSetup(consPractObservacionResource)
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
    public static ConsPractObservacion createEntity(EntityManager em) {
        ConsPractObservacion consPractObservacion = new ConsPractObservacion()
            .idConsPract(DEFAULT_ID_CONS_PRACT)
            .idObservacion(DEFAULT_ID_OBSERVACION);
        return consPractObservacion;
    }

    @Before
    public void initTest() {
        consPractObservacion = createEntity(em);
    }

    @Test
    @Transactional
    public void createConsPractObservacion() throws Exception {
        int databaseSizeBeforeCreate = consPractObservacionRepository.findAll().size();

        // Create the ConsPractObservacion
        ConsPractObservacionDTO consPractObservacionDTO = consPractObservacionMapper.toDto(consPractObservacion);
        restConsPractObservacionMockMvc.perform(post("/api/cons-pract-observacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consPractObservacionDTO)))
            .andExpect(status().isCreated());

        // Validate the ConsPractObservacion in the database
        List<ConsPractObservacion> consPractObservacionList = consPractObservacionRepository.findAll();
        assertThat(consPractObservacionList).hasSize(databaseSizeBeforeCreate + 1);
        ConsPractObservacion testConsPractObservacion = consPractObservacionList.get(consPractObservacionList.size() - 1);
        assertThat(testConsPractObservacion.getIdConsPract()).isEqualTo(DEFAULT_ID_CONS_PRACT);
        assertThat(testConsPractObservacion.getIdObservacion()).isEqualTo(DEFAULT_ID_OBSERVACION);
    }

    @Test
    @Transactional
    public void createConsPractObservacionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = consPractObservacionRepository.findAll().size();

        // Create the ConsPractObservacion with an existing ID
        consPractObservacion.setId(1L);
        ConsPractObservacionDTO consPractObservacionDTO = consPractObservacionMapper.toDto(consPractObservacion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConsPractObservacionMockMvc.perform(post("/api/cons-pract-observacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consPractObservacionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ConsPractObservacion in the database
        List<ConsPractObservacion> consPractObservacionList = consPractObservacionRepository.findAll();
        assertThat(consPractObservacionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllConsPractObservacions() throws Exception {
        // Initialize the database
        consPractObservacionRepository.saveAndFlush(consPractObservacion);

        // Get all the consPractObservacionList
        restConsPractObservacionMockMvc.perform(get("/api/cons-pract-observacions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(consPractObservacion.getId().intValue())))
            .andExpect(jsonPath("$.[*].idConsPract").value(hasItem(DEFAULT_ID_CONS_PRACT.toString())))
            .andExpect(jsonPath("$.[*].idObservacion").value(hasItem(DEFAULT_ID_OBSERVACION.toString())));
    }
    

    @Test
    @Transactional
    public void getConsPractObservacion() throws Exception {
        // Initialize the database
        consPractObservacionRepository.saveAndFlush(consPractObservacion);

        // Get the consPractObservacion
        restConsPractObservacionMockMvc.perform(get("/api/cons-pract-observacions/{id}", consPractObservacion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(consPractObservacion.getId().intValue()))
            .andExpect(jsonPath("$.idConsPract").value(DEFAULT_ID_CONS_PRACT.toString()))
            .andExpect(jsonPath("$.idObservacion").value(DEFAULT_ID_OBSERVACION.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingConsPractObservacion() throws Exception {
        // Get the consPractObservacion
        restConsPractObservacionMockMvc.perform(get("/api/cons-pract-observacions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConsPractObservacion() throws Exception {
        // Initialize the database
        consPractObservacionRepository.saveAndFlush(consPractObservacion);

        int databaseSizeBeforeUpdate = consPractObservacionRepository.findAll().size();

        // Update the consPractObservacion
        ConsPractObservacion updatedConsPractObservacion = consPractObservacionRepository.findById(consPractObservacion.getId()).get();
        // Disconnect from session so that the updates on updatedConsPractObservacion are not directly saved in db
        em.detach(updatedConsPractObservacion);
        updatedConsPractObservacion
            .idConsPract(UPDATED_ID_CONS_PRACT)
            .idObservacion(UPDATED_ID_OBSERVACION);
        ConsPractObservacionDTO consPractObservacionDTO = consPractObservacionMapper.toDto(updatedConsPractObservacion);

        restConsPractObservacionMockMvc.perform(put("/api/cons-pract-observacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consPractObservacionDTO)))
            .andExpect(status().isOk());

        // Validate the ConsPractObservacion in the database
        List<ConsPractObservacion> consPractObservacionList = consPractObservacionRepository.findAll();
        assertThat(consPractObservacionList).hasSize(databaseSizeBeforeUpdate);
        ConsPractObservacion testConsPractObservacion = consPractObservacionList.get(consPractObservacionList.size() - 1);
        assertThat(testConsPractObservacion.getIdConsPract()).isEqualTo(UPDATED_ID_CONS_PRACT);
        assertThat(testConsPractObservacion.getIdObservacion()).isEqualTo(UPDATED_ID_OBSERVACION);
    }

    @Test
    @Transactional
    public void updateNonExistingConsPractObservacion() throws Exception {
        int databaseSizeBeforeUpdate = consPractObservacionRepository.findAll().size();

        // Create the ConsPractObservacion
        ConsPractObservacionDTO consPractObservacionDTO = consPractObservacionMapper.toDto(consPractObservacion);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restConsPractObservacionMockMvc.perform(put("/api/cons-pract-observacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consPractObservacionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ConsPractObservacion in the database
        List<ConsPractObservacion> consPractObservacionList = consPractObservacionRepository.findAll();
        assertThat(consPractObservacionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteConsPractObservacion() throws Exception {
        // Initialize the database
        consPractObservacionRepository.saveAndFlush(consPractObservacion);

        int databaseSizeBeforeDelete = consPractObservacionRepository.findAll().size();

        // Get the consPractObservacion
        restConsPractObservacionMockMvc.perform(delete("/api/cons-pract-observacions/{id}", consPractObservacion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ConsPractObservacion> consPractObservacionList = consPractObservacionRepository.findAll();
        assertThat(consPractObservacionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConsPractObservacion.class);
        ConsPractObservacion consPractObservacion1 = new ConsPractObservacion();
        consPractObservacion1.setId(1L);
        ConsPractObservacion consPractObservacion2 = new ConsPractObservacion();
        consPractObservacion2.setId(consPractObservacion1.getId());
        assertThat(consPractObservacion1).isEqualTo(consPractObservacion2);
        consPractObservacion2.setId(2L);
        assertThat(consPractObservacion1).isNotEqualTo(consPractObservacion2);
        consPractObservacion1.setId(null);
        assertThat(consPractObservacion1).isNotEqualTo(consPractObservacion2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConsPractObservacionDTO.class);
        ConsPractObservacionDTO consPractObservacionDTO1 = new ConsPractObservacionDTO();
        consPractObservacionDTO1.setId(1L);
        ConsPractObservacionDTO consPractObservacionDTO2 = new ConsPractObservacionDTO();
        assertThat(consPractObservacionDTO1).isNotEqualTo(consPractObservacionDTO2);
        consPractObservacionDTO2.setId(consPractObservacionDTO1.getId());
        assertThat(consPractObservacionDTO1).isEqualTo(consPractObservacionDTO2);
        consPractObservacionDTO2.setId(2L);
        assertThat(consPractObservacionDTO1).isNotEqualTo(consPractObservacionDTO2);
        consPractObservacionDTO1.setId(null);
        assertThat(consPractObservacionDTO1).isNotEqualTo(consPractObservacionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(consPractObservacionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(consPractObservacionMapper.fromId(null)).isNull();
    }
}
