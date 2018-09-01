package com.cpsj.service.impl;

import com.cpsj.service.DiasService;
import com.cpsj.domain.Dias;
import com.cpsj.repository.DiasRepository;
import com.cpsj.service.dto.DiasDTO;
import com.cpsj.service.mapper.DiasMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing Dias.
 */
@Service
@Transactional
public class DiasServiceImpl implements DiasService {

    private final Logger log = LoggerFactory.getLogger(DiasServiceImpl.class);

    private final DiasRepository diasRepository;

    private final DiasMapper diasMapper;

    public DiasServiceImpl(DiasRepository diasRepository, DiasMapper diasMapper) {
        this.diasRepository = diasRepository;
        this.diasMapper = diasMapper;
    }

    /**
     * Save a dias.
     *
     * @param diasDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DiasDTO save(DiasDTO diasDTO) {
        log.debug("Request to save Dias : {}", diasDTO);
        Dias dias = diasMapper.toEntity(diasDTO);
        dias = diasRepository.save(dias);
        return diasMapper.toDto(dias);
    }

    /**
     * Get all the dias.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<DiasDTO> findAll() {
        log.debug("Request to get all Dias");
        return diasRepository.findAll().stream()
            .map(diasMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one dias by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DiasDTO> findOne(Long id) {
        log.debug("Request to get Dias : {}", id);
        return diasRepository.findById(id)
            .map(diasMapper::toDto);
    }

    /**
     * Delete the dias by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Dias : {}", id);
        diasRepository.deleteById(id);
    }
}
