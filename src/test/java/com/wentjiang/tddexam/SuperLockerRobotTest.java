package com.wentjiang.tddexam;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Collections;

public class SuperLockerRobotTest {

    @Test
    public void should_store_bag_success_and_return_ticket_when_SuperLockerRobot_store_bag_given_SuperLockerRobot_manage_one_not_full_L_locker() {
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(Collections.singletonList(LockerTestUtil.getLocker(10, 5, BagType.L)));
        Ticket ticket = superLockerRobot.storeBag(new Bag(BagType.L));
        Assertions.assertNotNull(ticket);
    }

}
