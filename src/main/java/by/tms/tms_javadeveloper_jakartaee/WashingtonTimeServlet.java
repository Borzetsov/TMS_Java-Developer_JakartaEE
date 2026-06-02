/**
 * Classname    WashingtonTimeServlet
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

@WebServlet("/washington")
public class WashingtonTimeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Указать кодировку, иначе в браузере месиво
        ServletContext servletContext = getServletContext();
        resp.setContentType((String)servletContext.getAttribute("contentType"));
        ZonedDateTime washingtonTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern((String)servletContext.getAttribute("dateFormat"));
        resp.getWriter().print("В Вашингтоне сейчас: " + washingtonTime.format(formatter));
    }
}
