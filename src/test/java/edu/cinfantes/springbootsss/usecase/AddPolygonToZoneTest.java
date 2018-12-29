package edu.cinfantes.springbootsss.usecase;

import edu.cinfantes.springbootsss.domain.Coordinates;
import edu.cinfantes.springbootsss.domain.Polygon;
import edu.cinfantes.springbootsss.domain.Zone;
import edu.cinfantes.springbootsss.domain.exception.ZoneNotFoundException;
import edu.cinfantes.springbootsss.domain.repository.PolygonRepository;
import edu.cinfantes.springbootsss.domain.repository.ZoneRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AddPolygonToZoneTest {
  @Mock
  private ZoneRepository zoneRepository;
  @Mock
  private PolygonRepository polygonRepository;
  private AddPolygonToZone addPolygonToZone;

  @Before
  public void setUp() {
    addPolygonToZone = new AddPolygonToZone(zoneRepository, polygonRepository);
  }

  @Test
  public void should_throw_exception_when_zone_is_null() {
    Throwable thrown = catchThrowable(() -> addPolygonToZone.execute(null));

    assertThat(thrown).isInstanceOf(ZoneNotFoundException.class);
  }

  @Test
  public void should_throw_exception_when_zone_not_found() {
    UUID notExistingZoneId = UUID.randomUUID();
    Polygon polygon = Polygon.builder()
      .zone(Zone.builder()
        .id(notExistingZoneId)
        .build())
      .build();

    when(zoneRepository.existsById(notExistingZoneId)).thenReturn(false);
    Throwable thrown = catchThrowable(() -> addPolygonToZone.execute(polygon));

    assertThat(thrown).isInstanceOf(ZoneNotFoundException.class);
  }

  @Test
  public void should_create_polygon() {
    UUID existingZoneId = UUID.randomUUID();
    Polygon polygon = Polygon.builder()
      .zone(Zone.builder()
        .id(existingZoneId)
        .build())
      .coordinates1(Coordinates.builder().x(1).y(1).build())
      .coordinates2(Coordinates.builder().x(2).y(2).build())
      .coordinates3(Coordinates.builder().x(3).y(3).build())
      .coordinates4(Coordinates.builder().x(4).y(4).build())
      .build();

    when(zoneRepository.existsById(existingZoneId)).thenReturn(true);

    addPolygonToZone.execute(polygon);

    ArgumentCaptor<Polygon> argumentCaptor = ArgumentCaptor.forClass(Polygon.class);

    verify(polygonRepository, times(1)).save(argumentCaptor.capture());
    verify(zoneRepository, times(1)).existsById(existingZoneId);
    verifyNoMoreInteractions(polygonRepository);
    verifyNoMoreInteractions(zoneRepository);

    assertThat(argumentCaptor.getValue().getId()).isNotNull();
    assertThat(argumentCaptor.getValue().getCoordinates1()).isEqualToComparingFieldByField(polygon.getCoordinates1());
    assertThat(argumentCaptor.getValue().getCoordinates2()).isEqualToComparingFieldByField(polygon.getCoordinates2());
    assertThat(argumentCaptor.getValue().getCoordinates3()).isEqualToComparingFieldByField(polygon.getCoordinates3());
    assertThat(argumentCaptor.getValue().getCoordinates4()).isEqualToComparingFieldByField(polygon.getCoordinates4());
    assertThat(argumentCaptor.getValue().getZone().getId()).isEqualTo(existingZoneId);
  }
}
