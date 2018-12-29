package edu.cinfantes.springbootsss.persistence;

import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "IN_ZONE")
@Builder
@NoArgsConstructor
class ZoneEntity {
  @Id
  @Type(type = "uuid-char")
  private UUID id;

  private String name;
  private int priority;
}
