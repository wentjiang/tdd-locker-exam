package com.wentjiang.tddexam;

public class LockerRobotManager {
    private final Locker locker;
    private final PrimaryLockerRobot primaryLockerRobot;
    private final SuperLockerRobot superLockerRobot;

    public LockerRobotManager(Locker locker, PrimaryLockerRobot primaryLockerRobot, SuperLockerRobot superLockerRobot) {
        this.locker = locker;
        this.primaryLockerRobot = primaryLockerRobot;
        this.superLockerRobot = superLockerRobot;
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
            case L:
                ticket = superLockerRobot.storeBag(bag);
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
            case L:
                bag = superLockerRobot.takeOutBag(ticket);
                break;
            default:
        }
        return bag;
    }
}
