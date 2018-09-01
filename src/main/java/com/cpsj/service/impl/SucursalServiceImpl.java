package com.cpsj.service.impl;

import com.cpsj.service.SucursalService;
import com.cpsj.domain.Sucursal;
import com.cpsj.repository.SucursalRepository;
import com.cpsj.service.dto.SucursalDTO;
import com.cpsj.service.mapper.SucursalMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing Sucursal.
 */
@Service
@Transactional
public class SucursalServiceImpl implements SucursalService {

    private final Logger log = LoggerFactory.getLogger(SucursalServiceImpl.class);

    private final SucursalRepository sucursalRepository;

    private final SucursalMapper sucursalMapper;

    public SucursalServiceImpl(SucursalRepository sucursalRepository, SucursalMapper sucursalMapper) {
        this.sucursalRepository = sucursalRepository;
        this.sucursalMapper = sucursalMapper;
    }

    /**
     * Save a sucursal.
     *
     * @param sucursalDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SucursalDTO save(SucursalDTO sucursalDTO) {
        log.debug("Request to save Sucursal : {}", sucursalDTO);
        Sucursal sucursal = sucursalMapper.toEntity(sucursalDTO);
        sucursal = sucursalRepository.save(sucursal);
        return sucursalMapper.toDto(sucursal);
    }

    /**
     * Get all the sucursals.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SucursalDTO> findAll() {
        log.debug("Request to get all Sucursals");
        return sucursalRepository.findAll().stream()
            .map(sucursalMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one sucursal by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SucursalDTO> findOne(Long id) {
        log.debug("Request to get Sucursal : {}", id);
        return sucursalRepository.findById(id)
            .map(sucursalMapper::toDto);
    }

    /**
     * Delete the sucursal by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Sucursal : {}", id);
        sucursalRepository.deleteById(id);
    }
}
