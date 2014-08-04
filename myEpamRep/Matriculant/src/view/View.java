package view;

import models.InstructorDTO;
import models.MatriculantDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: VirtBox
 * Date: 25.06.14
 * Time: 22:02
 * To change this template use File | Settings | File Templates.
 */
public interface View {

    // Ошибка: неверный логин или пароль
    public void errorLoginOrPassword(HttpServletRequest request, InstructorDTO instructorDTO);
    // Не корректные данные
    public void errorIncorrectly(HttpServletRequest request, MatriculantDTO matriculantDTO);
    // Абитуериет есть в базе
    public void isMatriculantExists(HttpServletRequest request, MatriculantDTO matriculantDTO);
    // Абитуериета нет в базе
    public void notMatriculantExists(HttpServletRequest request, MatriculantDTO matriculantDTO);
    // Добавили успешно
    public void addNewMatriculant(HttpServletRequest request, MatriculantDTO matriculantDTO);
    // Удалили успешно
    public void deleteMatriculant(HttpServletRequest request, MatriculantDTO matriculantDTO);
    // Абитуериет есть в базе
    public void editNewMatriculant(HttpServletRequest request, MatriculantDTO matriculantDTO);

    // переход на новую страницу
    public void redirPage(HttpServletResponse response,String url) throws IOException;
    // forward страницы
    public void forwardPage(HttpServlet hs,HttpServletRequest request,HttpServletResponse response,String page) throws ServletException, IOException;
    // forward страницы с DTO
    public void forwardPage(HttpServlet hs,HttpServletRequest request,HttpServletResponse response,String page, MatriculantDTO matriculantDTO) throws ServletException, IOException;
    // Ошибка: Общая ошибка
    public void printErrorPage(HttpServletRequest request,HttpServletResponse response);
}
