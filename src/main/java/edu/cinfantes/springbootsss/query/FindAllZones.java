package edu.cinfantes.springbootsss.query;

import edu.cinfantes.springbootsss.persistence.SpringZoneRepository;
import edu.cinfantes.springbootsss.persistence.ZoneEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@AllArgsConstructor
public class FindAllZones {
  private final SpringZoneRepository springZoneRepository;

  @GetMapping(path = "/zone")
  public Page<ZoneEntity> findAll(@NotNull Pageable page) {
    PageRequest pageRequest = PageRequest.of(page.getPageNumber(), page.getPageSize());

    Page<ZoneEntity> response = springZoneRepository.findAll(page);

    return new PageImpl<>(response.getContent(), pageRequest, response.getTotalElements());
  }
}
