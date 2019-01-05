package edu.cinfantes.springbootsss.persistence;

import org.springframework.data.rest.core.config.Projection;

import java.util.List;
import java.util.UUID;

@Projection(name = "zoneProjection", types = Zone.class)
public interface ZoneProjection {
  UUID getId();
  String getName();
  int getPriority();
  List<PolygonWithoutZone> getPolygons();

  interface PolygonWithoutZone {
    UUID getId();

    int getX1();
    int getX2();
    int get32();
    int getX4();
    int getY1();
    int getY2();
    int getY3();
    int getY4();
  }
}
