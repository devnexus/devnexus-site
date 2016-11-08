/*
 * Copyright 2016 the original author or authors.
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
package com.devnexus.ting.repository;

import com.devnexus.ting.model.Event;
import com.devnexus.ting.model.EventSignup;
import com.devnexus.ting.model.RegistrationDetails;
import java.util.List;

public interface RegistrationRepositoryCustom {

	public RegistrationDetails createRegistrationPendingPayment(RegistrationDetails pendingRegistration);

	public RegistrationDetails findByKey(String registrationKey);

	public List<RegistrationDetails> findPurchasedForEvent(Event event);

	public List<RegistrationDetails> findAllForEvent(Event event);

	public List<RegistrationDetails> findIncompletePaypalOrdersForEvent(Event event);

	public List<RegistrationDetails> findOrdersRequestingInvoiceForEvent(Event event);

	public List<RegistrationDetails> findOrdersWithContactEmail(String email, EventSignup signUp);

	public List<RegistrationDetails> findOrdersWithContactName(String[] names, EventSignup signUp);

}
