package com.cpsj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpsj.service.SucursalService;
import com.cpsj.web.rest.errors.BadRequestAlertException;
import com.cpsj.web.rest.util.HeaderUtil;
import com.cpsj.service.dto.SucursalDTO;
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
 * REST controller for managing Sucursal.
 */
@RestController
@RequestMapping("/api")
public class SucursalResource {

    private final Logger log = LoggerFactory.getLogger(SucursalResource.class);

    private static final String ENTITY_NAME = "sucursal";

    private final SucursalService sucursalService;

    public SucursalResource(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    /**
     * POST  /sucursals : Create a new sucursal.
     *
     * @param sucursalDTO the sucursalDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sucursalDTO, or with status 400 (Bad Request) if the sucursal has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sucursals")
    @Timed
    public ResponseEntity<SucursalDTO> createSucursal(@RequestBody SucursalDTO sucursalDTO) throws URISyntaxException {
        log.debug("REST request to save Sucursal : {}", sucursalDTO);
        if (sucursalDTO.getId() != null) {
            throw new BadRequestAlertException("A new sucursal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SucursalDTO result = sucursalService.save(sucursalDTO);
        return ResponseEntity.created(new URI("/api/sucursals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sucursals : Updates an existing sucursal.
     *
     * @param sucursalDTO the sucursalDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sucursalDTO,
     * or with status 400 (Bad Request) if the sucursalDTO is not valid,
     * or with status 500 (Internal Server Error) if the sucursalDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sucursals")
    @Timed
    public ResponseEntity<SucursalDTO> updateSucursal(@RequestBody SucursalDTO sucursalDTO) throws URISyntaxException {
        log.debug("REST request to update Sucursal : {}", sucursalDTO);
        if (sucursalDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SucursalDTO result = sucursalService.save(sucursalDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sucursalDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sucursals : get all the sucursals.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sucursals in body
     */
    @GetMapping("/sucursals")
    @Timed
    public List<SucursalDTO> getAllSucursals() {
        log.debug("REST request to get all Sucursals");
        return sucursalService.findAll();
    }

    /**
     * GET  /sucursals/:id : get the "id" sucursal.
     *
     * @param id the id of the sucursalDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sucursalDTO, or with status 404 (Not Found)
     */
    @GetMapping("/sucursals/{id}")
    @Timed
    public ResponseEntity<SucursalDTO> getSucursal(@PathVariable Long id) {
        log.debug("REST request to get Sucursal : {}", id);
        Optional<SucursalDTO> sucursalDTO = sucursalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sucursalDTO);
    }

    /**
     * DELETE  /sucursals/:id : delete the "id" sucursal.
     *
     * @param id the id of the sucursalDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sucursals/{id}")
    @Timed
    public ResponseEntity<Void> deleteSucursal(@PathVariable Long id) {
        log.debug("REST request to delete Sucursal : {}", id);
        sucursalService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
