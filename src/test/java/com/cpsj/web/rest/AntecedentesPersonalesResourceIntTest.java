package com.cpsj.web.rest;

import com.cpsj.CpsjApp;

import com.cpsj.domain.AntecedentesPersonales;
import com.cpsj.repository.AntecedentesPersonalesRepository;
import com.cpsj.service.AntecedentesPersonalesService;
import com.cpsj.service.dto.AntecedentesPersonalesDTO;
import com.cpsj.service.mapper.AntecedentesPersonalesMapper;
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

import com.cpsj.domain.enumeration.EnfermedadesEnum;
import com.cpsj.domain.enumeration.AlergiasEnum;
import com.cpsj.domain.enumeration.IntoleranciasEnum;
import com.cpsj.domain.enumeration.RegimenesEnum;
import com.cpsj.domain.enumeration.BebidasEnum;
import com.cpsj.domain.enumeration.EjerciciosEnum;
/**
 * Test class for the AntecedentesPersonalesResource REST controller.
 *
 * @see AntecedentesPersonalesResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CpsjApp.class)
public class AntecedentesPersonalesResourceIntTest {

    private static final EnfermedadesEnum DEFAULT_ENFERMEDAD = EnfermedadesEnum.ASMA;
    private static final EnfermedadesEnum UPDATED_ENFERMEDAD = EnfermedadesEnum.DIABETES;

    private static final AlergiasEnum DEFAULT_ALERGIA = AlergiasEnum.SALICILATOS;
    private static final AlergiasEnum UPDATED_ALERGIA = AlergiasEnum.PIRAZOLONAS;

    private static final IntoleranciasEnum DEFAULT_INTOLERANCIA = IntoleranciasEnum.GLUTEN;
    private static final IntoleranciasEnum UPDATED_INTOLERANCIA = IntoleranciasEnum.LACTOSA;

    private static final RegimenesEnum DEFAULT_REGIMEN = RegimenesEnum.NO;
    private static final RegimenesEnum UPDATED_REGIMEN = RegimenesEnum.HIPOGLUCIDOS;

    private static final BebidasEnum DEFAULT_BEBIDA = BebidasEnum.NO;
    private static final BebidasEnum UPDATED_BEBIDA = BebidasEnum.SISIEMPRE;

    private static final EjerciciosEnum DEFAULT_EJERCICIO = EjerciciosEnum.NO;
    private static final EjerciciosEnum UPDATED_EJERCICIO = EjerciciosEnum.UNDIA;

    private static final Boolean DEFAULT_TABACO = false;
    private static final Boolean UPDATED_TABACO = true;

    private static final Boolean DEFAULT_TECAFE = false;
    private static final Boolean UPDATED_TECAFE = true;

    @Autowired
    private AntecedentesPersonalesRepository antecedentesPersonalesRepository;


    @Autowired
    private AntecedentesPersonalesMapper antecedentesPersonalesMapper;
    

    @Autowired
    private AntecedentesPersonalesService antecedentesPersonalesService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAntecedentesPersonalesMockMvc;

    private AntecedentesPersonales antecedentesPersonales;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AntecedentesPersonalesResource antecedentesPersonalesResource = new AntecedentesPersonalesResource(antecedentesPersonalesService);
        this.restAntecedentesPersonalesMockMvc = MockMvcBuilders.standaloneSetup(antecedentesPersonalesResource)
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
    public static AntecedentesPersonales createEntity(EntityManager em) {
        AntecedentesPersonales antecedentesPersonales = new AntecedentesPersonales()
            .enfermedad(DEFAULT_ENFERMEDAD)
            .alergia(DEFAULT_ALERGIA)
            .intolerancia(DEFAULT_INTOLERANCIA)
            .regimen(DEFAULT_REGIMEN)
            .bebida(DEFAULT_BEBIDA)
            .ejercicio(DEFAULT_EJERCICIO)
            .tabaco(DEFAULT_TABACO)
            .tecafe(DEFAULT_TECAFE);
        return antecedentesPersonales;
    }

    @Before
    public void initTest() {
        antecedentesPersonales = createEntity(em);
    }

    @Test
    @Transactional
    public void createAntecedentesPersonales() throws Exception {
        int databaseSizeBeforeCreate = antecedentesPersonalesRepository.findAll().size();

        // Create the AntecedentesPersonales
        AntecedentesPersonalesDTO antecedentesPersonalesDTO = antecedentesPersonalesMapper.toDto(antecedentesPersonales);
        restAntecedentesPersonalesMockMvc.perform(post("/api/antecedentes-personales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(antecedentesPersonalesDTO)))
            .andExpect(status().isCreated());

        // Validate the AntecedentesPersonales in the database
        List<AntecedentesPersonales> antecedentesPersonalesList = antecedentesPersonalesRepository.findAll();
        assertThat(antecedentesPersonalesList).hasSize(databaseSizeBeforeCreate + 1);
        AntecedentesPersonales testAntecedentesPersonales = antecedentesPersonalesList.get(antecedentesPersonalesList.size() - 1);
        assertThat(testAntecedentesPersonales.getEnfermedad()).isEqualTo(DEFAULT_ENFERMEDAD);
        assertThat(testAntecedentesPersonales.getAlergia()).isEqualTo(DEFAULT_ALERGIA);
        assertThat(testAntecedentesPersonales.getIntolerancia()).isEqualTo(DEFAULT_INTOLERANCIA);
        assertThat(testAntecedentesPersonales.getRegimen()).isEqualTo(DEFAULT_REGIMEN);
        assertThat(testAntecedentesPersonales.getBebida()).isEqualTo(DEFAULT_BEBIDA);
        assertThat(testAntecedentesPersonales.getEjercicio()).isEqualTo(DEFAULT_EJERCICIO);
        assertThat(testAntecedentesPersonales.isTabaco()).isEqualTo(DEFAULT_TABACO);
        assertThat(testAntecedentesPersonales.isTecafe()).isEqualTo(DEFAULT_TECAFE);
    }

    @Test
    @Transactional
    public void createAntecedentesPersonalesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = antecedentesPersonalesRepository.findAll().size();

        // Create the AntecedentesPersonales with an existing ID
        antecedentesPersonales.setId(1L);
        AntecedentesPersonalesDTO antecedentesPersonalesDTO = antecedentesPersonalesMapper.toDto(antecedentesPersonales);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAntecedentesPersonalesMockMvc.perform(post("/api/antecedentes-personales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(antecedentesPersonalesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AntecedentesPersonales in the database
        List<AntecedentesPersonales> antecedentesPersonalesList = antecedentesPersonalesRepository.findAll();
        assertThat(antecedentesPersonalesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkEnfermedadIsRequired() throws Exception {
        int databaseSizeBeforeTest = antecedentesPersonalesRepository.findAll().size();
        // set the field null
        antecedentesPersonales.setEnfermedad(null);

        // Create the AntecedentesPersonales, which fails.
        AntecedentesPersonalesDTO antecedentesPersonalesDTO = antecedentesPersonalesMapper.toDto(antecedentesPersonales);

        restAntecedentesPersonalesMockMvc.perform(post("/api/antecedentes-personales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(antecedentesPersonalesDTO)))
            .andExpect(status().isBadRequest());

        List<AntecedentesPersonales> antecedentesPersonalesList = antecedentesPersonalesRepository.findAll();
        assertThat(antecedentesPersonalesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAlergiaIsRequired() throws Exception {
        int databaseSizeBeforeTest = antecedentesPersonalesRepository.findAll().size();
        // set the field null
        antecedentesPersonales.setAlergia(null);

        // Create the AntecedentesPersonales, which fails.
        AntecedentesPersonalesDTO antecedentesPersonalesDTO = antecedentesPersonalesMapper.toDto(antecedentesPersonales);

        restAntecedentesPersonalesMockMvc.perform(post("/api/antecedentes-personales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(antecedentesPersonalesDTO)))
            .andExpect(status().isBadRequest());

        List<AntecedentesPersonales> antecedentesPersonalesList = antecedentesPersonalesRepository.findAll();
        assertThat(antecedentesPersonalesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIntoleranciaIsRequired() throws Exception {
        int databaseSizeBeforeTest = antecedentesPersonalesRepository.findAll().size();
        // set the field null
        antecedentesPersonales.setIntolerancia(null);

        // Create the AntecedentesPersonales, which fails.
        AntecedentesPersonalesDTO antecedentesPersonalesDTO = antecedentesPersonalesMapper.toDto(antecedentesPersonales);

        restAntecedentesPersonalesMockMvc.perform(post("/api/antecedentes-personales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(antecedentesPersonalesDTO)))
            .andExpect(status().isBadRequest());

        List<AntecedentesPersonales> antecedentesPersonalesList = antecedentesPersonalesRepository.findAll();
        assertThat(antecedentesPersonalesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRegimenIsRequired() throws Exception {
        int databaseSizeBeforeTest = antecedentesPersonalesRepository.findAll().size();
        // set the field null
        antecedentesPersonales.setRegimen(null);

        // Create the AntecedentesPersonales, which fails.
        AntecedentesPersonalesDTO antecedentesPersonalesDTO = antecedentesPersonalesMapper.toDto(antecedentesPersonales);

        restAntecedentesPersonalesMockMvc.perform(post("/api/antecedentes-personales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(antecedentesPersonalesDTO)))
            .andExpect(status().isBadRequest());

        List<AntecedentesPersonales> antecedentesPersonalesList = antecedentesPersonalesRepository.findAll();
        assertThat(antecedentesPersonalesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBebidaIsRequired() throws Exception {
        int databaseSizeBeforeTest = antecedentesPersonalesRepository.findAll().size();
        // set the field null
        antecedentesPersonales.setBebida(null);

        // Create the AntecedentesPersonales, which fails.
        AntecedentesPersonalesDTO antecedentesPersonalesDTO = antecedentesPersonalesMapper.toDto(antecedentesPersonales);

        restAntecedentesPersonalesMockMvc.perform(post("/api/antecedentes-personales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(antecedentesPersonalesDTO)))
            .andExpect(status().isBadRequest());

        List<AntecedentesPersonales> antecedentesPersonalesList = antecedentesPersonalesRepository.findAll();
        assertThat(antecedentesPersonalesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEjercicioIsRequired() throws Exception {
        int databaseSizeBeforeTest = antecedentesPersonalesRepository.findAll().size();
        // set the field null
        antecedentesPersonales.setEjercicio(null);

        // Create the AntecedentesPersonales, which fails.
        AntecedentesPersonalesDTO antecedentesPersonalesDTO = antecedentesPersonalesMapper.toDto(antecedentesPersonales);

        restAntecedentesPersonalesMockMvc.perform(post("/api/antecedentes-personales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(antecedentesPersonalesDTO)))
            .andExpect(status().isBadRequest());

        List<AntecedentesPersonales> antecedentesPersonalesList = antecedentesPersonalesRepository.findAll();
        assertThat(antecedentesPersonalesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAntecedentesPersonales() throws Exception {
        // Initialize the database
        antecedentesPersonalesRepository.saveAndFlush(antecedentesPersonales);

        // Get all the antecedentesPersonalesList
        restAntecedentesPersonalesMockMvc.perform(get("/api/antecedentes-personales?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(antecedentesPersonales.getId().intValue())))
            .andExpect(jsonPath("$.[*].enfermedad").value(hasItem(DEFAULT_ENFERMEDAD.toString())))
            .andExpect(jsonPath("$.[*].alergia").value(hasItem(DEFAULT_ALERGIA.toString())))
            .andExpect(jsonPath("$.[*].intolerancia").value(hasItem(DEFAULT_INTOLERANCIA.toString())))
            .andExpect(jsonPath("$.[*].regimen").value(hasItem(DEFAULT_REGIMEN.toString())))
            .andExpect(jsonPath("$.[*].bebida").value(hasItem(DEFAULT_BEBIDA.toString())))
            .andExpect(jsonPath("$.[*].ejercicio").value(hasItem(DEFAULT_EJERCICIO.toString())))
            .andExpect(jsonPath("$.[*].tabaco").value(hasItem(DEFAULT_TABACO.booleanValue())))
            .andExpect(jsonPath("$.[*].tecafe").value(hasItem(DEFAULT_TECAFE.booleanValue())));
    }
    

    @Test
    @Transactional
    public void getAntecedentesPersonales() throws Exception {
        // Initialize the database
        antecedentesPersonalesRepository.saveAndFlush(antecedentesPersonales);

        // Get the antecedentesPersonales
        restAntecedentesPersonalesMockMvc.perform(get("/api/antecedentes-personales/{id}", antecedentesPersonales.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(antecedentesPersonales.getId().intValue()))
            .andExpect(jsonPath("$.enfermedad").value(DEFAULT_ENFERMEDAD.toString()))
            .andExpect(jsonPath("$.alergia").value(DEFAULT_ALERGIA.toString()))
            .andExpect(jsonPath("$.intolerancia").value(DEFAULT_INTOLERANCIA.toString()))
            .andExpect(jsonPath("$.regimen").value(DEFAULT_REGIMEN.toString()))
            .andExpect(jsonPath("$.bebida").value(DEFAULT_BEBIDA.toString()))
            .andExpect(jsonPath("$.ejercicio").value(DEFAULT_EJERCICIO.toString()))
            .andExpect(jsonPath("$.tabaco").value(DEFAULT_TABACO.booleanValue()))
            .andExpect(jsonPath("$.tecafe").value(DEFAULT_TECAFE.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingAntecedentesPersonales() throws Exception {
        // Get the antecedentesPersonales
        restAntecedentesPersonalesMockMvc.perform(get("/api/antecedentes-personales/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAntecedentesPersonales() throws Exception {
        // Initialize the database
        antecedentesPersonalesRepository.saveAndFlush(antecedentesPersonales);

        int databaseSizeBeforeUpdate = antecedentesPersonalesRepository.findAll().size();

        // Update the antecedentesPersonales
        AntecedentesPersonales updatedAntecedentesPersonales = antecedentesPersonalesRepository.findById(antecedentesPersonales.getId()).get();
        // Disconnect from session so that the updates on updatedAntecedentesPersonales are not directly saved in db
        em.detach(updatedAntecedentesPersonales);
        updatedAntecedentesPersonales
            .enfermedad(UPDATED_ENFERMEDAD)
            .alergia(UPDATED_ALERGIA)
            .intolerancia(UPDATED_INTOLERANCIA)
            .regimen(UPDATED_REGIMEN)
            .bebida(UPDATED_BEBIDA)
            .ejercicio(UPDATED_EJERCICIO)
            .tabaco(UPDATED_TABACO)
            .tecafe(UPDATED_TECAFE);
        AntecedentesPersonalesDTO antecedentesPersonalesDTO = antecedentesPersonalesMapper.toDto(updatedAntecedentesPersonales);

        restAntecedentesPersonalesMockMvc.perform(put("/api/antecedentes-personales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(antecedentesPersonalesDTO)))
            .andExpect(status().isOk());

        // Validate the AntecedentesPersonales in the database
        List<AntecedentesPersonales> antecedentesPersonalesList = antecedentesPersonalesRepository.findAll();
        assertThat(antecedentesPersonalesList).hasSize(databaseSizeBeforeUpdate);
        AntecedentesPersonales testAntecedentesPersonales = antecedentesPersonalesList.get(antecedentesPersonalesList.size() - 1);
        assertThat(testAntecedentesPersonales.getEnfermedad()).isEqualTo(UPDATED_ENFERMEDAD);
        assertThat(testAntecedentesPersonales.getAlergia()).isEqualTo(UPDATED_ALERGIA);
        assertThat(testAntecedentesPersonales.getIntolerancia()).isEqualTo(UPDATED_INTOLERANCIA);
        assertThat(testAntecedentesPersonales.getRegimen()).isEqualTo(UPDATED_REGIMEN);
        assertThat(testAntecedentesPersonales.getBebida()).isEqualTo(UPDATED_BEBIDA);
        assertThat(testAntecedentesPersonales.getEjercicio()).isEqualTo(UPDATED_EJERCICIO);
        assertThat(testAntecedentesPersonales.isTabaco()).isEqualTo(UPDATED_TABACO);
        assertThat(testAntecedentesPersonales.isTecafe()).isEqualTo(UPDATED_TECAFE);
    }

    @Test
    @Transactional
    public void updateNonExistingAntecedentesPersonales() throws Exception {
        int databaseSizeBeforeUpdate = antecedentesPersonalesRepository.findAll().size();

        // Create the AntecedentesPersonales
        AntecedentesPersonalesDTO antecedentesPersonalesDTO = antecedentesPersonalesMapper.toDto(antecedentesPersonales);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAntecedentesPersonalesMockMvc.perform(put("/api/antecedentes-personales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(antecedentesPersonalesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AntecedentesPersonales in the database
        List<AntecedentesPersonales> antecedentesPersonalesList = antecedentesPersonalesRepository.findAll();
        assertThat(antecedentesPersonalesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAntecedentesPersonales() throws Exception {
        // Initialize the database
        antecedentesPersonalesRepository.saveAndFlush(antecedentesPersonales);

        int databaseSizeBeforeDelete = antecedentesPersonalesRepository.findAll().size();

        // Get the antecedentesPersonales
        restAntecedentesPersonalesMockMvc.perform(delete("/api/antecedentes-personales/{id}", antecedentesPersonales.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AntecedentesPersonales> antecedentesPersonalesList = antecedentesPersonalesRepository.findAll();
        assertThat(antecedentesPersonalesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AntecedentesPersonales.class);
        AntecedentesPersonales antecedentesPersonales1 = new AntecedentesPersonales();
        antecedentesPersonales1.setId(1L);
        AntecedentesPersonales antecedentesPersonales2 = new AntecedentesPersonales();
        antecedentesPersonales2.setId(antecedentesPersonales1.getId());
        assertThat(antecedentesPersonales1).isEqualTo(antecedentesPersonales2);
        antecedentesPersonales2.setId(2L);
        assertThat(antecedentesPersonales1).isNotEqualTo(antecedentesPersonales2);
        antecedentesPersonales1.setId(null);
        assertThat(antecedentesPersonales1).isNotEqualTo(antecedentesPersonales2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AntecedentesPersonalesDTO.class);
        AntecedentesPersonalesDTO antecedentesPersonalesDTO1 = new AntecedentesPersonalesDTO();
        antecedentesPersonalesDTO1.setId(1L);
        AntecedentesPersonalesDTO antecedentesPersonalesDTO2 = new AntecedentesPersonalesDTO();
        assertThat(antecedentesPersonalesDTO1).isNotEqualTo(antecedentesPersonalesDTO2);
        antecedentesPersonalesDTO2.setId(antecedentesPersonalesDTO1.getId());
        assertThat(antecedentesPersonalesDTO1).isEqualTo(antecedentesPersonalesDTO2);
        antecedentesPersonalesDTO2.setId(2L);
        assertThat(antecedentesPersonalesDTO1).isNotEqualTo(antecedentesPersonalesDTO2);
        antecedentesPersonalesDTO1.setId(null);
        assertThat(antecedentesPersonalesDTO1).isNotEqualTo(antecedentesPersonalesDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(antecedentesPersonalesMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(antecedentesPersonalesMapper.fromId(null)).isNull();
    }
}
