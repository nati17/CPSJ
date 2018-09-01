package com.cpsj.web.rest;

import com.cpsj.CpsjApp;

import com.cpsj.domain.Digitalizacion;
import com.cpsj.repository.DigitalizacionRepository;
import com.cpsj.service.DigitalizacionService;
import com.cpsj.service.dto.DigitalizacionDTO;
import com.cpsj.service.mapper.DigitalizacionMapper;
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
 * Test class for the DigitalizacionResource REST controller.
 *
 * @see DigitalizacionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CpsjApp.class)
public class DigitalizacionResourceIntTest {

    private static final String DEFAULT_ID_DIGITALIZACION = "AAAAAAAAAA";
    private static final String UPDATED_ID_DIGITALIZACION = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION_DIGITALIZACION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION_DIGITALIZACION = "BBBBBBBBBB";

    private static final String DEFAULT_ELEMENTO_DIGITALIZACION = "AAAAAAAAAA";
    private static final String UPDATED_ELEMENTO_DIGITALIZACION = "BBBBBBBBBB";

    @Autowired
    private DigitalizacionRepository digitalizacionRepository;


    @Autowired
    private DigitalizacionMapper digitalizacionMapper;
    

    @Autowired
    private DigitalizacionService digitalizacionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDigitalizacionMockMvc;

    private Digitalizacion digitalizacion;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DigitalizacionResource digitalizacionResource = new DigitalizacionResource(digitalizacionService);
        this.restDigitalizacionMockMvc = MockMvcBuilders.standaloneSetup(digitalizacionResource)
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
    public static Digitalizacion createEntity(EntityManager em) {
        Digitalizacion digitalizacion = new Digitalizacion()
            .idDigitalizacion(DEFAULT_ID_DIGITALIZACION)
            .descripcionDigitalizacion(DEFAULT_DESCRIPCION_DIGITALIZACION)
            .elementoDigitalizacion(DEFAULT_ELEMENTO_DIGITALIZACION);
        return digitalizacion;
    }

    @Before
    public void initTest() {
        digitalizacion = createEntity(em);
    }

    @Test
    @Transactional
    public void createDigitalizacion() throws Exception {
        int databaseSizeBeforeCreate = digitalizacionRepository.findAll().size();

        // Create the Digitalizacion
        DigitalizacionDTO digitalizacionDTO = digitalizacionMapper.toDto(digitalizacion);
        restDigitalizacionMockMvc.perform(post("/api/digitalizacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(digitalizacionDTO)))
            .andExpect(status().isCreated());

        // Validate the Digitalizacion in the database
        List<Digitalizacion> digitalizacionList = digitalizacionRepository.findAll();
        assertThat(digitalizacionList).hasSize(databaseSizeBeforeCreate + 1);
        Digitalizacion testDigitalizacion = digitalizacionList.get(digitalizacionList.size() - 1);
        assertThat(testDigitalizacion.getIdDigitalizacion()).isEqualTo(DEFAULT_ID_DIGITALIZACION);
        assertThat(testDigitalizacion.getDescripcionDigitalizacion()).isEqualTo(DEFAULT_DESCRIPCION_DIGITALIZACION);
        assertThat(testDigitalizacion.getElementoDigitalizacion()).isEqualTo(DEFAULT_ELEMENTO_DIGITALIZACION);
    }

    @Test
    @Transactional
    public void createDigitalizacionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = digitalizacionRepository.findAll().size();

        // Create the Digitalizacion with an existing ID
        digitalizacion.setId(1L);
        DigitalizacionDTO digitalizacionDTO = digitalizacionMapper.toDto(digitalizacion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDigitalizacionMockMvc.perform(post("/api/digitalizacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(digitalizacionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Digitalizacion in the database
        List<Digitalizacion> digitalizacionList = digitalizacionRepository.findAll();
        assertThat(digitalizacionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDigitalizacions() throws Exception {
        // Initialize the database
        digitalizacionRepository.saveAndFlush(digitalizacion);

        // Get all the digitalizacionList
        restDigitalizacionMockMvc.perform(get("/api/digitalizacions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(digitalizacion.getId().intValue())))
            .andExpect(jsonPath("$.[*].idDigitalizacion").value(hasItem(DEFAULT_ID_DIGITALIZACION.toString())))
            .andExpect(jsonPath("$.[*].descripcionDigitalizacion").value(hasItem(DEFAULT_DESCRIPCION_DIGITALIZACION.toString())))
            .andExpect(jsonPath("$.[*].elementoDigitalizacion").value(hasItem(DEFAULT_ELEMENTO_DIGITALIZACION.toString())));
    }
    

    @Test
    @Transactional
    public void getDigitalizacion() throws Exception {
        // Initialize the database
        digitalizacionRepository.saveAndFlush(digitalizacion);

        // Get the digitalizacion
        restDigitalizacionMockMvc.perform(get("/api/digitalizacions/{id}", digitalizacion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(digitalizacion.getId().intValue()))
            .andExpect(jsonPath("$.idDigitalizacion").value(DEFAULT_ID_DIGITALIZACION.toString()))
            .andExpect(jsonPath("$.descripcionDigitalizacion").value(DEFAULT_DESCRIPCION_DIGITALIZACION.toString()))
            .andExpect(jsonPath("$.elementoDigitalizacion").value(DEFAULT_ELEMENTO_DIGITALIZACION.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingDigitalizacion() throws Exception {
        // Get the digitalizacion
        restDigitalizacionMockMvc.perform(get("/api/digitalizacions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDigitalizacion() throws Exception {
        // Initialize the database
        digitalizacionRepository.saveAndFlush(digitalizacion);

        int databaseSizeBeforeUpdate = digitalizacionRepository.findAll().size();

        // Update the digitalizacion
        Digitalizacion updatedDigitalizacion = digitalizacionRepository.findById(digitalizacion.getId()).get();
        // Disconnect from session so that the updates on updatedDigitalizacion are not directly saved in db
        em.detach(updatedDigitalizacion);
        updatedDigitalizacion
            .idDigitalizacion(UPDATED_ID_DIGITALIZACION)
            .descripcionDigitalizacion(UPDATED_DESCRIPCION_DIGITALIZACION)
            .elementoDigitalizacion(UPDATED_ELEMENTO_DIGITALIZACION);
        DigitalizacionDTO digitalizacionDTO = digitalizacionMapper.toDto(updatedDigitalizacion);

        restDigitalizacionMockMvc.perform(put("/api/digitalizacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(digitalizacionDTO)))
            .andExpect(status().isOk());

        // Validate the Digitalizacion in the database
        List<Digitalizacion> digitalizacionList = digitalizacionRepository.findAll();
        assertThat(digitalizacionList).hasSize(databaseSizeBeforeUpdate);
        Digitalizacion testDigitalizacion = digitalizacionList.get(digitalizacionList.size() - 1);
        assertThat(testDigitalizacion.getIdDigitalizacion()).isEqualTo(UPDATED_ID_DIGITALIZACION);
        assertThat(testDigitalizacion.getDescripcionDigitalizacion()).isEqualTo(UPDATED_DESCRIPCION_DIGITALIZACION);
        assertThat(testDigitalizacion.getElementoDigitalizacion()).isEqualTo(UPDATED_ELEMENTO_DIGITALIZACION);
    }

    @Test
    @Transactional
    public void updateNonExistingDigitalizacion() throws Exception {
        int databaseSizeBeforeUpdate = digitalizacionRepository.findAll().size();

        // Create the Digitalizacion
        DigitalizacionDTO digitalizacionDTO = digitalizacionMapper.toDto(digitalizacion);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDigitalizacionMockMvc.perform(put("/api/digitalizacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(digitalizacionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Digitalizacion in the database
        List<Digitalizacion> digitalizacionList = digitalizacionRepository.findAll();
        assertThat(digitalizacionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDigitalizacion() throws Exception {
        // Initialize the database
        digitalizacionRepository.saveAndFlush(digitalizacion);

        int databaseSizeBeforeDelete = digitalizacionRepository.findAll().size();

        // Get the digitalizacion
        restDigitalizacionMockMvc.perform(delete("/api/digitalizacions/{id}", digitalizacion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Digitalizacion> digitalizacionList = digitalizacionRepository.findAll();
        assertThat(digitalizacionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Digitalizacion.class);
        Digitalizacion digitalizacion1 = new Digitalizacion();
        digitalizacion1.setId(1L);
        Digitalizacion digitalizacion2 = new Digitalizacion();
        digitalizacion2.setId(digitalizacion1.getId());
        assertThat(digitalizacion1).isEqualTo(digitalizacion2);
        digitalizacion2.setId(2L);
        assertThat(digitalizacion1).isNotEqualTo(digitalizacion2);
        digitalizacion1.setId(null);
        assertThat(digitalizacion1).isNotEqualTo(digitalizacion2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DigitalizacionDTO.class);
        DigitalizacionDTO digitalizacionDTO1 = new DigitalizacionDTO();
        digitalizacionDTO1.setId(1L);
        DigitalizacionDTO digitalizacionDTO2 = new DigitalizacionDTO();
        assertThat(digitalizacionDTO1).isNotEqualTo(digitalizacionDTO2);
        digitalizacionDTO2.setId(digitalizacionDTO1.getId());
        assertThat(digitalizacionDTO1).isEqualTo(digitalizacionDTO2);
        digitalizacionDTO2.setId(2L);
        assertThat(digitalizacionDTO1).isNotEqualTo(digitalizacionDTO2);
        digitalizacionDTO1.setId(null);
        assertThat(digitalizacionDTO1).isNotEqualTo(digitalizacionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(digitalizacionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(digitalizacionMapper.fromId(null)).isNull();
    }
}
