package com.cpsj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpsj.service.DiasService;
import com.cpsj.web.rest.errors.BadRequestAlertException;
import com.cpsj.web.rest.util.HeaderUtil;
import com.cpsj.service.dto.DiasDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Dias.
 */
@RestController
@RequestMapping("/api")
public class DiasResource {

    private final Logger log = LoggerFactory.getLogger(DiasResource.class);

    private static final String ENTITY_NAME = "dias";

    private final DiasService diasService;

    public DiasResource(DiasService diasService) {
        this.diasService = diasService;
    }

    /**
     * POST  /dias : Create a new dias.
     *
     * @param diasDTO the diasDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new diasDTO, or with status 400 (Bad Request) if the dias has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/dias")
    @Timed
    public ResponseEntity<DiasDTO> createDias(@Valid @RequestBody DiasDTO diasDTO) throws URISyntaxException {
        log.debug("REST request to save Dias : {}", diasDTO);
        if (diasDTO.getId() != null) {
            throw new BadRequestAlertException("A new dias cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DiasDTO result = diasService.save(diasDTO);
        return ResponseEntity.created(new URI("/api/dias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /dias : Updates an existing dias.
     *
     * @param diasDTO the diasDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated diasDTO,
     * or with status 400 (Bad Request) if the diasDTO is not valid,
     * or with status 500 (Internal Server Error) if the diasDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/dias")
    @Timed
    public ResponseEntity<DiasDTO> updateDias(@Valid @RequestBody DiasDTO diasDTO) throws URISyntaxException {
        log.debug("REST request to update Dias : {}", diasDTO);
        if (diasDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DiasDTO result = diasService.save(diasDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, diasDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /dias : get all the dias.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of dias in body
     */
    @GetMapping("/dias")
    @Timed
    public List<DiasDTO> getAllDias() {
        log.debug("REST request to get all Dias");
        return diasService.findAll();
    }

    /**
     * GET  /dias/:id : get the "id" dias.
     *
     * @param id the id of the diasDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the diasDTO, or with status 404 (Not Found)
     */
    @GetMapping("/dias/{id}")
    @Timed
    public ResponseEntity<DiasDTO> getDias(@PathVariable Long id) {
        log.debug("REST request to get Dias : {}", id);
        Optional<DiasDTO> diasDTO = diasService.findOne(id);
        return ResponseUtil.wrapOrNotFound(diasDTO);
    }

    /**
     * DELETE  /dias/:id : delete the "id" dias.
     *
     * @param id the id of the diasDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/dias/{id}")
    @Timed
    public ResponseEntity<Void> deleteDias(@PathVariable Long id) {
        log.debug("REST request to delete Dias : {}", id);
        diasService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
