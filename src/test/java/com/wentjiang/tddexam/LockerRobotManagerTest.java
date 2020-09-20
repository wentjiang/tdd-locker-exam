package com.wentjiang.tddexam;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Collections;

public class LockerRobotManagerTest {

    @Test
    public void should_store_bag_success_return_ticket_when_manager_store_bag_given_LockerRobotManager_manager_one_s_locker_PrimaryLockerRobot_SuperLockerRobot_S_bag() {
        LockerRobotManager lockerRobotManager = new LockerRobotManager(LockerTestUtil.getLocker(10, 5, BagType.S),
                new PrimaryLockerRobot(Collections.singletonList(LockerTestUtil.getLocker(10, 5, BagType.M))),
                new SuperLockerRobot(Collections.singletonList(LockerTestUtil.getLocker(10, 5, BagType.L))));
        Ticket ticket = lockerRobotManager.storeBag(new Bag(BagType.S));
        Assertions.assertNotNull(ticket);
        Assertions.assertEquals(BagType.S, ticket.getBagType());
    }

    @Test
    public void should_store_bag_success_return_ticket_when_manager_store_bag_given_LockerRobotManager_manager_one_s_locker_PrimaryLockerRobot_SuperLockerRobot_M_bag() {
        LockerRobotManager lockerRobotManager = new LockerRobotManager(LockerTestUtil.getLocker(10, 5, BagType.S),
                new PrimaryLockerRobot(Collections.singletonList(LockerTestUtil.getLocker(10, 5, BagType.M))),
                new SuperLockerRobot(Collections.singletonList(LockerTestUtil.getLocker(10, 5, BagType.L))));
        Ticket ticket = lockerRobotManager.storeBag(new Bag(BagType.M));
        Assertions.assertNotNull(ticket);
        Assertions.assertEquals(BagType.M, ticket.getBagType());
    }

    @Test
    public void should_store_bag_success_return_ticket_when_manager_store_bag_given_LockerRobotManager_manager_one_s_locker_PrimaryLockerRobot_SuperLockerRobot_L_bag() {
        LockerRobotManager lockerRobotManager = new LockerRobotManager(LockerTestUtil.getLocker(10, 5, BagType.S),
                new PrimaryLockerRobot(Collections.singletonList(LockerTestUtil.getLocker(10, 5, BagType.M))),
                new SuperLockerRobot(Collections.singletonList(LockerTestUtil.getLocker(10, 5, BagType.L))));
        Ticket ticket = lockerRobotManager.storeBag(new Bag(BagType.L));
        Assertions.assertNotNull(ticket);
        Assertions.assertEquals(BagType.L, ticket.getBagType());
    }



}
