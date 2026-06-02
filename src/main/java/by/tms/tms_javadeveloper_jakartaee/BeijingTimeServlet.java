/**
 * Classname    BeijingTimeServlet
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         02.06.2026
 */

package by.tms.tms_javadeveloper_jakartaee;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/beijing")
public class BeijingTimeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Указать кодировку, иначе в браузере месиво
        ServletContext servletContext = getServletContext();
        resp.setContentType((String)servletContext.getAttribute("contentType"));
        ZonedDateTime beijingTime = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern((String)servletContext.getAttribute("dateFormat"));
        resp.getWriter().print("В Пекине сейчас: " + beijingTime.format(formatter));
    }
}
