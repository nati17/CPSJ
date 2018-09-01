package com.cpsj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpsj.service.DigitalizacionService;
import com.cpsj.web.rest.errors.BadRequestAlertException;
import com.cpsj.web.rest.util.HeaderUtil;
import com.cpsj.service.dto.DigitalizacionDTO;
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
 * REST controller for managing Digitalizacion.
 */
@RestController
@RequestMapping("/api")
public class DigitalizacionResource {

    private final Logger log = LoggerFactory.getLogger(DigitalizacionResource.class);

    private static final String ENTITY_NAME = "digitalizacion";

    private final DigitalizacionService digitalizacionService;

    public DigitalizacionResource(DigitalizacionService digitalizacionService) {
        this.digitalizacionService = digitalizacionService;
    }

    /**
     * POST  /digitalizacions : Create a new digitalizacion.
     *
     * @param digitalizacionDTO the digitalizacionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new digitalizacionDTO, or with status 400 (Bad Request) if the digitalizacion has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/digitalizacions")
    @Timed
    public ResponseEntity<DigitalizacionDTO> createDigitalizacion(@RequestBody DigitalizacionDTO digitalizacionDTO) throws URISyntaxException {
        log.debug("REST request to save Digitalizacion : {}", digitalizacionDTO);
        if (digitalizacionDTO.getId() != null) {
            throw new BadRequestAlertException("A new digitalizacion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DigitalizacionDTO result = digitalizacionService.save(digitalizacionDTO);
        return ResponseEntity.created(new URI("/api/digitalizacions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /digitalizacions : Updates an existing digitalizacion.
     *
     * @param digitalizacionDTO the digitalizacionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated digitalizacionDTO,
     * or with status 400 (Bad Request) if the digitalizacionDTO is not valid,
     * or with status 500 (Internal Server Error) if the digitalizacionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/digitalizacions")
    @Timed
    public ResponseEntity<DigitalizacionDTO> updateDigitalizacion(@RequestBody DigitalizacionDTO digitalizacionDTO) throws URISyntaxException {
        log.debug("REST request to update Digitalizacion : {}", digitalizacionDTO);
        if (digitalizacionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DigitalizacionDTO result = digitalizacionService.save(digitalizacionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, digitalizacionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /digitalizacions : get all the digitalizacions.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of digitalizacions in body
     */
    @GetMapping("/digitalizacions")
    @Timed
    public List<DigitalizacionDTO> getAllDigitalizacions() {
        log.debug("REST request to get all Digitalizacions");
        return digitalizacionService.findAll();
    }

    /**
     * GET  /digitalizacions/:id : get the "id" digitalizacion.
     *
     * @param id the id of the digitalizacionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the digitalizacionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/digitalizacions/{id}")
    @Timed
    public ResponseEntity<DigitalizacionDTO> getDigitalizacion(@PathVariable Long id) {
        log.debug("REST request to get Digitalizacion : {}", id);
        Optional<DigitalizacionDTO> digitalizacionDTO = digitalizacionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(digitalizacionDTO);
    }

    /**
     * DELETE  /digitalizacions/:id : delete the "id" digitalizacion.
     *
     * @param id the id of the digitalizacionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/digitalizacions/{id}")
    @Timed
    public ResponseEntity<Void> deleteDigitalizacion(@PathVariable Long id) {
        log.debug("REST request to delete Digitalizacion : {}", id);
        digitalizacionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
