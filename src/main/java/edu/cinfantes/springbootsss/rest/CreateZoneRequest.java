package edu.cinfantes.springbootsss.rest;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
class CreateZoneRequest {
  @NotNull(message = "name not found")
  private String name;
  @NotNull(message = "priority not found")
  private Integer priority;
}
