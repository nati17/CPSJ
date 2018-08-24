package com.cpsj.service.impl;

import com.cpsj.service.ConsultaService;
import com.cpsj.domain.Consulta;
import com.cpsj.repository.ConsultaRepository;
import com.cpsj.service.dto.ConsultaDTO;
import com.cpsj.service.mapper.ConsultaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing Consulta.
 */
@Service
@Transactional
public class ConsultaServiceImpl implements ConsultaService {

    private final Logger log = LoggerFactory.getLogger(ConsultaServiceImpl.class);

    private final ConsultaRepository consultaRepository;

    private final ConsultaMapper consultaMapper;

    public ConsultaServiceImpl(ConsultaRepository consultaRepository, ConsultaMapper consultaMapper) {
        this.consultaRepository = consultaRepository;
        this.consultaMapper = consultaMapper;
    }

    /**
     * Save a consulta.
     *
     * @param consultaDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ConsultaDTO save(ConsultaDTO consultaDTO) {
        log.debug("Request to save Consulta : {}", consultaDTO);
        Consulta consulta = consultaMapper.toEntity(consultaDTO);
        consulta = consultaRepository.save(consulta);
        return consultaMapper.toDto(consulta);
    }

    /**
     * Get all the consultas.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ConsultaDTO> findAll() {
        log.debug("Request to get all Consultas");
        return consultaRepository.findAll().stream()
            .map(consultaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one consulta by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ConsultaDTO> findOne(Long id) {
        log.debug("Request to get Consulta : {}", id);
        return consultaRepository.findById(id)
            .map(consultaMapper::toDto);
    }

    /**
     * Delete the consulta by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Consulta : {}", id);
        consultaRepository.deleteById(id);
    }
}
