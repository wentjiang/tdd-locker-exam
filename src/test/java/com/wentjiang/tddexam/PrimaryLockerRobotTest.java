package com.wentjiang.tddexam;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Collections;

public class PrimaryLockerRobotTest {

    @Test
    public void should_store_Bag_success_when_PrimaryLockerRobot_store_bag_given_PrimaryLockerRobot_manager_one_M_locker_and_m_bag() {
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Collections.singletonList(LockerTestUtil.getLocker(10, 5, BagType.M)));
        Ticket ticket = primaryLockerRobot.storeBag(new Bag(BagType.M));
        Assertions.assertNotNull(ticket);
    }

}