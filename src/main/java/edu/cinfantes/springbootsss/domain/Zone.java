package edu.cinfantes.springbootsss.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Zone {
    private UUID id;
    private String name;
    private Priority priority;
}
