package com.devnexus.ting.core.dao;

public interface SystemDao {

	void updateDatabase();

	void createDatabase(boolean outputOnly, String dialect);

}
