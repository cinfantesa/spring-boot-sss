package edu.cinfantes.springbootsss.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface SpringZoneRepository extends JpaRepository<ZoneEntity, UUID>, JpaSpecificationExecutor<ZoneEntity> {
  List<ZoneProjection> findByNameContaining(String name);
  List<ZoneProjection> findAllByOrderByNameDesc();
  List<ZoneProjection> findAllByPriority(int priority);
  List<ZoneProjection> findAllByCreatedBetween(Date from, Date to);

  @Query("select z from ZoneEntity z order by z.polygons.size desc")
  List<ZoneProjection> findAllByPolygonsSizeDesc();

  @Query("select z from ZoneEntity z inner join z.polygons p where p.id = :polygonId")
  ZoneProjection findByPolygonsId(@Param("polygonId") UUID polygonId);
}
