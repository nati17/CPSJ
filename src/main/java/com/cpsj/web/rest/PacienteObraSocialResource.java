package com.cpsj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpsj.service.PacienteObraSocialService;
import com.cpsj.web.rest.errors.BadRequestAlertException;
import com.cpsj.web.rest.util.HeaderUtil;
import com.cpsj.service.dto.PacienteObraSocialDTO;
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
 * REST controller for managing PacienteObraSocial.
 */
@RestController
@RequestMapping("/api")
public class PacienteObraSocialResource {

    private final Logger log = LoggerFactory.getLogger(PacienteObraSocialResource.class);

    private static final String ENTITY_NAME = "pacienteObraSocial";

    private final PacienteObraSocialService pacienteObraSocialService;

    public PacienteObraSocialResource(PacienteObraSocialService pacienteObraSocialService) {
        this.pacienteObraSocialService = pacienteObraSocialService;
    }

    /**
     * POST  /paciente-obra-socials : Create a new pacienteObraSocial.
     *
     * @param pacienteObraSocialDTO the pacienteObraSocialDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pacienteObraSocialDTO, or with status 400 (Bad Request) if the pacienteObraSocial has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/paciente-obra-socials")
    @Timed
    public ResponseEntity<PacienteObraSocialDTO> createPacienteObraSocial(@RequestBody PacienteObraSocialDTO pacienteObraSocialDTO) throws URISyntaxException {
        log.debug("REST request to save PacienteObraSocial : {}", pacienteObraSocialDTO);
        if (pacienteObraSocialDTO.getId() != null) {
            throw new BadRequestAlertException("A new pacienteObraSocial cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PacienteObraSocialDTO result = pacienteObraSocialService.save(pacienteObraSocialDTO);
        return ResponseEntity.created(new URI("/api/paciente-obra-socials/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /paciente-obra-socials : Updates an existing pacienteObraSocial.
     *
     * @param pacienteObraSocialDTO the pacienteObraSocialDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pacienteObraSocialDTO,
     * or with status 400 (Bad Request) if the pacienteObraSocialDTO is not valid,
     * or with status 500 (Internal Server Error) if the pacienteObraSocialDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/paciente-obra-socials")
    @Timed
    public ResponseEntity<PacienteObraSocialDTO> updatePacienteObraSocial(@RequestBody PacienteObraSocialDTO pacienteObraSocialDTO) throws URISyntaxException {
        log.debug("REST request to update PacienteObraSocial : {}", pacienteObraSocialDTO);
        if (pacienteObraSocialDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PacienteObraSocialDTO result = pacienteObraSocialService.save(pacienteObraSocialDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pacienteObraSocialDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /paciente-obra-socials : get all the pacienteObraSocials.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of pacienteObraSocials in body
     */
    @GetMapping("/paciente-obra-socials")
    @Timed
    public List<PacienteObraSocialDTO> getAllPacienteObraSocials() {
        log.debug("REST request to get all PacienteObraSocials");
        return pacienteObraSocialService.findAll();
    }

    /**
     * GET  /paciente-obra-socials/:id : get the "id" pacienteObraSocial.
     *
     * @param id the id of the pacienteObraSocialDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pacienteObraSocialDTO, or with status 404 (Not Found)
     */
    @GetMapping("/paciente-obra-socials/{id}")
    @Timed
    public ResponseEntity<PacienteObraSocialDTO> getPacienteObraSocial(@PathVariable Long id) {
        log.debug("REST request to get PacienteObraSocial : {}", id);
        Optional<PacienteObraSocialDTO> pacienteObraSocialDTO = pacienteObraSocialService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pacienteObraSocialDTO);
    }

    /**
     * DELETE  /paciente-obra-socials/:id : delete the "id" pacienteObraSocial.
     *
     * @param id the id of the pacienteObraSocialDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/paciente-obra-socials/{id}")
    @Timed
    public ResponseEntity<Void> deletePacienteObraSocial(@PathVariable Long id) {
        log.debug("REST request to delete PacienteObraSocial : {}", id);
        pacienteObraSocialService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
