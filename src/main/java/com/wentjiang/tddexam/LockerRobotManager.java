package com.wentjiang.tddexam;

public class LockerRobotManager {
    private final Locker locker;
    private final PrimaryLockerRobot primaryLockerRobot;

    public LockerRobotManager(Locker locker, PrimaryLockerRobot primaryLockerRobot, SuperLockerRobot superLockerRobot) {
        this.locker = locker;
        this.primaryLockerRobot = primaryLockerRobot;
    }

    public Ticket storeBag(Bag bag) {
        Ticket ticket = null;
        switch (bag.getBagType()) {
            case S:
                ticket = locker.storeBag(bag);
                break;
            case M:
                ticket = primaryLockerRobot.storeBag(bag);
                break;
            default:
        }
        return ticket;
    }

    public Bag takeOutBag(Ticket ticket) {
        Bag bag = null;
        switch (ticket.getBagType()) {
            case S:
                bag = locker.takeOutBag(ticket);
                break;
            case M:
                bag = primaryLockerRobot.takeOutBag(ticket);
                break;
            default:
        }
        return bag;
    }
}
