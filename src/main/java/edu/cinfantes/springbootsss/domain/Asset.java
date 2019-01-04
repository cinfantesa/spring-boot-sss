package edu.cinfantes.springbootsss.domain;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class Asset {
  private UUID id;
  private Zone zone;
  private String name;
}
