
package com.devnexus.ting.repository.jpa;

import com.devnexus.ting.model.RegistrationDetails;
import com.devnexus.ting.repository.RegistrationRepositoryCustom;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    
}
