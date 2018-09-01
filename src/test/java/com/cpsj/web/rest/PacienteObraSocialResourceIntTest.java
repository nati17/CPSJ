package com.cpsj.web.rest;

import com.cpsj.CpsjApp;

import com.cpsj.domain.PacienteObraSocial;
import com.cpsj.repository.PacienteObraSocialRepository;
import com.cpsj.service.PacienteObraSocialService;
import com.cpsj.service.dto.PacienteObraSocialDTO;
import com.cpsj.service.mapper.PacienteObraSocialMapper;
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
 * Test class for the PacienteObraSocialResource REST controller.
 *
 * @see PacienteObraSocialResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CpsjApp.class)
public class PacienteObraSocialResourceIntTest {

    private static final String DEFAULT_ID_PACIENTE = "AAAAAAAAAA";
    private static final String UPDATED_ID_PACIENTE = "BBBBBBBBBB";

    private static final String DEFAULT_ID_O_SOCIAL = "AAAAAAAAAA";
    private static final String UPDATED_ID_O_SOCIAL = "BBBBBBBBBB";

    private static final String DEFAULT_NRO_AFILIADO = "AAAAAAAAAA";
    private static final String UPDATED_NRO_AFILIADO = "BBBBBBBBBB";

    private static final String DEFAULT_PLAN_AFILIADO = "AAAAAAAAAA";
    private static final String UPDATED_PLAN_AFILIADO = "BBBBBBBBBB";

    private static final String DEFAULT_VIGENCIA = "AAAAAAAAAA";
    private static final String UPDATED_VIGENCIA = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ESTADO = false;
    private static final Boolean UPDATED_ESTADO = true;

    @Autowired
    private PacienteObraSocialRepository pacienteObraSocialRepository;


    @Autowired
    private PacienteObraSocialMapper pacienteObraSocialMapper;
    

    @Autowired
    private PacienteObraSocialService pacienteObraSocialService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPacienteObraSocialMockMvc;

    private PacienteObraSocial pacienteObraSocial;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PacienteObraSocialResource pacienteObraSocialResource = new PacienteObraSocialResource(pacienteObraSocialService);
        this.restPacienteObraSocialMockMvc = MockMvcBuilders.standaloneSetup(pacienteObraSocialResource)
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
    public static PacienteObraSocial createEntity(EntityManager em) {
        PacienteObraSocial pacienteObraSocial = new PacienteObraSocial()
            .idPaciente(DEFAULT_ID_PACIENTE)
            .idOSocial(DEFAULT_ID_O_SOCIAL)
            .nroAfiliado(DEFAULT_NRO_AFILIADO)
            .planAfiliado(DEFAULT_PLAN_AFILIADO)
            .vigencia(DEFAULT_VIGENCIA)
            .estado(DEFAULT_ESTADO);
        return pacienteObraSocial;
    }

    @Before
    public void initTest() {
        pacienteObraSocial = createEntity(em);
    }

    @Test
    @Transactional
    public void createPacienteObraSocial() throws Exception {
        int databaseSizeBeforeCreate = pacienteObraSocialRepository.findAll().size();

        // Create the PacienteObraSocial
        PacienteObraSocialDTO pacienteObraSocialDTO = pacienteObraSocialMapper.toDto(pacienteObraSocial);
        restPacienteObraSocialMockMvc.perform(post("/api/paciente-obra-socials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pacienteObraSocialDTO)))
            .andExpect(status().isCreated());

        // Validate the PacienteObraSocial in the database
        List<PacienteObraSocial> pacienteObraSocialList = pacienteObraSocialRepository.findAll();
        assertThat(pacienteObraSocialList).hasSize(databaseSizeBeforeCreate + 1);
        PacienteObraSocial testPacienteObraSocial = pacienteObraSocialList.get(pacienteObraSocialList.size() - 1);
        assertThat(testPacienteObraSocial.getIdPaciente()).isEqualTo(DEFAULT_ID_PACIENTE);
        assertThat(testPacienteObraSocial.getIdOSocial()).isEqualTo(DEFAULT_ID_O_SOCIAL);
        assertThat(testPacienteObraSocial.getNroAfiliado()).isEqualTo(DEFAULT_NRO_AFILIADO);
        assertThat(testPacienteObraSocial.getPlanAfiliado()).isEqualTo(DEFAULT_PLAN_AFILIADO);
        assertThat(testPacienteObraSocial.getVigencia()).isEqualTo(DEFAULT_VIGENCIA);
        assertThat(testPacienteObraSocial.isEstado()).isEqualTo(DEFAULT_ESTADO);
    }

    @Test
    @Transactional
    public void createPacienteObraSocialWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pacienteObraSocialRepository.findAll().size();

        // Create the PacienteObraSocial with an existing ID
        pacienteObraSocial.setId(1L);
        PacienteObraSocialDTO pacienteObraSocialDTO = pacienteObraSocialMapper.toDto(pacienteObraSocial);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPacienteObraSocialMockMvc.perform(post("/api/paciente-obra-socials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pacienteObraSocialDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PacienteObraSocial in the database
        List<PacienteObraSocial> pacienteObraSocialList = pacienteObraSocialRepository.findAll();
        assertThat(pacienteObraSocialList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPacienteObraSocials() throws Exception {
        // Initialize the database
        pacienteObraSocialRepository.saveAndFlush(pacienteObraSocial);

        // Get all the pacienteObraSocialList
        restPacienteObraSocialMockMvc.perform(get("/api/paciente-obra-socials?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pacienteObraSocial.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPaciente").value(hasItem(DEFAULT_ID_PACIENTE.toString())))
            .andExpect(jsonPath("$.[*].idOSocial").value(hasItem(DEFAULT_ID_O_SOCIAL.toString())))
            .andExpect(jsonPath("$.[*].nroAfiliado").value(hasItem(DEFAULT_NRO_AFILIADO.toString())))
            .andExpect(jsonPath("$.[*].planAfiliado").value(hasItem(DEFAULT_PLAN_AFILIADO.toString())))
            .andExpect(jsonPath("$.[*].vigencia").value(hasItem(DEFAULT_VIGENCIA.toString())))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.booleanValue())));
    }
    

    @Test
    @Transactional
    public void getPacienteObraSocial() throws Exception {
        // Initialize the database
        pacienteObraSocialRepository.saveAndFlush(pacienteObraSocial);

        // Get the pacienteObraSocial
        restPacienteObraSocialMockMvc.perform(get("/api/paciente-obra-socials/{id}", pacienteObraSocial.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pacienteObraSocial.getId().intValue()))
            .andExpect(jsonPath("$.idPaciente").value(DEFAULT_ID_PACIENTE.toString()))
            .andExpect(jsonPath("$.idOSocial").value(DEFAULT_ID_O_SOCIAL.toString()))
            .andExpect(jsonPath("$.nroAfiliado").value(DEFAULT_NRO_AFILIADO.toString()))
            .andExpect(jsonPath("$.planAfiliado").value(DEFAULT_PLAN_AFILIADO.toString()))
            .andExpect(jsonPath("$.vigencia").value(DEFAULT_VIGENCIA.toString()))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPacienteObraSocial() throws Exception {
        // Get the pacienteObraSocial
        restPacienteObraSocialMockMvc.perform(get("/api/paciente-obra-socials/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePacienteObraSocial() throws Exception {
        // Initialize the database
        pacienteObraSocialRepository.saveAndFlush(pacienteObraSocial);

        int databaseSizeBeforeUpdate = pacienteObraSocialRepository.findAll().size();

        // Update the pacienteObraSocial
        PacienteObraSocial updatedPacienteObraSocial = pacienteObraSocialRepository.findById(pacienteObraSocial.getId()).get();
        // Disconnect from session so that the updates on updatedPacienteObraSocial are not directly saved in db
        em.detach(updatedPacienteObraSocial);
        updatedPacienteObraSocial
            .idPaciente(UPDATED_ID_PACIENTE)
            .idOSocial(UPDATED_ID_O_SOCIAL)
            .nroAfiliado(UPDATED_NRO_AFILIADO)
            .planAfiliado(UPDATED_PLAN_AFILIADO)
            .vigencia(UPDATED_VIGENCIA)
            .estado(UPDATED_ESTADO);
        PacienteObraSocialDTO pacienteObraSocialDTO = pacienteObraSocialMapper.toDto(updatedPacienteObraSocial);

        restPacienteObraSocialMockMvc.perform(put("/api/paciente-obra-socials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pacienteObraSocialDTO)))
            .andExpect(status().isOk());

        // Validate the PacienteObraSocial in the database
        List<PacienteObraSocial> pacienteObraSocialList = pacienteObraSocialRepository.findAll();
        assertThat(pacienteObraSocialList).hasSize(databaseSizeBeforeUpdate);
        PacienteObraSocial testPacienteObraSocial = pacienteObraSocialList.get(pacienteObraSocialList.size() - 1);
        assertThat(testPacienteObraSocial.getIdPaciente()).isEqualTo(UPDATED_ID_PACIENTE);
        assertThat(testPacienteObraSocial.getIdOSocial()).isEqualTo(UPDATED_ID_O_SOCIAL);
        assertThat(testPacienteObraSocial.getNroAfiliado()).isEqualTo(UPDATED_NRO_AFILIADO);
        assertThat(testPacienteObraSocial.getPlanAfiliado()).isEqualTo(UPDATED_PLAN_AFILIADO);
        assertThat(testPacienteObraSocial.getVigencia()).isEqualTo(UPDATED_VIGENCIA);
        assertThat(testPacienteObraSocial.isEstado()).isEqualTo(UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void updateNonExistingPacienteObraSocial() throws Exception {
        int databaseSizeBeforeUpdate = pacienteObraSocialRepository.findAll().size();

        // Create the PacienteObraSocial
        PacienteObraSocialDTO pacienteObraSocialDTO = pacienteObraSocialMapper.toDto(pacienteObraSocial);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPacienteObraSocialMockMvc.perform(put("/api/paciente-obra-socials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pacienteObraSocialDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PacienteObraSocial in the database
        List<PacienteObraSocial> pacienteObraSocialList = pacienteObraSocialRepository.findAll();
        assertThat(pacienteObraSocialList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePacienteObraSocial() throws Exception {
        // Initialize the database
        pacienteObraSocialRepository.saveAndFlush(pacienteObraSocial);

        int databaseSizeBeforeDelete = pacienteObraSocialRepository.findAll().size();

        // Get the pacienteObraSocial
        restPacienteObraSocialMockMvc.perform(delete("/api/paciente-obra-socials/{id}", pacienteObraSocial.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PacienteObraSocial> pacienteObraSocialList = pacienteObraSocialRepository.findAll();
        assertThat(pacienteObraSocialList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PacienteObraSocial.class);
        PacienteObraSocial pacienteObraSocial1 = new PacienteObraSocial();
        pacienteObraSocial1.setId(1L);
        PacienteObraSocial pacienteObraSocial2 = new PacienteObraSocial();
        pacienteObraSocial2.setId(pacienteObraSocial1.getId());
        assertThat(pacienteObraSocial1).isEqualTo(pacienteObraSocial2);
        pacienteObraSocial2.setId(2L);
        assertThat(pacienteObraSocial1).isNotEqualTo(pacienteObraSocial2);
        pacienteObraSocial1.setId(null);
        assertThat(pacienteObraSocial1).isNotEqualTo(pacienteObraSocial2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PacienteObraSocialDTO.class);
        PacienteObraSocialDTO pacienteObraSocialDTO1 = new PacienteObraSocialDTO();
        pacienteObraSocialDTO1.setId(1L);
        PacienteObraSocialDTO pacienteObraSocialDTO2 = new PacienteObraSocialDTO();
        assertThat(pacienteObraSocialDTO1).isNotEqualTo(pacienteObraSocialDTO2);
        pacienteObraSocialDTO2.setId(pacienteObraSocialDTO1.getId());
        assertThat(pacienteObraSocialDTO1).isEqualTo(pacienteObraSocialDTO2);
        pacienteObraSocialDTO2.setId(2L);
        assertThat(pacienteObraSocialDTO1).isNotEqualTo(pacienteObraSocialDTO2);
        pacienteObraSocialDTO1.setId(null);
        assertThat(pacienteObraSocialDTO1).isNotEqualTo(pacienteObraSocialDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(pacienteObraSocialMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(pacienteObraSocialMapper.fromId(null)).isNull();
    }
}
