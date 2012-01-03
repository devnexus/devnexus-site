/*
 * Copyright 2002-2011 the original author or authors.
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
package com.devnexus.ting.core.dao.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.ReplicationMode;
import org.hibernate.Session;

import com.devnexus.ting.core.dao.GenericDao;

/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 *
 * <p>To register this class in your Spring context file, use the following XML.
 * <pre>
 *      &lt;bean id="fooDao" class="org.appfuse.dao.hibernate.GenericDaoHibernate"&gt;
 *          &lt;constructor-arg value="org.appfuse.model.Foo"/&gt;
 *          &lt;property name="sessionFactory" ref="sessionFactory"/&gt;
 *      &lt;/bean&gt;
 * </pre>
 *
 * @author <a href="mailto:bwnoll@gmail.com">Bryan Noll</a>
 */
public class GenericDaoJpa<T, PK extends Serializable> implements GenericDao<T, PK> {

    @PersistenceContext(unitName="base")
    protected EntityManager entityManager;

    private Class<T> persistentClass;

    public GenericDaoJpa(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll() {
        return this.entityManager.createQuery(
                "select obj from " + this.persistentClass.getName() + " obj")
                .getResultList();
    }

    /** {@inheritDoc} */
    @Override
    public T get(PK id) {
        T entity = this.entityManager.find(this.persistentClass, id);
        return entity;
    }

    /** {@inheritDoc} */
    @Override
    public boolean exists(PK id) {
        T entity = this.entityManager.find(this.persistentClass, id);
        return entity != null;
    }

    /** {@inheritDoc} */
    @Override
    public T save(T object) {
        return this.entityManager.merge(object);
    }

    /** {@inheritDoc} */
    @Override
    public void replicate(T object) {
        this.entityManager.unwrap(Session.class).replicate(object, ReplicationMode.EXCEPTION);
    }

    /** {@inheritDoc} */
    @Override
    public void remove(PK id) {
        this.entityManager.remove(this.get(id));
    }

    /** {@inheritDoc} */
    @Override
    public void remove(T object) {
        this.entityManager.remove(object);
    }

}
