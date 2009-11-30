package web;

import com.google.inject.*;
import com.google.inject.matcher.Matchers;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.wideplay.warp.persist.PersistenceService;
import com.wideplay.warp.persist.UnitOfWork;
import com.wideplay.warp.persist.jpa.JpaUnit;
import dao.Picker;
import entity.Recipient;
import flex.messaging.MessageBrokerServlet;

import javax.persistence.EntityManager;
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
                .forAll(Matchers.any())
                .addAccessor(dao.Program.class).addAccessor(Picker.class) // try to group common interfaces here !!
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
                        bindConstant().annotatedWith(JpaUnit.class).to("nestordb");
                    }
                });

        injector.getInstance(PersistenceService.class).start();

        // I guess we remove this in production ...
        createTestData(injector.getInstance(EntityManager.class));

        return injector;


    }

    private void createTestData(EntityManager em) {
        em.getTransaction().begin();

        Recipient recipient = new Recipient();

        recipient.setName_fr("Bruxelles");
        recipient.setName_nl("Brussels");
        recipient.setPostalCode("1000");

        em.persist(recipient);

        em.getTransaction().commit();



    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        Injector injector = (Injector) servletContextEvent.getServletContext().getAttribute(INJECTOR_NAME);
        injector.getInstance(PersistenceService.class).shutdown();
        super.contextDestroyed(servletContextEvent);
    }
}
