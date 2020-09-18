package com.wentjiang.tddexam;

public class Bag {
    public BagType getBagType() {
        return bagType;
    }

    private final BagType bagType;

    public Bag(BagType bagType) {
        this.bagType = bagType;
    }
}
