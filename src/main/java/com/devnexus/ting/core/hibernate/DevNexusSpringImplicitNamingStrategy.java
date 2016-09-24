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
package com.devnexus.ting.core.hibernate;

import org.atteo.evo.inflector.English;
import org.hibernate.HibernateException;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitEntityNameSource;
import org.hibernate.boot.model.naming.ImplicitJoinTableNameSource;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;

/**
 * 
 * @author Gunnar Hillert
 *
 */
public class DevNexusSpringImplicitNamingStrategy extends ImplicitNamingStrategyJpaCompliantImpl {

	private static final long serialVersionUID = 1L;

	@Override
	public Identifier determineJoinTableName(ImplicitJoinTableNameSource source) {
		String name = source.getOwningPhysicalTableName() + "_"
				+ source.getAssociationOwningAttributePath().getProperty();
		return toIdentifier(name, source.getBuildingContext());
	}

	@Override
	public Identifier determinePrimaryTableName(ImplicitEntityNameSource source) {
		if ( source == null ) {
			// should never happen, but to be defensive...
			throw new HibernateException( "Entity naming information was not provided." );
		}

		String tableName = transformEntityName( source.getEntityNaming() );

		if ( tableName == null ) {
			// todo : add info to error message - but how to know what to write since we failed to interpret the naming source
			throw new HibernateException( "Could not determine primary table name for entity" );
		}

		return toIdentifier( English.plural(tableName), source.getBuildingContext() );
	}
}
