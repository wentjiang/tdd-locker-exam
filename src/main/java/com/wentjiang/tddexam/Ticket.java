package com.wentjiang.tddexam;

import java.util.Objects;
import java.util.UUID;

public class Ticket {

    private final String id;
    private final BagType bagType;

    public Ticket(BagType bagType) {
        this.bagType = bagType;
        id = UUID.randomUUID().toString();
    }

    public BagType getBagType() {
        return bagType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
