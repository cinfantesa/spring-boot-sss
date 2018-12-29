package edu.cinfantes.springbootsss.usecase;

import edu.cinfantes.springbootsss.domain.Polygon;
import edu.cinfantes.springbootsss.domain.Zone;
import edu.cinfantes.springbootsss.domain.exception.ZoneNotFoundException;
import edu.cinfantes.springbootsss.domain.repository.PolygonRepository;
import edu.cinfantes.springbootsss.domain.repository.ZoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static java.util.UUID.randomUUID;

@Component
@AllArgsConstructor
public class AddPolygonToZone {
  private final ZoneRepository zoneRepository;
  private final PolygonRepository polygonRepository;

  public void execute(Polygon polygon){
    assertHasAValidZone(polygon.getZone());

    polygon.setId(randomUUID());

    polygonRepository.save(polygon);
  }

  private void assertHasAValidZone(Zone zone) {
    if (isNotExistingZone(zone)) {
      throw new ZoneNotFoundException();
    }
  }

  private boolean isNotExistingZone(Zone zone) {
    return Objects.isNull(zone.getId()) || !zoneRepository.existsById(zone.getId());
  }
}
