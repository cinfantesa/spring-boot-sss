package edu.cinfantes.springbootsss.domain.repository;

import edu.cinfantes.springbootsss.domain.Zone;
import edu.cinfantes.springbootsss.domain.ZoneCriteria;

import java.util.List;
import java.util.UUID;

public interface ZoneRepository {
    void save(Zone zone);
    boolean existsById(UUID id);
    List<Zone> findAllBy(ZoneCriteria criteria);
}
