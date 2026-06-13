/**
 * Classname    BookServlet
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

@WebServlet("/book")
public class BookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String OG_HTML = """
                <!doctype html>
                <html lang="en">
                  <head>
                    <meta charset="utf-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <title>Home work 25 Download books</title>
                  </head>
                  <body>
                    <h1>Choose a book</h1>
                    <ul>
                        <li>
                            <a href="/download?res=AVR_Assembler">
                                AVR_Assembler
                            </a>
                        </li>
                        <li>
                            <a href="/download?res=book1">
                                Book 1
                            </a>
                        </li>
                        <li>
                            <a href="/download?res=book2">
                                Book 2
                            </a>
                        </li>
                        <li>
                            <a href="/download?res=lab2">
                                lab2
                            </a>
                        </li>
                    </ul>
                    <div>
                        <a href="/">
                            Main page
                        </a>
                    </div>
                  </body>
                </html>
                """;

        resp.getWriter().println(OG_HTML);
    }
}
