/**
 * Classname    RootServlet
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         05.08.2026
 */

package by.tms.tms_javadeveloper_jakartaee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/")
public class RootServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String OG_HTML = """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>Home Work 26</title>
                </head>
                <body style="background-color:black; color:white; font-size:30px; font-family:ms sans serif;">
                    <div>
                        <p>
                            <span style="color:yellow;">&lt;</span>
                            <span>Teach</span>
                        </p>
                        <p>Me</p>
                        <p>
                            <span>Skills</span>
                            <span style="color:yellow;">/&gt;</span>
                        </p>
                    </div>
                </body>
                </html>
                """;
        resp.getWriter().print(OG_HTML);
    }
}
