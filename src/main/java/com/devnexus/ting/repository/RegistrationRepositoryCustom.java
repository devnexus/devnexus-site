package com.devnexus.ting.repository;

import com.devnexus.ting.model.RegistrationDetails;

public interface RegistrationRepositoryCustom {

    public RegistrationDetails createRegistrationPendingPayment(RegistrationDetails pendingRegistration);

    public RegistrationDetails findByKey(String registrationKey);

    public Long countSalesOfAddons(Long addOn);

}
