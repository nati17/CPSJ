package com.cpsj.service.impl;

import com.cpsj.service.RegimenService;
import com.cpsj.domain.Regimen;
import com.cpsj.repository.RegimenRepository;
import com.cpsj.service.dto.RegimenDTO;
import com.cpsj.service.mapper.RegimenMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing Regimen.
 */
@Service
@Transactional
public class RegimenServiceImpl implements RegimenService {

    private final Logger log = LoggerFactory.getLogger(RegimenServiceImpl.class);

    private final RegimenRepository regimenRepository;

    private final RegimenMapper regimenMapper;

    public RegimenServiceImpl(RegimenRepository regimenRepository, RegimenMapper regimenMapper) {
        this.regimenRepository = regimenRepository;
        this.regimenMapper = regimenMapper;
    }

    /**
     * Save a regimen.
     *
     * @param regimenDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RegimenDTO save(RegimenDTO regimenDTO) {
        log.debug("Request to save Regimen : {}", regimenDTO);
        Regimen regimen = regimenMapper.toEntity(regimenDTO);
        regimen = regimenRepository.save(regimen);
        return regimenMapper.toDto(regimen);
    }

    /**
     * Get all the regimen.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<RegimenDTO> findAll() {
        log.debug("Request to get all Regimen");
        return regimenRepository.findAllWithEagerRelationships().stream()
            .map(regimenMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the Regimen with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<RegimenDTO> findAllWithEagerRelationships(Pageable pageable) {
        return regimenRepository.findAllWithEagerRelationships(pageable).map(regimenMapper::toDto);
    }
    

    /**
     * Get one regimen by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RegimenDTO> findOne(Long id) {
        log.debug("Request to get Regimen : {}", id);
        return regimenRepository.findOneWithEagerRelationships(id)
            .map(regimenMapper::toDto);
    }

    /**
     * Delete the regimen by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Regimen : {}", id);
        regimenRepository.deleteById(id);
    }
}
