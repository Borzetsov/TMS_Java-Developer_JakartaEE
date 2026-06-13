/**
 * Classname    RootServlet
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         09.06.2026
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
                <!doctype html>
                <html lang="en">
                  <head>
                    <meta charset="utf-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <title>Home work 25</title>
                  </head>
                  <body>
                    <h1>Hello, Guest!</h1>
                    <h2>Books</h2>
                        </div>
                            <a href="/book">
                                Download
                            </a>
                        </div>
                        <div>
                            <a href="/load-book">
                                Upload
                            </a>
                        </div>
                  </body>
                </html>
                """;

        resp.getWriter().println(OG_HTML);
    }
}
