package com.devnexus.ting.core.hibernate;

import org.atteo.evo.inflector.English;
import org.hibernate.HibernateException;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitEntityNameSource;
import org.hibernate.boot.model.naming.ImplicitJoinTableNameSource;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;

public class DevNexusSpringImplicitNamingStrategy extends ImplicitNamingStrategyJpaCompliantImpl {

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
