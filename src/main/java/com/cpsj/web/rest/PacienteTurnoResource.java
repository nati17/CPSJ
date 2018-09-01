package com.cpsj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpsj.service.PacienteTurnoService;
import com.cpsj.web.rest.errors.BadRequestAlertException;
import com.cpsj.web.rest.util.HeaderUtil;
import com.cpsj.service.dto.PacienteTurnoDTO;
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
 * REST controller for managing PacienteTurno.
 */
@RestController
@RequestMapping("/api")
public class PacienteTurnoResource {

    private final Logger log = LoggerFactory.getLogger(PacienteTurnoResource.class);

    private static final String ENTITY_NAME = "pacienteTurno";

    private final PacienteTurnoService pacienteTurnoService;

    public PacienteTurnoResource(PacienteTurnoService pacienteTurnoService) {
        this.pacienteTurnoService = pacienteTurnoService;
    }

    /**
     * POST  /paciente-turnos : Create a new pacienteTurno.
     *
     * @param pacienteTurnoDTO the pacienteTurnoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pacienteTurnoDTO, or with status 400 (Bad Request) if the pacienteTurno has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/paciente-turnos")
    @Timed
    public ResponseEntity<PacienteTurnoDTO> createPacienteTurno(@RequestBody PacienteTurnoDTO pacienteTurnoDTO) throws URISyntaxException {
        log.debug("REST request to save PacienteTurno : {}", pacienteTurnoDTO);
        if (pacienteTurnoDTO.getId() != null) {
            throw new BadRequestAlertException("A new pacienteTurno cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PacienteTurnoDTO result = pacienteTurnoService.save(pacienteTurnoDTO);
        return ResponseEntity.created(new URI("/api/paciente-turnos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /paciente-turnos : Updates an existing pacienteTurno.
     *
     * @param pacienteTurnoDTO the pacienteTurnoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pacienteTurnoDTO,
     * or with status 400 (Bad Request) if the pacienteTurnoDTO is not valid,
     * or with status 500 (Internal Server Error) if the pacienteTurnoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/paciente-turnos")
    @Timed
    public ResponseEntity<PacienteTurnoDTO> updatePacienteTurno(@RequestBody PacienteTurnoDTO pacienteTurnoDTO) throws URISyntaxException {
        log.debug("REST request to update PacienteTurno : {}", pacienteTurnoDTO);
        if (pacienteTurnoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PacienteTurnoDTO result = pacienteTurnoService.save(pacienteTurnoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pacienteTurnoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /paciente-turnos : get all the pacienteTurnos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of pacienteTurnos in body
     */
    @GetMapping("/paciente-turnos")
    @Timed
    public List<PacienteTurnoDTO> getAllPacienteTurnos() {
        log.debug("REST request to get all PacienteTurnos");
        return pacienteTurnoService.findAll();
    }

    /**
     * GET  /paciente-turnos/:id : get the "id" pacienteTurno.
     *
     * @param id the id of the pacienteTurnoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pacienteTurnoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/paciente-turnos/{id}")
    @Timed
    public ResponseEntity<PacienteTurnoDTO> getPacienteTurno(@PathVariable Long id) {
        log.debug("REST request to get PacienteTurno : {}", id);
        Optional<PacienteTurnoDTO> pacienteTurnoDTO = pacienteTurnoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pacienteTurnoDTO);
    }

    /**
     * DELETE  /paciente-turnos/:id : delete the "id" pacienteTurno.
     *
     * @param id the id of the pacienteTurnoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/paciente-turnos/{id}")
    @Timed
    public ResponseEntity<Void> deletePacienteTurno(@PathVariable Long id) {
        log.debug("REST request to delete PacienteTurno : {}", id);
        pacienteTurnoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
