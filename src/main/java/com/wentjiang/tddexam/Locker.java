package com.wentjiang.tddexam;


import com.wentjiang.tddexam.exception.CapacityFullException;

import java.util.HashMap;
import java.util.Map;

public class Locker {

    private final BagType storeBagType;
    private final int capacity;
    private final Map<Ticket, Bag> ticketBagMap = new HashMap<>();

    public Locker(BagType storeBagType, int capacity) {
        this.storeBagType = storeBagType;
        this.capacity = capacity;
    }

    public Ticket storeBag(Bag bag) {
        Ticket ticket = new Ticket();
        if (ticketBagMap.size() >= capacity) {
            throw new CapacityFullException();
        }
        ticketBagMap.put(ticket, bag);
        return ticket;
    }
}
