package com.wentjiang.tddexam;

import com.wentjiang.tddexam.exception.BadTicketException;
import com.wentjiang.tddexam.exception.TicketUsedException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import java.util.Collections;

public class LockerRobotManagerTest {

    private LockerRobotManager defaultManager;
    @Before
    public void init(){
        defaultManager = new LockerRobotManager(LockerTestUtil.getLocker(10, 5, BagType.S),
                new PrimaryLockerRobot(Collections.singletonList(LockerTestUtil.getLocker(10, 5, BagType.M))),
                new SuperLockerRobot(Collections.singletonList(LockerTestUtil.getLocker(10, 5, BagType.L))));
    }

    @Test
    public void should_store_bag_success_return_ticket_when_manager_store_bag_given_LockerRobotManager_manager_one_s_locker_PrimaryLockerRobot_SuperLockerRobot_S_bag() {
        Ticket ticket = defaultManager.storeBag(new Bag(BagType.S));
        Assertions.assertNotNull(ticket);
        Assertions.assertEquals(BagType.S, ticket.getBagType());
    }

    @Test
    public void should_store_bag_success_return_ticket_when_manager_store_bag_given_LockerRobotManager_manager_one_s_locker_PrimaryLockerRobot_SuperLockerRobot_M_bag() {
        Ticket ticket = defaultManager.storeBag(new Bag(BagType.M));
        Assertions.assertNotNull(ticket);
        Assertions.assertEquals(BagType.M, ticket.getBagType());
    }

    @Test
    public void should_store_bag_success_return_ticket_when_manager_store_bag_given_LockerRobotManager_manager_one_s_locker_PrimaryLockerRobot_SuperLockerRobot_L_bag() {
        Ticket ticket = defaultManager.storeBag(new Bag(BagType.L));
        Assertions.assertNotNull(ticket);
        Assertions.assertEquals(BagType.L, ticket.getBagType());
    }

    @Test
    public void should_take_out_success_when_manager_take_out_bag_given_LockerRobotManager_valid_S_ticket() {
        Bag bag = new Bag(BagType.S);
        Ticket ticket = defaultManager.storeBag(bag);
        Assertions.assertEquals(bag, defaultManager.takeOutBag(ticket));
    }

    @Test
    public void should_take_out_success_when_manager_take_out_bag_given_LockerRobotManager_valid_M_ticket() {
        Bag bag = new Bag(BagType.M);
        Ticket ticket = defaultManager.storeBag(bag);
        Assertions.assertEquals(bag, defaultManager.takeOutBag(ticket));
    }

    @Test
    public void should_take_out_success_when_manager_take_out_bag_given_LockerRobotManager_valid_L_ticket() {
        Bag bag = new Bag(BagType.L);
        Ticket ticket = defaultManager.storeBag(bag);
        Assertions.assertEquals(bag, defaultManager.takeOutBag(ticket));
    }

    @Test
    public void should_take_out_fail_remind_bad_ticket_when_take_out_given_LockerRobotManager_bad_ticket() {
        Ticket ticket = defaultManager.storeBag(new Bag(BagType.L));
        Ticket badTicket = new Ticket(BagType.L);
        Assertions.assertThrows(BadTicketException.class, () -> defaultManager.takeOutBag(badTicket));
    }

    @Test
    public void should_take_out_fail_remind_used_ticket_when_take_out_given_LockerRobotManager_used_ticket() {
        Ticket ticket = defaultManager.storeBag(new Bag(BagType.L));
        defaultManager.takeOutBag(ticket);
        Assertions.assertThrows(TicketUsedException.class, () -> defaultManager.takeOutBag(ticket));
    }
}
