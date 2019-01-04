package edu.cinfantes.springbootsss.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "TERMINAL_ASSET")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetEntity {
  @Id
  @Type(type = "uuid-char")
  private UUID id;

  private String name;

  @ManyToOne
  @JoinColumn(name = "idiz")
  private ZoneEntity zone;
}
