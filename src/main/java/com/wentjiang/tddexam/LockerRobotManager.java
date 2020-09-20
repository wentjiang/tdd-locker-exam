package com.wentjiang.tddexam;

public class LockerRobotManager {
    private final Locker locker;

    public LockerRobotManager(Locker locker, PrimaryLockerRobot primaryLockerRobot, SuperLockerRobot superLockerRobot) {
        this.locker = locker;
    }

    public Ticket storeBag(Bag bag) {
        return locker.storeBag(bag);
    }

    public Bag takeOutBag(Ticket ticket) {
        return locker.takeOutBag(ticket);
    }
}
