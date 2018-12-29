package edu.cinfantes.springbootsss.persistence;

import edu.cinfantes.springbootsss.domain.Zone;
import edu.cinfantes.springbootsss.domain.repository.ZoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class ZoneRepositoryImpl implements ZoneRepository {
  private final SpringZoneRepository springZoneRepository;

  @Override
  public void save(Zone zone) {
    ZoneEntity entity = ZoneEntity.builder()
      .id(zone.getId())
      .name(zone.getName())
      .priority(zone.getPriority().getValue())
      .build();

    springZoneRepository.save(entity);
  }

  @Override
  public boolean existsById(UUID id) {
    return springZoneRepository.existsById(id);
  }
}
