/**
 * Classname    AppListener
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         09.06.2026
 */

package by.tms.tms_javadeveloper_jakartaee;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;

import java.time.LocalTime;

@WebListener
public class AppListener implements ServletContextListener, ServletRequestListener {

    public static int requestCounter = 0;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Web application has started");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        AppListener.requestCounter++;
        System.out.println("Request #" + AppListener.requestCounter +
                        " has been received at " + LocalTime.now());
    }
}
