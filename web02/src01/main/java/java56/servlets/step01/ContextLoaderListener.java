package java56.servlets.step01;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// => DAO 및 SqlSessionFactory 객체도 ServletContext에 보관한다.
// => setter 메서드를 찾아서 호출한다.

public class ContextLoaderListener implements ServletContextListener {
  static Logger logger = Logger.getLogger(ContextLoaderListener.class);
  ServletContext ctx;
  
  @Override
  public void contextInitialized(ServletContextEvent event) {
    logger.debug("contextInitialized 호출됨...");
    try {
      ctx = event.getServletContext();
      
      ApplicationContext beanContainer = new ClassPathXmlApplicationContext(
          new String[]{"java56/servlets/step01/application-context.xml"});
  
      ctx.setAttribute("beanContainer", beanContainer);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(ServletContextEvent event) {
    logger.debug("contextDestroyed 호출됨...");
  }
}












