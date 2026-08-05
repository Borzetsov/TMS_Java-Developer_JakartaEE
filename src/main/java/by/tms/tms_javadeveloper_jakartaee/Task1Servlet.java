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

@WebServlet("/task1")
public class Task1Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String OG_HTML = """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>Home Work 26 Task 1</title>
                </head>
                <body style="color:white; font-size:34px; font-family:ms sans serif;">
                    <div style="width:160px; height:160px; background-color:black; padding:40px;">
                        <p style="margin:0px; padding-top:20px;">
                            <span style="color:yellow;">&lt;</span>
                            <span>Teach</span>
                        </p>
                        <p style="margin:0px; padding-left:34px;">Me</p>
                        <p style="margin:0px; padding-left:34px;">
                            <span>Skills</span>
                            <span style="color:yellow;">/&gt;</span>
                        </p>
                    </div>
                    <a href="/">Back</a>
                </body>
                </html>
                """;
        resp.getWriter().print(OG_HTML);
    }
}
