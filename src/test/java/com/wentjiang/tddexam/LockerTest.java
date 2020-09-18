package com.wentjiang.tddexam;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class LockerTest {

    @Test
    public void should_store_bag_success_return_ticket_when_s_locker_save_bag_given_have_capacity_S_locker() {
        Locker locker = LockerTestUtil.getLocker(10, 5, BagType.S);
        Ticket ticket = locker.storeBag(new Bag(BagType.S));
        Assertions.assertNotNull(ticket);
    }
}
