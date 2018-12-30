package edu.cinfantes.springbootsss.domain;

import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;

import static java.util.Objects.nonNull;

@Data
@Builder
public class ZoneCriteria {
  private String name;
  private DateTime from;
  private DateTime to;
  private Priority priority;

  public boolean hasNameCriteria() {
    return nonNull(name);
  }

  public boolean hasBetweenCriteria() {
    return nonNull(from) && nonNull(to);
  }

  public boolean hasPriorityCriteria() {
    return nonNull(priority);
  }
}
