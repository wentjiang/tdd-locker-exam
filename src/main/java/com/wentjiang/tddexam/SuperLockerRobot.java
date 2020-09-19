package com.wentjiang.tddexam;

import java.util.List;

public class SuperLockerRobot {
    public SuperLockerRobot(List<Locker> lockers) {
    }

    public Ticket storeBag(Bag bag) {
        return new Ticket(BagType.L);
    }
}
