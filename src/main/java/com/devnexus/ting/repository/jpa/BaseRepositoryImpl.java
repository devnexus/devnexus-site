package com.devnexus.ting.repository.jpa;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.devnexus.ting.repository.BaseRepository;

public class BaseRepositoryImpl<T, ID extends Serializable> extends
		SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

	private final EntityManager entityManager;

	public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);

		// Keep the EntityManager around to used from the newly introduced
		// methods.
		this.entityManager = entityManager;
	}

	/** {@inheritDoc} */
	@Override
	public void replicate(T object) {
		this.entityManager.unwrap(Session.class).replicate(object, ReplicationMode.EXCEPTION);
	}
}
