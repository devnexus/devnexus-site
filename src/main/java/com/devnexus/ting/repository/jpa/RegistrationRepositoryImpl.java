/*
 * Copyright 2015 the original author or authors.
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
package com.devnexus.ting.repository.jpa;

import com.devnexus.ting.model.Event;
import com.devnexus.ting.model.EventSignup;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.devnexus.ting.model.RegistrationDetails;
import com.devnexus.ting.repository.RegistrationRepositoryCustom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.util.StringUtils;

/**
*
* @author Summers Pittman
*/
@Repository("registrationDao")
public class RegistrationRepositoryImpl implements RegistrationRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    @Transactional
    public RegistrationDetails createRegistrationPendingPayment(RegistrationDetails pendingRegistration) {
     
        pendingRegistration.setRegistrationFormKey(UUID.randomUUID().toString());
        
        while(!entityManager.createQuery("from RegistrationDetails where registrationFormKey = :registrationFormKey").setParameter("registrationFormKey", pendingRegistration.getRegistrationFormKey()).getResultList().isEmpty()) {
            pendingRegistration.setRegistrationFormKey(UUID.randomUUID().toString());
        };
        
        entityManager.persist(pendingRegistration);
        entityManager.flush();
        return pendingRegistration;
    }

    @Override
    public RegistrationDetails findByKey(String registrationKey) {
        return (RegistrationDetails) entityManager.createQuery("from RegistrationDetails where registrationFormKey = :registrationFormKey").setParameter("registrationFormKey", registrationKey).getSingleResult();
    }

    @Override
    public Long countSalesOfAddons(Long addOnId) {
        return (Long) entityManager.createQuery("select count(*) from TicketOrderDetail where ticketAddOn= :addOnId and (registration.paymentState=:PAID or registration.paymentState=:INVOICED)").setParameter("addOnId", addOnId).setParameter("PAID", RegistrationDetails.PaymentState.PAID).setParameter("INVOICED", RegistrationDetails.PaymentState.INVOICED).getSingleResult();
    }

    @Override
    public List<RegistrationDetails> findPurchasedForEvent(Event event) {
        return entityManager.createQuery("from RegistrationDetails where event.id = :eventId and (paymentState=:PAID or paymentState=:INVOICED)").setParameter("PAID", RegistrationDetails.PaymentState.PAID).setParameter("INVOICED", RegistrationDetails.PaymentState.INVOICED).setParameter("eventId", event.getId()).getResultList();
    }

    @Override
    public List<RegistrationDetails> findIncompletePaypalOrdersForEvent(Event event) {
        return entityManager.createQuery("from RegistrationDetails where event.id = :eventId and (paymentState=:PAYPAL_PENDING)").setParameter("PAYPAL_PENDING", RegistrationDetails.PaymentState.PAYPAL_CREATED).setParameter("eventId", event.getId()).getResultList();
    }

    @Override
    public List<RegistrationDetails> findOrdersRequestingInvoiceForEvent(Event event) {
        return entityManager.createQuery("from RegistrationDetails where event.id = :eventId and (paymentState=:REQUIRES_INVOICE)").setParameter("REQUIRES_INVOICE", RegistrationDetails.PaymentState.REQUIRES_INVOICE).setParameter("eventId", event.getId()).getResultList();
    }

    @Override
    public List findOrdersWithContactEmail(String email, EventSignup signUp) {
        List<Object[]> results = entityManager.createQuery("from RegistrationDetails details inner join details.orderDetails orderDetails where details.event.id = :event_id and (details.contactEmailAddress = :email or orderDetails.emailAddress = :ticket_email)").setParameter("event_id", signUp.getEvent().getId()).setParameter("email", email).setParameter("ticket_email", email).getResultList();
        List detailsFromEmail = new ArrayList();
        results.stream().forEach(objects -> {detailsFromEmail.addAll(Arrays.asList(objects));});
        return detailsFromEmail;
    }

    @Override
    public List findOrdersWithContactName(String[] namesA, EventSignup signUp) {
        String fullName = StringUtils.arrayToDelimitedString(namesA, " ");
        List names = Arrays.asList(namesA);
        List<Object[]> resultList = entityManager.createQuery("from RegistrationDetails details inner join details.orderDetails orderDetails where details.event.id = :event_id and (details.contactName LIKE :name or orderDetails.firstName in (:first_names) or orderDetails.lastName in (:last_names))").setParameter("event_id", signUp.getEvent().getId()).setParameter("name", "%" + fullName + "%").setParameter("first_names", names).setParameter("last_names", names).getResultList();
        
        List detailsFromEmail = new ArrayList();
        resultList.stream().forEach(objects -> {detailsFromEmail.addAll(Arrays.asList(objects));});
        
        return detailsFromEmail;
    }
    
}
