package com.devnexus.ting.repository;

import com.devnexus.ting.model.Event;
import com.devnexus.ting.model.RegistrationDetails;
import java.util.List;

public interface RegistrationRepositoryCustom {

    public RegistrationDetails createRegistrationPendingPayment(RegistrationDetails pendingRegistration);

    public RegistrationDetails findByKey(String registrationKey);

    public Long countSalesOfAddons(Long addOn);
    
    
    public List<RegistrationDetails> findPurchasedForEvent(Event event);

    
    public List<RegistrationDetails> findIncompletePaypalOrdersForEvent(Event event);

    public List<RegistrationDetails> findOrdersRequestingInvoiceForEvent(Event event);

    
}
