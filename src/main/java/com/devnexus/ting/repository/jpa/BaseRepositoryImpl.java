/*
 * Copyright 2014-2015 the original author or authors.
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

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.devnexus.ting.repository.BaseRepository;

/**
 *
 * @author Gunnar Hillert
 *
 * @param <T>
 * @param <ID>
 */
public class BaseRepositoryImpl<T, ID extends Serializable> extends
		SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

	private final EntityManager entityManager;

	public BaseRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
	}

	/** {@inheritDoc} */
	@Override
	public void replicate(T object) {
		this.entityManager.unwrap(Session.class).replicate(object, ReplicationMode.EXCEPTION);
	}
}
