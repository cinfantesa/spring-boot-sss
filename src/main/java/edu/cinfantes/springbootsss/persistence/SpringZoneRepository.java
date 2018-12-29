package edu.cinfantes.springbootsss.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringZoneRepository extends JpaRepository<ZoneEntity, UUID> {
}
