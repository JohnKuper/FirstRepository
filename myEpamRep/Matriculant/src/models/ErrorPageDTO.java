package models;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class ErrorPageDTO {
    HttpServletRequest request;
    HttpServletResponse response;

    public ErrorPageDTO(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void printErrorPage() throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("<html>\n" +
                "<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />"+
                "    <title>Ошибка</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Что-то пошло не так. Мы стараемся устранить эту ошибку</h1>"+
                "</body>\n" +
                "</html>");
    }
}
