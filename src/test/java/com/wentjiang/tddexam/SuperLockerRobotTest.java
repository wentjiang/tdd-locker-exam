package com.wentjiang.tddexam;

import com.wentjiang.tddexam.exception.BagTypeNotMatchException;
import com.wentjiang.tddexam.exception.CapacityFullException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Collections;

public class SuperLockerRobotTest {

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
    public void should_take_out_success_when_take_out_given_superLockerRobot_manager_two_not_full_locker(){
        Locker firstLocker = LockerTestUtil.getLocker(10, 5, BagType.L);
        Locker secondLocker = LockerTestUtil.getLocker(10, 3, BagType.L);
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(Arrays.asList(firstLocker, secondLocker));
        Bag bag = new Bag(BagType.L);
        Ticket ticket = superLockerRobot.storeBag(bag);
        Assertions.assertEquals(bag,superLockerRobot.takeOutBag(ticket));
    }

//    - given SuperLockerRobot管理2个未存满的L型locker,有效的小票 when SuperLockerRobot取包 then 取包成功
//- given SuperLockerRobot管理2个未存满的L型locker,用过的小票 when SuperLockerRobot取包 then 取包失败,提示用过的小票
//- given SuperLockerRobot管理2个未存满的L型locker,无效的小票 when SuperLockerRobot取包 then 取包失败,提示无效的小票

}
