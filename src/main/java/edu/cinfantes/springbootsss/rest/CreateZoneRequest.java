package edu.cinfantes.springbootsss.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class CreateZoneRequest {
  @NotNull(message = "name not found")
  private String name;
  @NotNull(message = "priority not found")
  private Integer priority;
}
