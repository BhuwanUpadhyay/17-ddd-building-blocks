package io.github.bhuwanupadhyay.rtms.order.domain;

import org.junit.jupiter.api.Test;

import static io.github.bhuwanupadhyay.ddd.Entity.ENTITY_ID_IS_REQUIRED;
import static io.github.bhuwanupadhyay.ddd.test.DomainAssertions.assertThat;
import static io.github.bhuwanupadhyay.rtms.order.domain.OrderId.REFERENCE_IS_REQUIRED;

class OrderTest {

  @Test
  void givenEmptyOrderId_thenOrderShouldNotCreated() {
    assertThat(() -> new Order(null)).hasErrorCode(ENTITY_ID_IS_REQUIRED);

    assertThat(() -> new Order(new OrderId(null))).hasErrorCode(REFERENCE_IS_REQUIRED);
  }

  @Test
  void givenNotEmptyOrderId_thenOrderShouldCreated() {
    assertThat(() -> new Order(new OrderId("O#0001"))).hasNoErrors();
  }
}
