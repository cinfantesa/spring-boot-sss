package edu.cinfantes.springbootsss.query;

import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ZoneView extends ResourceSupport {
  private UUID uuid;
  private DateTime created;
  private DateTime updated;
  private String name;
  private int priority;
  private List<PolygonView> polygons;
}
