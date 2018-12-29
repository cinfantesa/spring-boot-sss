package edu.cinfantes.springbootsss.persistence;

import edu.cinfantes.springbootsss.domain.Zone;
import edu.cinfantes.springbootsss.domain.repository.ZoneRepository;
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
public class ZoneRepositoryImplTest {
  @Autowired
  private SpringZoneRepository springZoneRepository;
  private ZoneRepository zoneRepository;

  @Before
  public void setUp() {
    zoneRepository = new ZoneRepositoryImpl(springZoneRepository);
  }

  @Test
  public void should_create_one_zone() {
    UUID zoneId = randomUUID();
    Zone zone = Zone.builder()
      .id(zoneId)
      .name("zone 1")
      .priority(HIGH)
      .build();

    zoneRepository.save(zone);
    List<ZoneEntity> all = springZoneRepository.findAll();

    ZoneEntity expectedEntity = ZoneEntity.builder()
      .id(zoneId)
      .name("zone 1")
      .priority(HIGH.getValue())
      .build();

    assertThat(all.size()).isEqualTo(1);
    ZoneEntity actualEntity = all.get(0);
    assertThat(expectedEntity).isEqualToComparingFieldByField(actualEntity);
  }
}