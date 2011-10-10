package com.devnexus.ting.core.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the schema_migrations database table.
 *
 */
@Entity
@Table(name="schema_migrations")
public class SchemaMigration implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable=false, length=255)
	private String version;

    public SchemaMigration(String version) {
		super();
		this.version = version;
	}

	public SchemaMigration() {
		super();
    }

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SchemaMigration other = (SchemaMigration) obj;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

}