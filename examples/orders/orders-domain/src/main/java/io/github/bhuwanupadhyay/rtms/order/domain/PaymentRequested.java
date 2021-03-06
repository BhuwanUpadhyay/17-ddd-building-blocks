package io.github.bhuwanupadhyay.rtms.order.domain;

import io.github.bhuwanupadhyay.ddd.DomainEvent;

public class PaymentRequested extends DomainEvent {

  private final OrderId orderId;

  public PaymentRequested(OrderId orderId) {
    super(DomainEventType.OUTSIDE);
    this.orderId = orderId;
  }

  public OrderId getOrderId() {
    return orderId;
  }
}
