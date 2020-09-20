package com.wentjiang.tddexam;

import com.wentjiang.tddexam.exception.BadTicketException;
import com.wentjiang.tddexam.exception.BagTypeNotMatchException;
import com.wentjiang.tddexam.exception.CapacityFullException;
import com.wentjiang.tddexam.exception.LockerTypeNotMatchException;

import java.util.List;
import java.util.Optional;

public class PrimaryLockerRobot {

    private final List<Locker> lockers;

    public PrimaryLockerRobot(List<Locker> lockers) {
        if (lockers.size() <= 0){
            throw new IllegalArgumentException("at last one locker");
        }
        if (lockers.stream().anyMatch(locker -> locker.getStoreBagType() != BagType.M)) {
            throw new LockerTypeNotMatchException();
        }
        this.lockers = lockers;
    }

    public Ticket storeBag(Bag bag) {
        if (bag.getBagType() != BagType.M) {
            throw new BagTypeNotMatchException();
        }
        Optional<Locker> locker = lockers.stream().filter(Locker::isNotFull).findFirst();
        if (!locker.isPresent()) {
            throw new CapacityFullException();
        }
        return locker.get().storeBag(bag);
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
