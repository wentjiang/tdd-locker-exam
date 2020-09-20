package com.wentjiang.tddexam;

public class LockerRobotManager {
    public LockerRobotManager(Locker locker, PrimaryLockerRobot primaryLockerRobot, SuperLockerRobot superLockerRobot) {
    }

    public Ticket storeBag(Bag bag) {
        return new Ticket(bag.getBagType());
    }
}
