package com.cpsj.service.impl;

import com.cpsj.service.MedicoEspecialidadDiagnosticoService;
import com.cpsj.domain.MedicoEspecialidadDiagnostico;
import com.cpsj.repository.MedicoEspecialidadDiagnosticoRepository;
import com.cpsj.service.dto.MedicoEspecialidadDiagnosticoDTO;
import com.cpsj.service.mapper.MedicoEspecialidadDiagnosticoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing MedicoEspecialidadDiagnostico.
 */
@Service
@Transactional
public class MedicoEspecialidadDiagnosticoServiceImpl implements MedicoEspecialidadDiagnosticoService {

    private final Logger log = LoggerFactory.getLogger(MedicoEspecialidadDiagnosticoServiceImpl.class);

    private final MedicoEspecialidadDiagnosticoRepository medicoEspecialidadDiagnosticoRepository;

    private final MedicoEspecialidadDiagnosticoMapper medicoEspecialidadDiagnosticoMapper;

    public MedicoEspecialidadDiagnosticoServiceImpl(MedicoEspecialidadDiagnosticoRepository medicoEspecialidadDiagnosticoRepository, MedicoEspecialidadDiagnosticoMapper medicoEspecialidadDiagnosticoMapper) {
        this.medicoEspecialidadDiagnosticoRepository = medicoEspecialidadDiagnosticoRepository;
        this.medicoEspecialidadDiagnosticoMapper = medicoEspecialidadDiagnosticoMapper;
    }

    /**
     * Save a medicoEspecialidadDiagnostico.
     *
     * @param medicoEspecialidadDiagnosticoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MedicoEspecialidadDiagnosticoDTO save(MedicoEspecialidadDiagnosticoDTO medicoEspecialidadDiagnosticoDTO) {
        log.debug("Request to save MedicoEspecialidadDiagnostico : {}", medicoEspecialidadDiagnosticoDTO);
        MedicoEspecialidadDiagnostico medicoEspecialidadDiagnostico = medicoEspecialidadDiagnosticoMapper.toEntity(medicoEspecialidadDiagnosticoDTO);
        medicoEspecialidadDiagnostico = medicoEspecialidadDiagnosticoRepository.save(medicoEspecialidadDiagnostico);
        return medicoEspecialidadDiagnosticoMapper.toDto(medicoEspecialidadDiagnostico);
    }

    /**
     * Get all the medicoEspecialidadDiagnosticos.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<MedicoEspecialidadDiagnosticoDTO> findAll() {
        log.debug("Request to get all MedicoEspecialidadDiagnosticos");
        return medicoEspecialidadDiagnosticoRepository.findAll().stream()
            .map(medicoEspecialidadDiagnosticoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one medicoEspecialidadDiagnostico by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MedicoEspecialidadDiagnosticoDTO> findOne(Long id) {
        log.debug("Request to get MedicoEspecialidadDiagnostico : {}", id);
        return medicoEspecialidadDiagnosticoRepository.findById(id)
            .map(medicoEspecialidadDiagnosticoMapper::toDto);
    }

    /**
     * Delete the medicoEspecialidadDiagnostico by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MedicoEspecialidadDiagnostico : {}", id);
        medicoEspecialidadDiagnosticoRepository.deleteById(id);
    }
}
