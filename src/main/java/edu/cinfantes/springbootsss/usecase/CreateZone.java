package edu.cinfantes.springbootsss.usecase;

import edu.cinfantes.springbootsss.domain.Zone;
import edu.cinfantes.springbootsss.domain.repository.ZoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class CreateZone {
  private final ZoneRepository zoneRepository;

  public void execute(Zone zone) {
    zone.setId(UUID.randomUUID());
    zoneRepository.save(zone);
  }
}
