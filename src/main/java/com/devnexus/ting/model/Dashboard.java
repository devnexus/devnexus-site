package com.devnexus.ting.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Dashboard {

    private final List<RegistrationDetails> orders = new ArrayList<>();
    private final Map<TicketGroup, Integer> salesByType = new HashMap<>();
    private final Map<TicketAddOn, Integer> salesOfWorkshops;
    
    
    public Dashboard() {
        this.salesOfWorkshops = new HashMap<>();
    }
    
    public Integer getTotalTicketsSold() {
        final AtomicInteger count = new AtomicInteger(0);
        orders.forEach((order) -> {count.addAndGet(order.getTicketCount());});
        return count.get();
    }

    public BigDecimal getTotalDollars() {
        return orders.stream().map(RegistrationDetails::getFinalCost).reduce(BigDecimal.ZERO, (BigDecimal a, BigDecimal b)->{return a.add(b);});
    }

    
    public List<RegistrationDetails> getOrders() {
        return new ArrayList<>(orders);
    }

    public Map<TicketGroup, Integer> getSalesByType() {
        return new HashMap<>(salesByType);
    }

    public Map<TicketAddOn, Integer> getSalesOfWorkshops() {
        return new HashMap<>(salesOfWorkshops);
    }
    
    public void addOrder(RegistrationDetails order) {
        orders.add(order);
    }
    
    public void addSale(TicketGroup type, int count) {
        salesByType.put(type, count);
    }
    
    public void addWorkshopSale(TicketAddOn workshop, int count) {
        salesOfWorkshops.put(workshop, count);
    }
    
    
}
