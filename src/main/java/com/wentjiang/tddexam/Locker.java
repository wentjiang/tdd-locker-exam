package com.wentjiang.tddexam;


import com.wentjiang.tddexam.exception.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Locker {

    private final BagType storeBagType;
    private final int capacity;
    private final Map<Ticket, Bag> ticketBagMap = new HashMap<>();
    private final Set<Ticket> usedTicketSet = new HashSet<>();

    public Locker(BagType storeBagType, int capacity) {
        this.storeBagType = storeBagType;
        this.capacity = capacity;
    }

    public Ticket storeBag(Bag bag) {
        if (bag.getBagType() != this.storeBagType) {
            throw new BagTypeNotMatchException();
        }
        if (ticketBagMap.size() >= capacity) {
            throw new CapacityFullException();
        }
        Ticket ticket = new Ticket(storeBagType);
        ticketBagMap.put(ticket, bag);
        return ticket;
    }

    public Bag takeOutBag(Ticket ticket) {
        if (ticket.getBagType() != storeBagType) {
            throw new TicketTypeNotMatchException();
        }
        if (usedTicketSet.contains(ticket)) {
            throw new TicketUsedException();
        }
        if (ticketBagMap.get(ticket) == null) {
            throw new BadTicketException();
        }
        usedTicketSet.add(ticket);
        return ticketBagMap.get(ticket);
    }

    public BagType getStoreBagType() {
        return storeBagType;
    }

    public boolean isNotFull() {
        return capacity > ticketBagMap.size();
    }

    public int getCapacity() {
        return capacity;
    }

    public int getUsedCapacity(){
        return ticketBagMap.size();
    }
}
