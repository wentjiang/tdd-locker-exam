package com.wentjiang.tddexam;

import com.wentjiang.tddexam.exception.BadTicketException;
import com.wentjiang.tddexam.exception.BagTypeNotMatchException;
import com.wentjiang.tddexam.exception.CapacityFullException;
import com.wentjiang.tddexam.exception.LockerTypeNotMatchException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SuperLockerRobot {

    private final List<Locker> lockers;

    public SuperLockerRobot(List<Locker> lockers) {
        if (lockers.size() <= 0) {
            throw new IllegalArgumentException("at last one locker");
        }
        this.lockers = lockers;
    }

    public Ticket storeBag(Bag bag) {
        if (bag.getBagType() != BagType.L) {
            throw new BagTypeNotMatchException();
        }
        Optional<Locker> minUsedLocker = lockers.stream().min(Comparator.comparingDouble(locker -> ((double) locker.getUsedCapacity() / (double) locker.getCapacity())));
        return minUsedLocker.get().storeBag(bag);
    }

    public Bag takeOutBag(Ticket ticket) {
        for (Locker locker : lockers) {
            try {
                return locker.takeOutBag(ticket);
            } catch (BadTicketException ignored) {
            }
        }
        throw new BadTicketException();
    }
}
