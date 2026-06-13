/**
 * Classname    DownloadServlet
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         13.06.2026
 */

package by.tms.tms_javadeveloper_jakartaee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String book = req.getParameter("res");
        URL resource = switch (book) {
            case "AVR_Assembler" ->
                // Передаем имя файла из папки resources/Books/
                    getClass().getClassLoader().getResource("Books/AVR_Assembler.pdf");
            case "book1" ->
                // Передаем имя файла из папки resources/Books/
                    getClass().getClassLoader().getResource("Books/Book1.txt");
            case "book2" ->
                // Передаем имя файла из папки resources/Books/
                    getClass().getClassLoader().getResource("Books/Book2.txt");
            case "lab2" ->
                // Передаем имя файла из папки resources/Books/
                    getClass().getClassLoader().getResource("Books/lab2.s");
            default -> null;
        };

        if (resource == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Файл не найден в ресурсах");
            return;
        }

        // Превращаем URL в объект File
        File file = null;
        try {
            file = new File(resource.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String mimeType = getServletContext().getMimeType(resource.getPath());
        resp.setContentType(mimeType);
        resp.setContentLengthLong(file.length());
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", file.getName());
        resp.setHeader(headerKey, headerValue);
        try (FileInputStream inStream = new FileInputStream(file);
             OutputStream outStream = resp.getOutputStream()) {
            byte[] buffer = new byte[4095];
            int bytesRead;
            while((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            resp.getWriter().print("Ошибка скачивания файла!");
        }
    }
}
