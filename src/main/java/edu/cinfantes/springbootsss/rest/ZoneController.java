package edu.cinfantes.springbootsss.rest;

import edu.cinfantes.springbootsss.domain.Coordinates;
import edu.cinfantes.springbootsss.domain.Polygon;
import edu.cinfantes.springbootsss.domain.Priority;
import edu.cinfantes.springbootsss.domain.Zone;
import edu.cinfantes.springbootsss.usecase.AddPolygonToZone;
import edu.cinfantes.springbootsss.usecase.CreateZone;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/zone")
public class ZoneController {
  private final CreateZone createZone;
  private final AddPolygonToZone addPolygonToZone;

  @PostMapping
  public void createZone(@Valid @RequestBody CreateZoneRequest request) {
    Zone zone = convertToZone(request);

    createZone.execute(zone);
  }

  @PostMapping(path = "/{zoneId}/polygon")
  public void addPolygon(@Valid @RequestBody AddPolygonRequest request, @PathVariable("zoneId") UUID zoneId) {
    Polygon polygon = convertToPolygon(request, zoneId);

    addPolygonToZone.execute(polygon);
  }

  private Polygon convertToPolygon(AddPolygonRequest request, UUID zoneId) {
    return Polygon.builder()
      .zone(Zone.builder().id(zoneId).build())
      .coordinates1(Coordinates.builder()
        .x(request.getCoordinates1().getX())
        .y(request.getCoordinates1().getY())
        .build())
      .coordinates2(Coordinates.builder()
        .x(request.getCoordinates2().getX())
        .y(request.getCoordinates2().getY())
        .build())
      .coordinates3(Coordinates.builder()
        .x(request.getCoordinates3().getX())
        .y(request.getCoordinates3().getY())
        .build())
      .coordinates4(Coordinates.builder()
        .x(request.getCoordinates4().getX())
        .y(request.getCoordinates4().getY())
        .build())
      .build();
  }

  private Zone convertToZone(CreateZoneRequest request) {
    return Zone.builder()
      .name(request.getName())
      .priority(Priority.fromValue(request.getPriority()))
      .build();
  }
}
