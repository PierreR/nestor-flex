package web;

import com.google.inject.*;
import com.google.inject.matcher.Matchers;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.wideplay.warp.persist.PersistenceService;
import com.wideplay.warp.persist.UnitOfWork;
import com.wideplay.warp.persist.jpa.JpaUnit;
import dao.ProgramQ;
import flex.messaging.MessageBrokerServlet;

import javax.servlet.ServletContextEvent;

/**
 * Date: Nov 20, 2009
 */
public class ServletListener extends GuiceServletContextListener {

    // Not sure to understand why this is not public in the first place ...
    static final String INJECTOR_NAME = Injector.class.getName();

    @Override
    protected Injector getInjector() {
        Injector injector = Guice.createInjector(PersistenceService.usingJpa()
                .across(UnitOfWork.REQUEST)
                .forAll(Matchers.inSubpackage("srv"))
                .addAccessor(ProgramQ.class)  // try to group common interfaces here !!
                .buildModule(),
                new ServletModule() {
                    @Override
                    protected void configureServlets() {
                        super.configureServlets();
                        this.bind(MessageBrokerServlet.class).in(Scopes.SINGLETON);
                        this.serve("/messagebroker/*").with(MessageBrokerServlet.class);
                    }
                },
                new AbstractModule() {
                    @Override
                    protected void configure() {
                        bindConstant().annotatedWith(JpaUnit.class).to("mexitdb");
                    }
                });

        injector.getInstance(PersistenceService.class).start();

        return injector;


    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        Injector injector = (Injector) servletContextEvent.getServletContext().getAttribute(INJECTOR_NAME);
        injector.getInstance(PersistenceService.class).shutdown();
        super.contextDestroyed(servletContextEvent);
    }
}
