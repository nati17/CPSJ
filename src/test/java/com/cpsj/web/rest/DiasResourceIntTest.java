package com.cpsj.web.rest;

import com.cpsj.CpsjApp;

import com.cpsj.domain.Dias;
import com.cpsj.repository.DiasRepository;
import com.cpsj.service.DiasService;
import com.cpsj.service.dto.DiasDTO;
import com.cpsj.service.mapper.DiasMapper;
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

import com.cpsj.domain.enumeration.DiasSemanaEnum;
/**
 * Test class for the DiasResource REST controller.
 *
 * @see DiasResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CpsjApp.class)
public class DiasResourceIntTest {

    private static final DiasSemanaEnum DEFAULT_VALOR = DiasSemanaEnum.LUNES;
    private static final DiasSemanaEnum UPDATED_VALOR = DiasSemanaEnum.MARTES;

    @Autowired
    private DiasRepository diasRepository;


    @Autowired
    private DiasMapper diasMapper;
    

    @Autowired
    private DiasService diasService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDiasMockMvc;

    private Dias dias;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DiasResource diasResource = new DiasResource(diasService);
        this.restDiasMockMvc = MockMvcBuilders.standaloneSetup(diasResource)
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
    public static Dias createEntity(EntityManager em) {
        Dias dias = new Dias()
            .valor(DEFAULT_VALOR);
        return dias;
    }

    @Before
    public void initTest() {
        dias = createEntity(em);
    }

    @Test
    @Transactional
    public void createDias() throws Exception {
        int databaseSizeBeforeCreate = diasRepository.findAll().size();

        // Create the Dias
        DiasDTO diasDTO = diasMapper.toDto(dias);
        restDiasMockMvc.perform(post("/api/dias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diasDTO)))
            .andExpect(status().isCreated());

        // Validate the Dias in the database
        List<Dias> diasList = diasRepository.findAll();
        assertThat(diasList).hasSize(databaseSizeBeforeCreate + 1);
        Dias testDias = diasList.get(diasList.size() - 1);
        assertThat(testDias.getValor()).isEqualTo(DEFAULT_VALOR);
    }

    @Test
    @Transactional
    public void createDiasWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = diasRepository.findAll().size();

        // Create the Dias with an existing ID
        dias.setId(1L);
        DiasDTO diasDTO = diasMapper.toDto(dias);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDiasMockMvc.perform(post("/api/dias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diasDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dias in the database
        List<Dias> diasList = diasRepository.findAll();
        assertThat(diasList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkValorIsRequired() throws Exception {
        int databaseSizeBeforeTest = diasRepository.findAll().size();
        // set the field null
        dias.setValor(null);

        // Create the Dias, which fails.
        DiasDTO diasDTO = diasMapper.toDto(dias);

        restDiasMockMvc.perform(post("/api/dias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diasDTO)))
            .andExpect(status().isBadRequest());

        List<Dias> diasList = diasRepository.findAll();
        assertThat(diasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDias() throws Exception {
        // Initialize the database
        diasRepository.saveAndFlush(dias);

        // Get all the diasList
        restDiasMockMvc.perform(get("/api/dias?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dias.getId().intValue())))
            .andExpect(jsonPath("$.[*].valor").value(hasItem(DEFAULT_VALOR.toString())));
    }
    

    @Test
    @Transactional
    public void getDias() throws Exception {
        // Initialize the database
        diasRepository.saveAndFlush(dias);

        // Get the dias
        restDiasMockMvc.perform(get("/api/dias/{id}", dias.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dias.getId().intValue()))
            .andExpect(jsonPath("$.valor").value(DEFAULT_VALOR.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingDias() throws Exception {
        // Get the dias
        restDiasMockMvc.perform(get("/api/dias/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDias() throws Exception {
        // Initialize the database
        diasRepository.saveAndFlush(dias);

        int databaseSizeBeforeUpdate = diasRepository.findAll().size();

        // Update the dias
        Dias updatedDias = diasRepository.findById(dias.getId()).get();
        // Disconnect from session so that the updates on updatedDias are not directly saved in db
        em.detach(updatedDias);
        updatedDias
            .valor(UPDATED_VALOR);
        DiasDTO diasDTO = diasMapper.toDto(updatedDias);

        restDiasMockMvc.perform(put("/api/dias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diasDTO)))
            .andExpect(status().isOk());

        // Validate the Dias in the database
        List<Dias> diasList = diasRepository.findAll();
        assertThat(diasList).hasSize(databaseSizeBeforeUpdate);
        Dias testDias = diasList.get(diasList.size() - 1);
        assertThat(testDias.getValor()).isEqualTo(UPDATED_VALOR);
    }

    @Test
    @Transactional
    public void updateNonExistingDias() throws Exception {
        int databaseSizeBeforeUpdate = diasRepository.findAll().size();

        // Create the Dias
        DiasDTO diasDTO = diasMapper.toDto(dias);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDiasMockMvc.perform(put("/api/dias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diasDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dias in the database
        List<Dias> diasList = diasRepository.findAll();
        assertThat(diasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDias() throws Exception {
        // Initialize the database
        diasRepository.saveAndFlush(dias);

        int databaseSizeBeforeDelete = diasRepository.findAll().size();

        // Get the dias
        restDiasMockMvc.perform(delete("/api/dias/{id}", dias.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Dias> diasList = diasRepository.findAll();
        assertThat(diasList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Dias.class);
        Dias dias1 = new Dias();
        dias1.setId(1L);
        Dias dias2 = new Dias();
        dias2.setId(dias1.getId());
        assertThat(dias1).isEqualTo(dias2);
        dias2.setId(2L);
        assertThat(dias1).isNotEqualTo(dias2);
        dias1.setId(null);
        assertThat(dias1).isNotEqualTo(dias2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DiasDTO.class);
        DiasDTO diasDTO1 = new DiasDTO();
        diasDTO1.setId(1L);
        DiasDTO diasDTO2 = new DiasDTO();
        assertThat(diasDTO1).isNotEqualTo(diasDTO2);
        diasDTO2.setId(diasDTO1.getId());
        assertThat(diasDTO1).isEqualTo(diasDTO2);
        diasDTO2.setId(2L);
        assertThat(diasDTO1).isNotEqualTo(diasDTO2);
        diasDTO1.setId(null);
        assertThat(diasDTO1).isNotEqualTo(diasDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(diasMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(diasMapper.fromId(null)).isNull();
    }
}
