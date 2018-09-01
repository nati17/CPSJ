package com.cpsj.web.rest;

import com.cpsj.CpsjApp;

import com.cpsj.domain.MedicoObraSocial;
import com.cpsj.repository.MedicoObraSocialRepository;
import com.cpsj.service.MedicoObraSocialService;
import com.cpsj.service.dto.MedicoObraSocialDTO;
import com.cpsj.service.mapper.MedicoObraSocialMapper;
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
 * Test class for the MedicoObraSocialResource REST controller.
 *
 * @see MedicoObraSocialResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CpsjApp.class)
public class MedicoObraSocialResourceIntTest {

    private static final String DEFAULT_ID_MEDICO = "AAAAAAAAAA";
    private static final String UPDATED_ID_MEDICO = "BBBBBBBBBB";

    private static final String DEFAULT_ID_O_SOCIAL = "AAAAAAAAAA";
    private static final String UPDATED_ID_O_SOCIAL = "BBBBBBBBBB";

    private static final String DEFAULT_OBSERVACIONES = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVACIONES = "BBBBBBBBBB";

    @Autowired
    private MedicoObraSocialRepository medicoObraSocialRepository;


    @Autowired
    private MedicoObraSocialMapper medicoObraSocialMapper;
    

    @Autowired
    private MedicoObraSocialService medicoObraSocialService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMedicoObraSocialMockMvc;

    private MedicoObraSocial medicoObraSocial;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MedicoObraSocialResource medicoObraSocialResource = new MedicoObraSocialResource(medicoObraSocialService);
        this.restMedicoObraSocialMockMvc = MockMvcBuilders.standaloneSetup(medicoObraSocialResource)
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
    public static MedicoObraSocial createEntity(EntityManager em) {
        MedicoObraSocial medicoObraSocial = new MedicoObraSocial()
            .idMedico(DEFAULT_ID_MEDICO)
            .idOSocial(DEFAULT_ID_O_SOCIAL)
            .observaciones(DEFAULT_OBSERVACIONES);
        return medicoObraSocial;
    }

    @Before
    public void initTest() {
        medicoObraSocial = createEntity(em);
    }

    @Test
    @Transactional
    public void createMedicoObraSocial() throws Exception {
        int databaseSizeBeforeCreate = medicoObraSocialRepository.findAll().size();

        // Create the MedicoObraSocial
        MedicoObraSocialDTO medicoObraSocialDTO = medicoObraSocialMapper.toDto(medicoObraSocial);
        restMedicoObraSocialMockMvc.perform(post("/api/medico-obra-socials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medicoObraSocialDTO)))
            .andExpect(status().isCreated());

        // Validate the MedicoObraSocial in the database
        List<MedicoObraSocial> medicoObraSocialList = medicoObraSocialRepository.findAll();
        assertThat(medicoObraSocialList).hasSize(databaseSizeBeforeCreate + 1);
        MedicoObraSocial testMedicoObraSocial = medicoObraSocialList.get(medicoObraSocialList.size() - 1);
        assertThat(testMedicoObraSocial.getIdMedico()).isEqualTo(DEFAULT_ID_MEDICO);
        assertThat(testMedicoObraSocial.getIdOSocial()).isEqualTo(DEFAULT_ID_O_SOCIAL);
        assertThat(testMedicoObraSocial.getObservaciones()).isEqualTo(DEFAULT_OBSERVACIONES);
    }

    @Test
    @Transactional
    public void createMedicoObraSocialWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = medicoObraSocialRepository.findAll().size();

        // Create the MedicoObraSocial with an existing ID
        medicoObraSocial.setId(1L);
        MedicoObraSocialDTO medicoObraSocialDTO = medicoObraSocialMapper.toDto(medicoObraSocial);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMedicoObraSocialMockMvc.perform(post("/api/medico-obra-socials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medicoObraSocialDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedicoObraSocial in the database
        List<MedicoObraSocial> medicoObraSocialList = medicoObraSocialRepository.findAll();
        assertThat(medicoObraSocialList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMedicoObraSocials() throws Exception {
        // Initialize the database
        medicoObraSocialRepository.saveAndFlush(medicoObraSocial);

        // Get all the medicoObraSocialList
        restMedicoObraSocialMockMvc.perform(get("/api/medico-obra-socials?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(medicoObraSocial.getId().intValue())))
            .andExpect(jsonPath("$.[*].idMedico").value(hasItem(DEFAULT_ID_MEDICO.toString())))
            .andExpect(jsonPath("$.[*].idOSocial").value(hasItem(DEFAULT_ID_O_SOCIAL.toString())))
            .andExpect(jsonPath("$.[*].observaciones").value(hasItem(DEFAULT_OBSERVACIONES.toString())));
    }
    

    @Test
    @Transactional
    public void getMedicoObraSocial() throws Exception {
        // Initialize the database
        medicoObraSocialRepository.saveAndFlush(medicoObraSocial);

        // Get the medicoObraSocial
        restMedicoObraSocialMockMvc.perform(get("/api/medico-obra-socials/{id}", medicoObraSocial.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(medicoObraSocial.getId().intValue()))
            .andExpect(jsonPath("$.idMedico").value(DEFAULT_ID_MEDICO.toString()))
            .andExpect(jsonPath("$.idOSocial").value(DEFAULT_ID_O_SOCIAL.toString()))
            .andExpect(jsonPath("$.observaciones").value(DEFAULT_OBSERVACIONES.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingMedicoObraSocial() throws Exception {
        // Get the medicoObraSocial
        restMedicoObraSocialMockMvc.perform(get("/api/medico-obra-socials/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMedicoObraSocial() throws Exception {
        // Initialize the database
        medicoObraSocialRepository.saveAndFlush(medicoObraSocial);

        int databaseSizeBeforeUpdate = medicoObraSocialRepository.findAll().size();

        // Update the medicoObraSocial
        MedicoObraSocial updatedMedicoObraSocial = medicoObraSocialRepository.findById(medicoObraSocial.getId()).get();
        // Disconnect from session so that the updates on updatedMedicoObraSocial are not directly saved in db
        em.detach(updatedMedicoObraSocial);
        updatedMedicoObraSocial
            .idMedico(UPDATED_ID_MEDICO)
            .idOSocial(UPDATED_ID_O_SOCIAL)
            .observaciones(UPDATED_OBSERVACIONES);
        MedicoObraSocialDTO medicoObraSocialDTO = medicoObraSocialMapper.toDto(updatedMedicoObraSocial);

        restMedicoObraSocialMockMvc.perform(put("/api/medico-obra-socials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medicoObraSocialDTO)))
            .andExpect(status().isOk());

        // Validate the MedicoObraSocial in the database
        List<MedicoObraSocial> medicoObraSocialList = medicoObraSocialRepository.findAll();
        assertThat(medicoObraSocialList).hasSize(databaseSizeBeforeUpdate);
        MedicoObraSocial testMedicoObraSocial = medicoObraSocialList.get(medicoObraSocialList.size() - 1);
        assertThat(testMedicoObraSocial.getIdMedico()).isEqualTo(UPDATED_ID_MEDICO);
        assertThat(testMedicoObraSocial.getIdOSocial()).isEqualTo(UPDATED_ID_O_SOCIAL);
        assertThat(testMedicoObraSocial.getObservaciones()).isEqualTo(UPDATED_OBSERVACIONES);
    }

    @Test
    @Transactional
    public void updateNonExistingMedicoObraSocial() throws Exception {
        int databaseSizeBeforeUpdate = medicoObraSocialRepository.findAll().size();

        // Create the MedicoObraSocial
        MedicoObraSocialDTO medicoObraSocialDTO = medicoObraSocialMapper.toDto(medicoObraSocial);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMedicoObraSocialMockMvc.perform(put("/api/medico-obra-socials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medicoObraSocialDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedicoObraSocial in the database
        List<MedicoObraSocial> medicoObraSocialList = medicoObraSocialRepository.findAll();
        assertThat(medicoObraSocialList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMedicoObraSocial() throws Exception {
        // Initialize the database
        medicoObraSocialRepository.saveAndFlush(medicoObraSocial);

        int databaseSizeBeforeDelete = medicoObraSocialRepository.findAll().size();

        // Get the medicoObraSocial
        restMedicoObraSocialMockMvc.perform(delete("/api/medico-obra-socials/{id}", medicoObraSocial.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MedicoObraSocial> medicoObraSocialList = medicoObraSocialRepository.findAll();
        assertThat(medicoObraSocialList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedicoObraSocial.class);
        MedicoObraSocial medicoObraSocial1 = new MedicoObraSocial();
        medicoObraSocial1.setId(1L);
        MedicoObraSocial medicoObraSocial2 = new MedicoObraSocial();
        medicoObraSocial2.setId(medicoObraSocial1.getId());
        assertThat(medicoObraSocial1).isEqualTo(medicoObraSocial2);
        medicoObraSocial2.setId(2L);
        assertThat(medicoObraSocial1).isNotEqualTo(medicoObraSocial2);
        medicoObraSocial1.setId(null);
        assertThat(medicoObraSocial1).isNotEqualTo(medicoObraSocial2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedicoObraSocialDTO.class);
        MedicoObraSocialDTO medicoObraSocialDTO1 = new MedicoObraSocialDTO();
        medicoObraSocialDTO1.setId(1L);
        MedicoObraSocialDTO medicoObraSocialDTO2 = new MedicoObraSocialDTO();
        assertThat(medicoObraSocialDTO1).isNotEqualTo(medicoObraSocialDTO2);
        medicoObraSocialDTO2.setId(medicoObraSocialDTO1.getId());
        assertThat(medicoObraSocialDTO1).isEqualTo(medicoObraSocialDTO2);
        medicoObraSocialDTO2.setId(2L);
        assertThat(medicoObraSocialDTO1).isNotEqualTo(medicoObraSocialDTO2);
        medicoObraSocialDTO1.setId(null);
        assertThat(medicoObraSocialDTO1).isNotEqualTo(medicoObraSocialDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(medicoObraSocialMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(medicoObraSocialMapper.fromId(null)).isNull();
    }
}
