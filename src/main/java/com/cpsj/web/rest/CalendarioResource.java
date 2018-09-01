package com.cpsj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpsj.service.CalendarioService;
import com.cpsj.web.rest.errors.BadRequestAlertException;
import com.cpsj.web.rest.util.HeaderUtil;
import com.cpsj.service.dto.CalendarioDTO;
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
 * REST controller for managing Calendario.
 */
@RestController
@RequestMapping("/api")
public class CalendarioResource {

    private final Logger log = LoggerFactory.getLogger(CalendarioResource.class);

    private static final String ENTITY_NAME = "calendario";

    private final CalendarioService calendarioService;

    public CalendarioResource(CalendarioService calendarioService) {
        this.calendarioService = calendarioService;
    }

    /**
     * POST  /calendarios : Create a new calendario.
     *
     * @param calendarioDTO the calendarioDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new calendarioDTO, or with status 400 (Bad Request) if the calendario has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/calendarios")
    @Timed
    public ResponseEntity<CalendarioDTO> createCalendario(@RequestBody CalendarioDTO calendarioDTO) throws URISyntaxException {
        log.debug("REST request to save Calendario : {}", calendarioDTO);
        if (calendarioDTO.getId() != null) {
            throw new BadRequestAlertException("A new calendario cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CalendarioDTO result = calendarioService.save(calendarioDTO);
        return ResponseEntity.created(new URI("/api/calendarios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /calendarios : Updates an existing calendario.
     *
     * @param calendarioDTO the calendarioDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated calendarioDTO,
     * or with status 400 (Bad Request) if the calendarioDTO is not valid,
     * or with status 500 (Internal Server Error) if the calendarioDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/calendarios")
    @Timed
    public ResponseEntity<CalendarioDTO> updateCalendario(@RequestBody CalendarioDTO calendarioDTO) throws URISyntaxException {
        log.debug("REST request to update Calendario : {}", calendarioDTO);
        if (calendarioDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CalendarioDTO result = calendarioService.save(calendarioDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, calendarioDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /calendarios : get all the calendarios.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of calendarios in body
     */
    @GetMapping("/calendarios")
    @Timed
    public List<CalendarioDTO> getAllCalendarios() {
        log.debug("REST request to get all Calendarios");
        return calendarioService.findAll();
    }

    /**
     * GET  /calendarios/:id : get the "id" calendario.
     *
     * @param id the id of the calendarioDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the calendarioDTO, or with status 404 (Not Found)
     */
    @GetMapping("/calendarios/{id}")
    @Timed
    public ResponseEntity<CalendarioDTO> getCalendario(@PathVariable Long id) {
        log.debug("REST request to get Calendario : {}", id);
        Optional<CalendarioDTO> calendarioDTO = calendarioService.findOne(id);
        return ResponseUtil.wrapOrNotFound(calendarioDTO);
    }

    /**
     * DELETE  /calendarios/:id : delete the "id" calendario.
     *
     * @param id the id of the calendarioDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/calendarios/{id}")
    @Timed
    public ResponseEntity<Void> deleteCalendario(@PathVariable Long id) {
        log.debug("REST request to delete Calendario : {}", id);
        calendarioService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
