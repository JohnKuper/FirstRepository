package kuper.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Сервлет отвечающий за удаление абонента из базы данных
 * @author Коробейников Дмитрий
 */
@WebServlet(name = "DeleteSubscriberServlet", urlPatterns = {"/DeleteSubscriberServlet"})
public class DeleteSubscriberServlet extends HttpServlet {
    
    /**
     * Метод вызывается из метода doPost() и берет на себя обработку запроса.
     * @param request Объект расширяющий интерфейс {@link javax.servlet.ServletRequest} и содержащий данные запроса клиента для HTTP сервлета.
     * @param response Объект расширяющий интерфейс {@link javax.servlet.ServletResponse} и обеспечивающий HTTP функциональность при отправке ответа клиенту.
     * @throws ServletException Общее исключение сервлета если произошли какие-то трудности.
     * @throws IOException Общее исключение связанное с прерванными или неудачными операциями ввода/вывода.
     */

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Установка кодировки в формат UTF-8 (Юникод)
        request.setCharacterEncoding("UTF-8");
        //Считывание ID абонента из запроса
        int id = Integer.parseInt(request.getParameter("delId"));
        //Создание объекта отвечающего за действия добавления, удаления, изменения информации об абонентах в базе данных
        SubscriberController subcontroller = new SubscriberController();
        //Удаление абонента с данным ID из базы данных
        subcontroller.deleteSubscriber(id);
        //В ответе от сервлета происходит переадресация клиента на allSubscribersView.jsp
        response.sendRedirect("allSubscribersView.jsp");
    }

   
    /**
     * Метод вызывается при получении запроса с типом post от клиента.
     * @param request Объект расширяющий интерфейс {@link javax.servlet.ServletRequest} и содержащий данные запроса клиента для HTTP сервлета.
     * @param response Объект расширяющий интерфейс {@link javax.servlet.ServletResponse} и обеспечивающий HTTP функциональность при отправке ответа клиенту.
     * @throws ServletException Общее исключение сервлета если произошли какие-то трудности.
     * @throws IOException Общее исключение связанное с прерванными или неудачными операциями ввода/вывода.
     */
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Передача обработки запроса в метод processRequest
        processRequest(request, response);
    }

   
}
