package com.cpsj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpsj.service.ObservacionService;
import com.cpsj.web.rest.errors.BadRequestAlertException;
import com.cpsj.web.rest.util.HeaderUtil;
import com.cpsj.service.dto.ObservacionDTO;
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
 * REST controller for managing Observacion.
 */
@RestController
@RequestMapping("/api")
public class ObservacionResource {

    private final Logger log = LoggerFactory.getLogger(ObservacionResource.class);

    private static final String ENTITY_NAME = "observacion";

    private final ObservacionService observacionService;

    public ObservacionResource(ObservacionService observacionService) {
        this.observacionService = observacionService;
    }

    /**
     * POST  /observacions : Create a new observacion.
     *
     * @param observacionDTO the observacionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new observacionDTO, or with status 400 (Bad Request) if the observacion has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/observacions")
    @Timed
    public ResponseEntity<ObservacionDTO> createObservacion(@RequestBody ObservacionDTO observacionDTO) throws URISyntaxException {
        log.debug("REST request to save Observacion : {}", observacionDTO);
        if (observacionDTO.getId() != null) {
            throw new BadRequestAlertException("A new observacion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ObservacionDTO result = observacionService.save(observacionDTO);
        return ResponseEntity.created(new URI("/api/observacions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /observacions : Updates an existing observacion.
     *
     * @param observacionDTO the observacionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated observacionDTO,
     * or with status 400 (Bad Request) if the observacionDTO is not valid,
     * or with status 500 (Internal Server Error) if the observacionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/observacions")
    @Timed
    public ResponseEntity<ObservacionDTO> updateObservacion(@RequestBody ObservacionDTO observacionDTO) throws URISyntaxException {
        log.debug("REST request to update Observacion : {}", observacionDTO);
        if (observacionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ObservacionDTO result = observacionService.save(observacionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, observacionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /observacions : get all the observacions.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of observacions in body
     */
    @GetMapping("/observacions")
    @Timed
    public List<ObservacionDTO> getAllObservacions() {
        log.debug("REST request to get all Observacions");
        return observacionService.findAll();
    }

    /**
     * GET  /observacions/:id : get the "id" observacion.
     *
     * @param id the id of the observacionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the observacionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/observacions/{id}")
    @Timed
    public ResponseEntity<ObservacionDTO> getObservacion(@PathVariable Long id) {
        log.debug("REST request to get Observacion : {}", id);
        Optional<ObservacionDTO> observacionDTO = observacionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(observacionDTO);
    }

    /**
     * DELETE  /observacions/:id : delete the "id" observacion.
     *
     * @param id the id of the observacionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/observacions/{id}")
    @Timed
    public ResponseEntity<Void> deleteObservacion(@PathVariable Long id) {
        log.debug("REST request to delete Observacion : {}", id);
        observacionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
