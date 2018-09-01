package com.cpsj.web.rest;

import com.cpsj.CpsjApp;

import com.cpsj.domain.Observacion;
import com.cpsj.repository.ObservacionRepository;
import com.cpsj.service.ObservacionService;
import com.cpsj.service.dto.ObservacionDTO;
import com.cpsj.service.mapper.ObservacionMapper;
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
 * Test class for the ObservacionResource REST controller.
 *
 * @see ObservacionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CpsjApp.class)
public class ObservacionResourceIntTest {

    private static final String DEFAULT_ID_OBSERVACION = "AAAAAAAAAA";
    private static final String UPDATED_ID_OBSERVACION = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION_OBSERVACION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION_OBSERVACION = "BBBBBBBBBB";

    @Autowired
    private ObservacionRepository observacionRepository;


    @Autowired
    private ObservacionMapper observacionMapper;
    

    @Autowired
    private ObservacionService observacionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restObservacionMockMvc;

    private Observacion observacion;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ObservacionResource observacionResource = new ObservacionResource(observacionService);
        this.restObservacionMockMvc = MockMvcBuilders.standaloneSetup(observacionResource)
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
    public static Observacion createEntity(EntityManager em) {
        Observacion observacion = new Observacion()
            .idObservacion(DEFAULT_ID_OBSERVACION)
            .descripcionObservacion(DEFAULT_DESCRIPCION_OBSERVACION);
        return observacion;
    }

    @Before
    public void initTest() {
        observacion = createEntity(em);
    }

    @Test
    @Transactional
    public void createObservacion() throws Exception {
        int databaseSizeBeforeCreate = observacionRepository.findAll().size();

        // Create the Observacion
        ObservacionDTO observacionDTO = observacionMapper.toDto(observacion);
        restObservacionMockMvc.perform(post("/api/observacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(observacionDTO)))
            .andExpect(status().isCreated());

        // Validate the Observacion in the database
        List<Observacion> observacionList = observacionRepository.findAll();
        assertThat(observacionList).hasSize(databaseSizeBeforeCreate + 1);
        Observacion testObservacion = observacionList.get(observacionList.size() - 1);
        assertThat(testObservacion.getIdObservacion()).isEqualTo(DEFAULT_ID_OBSERVACION);
        assertThat(testObservacion.getDescripcionObservacion()).isEqualTo(DEFAULT_DESCRIPCION_OBSERVACION);
    }

    @Test
    @Transactional
    public void createObservacionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = observacionRepository.findAll().size();

        // Create the Observacion with an existing ID
        observacion.setId(1L);
        ObservacionDTO observacionDTO = observacionMapper.toDto(observacion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restObservacionMockMvc.perform(post("/api/observacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(observacionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Observacion in the database
        List<Observacion> observacionList = observacionRepository.findAll();
        assertThat(observacionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllObservacions() throws Exception {
        // Initialize the database
        observacionRepository.saveAndFlush(observacion);

        // Get all the observacionList
        restObservacionMockMvc.perform(get("/api/observacions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(observacion.getId().intValue())))
            .andExpect(jsonPath("$.[*].idObservacion").value(hasItem(DEFAULT_ID_OBSERVACION.toString())))
            .andExpect(jsonPath("$.[*].descripcionObservacion").value(hasItem(DEFAULT_DESCRIPCION_OBSERVACION.toString())));
    }
    

    @Test
    @Transactional
    public void getObservacion() throws Exception {
        // Initialize the database
        observacionRepository.saveAndFlush(observacion);

        // Get the observacion
        restObservacionMockMvc.perform(get("/api/observacions/{id}", observacion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(observacion.getId().intValue()))
            .andExpect(jsonPath("$.idObservacion").value(DEFAULT_ID_OBSERVACION.toString()))
            .andExpect(jsonPath("$.descripcionObservacion").value(DEFAULT_DESCRIPCION_OBSERVACION.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingObservacion() throws Exception {
        // Get the observacion
        restObservacionMockMvc.perform(get("/api/observacions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateObservacion() throws Exception {
        // Initialize the database
        observacionRepository.saveAndFlush(observacion);

        int databaseSizeBeforeUpdate = observacionRepository.findAll().size();

        // Update the observacion
        Observacion updatedObservacion = observacionRepository.findById(observacion.getId()).get();
        // Disconnect from session so that the updates on updatedObservacion are not directly saved in db
        em.detach(updatedObservacion);
        updatedObservacion
            .idObservacion(UPDATED_ID_OBSERVACION)
            .descripcionObservacion(UPDATED_DESCRIPCION_OBSERVACION);
        ObservacionDTO observacionDTO = observacionMapper.toDto(updatedObservacion);

        restObservacionMockMvc.perform(put("/api/observacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(observacionDTO)))
            .andExpect(status().isOk());

        // Validate the Observacion in the database
        List<Observacion> observacionList = observacionRepository.findAll();
        assertThat(observacionList).hasSize(databaseSizeBeforeUpdate);
        Observacion testObservacion = observacionList.get(observacionList.size() - 1);
        assertThat(testObservacion.getIdObservacion()).isEqualTo(UPDATED_ID_OBSERVACION);
        assertThat(testObservacion.getDescripcionObservacion()).isEqualTo(UPDATED_DESCRIPCION_OBSERVACION);
    }

    @Test
    @Transactional
    public void updateNonExistingObservacion() throws Exception {
        int databaseSizeBeforeUpdate = observacionRepository.findAll().size();

        // Create the Observacion
        ObservacionDTO observacionDTO = observacionMapper.toDto(observacion);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restObservacionMockMvc.perform(put("/api/observacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(observacionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Observacion in the database
        List<Observacion> observacionList = observacionRepository.findAll();
        assertThat(observacionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteObservacion() throws Exception {
        // Initialize the database
        observacionRepository.saveAndFlush(observacion);

        int databaseSizeBeforeDelete = observacionRepository.findAll().size();

        // Get the observacion
        restObservacionMockMvc.perform(delete("/api/observacions/{id}", observacion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Observacion> observacionList = observacionRepository.findAll();
        assertThat(observacionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Observacion.class);
        Observacion observacion1 = new Observacion();
        observacion1.setId(1L);
        Observacion observacion2 = new Observacion();
        observacion2.setId(observacion1.getId());
        assertThat(observacion1).isEqualTo(observacion2);
        observacion2.setId(2L);
        assertThat(observacion1).isNotEqualTo(observacion2);
        observacion1.setId(null);
        assertThat(observacion1).isNotEqualTo(observacion2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ObservacionDTO.class);
        ObservacionDTO observacionDTO1 = new ObservacionDTO();
        observacionDTO1.setId(1L);
        ObservacionDTO observacionDTO2 = new ObservacionDTO();
        assertThat(observacionDTO1).isNotEqualTo(observacionDTO2);
        observacionDTO2.setId(observacionDTO1.getId());
        assertThat(observacionDTO1).isEqualTo(observacionDTO2);
        observacionDTO2.setId(2L);
        assertThat(observacionDTO1).isNotEqualTo(observacionDTO2);
        observacionDTO1.setId(null);
        assertThat(observacionDTO1).isNotEqualTo(observacionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(observacionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(observacionMapper.fromId(null)).isNull();
    }
}
