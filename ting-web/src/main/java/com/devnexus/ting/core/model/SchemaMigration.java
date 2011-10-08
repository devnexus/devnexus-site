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

    public SchemaMigration() {
    }

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}