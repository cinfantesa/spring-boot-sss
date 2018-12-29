package edu.cinfantes.springbootsss.persistence;

import edu.cinfantes.springbootsss.domain.Coordinates;
import edu.cinfantes.springbootsss.domain.Polygon;
import edu.cinfantes.springbootsss.domain.Priority;
import edu.cinfantes.springbootsss.domain.Zone;
import edu.cinfantes.springbootsss.domain.repository.PolygonRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.UUID;

import static edu.cinfantes.springbootsss.domain.Priority.HIGH;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PolygonRepositoryImplTest {
  @Autowired
  private SpringZoneRepository springZoneRepository;
  @Autowired
  private SpringPolygonRepository springPolygonRepository;
  private PolygonRepository polygonRepository;

  @Before
  public void setUp() {
    polygonRepository = new PolygonRepositoryImpl(springPolygonRepository);
  }

  @Test
  public void should_add_polygon_to_zone() {
    UUID existingZoneId = randomUUID();
    springZoneRepository.save(ZoneEntity.builder()
      .id(existingZoneId)
      .name("Nombre")
      .priority(HIGH.getValue())
      .build());

    Polygon polygon = Polygon.builder()
      .id(randomUUID())
      .zone(Zone.builder()
        .id(existingZoneId)
        .build())
      .coordinates1(Coordinates.builder().x(1).y(1).build())
      .coordinates2(Coordinates.builder().x(2).y(2).build())
      .coordinates3(Coordinates.builder().x(3).y(3).build())
      .coordinates4(Coordinates.builder().x(4).y(4).build())
      .build();

    polygonRepository.save(polygon);

    List<PolygonEntity> all = springPolygonRepository.findAll();
    assertThat(all.size()).isEqualTo(1);
    assertThat(all.get(0)).isEqualToComparingFieldByField(polygon);
  }
}