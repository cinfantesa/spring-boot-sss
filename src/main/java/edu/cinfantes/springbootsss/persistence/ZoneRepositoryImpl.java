package edu.cinfantes.springbootsss.persistence;

import edu.cinfantes.springbootsss.domain.Priority;
import edu.cinfantes.springbootsss.domain.Zone;
import edu.cinfantes.springbootsss.domain.ZoneCriteria;
import edu.cinfantes.springbootsss.domain.repository.ZoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static edu.cinfantes.springbootsss.persistence.ZoneSpecifications.createdBetween;
import static edu.cinfantes.springbootsss.persistence.ZoneSpecifications.nameIs;
import static edu.cinfantes.springbootsss.persistence.ZoneSpecifications.priorityIs;

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

  @Override
  public List<Zone> findAllBy(ZoneCriteria criteria) {
    Specification<ZoneEntity> specs = Specification.where(null);

    specs = appendNameSpecification(criteria, specs);
    specs = appendCreatedBetweenSpecification(criteria, specs);
    specs = appendPrioritySpecification(criteria, specs);

    return springZoneRepository.findAll(specs).stream()
      .map(this::mapToZone)
      .collect(Collectors.toList());
  }

  private Zone mapToZone(ZoneEntity entity) {
    return Zone.builder()
      .id(entity.getId())
      .priority(Priority.fromValue(entity.getPriority()))
      .name(entity.getName())
      .build();
  }

  private Specification<ZoneEntity> appendPrioritySpecification(ZoneCriteria criteria, Specification<ZoneEntity> specs) {
    if (criteria.hasPriorityCriteria()) {
      specs = specs.and(priorityIs(criteria.getPriority().getValue()));
    }
    return specs;
  }

  private Specification<ZoneEntity> appendCreatedBetweenSpecification(ZoneCriteria criteria, Specification<ZoneEntity> specs) {
    if (criteria.hasBetweenCriteria()) {
      specs = specs.and(createdBetween(criteria.getFrom().toDate(), criteria.getTo().toDate()));
    }
    return specs;
  }

  private Specification<ZoneEntity> appendNameSpecification(ZoneCriteria criteria, Specification<ZoneEntity> specs) {
    if (criteria.hasNameCriteria()) {
      specs = specs.and(nameIs(criteria.getName()));
    }
    return specs;
  }
}
