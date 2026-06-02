/**
 * Classname    AgeCheckServlet
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

@WebServlet("/ageCheck") //localhost:8080/ageCheck?age=21
public class AgeCheckServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        resp.setContentType((String)servletContext.getAttribute("contentType"));
        String respValue;
        try {
            int adultAge = Integer.parseInt((String) servletContext.getAttribute("adultAge"));
            int reqAge = Integer.parseInt(req.getParameter("age"));
            respValue = (reqAge < adultAge)
                    ? "Не совершеннолетний" : "Совершеннолетний";
        } catch (NumberFormatException e) {
            respValue = "Ошибка формата";
        }
        resp.getWriter().print(respValue);
    }
}
