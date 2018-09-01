package com.cpsj.web.rest;

import com.cpsj.CpsjApp;

import com.cpsj.domain.Sucursal;
import com.cpsj.repository.SucursalRepository;
import com.cpsj.service.SucursalService;
import com.cpsj.service.dto.SucursalDTO;
import com.cpsj.service.mapper.SucursalMapper;
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
 * Test class for the SucursalResource REST controller.
 *
 * @see SucursalResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CpsjApp.class)
public class SucursalResourceIntTest {

    private static final String DEFAULT_NOMBRE_SUCURSAL = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_SUCURSAL = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMERO_SUCURSAL = 1;
    private static final Integer UPDATED_NUMERO_SUCURSAL = 2;

    @Autowired
    private SucursalRepository sucursalRepository;


    @Autowired
    private SucursalMapper sucursalMapper;
    

    @Autowired
    private SucursalService sucursalService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSucursalMockMvc;

    private Sucursal sucursal;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SucursalResource sucursalResource = new SucursalResource(sucursalService);
        this.restSucursalMockMvc = MockMvcBuilders.standaloneSetup(sucursalResource)
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
    public static Sucursal createEntity(EntityManager em) {
        Sucursal sucursal = new Sucursal()
            .nombreSucursal(DEFAULT_NOMBRE_SUCURSAL)
            .numeroSucursal(DEFAULT_NUMERO_SUCURSAL);
        return sucursal;
    }

    @Before
    public void initTest() {
        sucursal = createEntity(em);
    }

    @Test
    @Transactional
    public void createSucursal() throws Exception {
        int databaseSizeBeforeCreate = sucursalRepository.findAll().size();

        // Create the Sucursal
        SucursalDTO sucursalDTO = sucursalMapper.toDto(sucursal);
        restSucursalMockMvc.perform(post("/api/sucursals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sucursalDTO)))
            .andExpect(status().isCreated());

        // Validate the Sucursal in the database
        List<Sucursal> sucursalList = sucursalRepository.findAll();
        assertThat(sucursalList).hasSize(databaseSizeBeforeCreate + 1);
        Sucursal testSucursal = sucursalList.get(sucursalList.size() - 1);
        assertThat(testSucursal.getNombreSucursal()).isEqualTo(DEFAULT_NOMBRE_SUCURSAL);
        assertThat(testSucursal.getNumeroSucursal()).isEqualTo(DEFAULT_NUMERO_SUCURSAL);
    }

    @Test
    @Transactional
    public void createSucursalWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sucursalRepository.findAll().size();

        // Create the Sucursal with an existing ID
        sucursal.setId(1L);
        SucursalDTO sucursalDTO = sucursalMapper.toDto(sucursal);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSucursalMockMvc.perform(post("/api/sucursals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sucursalDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Sucursal in the database
        List<Sucursal> sucursalList = sucursalRepository.findAll();
        assertThat(sucursalList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSucursals() throws Exception {
        // Initialize the database
        sucursalRepository.saveAndFlush(sucursal);

        // Get all the sucursalList
        restSucursalMockMvc.perform(get("/api/sucursals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sucursal.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombreSucursal").value(hasItem(DEFAULT_NOMBRE_SUCURSAL.toString())))
            .andExpect(jsonPath("$.[*].numeroSucursal").value(hasItem(DEFAULT_NUMERO_SUCURSAL)));
    }
    

    @Test
    @Transactional
    public void getSucursal() throws Exception {
        // Initialize the database
        sucursalRepository.saveAndFlush(sucursal);

        // Get the sucursal
        restSucursalMockMvc.perform(get("/api/sucursals/{id}", sucursal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sucursal.getId().intValue()))
            .andExpect(jsonPath("$.nombreSucursal").value(DEFAULT_NOMBRE_SUCURSAL.toString()))
            .andExpect(jsonPath("$.numeroSucursal").value(DEFAULT_NUMERO_SUCURSAL));
    }
    @Test
    @Transactional
    public void getNonExistingSucursal() throws Exception {
        // Get the sucursal
        restSucursalMockMvc.perform(get("/api/sucursals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSucursal() throws Exception {
        // Initialize the database
        sucursalRepository.saveAndFlush(sucursal);

        int databaseSizeBeforeUpdate = sucursalRepository.findAll().size();

        // Update the sucursal
        Sucursal updatedSucursal = sucursalRepository.findById(sucursal.getId()).get();
        // Disconnect from session so that the updates on updatedSucursal are not directly saved in db
        em.detach(updatedSucursal);
        updatedSucursal
            .nombreSucursal(UPDATED_NOMBRE_SUCURSAL)
            .numeroSucursal(UPDATED_NUMERO_SUCURSAL);
        SucursalDTO sucursalDTO = sucursalMapper.toDto(updatedSucursal);

        restSucursalMockMvc.perform(put("/api/sucursals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sucursalDTO)))
            .andExpect(status().isOk());

        // Validate the Sucursal in the database
        List<Sucursal> sucursalList = sucursalRepository.findAll();
        assertThat(sucursalList).hasSize(databaseSizeBeforeUpdate);
        Sucursal testSucursal = sucursalList.get(sucursalList.size() - 1);
        assertThat(testSucursal.getNombreSucursal()).isEqualTo(UPDATED_NOMBRE_SUCURSAL);
        assertThat(testSucursal.getNumeroSucursal()).isEqualTo(UPDATED_NUMERO_SUCURSAL);
    }

    @Test
    @Transactional
    public void updateNonExistingSucursal() throws Exception {
        int databaseSizeBeforeUpdate = sucursalRepository.findAll().size();

        // Create the Sucursal
        SucursalDTO sucursalDTO = sucursalMapper.toDto(sucursal);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSucursalMockMvc.perform(put("/api/sucursals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sucursalDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Sucursal in the database
        List<Sucursal> sucursalList = sucursalRepository.findAll();
        assertThat(sucursalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSucursal() throws Exception {
        // Initialize the database
        sucursalRepository.saveAndFlush(sucursal);

        int databaseSizeBeforeDelete = sucursalRepository.findAll().size();

        // Get the sucursal
        restSucursalMockMvc.perform(delete("/api/sucursals/{id}", sucursal.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Sucursal> sucursalList = sucursalRepository.findAll();
        assertThat(sucursalList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Sucursal.class);
        Sucursal sucursal1 = new Sucursal();
        sucursal1.setId(1L);
        Sucursal sucursal2 = new Sucursal();
        sucursal2.setId(sucursal1.getId());
        assertThat(sucursal1).isEqualTo(sucursal2);
        sucursal2.setId(2L);
        assertThat(sucursal1).isNotEqualTo(sucursal2);
        sucursal1.setId(null);
        assertThat(sucursal1).isNotEqualTo(sucursal2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SucursalDTO.class);
        SucursalDTO sucursalDTO1 = new SucursalDTO();
        sucursalDTO1.setId(1L);
        SucursalDTO sucursalDTO2 = new SucursalDTO();
        assertThat(sucursalDTO1).isNotEqualTo(sucursalDTO2);
        sucursalDTO2.setId(sucursalDTO1.getId());
        assertThat(sucursalDTO1).isEqualTo(sucursalDTO2);
        sucursalDTO2.setId(2L);
        assertThat(sucursalDTO1).isNotEqualTo(sucursalDTO2);
        sucursalDTO1.setId(null);
        assertThat(sucursalDTO1).isNotEqualTo(sucursalDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(sucursalMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(sucursalMapper.fromId(null)).isNull();
    }
}
