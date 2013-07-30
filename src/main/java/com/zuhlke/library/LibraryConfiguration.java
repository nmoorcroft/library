package com.zuhlke.library;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.db.DatabaseConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class LibraryConfiguration extends Configuration {

	@Valid
	@NotNull
	@JsonProperty
	private DatabaseConfiguration database = new DatabaseConfiguration();

	@NotEmpty
	@JsonProperty
	private String imgStore;
	
	@NotEmpty
	@JsonProperty
	private String wroDebug;
	
	public DatabaseConfiguration getDatabaseConfiguration() {
		return database;
	}

	public String getImgStore() {
        return imgStore;
    }
	
	public String isWroDebug() {
		return wroDebug;
	}
	
}
