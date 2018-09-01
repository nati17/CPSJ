package com.cpsj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpsj.service.MontoConsultaPracticaService;
import com.cpsj.web.rest.errors.BadRequestAlertException;
import com.cpsj.web.rest.util.HeaderUtil;
import com.cpsj.service.dto.MontoConsultaPracticaDTO;
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
 * REST controller for managing MontoConsultaPractica.
 */
@RestController
@RequestMapping("/api")
public class MontoConsultaPracticaResource {

    private final Logger log = LoggerFactory.getLogger(MontoConsultaPracticaResource.class);

    private static final String ENTITY_NAME = "montoConsultaPractica";

    private final MontoConsultaPracticaService montoConsultaPracticaService;

    public MontoConsultaPracticaResource(MontoConsultaPracticaService montoConsultaPracticaService) {
        this.montoConsultaPracticaService = montoConsultaPracticaService;
    }

    /**
     * POST  /monto-consulta-practicas : Create a new montoConsultaPractica.
     *
     * @param montoConsultaPracticaDTO the montoConsultaPracticaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new montoConsultaPracticaDTO, or with status 400 (Bad Request) if the montoConsultaPractica has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/monto-consulta-practicas")
    @Timed
    public ResponseEntity<MontoConsultaPracticaDTO> createMontoConsultaPractica(@RequestBody MontoConsultaPracticaDTO montoConsultaPracticaDTO) throws URISyntaxException {
        log.debug("REST request to save MontoConsultaPractica : {}", montoConsultaPracticaDTO);
        if (montoConsultaPracticaDTO.getId() != null) {
            throw new BadRequestAlertException("A new montoConsultaPractica cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MontoConsultaPracticaDTO result = montoConsultaPracticaService.save(montoConsultaPracticaDTO);
        return ResponseEntity.created(new URI("/api/monto-consulta-practicas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /monto-consulta-practicas : Updates an existing montoConsultaPractica.
     *
     * @param montoConsultaPracticaDTO the montoConsultaPracticaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated montoConsultaPracticaDTO,
     * or with status 400 (Bad Request) if the montoConsultaPracticaDTO is not valid,
     * or with status 500 (Internal Server Error) if the montoConsultaPracticaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/monto-consulta-practicas")
    @Timed
    public ResponseEntity<MontoConsultaPracticaDTO> updateMontoConsultaPractica(@RequestBody MontoConsultaPracticaDTO montoConsultaPracticaDTO) throws URISyntaxException {
        log.debug("REST request to update MontoConsultaPractica : {}", montoConsultaPracticaDTO);
        if (montoConsultaPracticaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MontoConsultaPracticaDTO result = montoConsultaPracticaService.save(montoConsultaPracticaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, montoConsultaPracticaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /monto-consulta-practicas : get all the montoConsultaPracticas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of montoConsultaPracticas in body
     */
    @GetMapping("/monto-consulta-practicas")
    @Timed
    public List<MontoConsultaPracticaDTO> getAllMontoConsultaPracticas() {
        log.debug("REST request to get all MontoConsultaPracticas");
        return montoConsultaPracticaService.findAll();
    }

    /**
     * GET  /monto-consulta-practicas/:id : get the "id" montoConsultaPractica.
     *
     * @param id the id of the montoConsultaPracticaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the montoConsultaPracticaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/monto-consulta-practicas/{id}")
    @Timed
    public ResponseEntity<MontoConsultaPracticaDTO> getMontoConsultaPractica(@PathVariable Long id) {
        log.debug("REST request to get MontoConsultaPractica : {}", id);
        Optional<MontoConsultaPracticaDTO> montoConsultaPracticaDTO = montoConsultaPracticaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(montoConsultaPracticaDTO);
    }

    /**
     * DELETE  /monto-consulta-practicas/:id : delete the "id" montoConsultaPractica.
     *
     * @param id the id of the montoConsultaPracticaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/monto-consulta-practicas/{id}")
    @Timed
    public ResponseEntity<Void> deleteMontoConsultaPractica(@PathVariable Long id) {
        log.debug("REST request to delete MontoConsultaPractica : {}", id);
        montoConsultaPracticaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
