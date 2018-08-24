package com.cpsj.service.impl;

import com.cpsj.service.AlergiaService;
import com.cpsj.domain.Alergia;
import com.cpsj.repository.AlergiaRepository;
import com.cpsj.service.dto.AlergiaDTO;
import com.cpsj.service.mapper.AlergiaMapper;
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
 * Service Implementation for managing Alergia.
 */
@Service
@Transactional
public class AlergiaServiceImpl implements AlergiaService {

    private final Logger log = LoggerFactory.getLogger(AlergiaServiceImpl.class);

    private final AlergiaRepository alergiaRepository;

    private final AlergiaMapper alergiaMapper;

    public AlergiaServiceImpl(AlergiaRepository alergiaRepository, AlergiaMapper alergiaMapper) {
        this.alergiaRepository = alergiaRepository;
        this.alergiaMapper = alergiaMapper;
    }

    /**
     * Save a alergia.
     *
     * @param alergiaDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AlergiaDTO save(AlergiaDTO alergiaDTO) {
        log.debug("Request to save Alergia : {}", alergiaDTO);
        Alergia alergia = alergiaMapper.toEntity(alergiaDTO);
        alergia = alergiaRepository.save(alergia);
        return alergiaMapper.toDto(alergia);
    }

    /**
     * Get all the alergias.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AlergiaDTO> findAll() {
        log.debug("Request to get all Alergias");
        return alergiaRepository.findAllWithEagerRelationships().stream()
            .map(alergiaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the Alergia with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<AlergiaDTO> findAllWithEagerRelationships(Pageable pageable) {
        return alergiaRepository.findAllWithEagerRelationships(pageable).map(alergiaMapper::toDto);
    }
    

    /**
     * Get one alergia by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AlergiaDTO> findOne(Long id) {
        log.debug("Request to get Alergia : {}", id);
        return alergiaRepository.findOneWithEagerRelationships(id)
            .map(alergiaMapper::toDto);
    }

    /**
     * Delete the alergia by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Alergia : {}", id);
        alergiaRepository.deleteById(id);
    }
}
