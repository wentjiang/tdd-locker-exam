package com.wentjiang.tddexam;

import com.wentjiang.tddexam.exception.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PrimaryLockerRobotTest {

    @Test
    public void should_remind_at_last_have_one_locker_when_init_robot_given_PrimaryLockerRobot() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PrimaryLockerRobot(new ArrayList<>()));
    }

    @Test
    public void should_return_PrimaryLockerRobot_when_generate_PrimaryLockerRobot_given_M_locker() {
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Collections.singletonList(LockerTestUtil.getLocker(10, 5, BagType.M)));
        Assertions.assertNotNull(primaryLockerRobot);
    }

    @Test
    public void should_remind_Locker_type_not_match_when_generate_PrimaryLockerRobot_given_L_locker() {
        Assertions.assertThrows(LockerTypeNotMatchException.class,
                () -> new PrimaryLockerRobot(Collections.singletonList(LockerTestUtil.getLocker(10, 5, BagType.L))));
    }

    @Test
    public void should_store_Bag_success_when_PrimaryLockerRobot_store_bag_given_PrimaryLockerRobot_manager_one_M_locker_and_m_bag() {
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Collections.singletonList(LockerTestUtil.getLocker(10, 5, BagType.M)));
        Ticket ticket = primaryLockerRobot.storeBag(new Bag(BagType.M));
        Assertions.assertNotNull(ticket);
    }

    @Test
    public void should_store_bag_success_when_PrimaryLockerRobot_store_bag_given_PrimaryLockerRobot_manager_two_m_lockers_and_m_bag() {
        Locker firstLocker = LockerTestUtil.getLocker(10, 5, BagType.M);
        Locker secondLocker = LockerTestUtil.getLocker(10, 5, BagType.M);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(firstLocker, secondLocker));
        Bag bag = new Bag(BagType.M);
        Ticket ticket = primaryLockerRobot.storeBag(bag);
        Assertions.assertNotNull(ticket);
        Assertions.assertEquals(bag, firstLocker.takeOutBag(ticket));
    }

    @Test
    public void should_store_bag_fail_remind_bag_type_exception_when_PrimaryLockerRobot_store_bag_when_PrimaryLockerRobot_manager_2_not_full_M_locker() {
        Locker firstLocker = LockerTestUtil.getLocker(10, 5, BagType.M);
        Locker secondLocker = LockerTestUtil.getLocker(10, 5, BagType.M);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(firstLocker, secondLocker));
        Assertions.assertThrows(BagTypeNotMatchException.class, () -> primaryLockerRobot.storeBag(new Bag(BagType.L)));
    }

    @Test
    public void should_take_out_bag_success_when_take_out_bag_given_PrimaryLockerRobot_manager_two_M_locker_valid_ticket() {
        Locker firstLocker = LockerTestUtil.getLocker(10, 5, BagType.M);
        Locker secondLocker = LockerTestUtil.getLocker(10, 5, BagType.M);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(firstLocker, secondLocker));
        Bag bag = new Bag(BagType.M);
        Ticket ticket = primaryLockerRobot.storeBag(bag);
        Assertions.assertEquals(bag, primaryLockerRobot.takeOutBag(ticket));
    }

    @Test
    public void should_take_out_fail_remind_bad_ticket_when_take_out_bag_given_PrimaryLockerRobot_manager_two_M_locker_bad_ticket() {
        Locker firstLocker = LockerTestUtil.getLocker(10, 5, BagType.M);
        Locker secondLocker = LockerTestUtil.getLocker(10, 5, BagType.M);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(firstLocker, secondLocker));
        Bag bag = new Bag(BagType.M);
        Ticket ticket = primaryLockerRobot.storeBag(bag);
        Ticket badTicket = new Ticket(BagType.M);
        Assertions.assertThrows(BadTicketException.class, () -> primaryLockerRobot.takeOutBag(badTicket));
    }

    @Test
    public void should_take_out_fail_remind_used_ticket_when_take_out_bag_given_PrimaryLockerRobot_manager_two_M_locker_used_ticket() {
        Locker firstLocker = LockerTestUtil.getLocker(10, 5, BagType.M);
        Locker secondLocker = LockerTestUtil.getLocker(10, 5, BagType.M);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(firstLocker, secondLocker));
        Bag bag = new Bag(BagType.M);
        Ticket ticket = primaryLockerRobot.storeBag(bag);
        primaryLockerRobot.takeOutBag(ticket);
        Assertions.assertThrows(TicketUsedException.class, () -> primaryLockerRobot.takeOutBag(ticket));
    }

    @Test
    public void should_take_out_fail_remind_ticket_type_error_when_take_out_bag_given_PrimaryLockerRobot_manager_two_M_locker_S_ticket() {
        Locker firstLocker = LockerTestUtil.getLocker(10, 5, BagType.M);
        Locker secondLocker = LockerTestUtil.getLocker(10, 5, BagType.M);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(firstLocker, secondLocker));
        Assertions.assertThrows(TicketTypeNotMatchException.class, () -> primaryLockerRobot.takeOutBag(new Ticket(BagType.S)));
    }

    @Test
    public void should_store_bag_fail_remind_capacity_full_when_store_bag_given_PrimaryLockerRobot_manager_one_full_M_locker() {
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Collections.singletonList(LockerTestUtil.getLocker(10, 10, BagType.M)));
        Assertions.assertThrows(CapacityFullException.class, () -> primaryLockerRobot.storeBag(new Bag(BagType.M)));
    }

}