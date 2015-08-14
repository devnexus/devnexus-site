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

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.devnexus.ting.model.RegistrationDetails;
import com.devnexus.ting.repository.RegistrationRepositoryCustom;

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
    
}
