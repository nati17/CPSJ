package com.cpsj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpsj.service.ConsultaPracticaService;
import com.cpsj.web.rest.errors.BadRequestAlertException;
import com.cpsj.web.rest.util.HeaderUtil;
import com.cpsj.service.dto.ConsultaPracticaDTO;
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
 * REST controller for managing ConsultaPractica.
 */
@RestController
@RequestMapping("/api")
public class ConsultaPracticaResource {

    private final Logger log = LoggerFactory.getLogger(ConsultaPracticaResource.class);

    private static final String ENTITY_NAME = "consultaPractica";

    private final ConsultaPracticaService consultaPracticaService;

    public ConsultaPracticaResource(ConsultaPracticaService consultaPracticaService) {
        this.consultaPracticaService = consultaPracticaService;
    }

    /**
     * POST  /consulta-practicas : Create a new consultaPractica.
     *
     * @param consultaPracticaDTO the consultaPracticaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new consultaPracticaDTO, or with status 400 (Bad Request) if the consultaPractica has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/consulta-practicas")
    @Timed
    public ResponseEntity<ConsultaPracticaDTO> createConsultaPractica(@RequestBody ConsultaPracticaDTO consultaPracticaDTO) throws URISyntaxException {
        log.debug("REST request to save ConsultaPractica : {}", consultaPracticaDTO);
        if (consultaPracticaDTO.getId() != null) {
            throw new BadRequestAlertException("A new consultaPractica cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ConsultaPracticaDTO result = consultaPracticaService.save(consultaPracticaDTO);
        return ResponseEntity.created(new URI("/api/consulta-practicas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /consulta-practicas : Updates an existing consultaPractica.
     *
     * @param consultaPracticaDTO the consultaPracticaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated consultaPracticaDTO,
     * or with status 400 (Bad Request) if the consultaPracticaDTO is not valid,
     * or with status 500 (Internal Server Error) if the consultaPracticaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/consulta-practicas")
    @Timed
    public ResponseEntity<ConsultaPracticaDTO> updateConsultaPractica(@RequestBody ConsultaPracticaDTO consultaPracticaDTO) throws URISyntaxException {
        log.debug("REST request to update ConsultaPractica : {}", consultaPracticaDTO);
        if (consultaPracticaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ConsultaPracticaDTO result = consultaPracticaService.save(consultaPracticaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, consultaPracticaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /consulta-practicas : get all the consultaPracticas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of consultaPracticas in body
     */
    @GetMapping("/consulta-practicas")
    @Timed
    public List<ConsultaPracticaDTO> getAllConsultaPracticas() {
        log.debug("REST request to get all ConsultaPracticas");
        return consultaPracticaService.findAll();
    }

    /**
     * GET  /consulta-practicas/:id : get the "id" consultaPractica.
     *
     * @param id the id of the consultaPracticaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the consultaPracticaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/consulta-practicas/{id}")
    @Timed
    public ResponseEntity<ConsultaPracticaDTO> getConsultaPractica(@PathVariable Long id) {
        log.debug("REST request to get ConsultaPractica : {}", id);
        Optional<ConsultaPracticaDTO> consultaPracticaDTO = consultaPracticaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(consultaPracticaDTO);
    }

    /**
     * DELETE  /consulta-practicas/:id : delete the "id" consultaPractica.
     *
     * @param id the id of the consultaPracticaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/consulta-practicas/{id}")
    @Timed
    public ResponseEntity<Void> deleteConsultaPractica(@PathVariable Long id) {
        log.debug("REST request to delete ConsultaPractica : {}", id);
        consultaPracticaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
