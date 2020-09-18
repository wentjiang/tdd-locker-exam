package com.wentjiang.tddexam;


public class Locker {

    private BagType storeBagType;
    private int capacity;

    public Locker(BagType storeBagType, int capacity) {
        this.storeBagType = storeBagType;
        this.capacity = capacity;
    }

    public Ticket storeBag(Bag bag) {
        return new Ticket();
    }
}
