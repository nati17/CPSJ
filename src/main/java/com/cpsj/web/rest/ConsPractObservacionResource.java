package com.cpsj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpsj.service.ConsPractObservacionService;
import com.cpsj.web.rest.errors.BadRequestAlertException;
import com.cpsj.web.rest.util.HeaderUtil;
import com.cpsj.service.dto.ConsPractObservacionDTO;
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
 * REST controller for managing ConsPractObservacion.
 */
@RestController
@RequestMapping("/api")
public class ConsPractObservacionResource {

    private final Logger log = LoggerFactory.getLogger(ConsPractObservacionResource.class);

    private static final String ENTITY_NAME = "consPractObservacion";

    private final ConsPractObservacionService consPractObservacionService;

    public ConsPractObservacionResource(ConsPractObservacionService consPractObservacionService) {
        this.consPractObservacionService = consPractObservacionService;
    }

    /**
     * POST  /cons-pract-observacions : Create a new consPractObservacion.
     *
     * @param consPractObservacionDTO the consPractObservacionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new consPractObservacionDTO, or with status 400 (Bad Request) if the consPractObservacion has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cons-pract-observacions")
    @Timed
    public ResponseEntity<ConsPractObservacionDTO> createConsPractObservacion(@RequestBody ConsPractObservacionDTO consPractObservacionDTO) throws URISyntaxException {
        log.debug("REST request to save ConsPractObservacion : {}", consPractObservacionDTO);
        if (consPractObservacionDTO.getId() != null) {
            throw new BadRequestAlertException("A new consPractObservacion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ConsPractObservacionDTO result = consPractObservacionService.save(consPractObservacionDTO);
        return ResponseEntity.created(new URI("/api/cons-pract-observacions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cons-pract-observacions : Updates an existing consPractObservacion.
     *
     * @param consPractObservacionDTO the consPractObservacionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated consPractObservacionDTO,
     * or with status 400 (Bad Request) if the consPractObservacionDTO is not valid,
     * or with status 500 (Internal Server Error) if the consPractObservacionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cons-pract-observacions")
    @Timed
    public ResponseEntity<ConsPractObservacionDTO> updateConsPractObservacion(@RequestBody ConsPractObservacionDTO consPractObservacionDTO) throws URISyntaxException {
        log.debug("REST request to update ConsPractObservacion : {}", consPractObservacionDTO);
        if (consPractObservacionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ConsPractObservacionDTO result = consPractObservacionService.save(consPractObservacionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, consPractObservacionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cons-pract-observacions : get all the consPractObservacions.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of consPractObservacions in body
     */
    @GetMapping("/cons-pract-observacions")
    @Timed
    public List<ConsPractObservacionDTO> getAllConsPractObservacions() {
        log.debug("REST request to get all ConsPractObservacions");
        return consPractObservacionService.findAll();
    }

    /**
     * GET  /cons-pract-observacions/:id : get the "id" consPractObservacion.
     *
     * @param id the id of the consPractObservacionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the consPractObservacionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/cons-pract-observacions/{id}")
    @Timed
    public ResponseEntity<ConsPractObservacionDTO> getConsPractObservacion(@PathVariable Long id) {
        log.debug("REST request to get ConsPractObservacion : {}", id);
        Optional<ConsPractObservacionDTO> consPractObservacionDTO = consPractObservacionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(consPractObservacionDTO);
    }

    /**
     * DELETE  /cons-pract-observacions/:id : delete the "id" consPractObservacion.
     *
     * @param id the id of the consPractObservacionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cons-pract-observacions/{id}")
    @Timed
    public ResponseEntity<Void> deleteConsPractObservacion(@PathVariable Long id) {
        log.debug("REST request to delete ConsPractObservacion : {}", id);
        consPractObservacionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
