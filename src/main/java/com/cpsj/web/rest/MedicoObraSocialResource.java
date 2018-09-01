package com.cpsj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpsj.service.MedicoObraSocialService;
import com.cpsj.web.rest.errors.BadRequestAlertException;
import com.cpsj.web.rest.util.HeaderUtil;
import com.cpsj.service.dto.MedicoObraSocialDTO;
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
 * REST controller for managing MedicoObraSocial.
 */
@RestController
@RequestMapping("/api")
public class MedicoObraSocialResource {

    private final Logger log = LoggerFactory.getLogger(MedicoObraSocialResource.class);

    private static final String ENTITY_NAME = "medicoObraSocial";

    private final MedicoObraSocialService medicoObraSocialService;

    public MedicoObraSocialResource(MedicoObraSocialService medicoObraSocialService) {
        this.medicoObraSocialService = medicoObraSocialService;
    }

    /**
     * POST  /medico-obra-socials : Create a new medicoObraSocial.
     *
     * @param medicoObraSocialDTO the medicoObraSocialDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new medicoObraSocialDTO, or with status 400 (Bad Request) if the medicoObraSocial has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/medico-obra-socials")
    @Timed
    public ResponseEntity<MedicoObraSocialDTO> createMedicoObraSocial(@RequestBody MedicoObraSocialDTO medicoObraSocialDTO) throws URISyntaxException {
        log.debug("REST request to save MedicoObraSocial : {}", medicoObraSocialDTO);
        if (medicoObraSocialDTO.getId() != null) {
            throw new BadRequestAlertException("A new medicoObraSocial cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MedicoObraSocialDTO result = medicoObraSocialService.save(medicoObraSocialDTO);
        return ResponseEntity.created(new URI("/api/medico-obra-socials/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /medico-obra-socials : Updates an existing medicoObraSocial.
     *
     * @param medicoObraSocialDTO the medicoObraSocialDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated medicoObraSocialDTO,
     * or with status 400 (Bad Request) if the medicoObraSocialDTO is not valid,
     * or with status 500 (Internal Server Error) if the medicoObraSocialDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/medico-obra-socials")
    @Timed
    public ResponseEntity<MedicoObraSocialDTO> updateMedicoObraSocial(@RequestBody MedicoObraSocialDTO medicoObraSocialDTO) throws URISyntaxException {
        log.debug("REST request to update MedicoObraSocial : {}", medicoObraSocialDTO);
        if (medicoObraSocialDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MedicoObraSocialDTO result = medicoObraSocialService.save(medicoObraSocialDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, medicoObraSocialDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /medico-obra-socials : get all the medicoObraSocials.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of medicoObraSocials in body
     */
    @GetMapping("/medico-obra-socials")
    @Timed
    public List<MedicoObraSocialDTO> getAllMedicoObraSocials() {
        log.debug("REST request to get all MedicoObraSocials");
        return medicoObraSocialService.findAll();
    }

    /**
     * GET  /medico-obra-socials/:id : get the "id" medicoObraSocial.
     *
     * @param id the id of the medicoObraSocialDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the medicoObraSocialDTO, or with status 404 (Not Found)
     */
    @GetMapping("/medico-obra-socials/{id}")
    @Timed
    public ResponseEntity<MedicoObraSocialDTO> getMedicoObraSocial(@PathVariable Long id) {
        log.debug("REST request to get MedicoObraSocial : {}", id);
        Optional<MedicoObraSocialDTO> medicoObraSocialDTO = medicoObraSocialService.findOne(id);
        return ResponseUtil.wrapOrNotFound(medicoObraSocialDTO);
    }

    /**
     * DELETE  /medico-obra-socials/:id : delete the "id" medicoObraSocial.
     *
     * @param id the id of the medicoObraSocialDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/medico-obra-socials/{id}")
    @Timed
    public ResponseEntity<Void> deleteMedicoObraSocial(@PathVariable Long id) {
        log.debug("REST request to delete MedicoObraSocial : {}", id);
        medicoObraSocialService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
