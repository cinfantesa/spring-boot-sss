package edu.cinfantes.springbootsss;


import edu.cinfantes.springbootsss.domain.Coordinates;
import edu.cinfantes.springbootsss.domain.Polygon;
import edu.cinfantes.springbootsss.domain.Priority;
import edu.cinfantes.springbootsss.domain.Zone;
import edu.cinfantes.springbootsss.domain.ZoneCriteria;
import edu.cinfantes.springbootsss.domain.repository.ZoneRepository;
import edu.cinfantes.springbootsss.persistence.AssetEntity;
import edu.cinfantes.springbootsss.persistence.SpringAssetRepository;
import edu.cinfantes.springbootsss.persistence.SpringPolygonRepository;
import edu.cinfantes.springbootsss.persistence.SpringZoneRepository;
import edu.cinfantes.springbootsss.persistence.ZoneEntity;
import edu.cinfantes.springbootsss.usecase.AddPolygonToZone;
import edu.cinfantes.springbootsss.usecase.CreateZone;
import edu.cinfantes.springbootsss.usecase.MarkAssetToEvaluate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.UUID;

import static edu.cinfantes.springbootsss.domain.Priority.HIGH;
import static java.util.Collections.singletonList;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.joda.time.DateTime.now;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringBootSssApplicationTest {
  @Autowired
  private CreateZone createZone;
  @Autowired
  private AddPolygonToZone addPolygonToZone;
  @Autowired
  private ZoneRepository zoneRepository;
  @Autowired
  private MarkAssetToEvaluate markAssetToEvaluate;
  @Autowired
  private SpringZoneRepository springZoneRepository;
  @Autowired
  private SpringPolygonRepository springPolygonRepository;
  @Autowired
  private SpringAssetRepository springAssetRepository;

  @Test
  public void should_insert_one_zone() {
    createZone.execute(Zone.builder()
      .name("zone 1")
      .priority(HIGH)
      .build());

    assertThat(springZoneRepository.findAll().size()).isEqualTo(1);
  }

  @Test
  public void should_insert_one_polygon() {
    UUID zoneId = randomUUID();
    springZoneRepository.save(ZoneEntity.builder()
      .id(zoneId)
      .name("nombre")
      .priority(HIGH.getValue())
      .build());

    addPolygonToZone.execute(Polygon.builder()
      .zone(Zone.builder()
        .id(zoneId)
        .build())
      .coordinates1(Coordinates.builder().x(1).y(1).build())
      .coordinates2(Coordinates.builder().x(2).y(2).build())
      .coordinates3(Coordinates.builder().x(3).y(3).build())
      .coordinates4(Coordinates.builder().x(4).y(4).build())
      .build());

    assertThat(springPolygonRepository.findAll().size()).isEqualTo(1);
  }

  @Transactional
  @Test
  public void should_queries_work() {
    springZoneRepository.findAllByPriority(Priority.LOW.getValue()).forEach(System.out::println);
    springZoneRepository.findAllByOrderByNameDesc().forEach(System.out::println);
    springZoneRepository.findByNameContaining("1").forEach(System.out::println);
    springZoneRepository.findAllByCreatedBetween(now().withDate(2018, 12, 27).toDate(), now().withDate(2018, 12, 29).toDate()).forEach(System.out::println);
  }

  @Transactional
  @Test
  public void should_queries_work_again() {
    springZoneRepository.findAllByPolygonsSizeDesc().forEach(System.out::println);
    springZoneRepository.findByPolygonsId(UUID.fromString("5b6db652-30ab-4fd1-a1dc-0c707160de31"));
  }

  @Test
  public void should_specifications_works() {
    zoneRepository.findAllBy(ZoneCriteria.builder()
      .priority(Priority.LOW)
      .build(), 1, 5).forEach(System.out::println);
  }

  @Test
  public void should_insert_asset() {
    UUID zoneId = randomUUID();
    springZoneRepository.save(ZoneEntity.builder()
      .id(zoneId)
      .name("nombre")
      .priority(HIGH.getValue())
      .build());

    UUID assetId = randomUUID();
    springAssetRepository.save(AssetEntity.builder()
      .id(assetId)
      .name("Asset")
      .build());

    markAssetToEvaluate.execute(singletonList(assetId), zoneId);

    assertThat(springAssetRepository.findAll().size()).isEqualTo(1);
  }
}