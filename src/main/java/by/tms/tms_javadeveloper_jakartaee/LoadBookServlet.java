/**
 * Classname    LoadBookServlet
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         09.06.2026
 */

package by.tms.tms_javadeveloper_jakartaee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

@WebServlet("/load-book")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,  // 2 МБ (память перед записью на диск)
        maxFileSize = 1024 * 1024 * 10,       // 10 МБ (максимальный размер одного файла)
        maxRequestSize = 1024 * 1024 * 50     // 50 МБ (общий размер запроса)
)
public class LoadBookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String OG_HTML = """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>Upload book</title>
                </head>
                <body>
                    <h2>Select a book</h2>
                    <!-- Атрибут action указывает на URL нашего сервлета -->
                    <form action="load-book" method="post" enctype="multipart/form-data">
                        <input type="file" name="file" required /><br/><br/>
                        <button type="submit">Upload</button>
                    </form>
                    <div>
                        <a href="/">
                            Main page
                        </a>
                    </div>
                </body>
                </html>
                """;
        resp.getWriter().print(OG_HTML);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Определить ОС клиента
        String userAgent = req.getHeader("User-Agent");
        //Определить соответствующий путь для сохранения
        String savePath = "unknown";
        if (userAgent.contains("Windows")) {
            savePath = System.getProperty("user.home") + "/Desktop/HomeWork25";
        }
        else if (userAgent.contains("Macintosh")) {
            savePath = System.getProperty("user.home") + "/Desktop/HomeWork25";
        }
        else if (userAgent.contains("Linux") && !userAgent.contains("Android")) {
            String home = System.getProperty("user.home");
            java.io.File desktop = new java.io.File(home, "Desktop");

            if (!desktop.exists()) {
                // Если англоязычной папки нет, проверяем русскоязычный вариант
                desktop = new java.io.File(home, "Рабочий стол");
            }
            savePath = desktop.getAbsolutePath() + "/HomeWork25";
        }
        else {
            resp.sendError(500, "Unsupported OS");
        }
        // Создаем директорию, если она не существует
        File uploadDir = new File(savePath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        String OG_HTML_OK = """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>Upload book</title>
                </head>
                <body>
                    <h2>Select a book</h2>
                    <!-- Атрибут action указывает на URL нашего сервлета -->
                    <form action="load-book" method="post" enctype="multipart/form-data">
                        <input type="file" name="file" required /><br/><br/>
                        <button type="submit">Upload</button>
                    </form>
                    <h3>The file has been successfully uploaded</h3>
                    <div>
                        <a href="/">
                            Main page
                        </a>
                    </div>
                </body>
                </html>
                """;
        String OG_HTML_FAIL = """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>Upload book</title>
                </head>
                <body>
                    <h2>Select a book</h2>
                    <!-- Атрибут action указывает на URL нашего сервлета -->
                    <form action="load-book" method="post" enctype="multipart/form-data">
                        <input type="file" name="file" required /><br/><br/>
                        <button type="submit">Upload</button>
                    </form>
                    <h3>Fail to upload the file</h3>""";
        try {
            // "file" — это значение атрибута name из HTML-формы
            Part filePart = req.getPart("file");

            // Извлекаем оригинальное имя файла
            String fileName = filePart.getSubmittedFileName();

            // Формируем полный путь и сохраняем файл на диск
            String fullPath = savePath + File.separator + fileName;
            filePart.write(fullPath);

            resp.getWriter().print(OG_HTML_OK);

        } catch (Exception e) {
            resp.getWriter().print(OG_HTML_FAIL + """
                    <h3>
                    """ + e.getMessage() +
                    """
                    </h3>
                    <div>
                        <a href="/">
                            Main page
                        </a>
                    </div>
                </body>
                </html>
                """);
        }
    }
}
