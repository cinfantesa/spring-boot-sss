package edu.cinfantes.springbootsss.query;

import edu.cinfantes.springbootsss.domain.Zone;
import edu.cinfantes.springbootsss.persistence.SpringZoneRepository;
import edu.cinfantes.springbootsss.rest.ZoneController;
import lombok.AllArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@AllArgsConstructor
public class FindAllZones {
  private final SpringZoneRepository springZoneRepository;
  private final EntityLinks entityLinks;

  @GetMapping(path = "/zone")
  public Page<ZoneView> findAll(@NotNull Pageable page) {
    PageRequest pageRequest = PageRequest.of(page.getPageNumber(), page.getPageSize());

    Page<ZoneView> response = convertToView(page);

    response.forEach(zone -> {
      zone.add(new Link("www.google.es").withRel("google"));
      zone.add(linkTo(ZoneController.class).withRel("zone"));
      zone.add(linkTo(ZoneController.class).slash(zone.getUuid()).withSelfRel());
      zone.add(entityLinks.linkToCollectionResource(Zone.class));
    });

    return new PageImpl<>(response.getContent(), pageRequest, response.getTotalElements());
  }

  private Page<ZoneView> convertToView(@NotNull Pageable page) {
    return springZoneRepository.findAll(page).map(entity -> {
        ZoneView view = ZoneView.builder()
          .uuid(entity.getId())
          .created(new DateTime(entity.getCreated()))
          .updated(new DateTime(entity.getUpdated()))
          .name(entity.getName())
          .priority(entity.getPriority())
          .build();

        if (nonNull(entity.getPolygons())) {
          List<PolygonView> polygonViews = entity.getPolygons().stream()
            .map(it -> PolygonView.builder()
              .uuid(it.getId())
              .x1(it.getX1())
              .x2(it.getX2())
              .x3(it.getX3())
              .x4(it.getX4())
              .y1(it.getY1())
              .y2(it.getY2())
              .y3(it.getY3())
              .y4(it.getY4())
              .build())
            .collect(Collectors.toList());

          view.setPolygons(polygonViews);
        }

        return view;
      });
  }
}
