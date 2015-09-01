package com.devnexus.ting.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Dashboard {

    private final List<RegistrationDetails> orders = new ArrayList<>();
    private final List<RegistrationDetails> inCompletePaypalOrders = new ArrayList<>();
    private final List<RegistrationDetails> ordersRequestingInvoice = new ArrayList<>();
    private final Map<TicketGroup, Integer> salesByType = new HashMap<>();
    
    
    
    public Dashboard() {
    
    }
    
    public Integer getTotalTicketsSold() {
        final AtomicInteger count = new AtomicInteger(0);
        orders.forEach((order) -> {count.addAndGet(order.getOrderDetails().size());});
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

    public void addOrder(RegistrationDetails order) {
        orders.add(order);
    }
    
    public void addSale(TicketGroup type, int count) {
        salesByType.put(type, count);
    }
    
    
    public void addInCompletePaypalOrders(RegistrationDetails order) {
        inCompletePaypalOrders.add(order);
    }
    
    
    public List<RegistrationDetails> getInCompletePaypalOrders() {
        return inCompletePaypalOrders;
    }

    public void addOrdersRequestingInvoice(RegistrationDetails order) {
        ordersRequestingInvoice.add(order);
    }
    
    public List<RegistrationDetails> getOrdersRequestingInvoice() {
        return ordersRequestingInvoice;
    }
    
    
    
    
}
