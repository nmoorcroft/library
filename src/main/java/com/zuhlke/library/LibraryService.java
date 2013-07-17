package com.zuhlke.library;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.auth.basic.BasicAuthProvider;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import com.yammer.dropwizard.db.ManagedDataSource;
import com.yammer.dropwizard.db.ManagedDataSourceFactory;
import com.yammer.dropwizard.migrations.MigrationsBundle;
import com.zuhlke.library.artwork.ArtworkResource;
import com.zuhlke.library.book.BookResource;
import com.zuhlke.library.domain.User;
import com.zuhlke.library.heathcheck.DatabaseHealthCheck;
import com.zuhlke.library.security.LibraryAuthenticator;

public class LibraryService extends Service<LibraryConfiguration> {

    public static void main(String[] args) throws Exception {
        new LibraryService().run(args);
    }

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
    }

    @Override
    public void run(LibraryConfiguration configuration, Environment environment) throws Exception {
        ApplicationContext context = createApplicationContext(configuration);
        environment.addResource(context.getBean(BookResource.class));
        environment.addResource(context.getBean(ArtworkResource.class));
        environment.addHealthCheck(context.getBean(DatabaseHealthCheck.class));
        environment.addProvider(new BasicAuthProvider<User>(context.getBean(LibraryAuthenticator.class), "Library"));
    }
    
    
    private ApplicationContext createApplicationContext(LibraryConfiguration configuration) throws Exception {
        ManagedDataSource dataSource = new ManagedDataSourceFactory().build(configuration.getDatabaseConfiguration()); 
        DefaultListableBeanFactory parentBeanFactory = new DefaultListableBeanFactory();
        parentBeanFactory.registerSingleton("configuration", configuration);
        parentBeanFactory.registerSingleton("dataSource", dataSource);
        GenericApplicationContext parentContext = new GenericApplicationContext(parentBeanFactory);
        parentContext.refresh();
        return new ClassPathXmlApplicationContext(new String[] { "/spring/application-context.xml" }, parentContext);
    }

}

