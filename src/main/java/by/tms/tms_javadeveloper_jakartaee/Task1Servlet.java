/**
 * Classname    Task1Servlet
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         06.08.2026
 */

package by.tms.tms_javadeveloper_jakartaee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@WebServlet("/task1")
public class Task1Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String OG_HTML;
        try (InputStream is = getServletContext().getResourceAsStream("/task1.html")) {
            OG_HTML = new String(is.readAllBytes(), StandardCharsets.UTF_8);

            if (OG_HTML == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().print(OG_HTML);
        } catch (IOException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
