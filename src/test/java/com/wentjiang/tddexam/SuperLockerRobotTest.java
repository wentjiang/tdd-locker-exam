package com.wentjiang.tddexam;

import com.wentjiang.tddexam.exception.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SuperLockerRobotTest {

    @Test
    public void should_remind_at_last_have_one_locker_when_init_robot_given_SuperLockerRobot() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SuperLockerRobot(new ArrayList<>()));
    }

    @Test
    public void should_remind_type_not_match_when_init_superLockerRobot_given_superLockerRobot_M_locker() {
        Assertions.assertThrows(LockerTypeNotMatchException.class, () -> new SuperLockerRobot(Collections.singletonList(LockerTestUtil.getLocker(10, 5, BagType.M))));
    }

    @Test
    public void should_store_bag_success_and_return_ticket_when_SuperLockerRobot_store_bag_given_SuperLockerRobot_manage_one_not_full_L_locker() {
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(Collections.singletonList(LockerTestUtil.getLocker(10, 5, BagType.L)));
        Ticket ticket = superLockerRobot.storeBag(new Bag(BagType.L));
        Assertions.assertNotNull(ticket);
    }

    @Test
    public void should_store_bag_success_in_second_locker_when_uperLockerRobot_store_bag_given_SuperLockerRobot_manage_two_locker_first_10_5_second_10_3() {
        Locker firstLocker = LockerTestUtil.getLocker(10, 5, BagType.L);
        Locker secondLocker = LockerTestUtil.getLocker(10, 3, BagType.L);
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(Arrays.asList(firstLocker, secondLocker));
        Bag bag = new Bag(BagType.L);
        Ticket ticket = superLockerRobot.storeBag(bag);
        Assertions.assertEquals(bag, secondLocker.takeOutBag(ticket));
    }

    @Test
    public void should_store_bag_fail_remind_bag_type_error_when_store_bag_given_SuperLockerRobot_manager_2_not_full_L_locker_S_bag() {
        Locker firstLocker = LockerTestUtil.getLocker(10, 5, BagType.L);
        Locker secondLocker = LockerTestUtil.getLocker(10, 3, BagType.L);
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(Arrays.asList(firstLocker, secondLocker));
        Assertions.assertThrows(BagTypeNotMatchException.class, () -> superLockerRobot.storeBag(new Bag(BagType.S)));
    }

    @Test
    public void should_store_bag_fail_capacity_full_when_store_bag_given_SuperLockerRobot_manager_1_full_L_locker() {
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(Collections.singletonList(LockerTestUtil.getLocker(10, 10, BagType.L)));
        Assertions.assertThrows(CapacityFullException.class, () -> superLockerRobot.storeBag(new Bag(BagType.L)));
    }

    @Test
    public void should_take_out_fail_when_take_out_given_superLockerRobot_manager_two_not_full_locker_S_ticket() {
        Locker firstLocker = LockerTestUtil.getLocker(10, 5, BagType.L);
        Locker secondLocker = LockerTestUtil.getLocker(10, 3, BagType.L);
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(Arrays.asList(firstLocker, secondLocker));
        Assertions.assertThrows(TicketTypeNotMatchException.class, () -> superLockerRobot.takeOutBag(new Ticket(BagType.S)));
    }

    @Test
    public void should_take_out_success_when_take_out_given_superLockerRobot_manager_two_not_full_locker() {
        Locker firstLocker = LockerTestUtil.getLocker(10, 5, BagType.L);
        Locker secondLocker = LockerTestUtil.getLocker(10, 3, BagType.L);
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(Arrays.asList(firstLocker, secondLocker));
        Bag bag = new Bag(BagType.L);
        Ticket ticket = superLockerRobot.storeBag(bag);
        Assertions.assertEquals(bag, superLockerRobot.takeOutBag(ticket));
    }

    @Test
    public void should_take_out_fail_remind_used_ticket_when_take_out_given_superLockerRobot_manager_two_not_full_locker() {
        Locker firstLocker = LockerTestUtil.getLocker(10, 5, BagType.L);
        Locker secondLocker = LockerTestUtil.getLocker(10, 3, BagType.L);
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(Arrays.asList(firstLocker, secondLocker));
        Ticket ticket = superLockerRobot.storeBag(new Bag(BagType.L));
        superLockerRobot.takeOutBag(ticket);
        Assertions.assertThrows(TicketUsedException.class, () -> superLockerRobot.takeOutBag(ticket));
    }

    @Test
    public void should_take_out_fail_remind_bad_ticket_when_take_out_given_superLockerRobot_manager_two_not_full_locker() {
        Locker firstLocker = LockerTestUtil.getLocker(10, 5, BagType.L);
        Locker secondLocker = LockerTestUtil.getLocker(10, 3, BagType.L);
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(Arrays.asList(firstLocker, secondLocker));
        Ticket ticket = superLockerRobot.storeBag(new Bag(BagType.L));
        Ticket badTicket = new Ticket(BagType.L);
        Assertions.assertThrows(BadTicketException.class, () -> superLockerRobot.takeOutBag(badTicket));
    }

}
