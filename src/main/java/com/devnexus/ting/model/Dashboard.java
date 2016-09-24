/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.devnexus.ting.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Summers Pittman
 *
 */
public class Dashboard {

	private final List<RegistrationDetails> orders = new ArrayList<>();
	private final List<RegistrationDetails> inCompletePaypalOrders = new ArrayList<>();
	private final List<RegistrationDetails> ordersRequestingInvoice = new ArrayList<>();
	private final Map<TicketGroup, Integer> salesByType = new HashMap<>();

	public Dashboard() {

	}

	public Integer getTotalTicketsSold() {
		final AtomicInteger count = new AtomicInteger(0);
		orders.forEach((order) -> {
			count.addAndGet(order.getOrderDetails().size());
		});
		return count.get();
	}

	public BigDecimal getTotalDollars() {
		return orders.stream().map(RegistrationDetails::getFinalCost).reduce(BigDecimal.ZERO,
				(BigDecimal a, BigDecimal b) -> {
					return a.add(b);
				});
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
