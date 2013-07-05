package com.zuhlke.library;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import com.yammer.dropwizard.hibernate.HibernateBundle;
import com.yammer.dropwizard.migrations.MigrationsBundle;
import com.zuhlke.library.api.BookResource;
import com.zuhlke.library.core.Book;
import com.zuhlke.library.dao.BookDAO;

public class LibraryService extends Service<LibraryConfiguration> {

    public static void main(String[] args) throws Exception {
        new LibraryService().run(args);
    }
    
    private final HibernateBundle<LibraryConfiguration> hibernate = new HibernateBundle<LibraryConfiguration>(Book.class) {
        @Override
        public DatabaseConfiguration getDatabaseConfiguration(LibraryConfiguration configuration) {
            return configuration.getDatabaseConfiguration();
        }
    };
    
    @Override
    public void initialize(Bootstrap<LibraryConfiguration> bootstrap) {
        bootstrap.setName("library");
        bootstrap.addBundle(new AssetsBundle("/assets/", "/"));
        bootstrap.addBundle(new MigrationsBundle<LibraryConfiguration>() {
            @Override
            public DatabaseConfiguration getDatabaseConfiguration(LibraryConfiguration configuration) {
                return configuration.getDatabaseConfiguration();
            }
        });
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(LibraryConfiguration configuration, Environment environment) throws Exception {
        environment.addResource(new BookResource(new BookDAO(hibernate.getSessionFactory())));

    }
    
}
