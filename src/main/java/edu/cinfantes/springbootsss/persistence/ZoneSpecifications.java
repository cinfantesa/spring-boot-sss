package edu.cinfantes.springbootsss.persistence;

import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

class ZoneSpecifications {
  private static final String NAME_COLUMN = "name";
  private static final String CREATED_COLUMN = "created";
  private static final String PRIORITY_COLUMN = "priority";

  static Specification<ZoneEntity> nameIs(String name) {
    return (Specification<ZoneEntity>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(NAME_COLUMN), name);
  }

  static Specification<ZoneEntity> createdBetween(Date from, Date to) {
    return (Specification<ZoneEntity>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.between(root.get(CREATED_COLUMN), from, to);
  }

  static Specification<ZoneEntity> priorityIs(int priority) {
    return (Specification<ZoneEntity>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(PRIORITY_COLUMN), priority);
  }
}
