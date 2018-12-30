package edu.cinfantes.springbootsss.rest;

import edu.cinfantes.springbootsss.domain.Priority;
import edu.cinfantes.springbootsss.domain.Zone;
import edu.cinfantes.springbootsss.usecase.CreateZone;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/zone")
public class ZoneController {
  private final CreateZone createZone;

  @PostMapping
  public void createZone(@Valid @RequestBody CreateZoneRequest request) {
    Zone zone = convertToZone(request);

    createZone.execute(zone);
  }

  private Zone convertToZone(CreateZoneRequest request) {
    return Zone.builder()
      .name(request.getName())
      .priority(Priority.fromValue(request.getPriority()))
      .build();
  }
}
