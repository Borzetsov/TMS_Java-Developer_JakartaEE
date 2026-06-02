/**
 * Classname    TimeServletListener
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         02.06.2026
 */

package by.tms.tms_javadeveloper_jakartaee;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class TimeServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("contentType", "text/html; charset=UTF-8");
        sce.getServletContext().setAttribute("dateFormat", "HH:mm:ss");
    }
}
