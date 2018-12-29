package edu.cinfantes.springbootsss.usecase;

import edu.cinfantes.springbootsss.domain.Zone;
import edu.cinfantes.springbootsss.domain.repository.ZoneRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static edu.cinfantes.springbootsss.domain.Priority.HIGH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CreateZoneTest {
  @Mock
  private ZoneRepository zoneRepository;

  private CreateZone createZone;

  @Before
  public void setUp() {
    createZone = new CreateZone(zoneRepository);
  }

  @Test
  public void should_create_one_zone() {
    Zone zone = Zone.builder()
      .name("zone 1")
      .priority(HIGH)
      .build();

    createZone.execute(zone);

    ArgumentCaptor<Zone> argumentCaptor = ArgumentCaptor.forClass(Zone.class);

    verify(zoneRepository, times(1)).save(argumentCaptor.capture());
    verifyNoMoreInteractions(zoneRepository);

    assertThat(argumentCaptor.getValue().getId()).isNotNull();
    assertThat(argumentCaptor.getValue().getName()).isEqualTo(zone.getName());
    assertThat(argumentCaptor.getValue().getPriority()).isEqualTo(zone.getPriority());
  }
}