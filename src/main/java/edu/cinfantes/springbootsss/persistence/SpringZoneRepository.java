package edu.cinfantes.springbootsss.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RepositoryRestResource(path = "zonas", excerptProjection = ZoneProjection.class)
public interface SpringZoneRepository extends JpaRepository<Zone, UUID>, JpaSpecificationExecutor<Zone> {
  @Override
  <S extends Zone> List<S> saveAll(Iterable<S> iterable);

  @RestResource(path = "prueba", rel = "prueba")
  List<ZoneProjection> findByNameContaining(String name);
  List<ZoneProjection> findAllByOrderByNameDesc();
  List<ZoneProjection> findAllByPriority(int priority);
  List<ZoneProjection> findAllByCreatedBetween(Date from, Date to);

  @RestResource(exported = false)
  @Query("select z from Zone z order by z.polygons.size desc")
  List<ZoneProjection> findAllByPolygonsSizeDesc();

  @Query("select z from Zone z inner join z.polygons p where p.id = :polygonId")
  ZoneProjection findByPolygonsId(@Param("polygonId") UUID polygonId);
}
