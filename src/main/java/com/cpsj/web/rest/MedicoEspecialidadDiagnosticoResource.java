package com.cpsj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpsj.service.MedicoEspecialidadDiagnosticoService;
import com.cpsj.web.rest.errors.BadRequestAlertException;
import com.cpsj.web.rest.util.HeaderUtil;
import com.cpsj.service.dto.MedicoEspecialidadDiagnosticoDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing MedicoEspecialidadDiagnostico.
 */
@RestController
@RequestMapping("/api")
public class MedicoEspecialidadDiagnosticoResource {

    private final Logger log = LoggerFactory.getLogger(MedicoEspecialidadDiagnosticoResource.class);

    private static final String ENTITY_NAME = "medicoEspecialidadDiagnostico";

    private final MedicoEspecialidadDiagnosticoService medicoEspecialidadDiagnosticoService;

    public MedicoEspecialidadDiagnosticoResource(MedicoEspecialidadDiagnosticoService medicoEspecialidadDiagnosticoService) {
        this.medicoEspecialidadDiagnosticoService = medicoEspecialidadDiagnosticoService;
    }

    /**
     * POST  /medico-especialidad-diagnosticos : Create a new medicoEspecialidadDiagnostico.
     *
     * @param medicoEspecialidadDiagnosticoDTO the medicoEspecialidadDiagnosticoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new medicoEspecialidadDiagnosticoDTO, or with status 400 (Bad Request) if the medicoEspecialidadDiagnostico has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/medico-especialidad-diagnosticos")
    @Timed
    public ResponseEntity<MedicoEspecialidadDiagnosticoDTO> createMedicoEspecialidadDiagnostico(@RequestBody MedicoEspecialidadDiagnosticoDTO medicoEspecialidadDiagnosticoDTO) throws URISyntaxException {
        log.debug("REST request to save MedicoEspecialidadDiagnostico : {}", medicoEspecialidadDiagnosticoDTO);
        if (medicoEspecialidadDiagnosticoDTO.getId() != null) {
            throw new BadRequestAlertException("A new medicoEspecialidadDiagnostico cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MedicoEspecialidadDiagnosticoDTO result = medicoEspecialidadDiagnosticoService.save(medicoEspecialidadDiagnosticoDTO);
        return ResponseEntity.created(new URI("/api/medico-especialidad-diagnosticos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /medico-especialidad-diagnosticos : Updates an existing medicoEspecialidadDiagnostico.
     *
     * @param medicoEspecialidadDiagnosticoDTO the medicoEspecialidadDiagnosticoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated medicoEspecialidadDiagnosticoDTO,
     * or with status 400 (Bad Request) if the medicoEspecialidadDiagnosticoDTO is not valid,
     * or with status 500 (Internal Server Error) if the medicoEspecialidadDiagnosticoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/medico-especialidad-diagnosticos")
    @Timed
    public ResponseEntity<MedicoEspecialidadDiagnosticoDTO> updateMedicoEspecialidadDiagnostico(@RequestBody MedicoEspecialidadDiagnosticoDTO medicoEspecialidadDiagnosticoDTO) throws URISyntaxException {
        log.debug("REST request to update MedicoEspecialidadDiagnostico : {}", medicoEspecialidadDiagnosticoDTO);
        if (medicoEspecialidadDiagnosticoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MedicoEspecialidadDiagnosticoDTO result = medicoEspecialidadDiagnosticoService.save(medicoEspecialidadDiagnosticoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, medicoEspecialidadDiagnosticoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /medico-especialidad-diagnosticos : get all the medicoEspecialidadDiagnosticos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of medicoEspecialidadDiagnosticos in body
     */
    @GetMapping("/medico-especialidad-diagnosticos")
    @Timed
    public List<MedicoEspecialidadDiagnosticoDTO> getAllMedicoEspecialidadDiagnosticos() {
        log.debug("REST request to get all MedicoEspecialidadDiagnosticos");
        return medicoEspecialidadDiagnosticoService.findAll();
    }

    /**
     * GET  /medico-especialidad-diagnosticos/:id : get the "id" medicoEspecialidadDiagnostico.
     *
     * @param id the id of the medicoEspecialidadDiagnosticoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the medicoEspecialidadDiagnosticoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/medico-especialidad-diagnosticos/{id}")
    @Timed
    public ResponseEntity<MedicoEspecialidadDiagnosticoDTO> getMedicoEspecialidadDiagnostico(@PathVariable Long id) {
        log.debug("REST request to get MedicoEspecialidadDiagnostico : {}", id);
        Optional<MedicoEspecialidadDiagnosticoDTO> medicoEspecialidadDiagnosticoDTO = medicoEspecialidadDiagnosticoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(medicoEspecialidadDiagnosticoDTO);
    }

    /**
     * DELETE  /medico-especialidad-diagnosticos/:id : delete the "id" medicoEspecialidadDiagnostico.
     *
     * @param id the id of the medicoEspecialidadDiagnosticoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/medico-especialidad-diagnosticos/{id}")
    @Timed
    public ResponseEntity<Void> deleteMedicoEspecialidadDiagnostico(@PathVariable Long id) {
        log.debug("REST request to delete MedicoEspecialidadDiagnostico : {}", id);
        medicoEspecialidadDiagnosticoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
