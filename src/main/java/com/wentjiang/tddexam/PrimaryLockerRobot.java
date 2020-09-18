package com.wentjiang.tddexam;

import com.wentjiang.tddexam.exception.BagTypeNotMatchException;
import com.wentjiang.tddexam.exception.LockerTypeNotMatchException;

import java.util.List;

public class PrimaryLockerRobot {

    private final List<Locker> lockers;

    public PrimaryLockerRobot(List<Locker> lockers) {
        if (lockers.stream().anyMatch(locker -> locker.getStoreBagType() != BagType.M)) {
            throw new LockerTypeNotMatchException();
        }
        this.lockers = lockers;
    }

    public Ticket storeBag(Bag bag) {
        if (bag.getBagType() != BagType.M) {
            throw new BagTypeNotMatchException();
        }
        return new Ticket();
    }
}
