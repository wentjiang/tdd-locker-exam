package com.wentjiang.tddexam;

import java.util.Arrays;

public class LockerTestUtil {

    public static Locker getLocker(int capacity, int usedCapacity, BagType bagType) {
        Locker locker = new Locker(bagType,capacity);
        for (int i = 0;i<usedCapacity;i++){
            locker.storeBag(new Bag(bagType));
        }
        return locker;
    }

}
