package edu.cinfantes.springbootsss.domain;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Zone {
    private UUID id;
    private String name;
    private Priority priority;
}
