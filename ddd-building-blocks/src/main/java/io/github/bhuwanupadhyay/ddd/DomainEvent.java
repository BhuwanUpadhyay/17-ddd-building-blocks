package io.github.bhuwanupadhyay.ddd;

import java.util.Objects;
import java.util.UUID;

import static io.github.bhuwanupadhyay.ddd.DomainAsserts.begin;

public abstract class DomainEvent {

  private final String eventId = UUID.randomUUID().toString();

  private final String eventClassName = getClass().getName();

  private final String domainEventType;

  public DomainEvent(DomainEventType domainEventType) {
    begin(domainEventType).notNull(DomainError.create(this, "DomainEventTypeIsRequired")).end();
    this.domainEventType = domainEventType.name();
  }

  public boolean isInsideContext() {
    return Objects.equals(DomainEventType.BOTH.name(), this.getDomainEventType())
        || Objects.equals(DomainEventType.INSIDE.name(), this.getDomainEventType());
  }

  public boolean isOutsideContext() {
    return Objects.equals(DomainEventType.BOTH.name(), this.getDomainEventType())
        || Objects.equals(DomainEventType.OUTSIDE.name(), this.getDomainEventType());
  }

  public String getEventId() {
    return eventId;
  }

  public String getEventClassName() {
    return eventClassName;
  }

  private String getDomainEventType() {
    return domainEventType;
  }

  public enum DomainEventType {
    /** Represents domain event is inside same bounded context only. */
    INSIDE,
    /** Represents domain event is only for other bounded context. */
    OUTSIDE,
    /**
     * Represents domain event is available for both i.e. inside same bounded context and other
     * bounded context.
     */
    BOTH
  }
}
