package edu.cinfantes.springbootsss.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Polygon {
  private UUID id;
  private Zone zone;
  private Coordinates coordinates1;
  private Coordinates coordinates2;
  private Coordinates coordinates3;
  private Coordinates coordinates4;
}
