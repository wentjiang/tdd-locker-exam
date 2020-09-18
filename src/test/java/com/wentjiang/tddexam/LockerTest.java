package com.wentjiang.tddexam;

import com.wentjiang.tddexam.exception.CapacityFullException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class LockerTest {

    @Test
    public void should_store_bag_success_return_ticket_when_s_locker_save_bag_given_have_capacity_S_locker() {
        Locker locker = LockerTestUtil.getLocker(10, 5, BagType.S);
        Ticket ticket = locker.storeBag(new Bag(BagType.S));
        Assertions.assertNotNull(ticket);
    }

    @Test
    public void should_store_bag_fail_and_remind_capacity_full_when_s_locker_save_bag_given_full_s_locker() {
        Locker locker = LockerTestUtil.getLocker(10, 10, BagType.S);
        Assertions.assertThrows(CapacityFullException.class, () -> locker.storeBag(new Bag(BagType.S)));
    }
}
