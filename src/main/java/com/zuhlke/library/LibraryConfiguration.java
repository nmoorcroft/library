package com.zuhlke.library;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.db.DatabaseConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class LibraryConfiguration extends Configuration {

	@Valid
	@NotNull
	@JsonProperty
	private DatabaseConfiguration database = new DatabaseConfiguration();

	public DatabaseConfiguration getDatabaseConfiguration() {
		return database;
	}

}
