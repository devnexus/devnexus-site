package com.devnexus.ting.repository;

import com.devnexus.ting.model.Event;
import com.devnexus.ting.model.RegistrationDetails;
import java.util.List;



public interface RegistrationRepository extends BaseRepository<RegistrationDetails, Long>, RegistrationRepositoryCustom {

}
