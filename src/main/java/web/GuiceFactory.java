package web;

import com.google.inject.Injector;
import flex.messaging.FactoryInstance;
import flex.messaging.FlexContext;
import flex.messaging.FlexFactory;
import flex.messaging.config.ConfigMap;
import flex.messaging.services.ServiceException;

import java.util.HashMap;
import java.util.Map;

/**
 * This code is a largely inspired (if not pasted) from <a href="http://fponchel.developpez.com/tutoriel/flex3/integration/blazeds-spring/">Jeff Vroom's SpringFactory</a>.
 *
 * Date: Nov 21, 2009
 *
 */
public class GuiceFactory implements FlexFactory {
  private static final String SOURCE = "source";

  /**
   * @see flex.messaging.FlexFactory#createFactoryInstance(java.lang.String, flex.messaging.config.ConfigMap)
   */
  public FactoryInstance createFactoryInstance(final String id, final ConfigMap properties) {
    final GuiceFactoryInstance instance = new GuiceFactoryInstance(this, id, properties);
    instance.setSource(properties.getPropertyAsString(SOURCE, instance.getId()));
    return instance;
  }

  /**
   * @see flex.messaging.FlexConfigurable#initialize(java.lang.String, flex.messaging.config.ConfigMap)
   */
  public void initialize(final String id, final ConfigMap configMap) {
  }

  /**
   * @see flex.messaging.FlexFactory#lookup(flex.messaging.FactoryInstance)
   */
  public Object lookup(final FactoryInstance inst) {
    return inst.lookup();
  }

  static class GuiceFactoryInstance extends FactoryInstance {
    private Map<String, Class<?>> classes = new HashMap<String, Class<?>>();

      GuiceFactoryInstance(final GuiceFactory factory, final String id, final ConfigMap properties) {
      super(factory, id, properties);
    }

    @Override
    public Object lookup() {
      final Injector injector = (Injector) FlexContext.getServletContext().getAttribute(ServletListener.INJECTOR_NAME);
      injector.injectMembers(this);
      String className = this.getSource();
      Class<?> clazz = this.classes.get(className);
      if (clazz == null) {
        try {
          clazz = Class.forName(this.getSource());
          this.classes.put(className, clazz);
        }
        catch (ClassNotFoundException ex) {
          ServiceException throwing = new ServiceException();
          throwing.setMessage("Could not find remote service class '" + this.getSource() + "'");
          throwing.setRootCause(ex);
          throwing.setCode("Server.Processing");
          throw throwing;
        }
      }
      return injector.getInstance(clazz);
    }

    @Override
    public String toString() {
      return "Guice factory <id='" + this.getId() + "',source='" + this.getSource() +
          "',scope='" + this.getScope() + "'>";
    }
  }
}