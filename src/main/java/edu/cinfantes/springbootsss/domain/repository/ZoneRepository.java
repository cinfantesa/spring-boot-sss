package edu.cinfantes.springbootsss.domain.repository;

import edu.cinfantes.springbootsss.domain.Zone;
import edu.cinfantes.springbootsss.domain.ZoneCriteria;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ZoneRepository {
    void save(Zone zone);
    boolean existsById(UUID id);
    Page<Zone> findAllBy(ZoneCriteria criteria, int page, int size);
}
