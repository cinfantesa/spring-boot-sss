package edu.cinfantes.springbootsss.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "IN_ZONE")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ZoneEntity {
  @Id
  @Type(type = "uuid-char")
  private UUID id;

  @CreatedDate
  private Date created;

  @LastModifiedDate
  private Date updated;

  private String name;
  private int priority;

  @OneToMany
  @JoinColumn(name = "idz")
  private List<PolygonEntity> polygons;
}
