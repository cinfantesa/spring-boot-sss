package edu.cinfantes.springbootsss;


import edu.cinfantes.springbootsss.domain.Zone;
import edu.cinfantes.springbootsss.persistence.SpringZoneRepository;
import edu.cinfantes.springbootsss.usecase.CreateZone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static edu.cinfantes.springbootsss.domain.Priority.HIGH;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringBootSssApplicationTest {
  @Autowired
  private CreateZone createZone;
  @Autowired
  private SpringZoneRepository springZoneRepository;

  @Test
  public void should_insert_one_zone() {
    createZone.execute(Zone.builder()
      .name("zone 1")
      .priority(HIGH)
      .build());

    assertEquals(1, springZoneRepository.findAll().size());
  }
}