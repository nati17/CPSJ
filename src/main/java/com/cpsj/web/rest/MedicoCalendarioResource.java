package com.cpsj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpsj.service.MedicoCalendarioService;
import com.cpsj.web.rest.errors.BadRequestAlertException;
import com.cpsj.web.rest.util.HeaderUtil;
import com.cpsj.service.dto.MedicoCalendarioDTO;
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
 * REST controller for managing MedicoCalendario.
 */
@RestController
@RequestMapping("/api")
public class MedicoCalendarioResource {

    private final Logger log = LoggerFactory.getLogger(MedicoCalendarioResource.class);

    private static final String ENTITY_NAME = "medicoCalendario";

    private final MedicoCalendarioService medicoCalendarioService;

    public MedicoCalendarioResource(MedicoCalendarioService medicoCalendarioService) {
        this.medicoCalendarioService = medicoCalendarioService;
    }

    /**
     * POST  /medico-calendarios : Create a new medicoCalendario.
     *
     * @param medicoCalendarioDTO the medicoCalendarioDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new medicoCalendarioDTO, or with status 400 (Bad Request) if the medicoCalendario has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/medico-calendarios")
    @Timed
    public ResponseEntity<MedicoCalendarioDTO> createMedicoCalendario(@RequestBody MedicoCalendarioDTO medicoCalendarioDTO) throws URISyntaxException {
        log.debug("REST request to save MedicoCalendario : {}", medicoCalendarioDTO);
        if (medicoCalendarioDTO.getId() != null) {
            throw new BadRequestAlertException("A new medicoCalendario cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MedicoCalendarioDTO result = medicoCalendarioService.save(medicoCalendarioDTO);
        return ResponseEntity.created(new URI("/api/medico-calendarios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /medico-calendarios : Updates an existing medicoCalendario.
     *
     * @param medicoCalendarioDTO the medicoCalendarioDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated medicoCalendarioDTO,
     * or with status 400 (Bad Request) if the medicoCalendarioDTO is not valid,
     * or with status 500 (Internal Server Error) if the medicoCalendarioDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/medico-calendarios")
    @Timed
    public ResponseEntity<MedicoCalendarioDTO> updateMedicoCalendario(@RequestBody MedicoCalendarioDTO medicoCalendarioDTO) throws URISyntaxException {
        log.debug("REST request to update MedicoCalendario : {}", medicoCalendarioDTO);
        if (medicoCalendarioDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MedicoCalendarioDTO result = medicoCalendarioService.save(medicoCalendarioDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, medicoCalendarioDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /medico-calendarios : get all the medicoCalendarios.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of medicoCalendarios in body
     */
    @GetMapping("/medico-calendarios")
    @Timed
    public List<MedicoCalendarioDTO> getAllMedicoCalendarios() {
        log.debug("REST request to get all MedicoCalendarios");
        return medicoCalendarioService.findAll();
    }

    /**
     * GET  /medico-calendarios/:id : get the "id" medicoCalendario.
     *
     * @param id the id of the medicoCalendarioDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the medicoCalendarioDTO, or with status 404 (Not Found)
     */
    @GetMapping("/medico-calendarios/{id}")
    @Timed
    public ResponseEntity<MedicoCalendarioDTO> getMedicoCalendario(@PathVariable Long id) {
        log.debug("REST request to get MedicoCalendario : {}", id);
        Optional<MedicoCalendarioDTO> medicoCalendarioDTO = medicoCalendarioService.findOne(id);
        return ResponseUtil.wrapOrNotFound(medicoCalendarioDTO);
    }

    /**
     * DELETE  /medico-calendarios/:id : delete the "id" medicoCalendario.
     *
     * @param id the id of the medicoCalendarioDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/medico-calendarios/{id}")
    @Timed
    public ResponseEntity<Void> deleteMedicoCalendario(@PathVariable Long id) {
        log.debug("REST request to delete MedicoCalendario : {}", id);
        medicoCalendarioService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
