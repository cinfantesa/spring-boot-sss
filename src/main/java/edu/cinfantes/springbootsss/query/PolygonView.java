package edu.cinfantes.springbootsss.query;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PolygonView {
  private UUID uuid;

  private int x1;
  private int y1;
  private int x2;
  private int y2;
  private int x3;
  private int y3;
  private int x4;
  private int y4;
}
