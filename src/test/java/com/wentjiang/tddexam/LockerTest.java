package com.wentjiang.tddexam;

import com.wentjiang.tddexam.exception.BadTicketException;
import com.wentjiang.tddexam.exception.BagTypeNotMatchException;
import com.wentjiang.tddexam.exception.CapacityFullException;
import com.wentjiang.tddexam.exception.TicketUsedException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class LockerTest {

    private Locker defaultLocker;

    @Before
    public void init(){
        defaultLocker = LockerTestUtil.getLocker(10, 5, BagType.S);
    }

    @Test
    public void should_store_bag_success_return_ticket_when_s_locker_save_bag_given_have_capacity_S_locker() {
        Ticket ticket = defaultLocker.storeBag(new Bag(BagType.S));
        Assertions.assertNotNull(ticket);
    }

    @Test
    public void should_store_bag_fail_and_remind_bag_type_error_when_s_locker_save_MType_bag_given_have_capacity_S_locker() {
        Assertions.assertThrows(BagTypeNotMatchException.class, () -> defaultLocker.storeBag(new Bag(BagType.M)));
    }

    @Test
    public void should_store_bag_fail_and_remind_capacity_full_when_s_locker_save_bag_given_full_s_locker() {
        Locker locker = LockerTestUtil.getLocker(10, 10, BagType.S);
        Assertions.assertThrows(CapacityFullException.class, () -> locker.storeBag(new Bag(BagType.S)));
    }

    @Test
    public void should_take_out_bag_success_when_take_out_bag_given_s_locker_valid_ticket() {
        Bag bag = new Bag(BagType.S);
        Ticket ticket = defaultLocker.storeBag(bag);
        Assertions.assertEquals(bag, defaultLocker.takeOutBag(ticket));
    }

    @Test
    public void should_take_out_bag_fail_remind_bad_ticket_when_take_out_bag_given_s_locker_bad_ticket() {
        Ticket validTicket = defaultLocker.storeBag(new Bag(BagType.S));
        Ticket badTicket = new Ticket(BagType.S);
        Assertions.assertThrows(BadTicketException.class, () -> defaultLocker.takeOutBag(badTicket));
    }

    @Test
    public void should_take_out_bag_fail_remind_ticket_used_when_take_out_bag_given_s_locker_used_ticket(){
        Ticket ticket = defaultLocker.storeBag(new Bag(BagType.S));
        defaultLocker.takeOutBag(ticket);
        Assertions.assertThrows(TicketUsedException.class,()->defaultLocker.takeOutBag(ticket));
    }

}
