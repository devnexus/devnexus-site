package com.devnexus.ting.core.dao.jpa;


import org.springframework.stereotype.Repository;

import com.devnexus.ting.core.dao.SchemaMigrationDao;
import com.devnexus.ting.core.model.SchemaMigration;

/**
 *
 * @author Gunnar Hillert
 * @since 2.0
 */
@Repository("schemaMigrationDao")
public final class SchemaMigrationDaoJpa extends GenericDaoJpa< SchemaMigration, Long>
                                  implements SchemaMigrationDao {

    /**
     * Constructor.
     */
    private SchemaMigrationDaoJpa() {
        super(SchemaMigration.class);
    }

}
