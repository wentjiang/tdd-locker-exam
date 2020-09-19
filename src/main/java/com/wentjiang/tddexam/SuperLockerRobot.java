package com.wentjiang.tddexam;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SuperLockerRobot {

    private final List<Locker> lockers;

    public SuperLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket storeBag(Bag bag) {
        Optional<Locker> minUsedLocker = lockers.stream().min(Comparator.comparingDouble(locker -> ((double) locker.getUsedCapacity() / (double) locker.getCapacity())));
        return minUsedLocker.get().storeBag(bag);
    }
}
