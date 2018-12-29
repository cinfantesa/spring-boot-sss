package edu.cinfantes.springbootsss.domain.repository;

import edu.cinfantes.springbootsss.domain.Zone;

import java.util.UUID;

public interface ZoneRepository {
    void save(Zone zone);
    boolean existsById(UUID id);
}
