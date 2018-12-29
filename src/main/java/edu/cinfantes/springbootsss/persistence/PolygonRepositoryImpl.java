package edu.cinfantes.springbootsss.persistence;

import edu.cinfantes.springbootsss.domain.Polygon;
import edu.cinfantes.springbootsss.domain.repository.PolygonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PolygonRepositoryImpl implements PolygonRepository {
  private final SpringPolygonRepository springPolygonRepository;

  @Override
  public void save(Polygon polygon) {
    PolygonEntity entity = PolygonEntity.builder()
      .id(polygon.getId())
      .zone(ZoneEntity.builder()
        .id(polygon.getZone().getId())
        .build())
      .x1(polygon.getCoordinates1().getX())
      .y1(polygon.getCoordinates1().getY())
      .x2(polygon.getCoordinates2().getX())
      .y2(polygon.getCoordinates2().getY())
      .x3(polygon.getCoordinates3().getX())
      .y3(polygon.getCoordinates3().getY())
      .x4(polygon.getCoordinates4().getX())
      .y4(polygon.getCoordinates4().getY())
      .build();

    springPolygonRepository.save(entity);
  }
}
