package edu.cinfantes.springbootsss.persistence;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "IN_ZONE_POLYGON")
@ToString(exclude = "zone")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolygonEntity {
  @Id
  @Type(type = "uuid-char")
  @Column(name = "idzp")
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "idz")
  private ZoneEntity zone;

  private int x1;
  private int y1;
  private int x2;
  private int y2;
  private int x3;
  private int y3;
  private int x4;
  private int y4;
}
