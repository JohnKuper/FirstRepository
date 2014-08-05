package kuper.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Сервлет отвечающий за изменение данных абонента в базе данных
 *
 * @author Коробейников Дмитрий
 */
@WebServlet(name = "UpdateSubscriberServlet", urlPatterns = {"/UpdateSubscriberServlet"})
public class UpdateSubscriberServlet extends HttpServlet {

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
        //Проверка входных данных на корректность через валидатор
        InputDataValidator validator = new InputDataValidator(request);
        //Если метод вернул true, в ответе от сервлета происходит переадресация клиента на allSubscribersView.jsp с positivemessage
        if (validator.UpdateSubscriberFormValidation()) {

            String positivemessage = "Данные были успешно изменены.";
            request.setAttribute("positiveMessage", positivemessage);
            RequestDispatcher rd = request.getRequestDispatcher("/allSubscribersView.jsp");
            rd.forward(request, response);
        //Если метод вернул false, в ответе от сервлета происходит переадресация клиента на allSubscribersView.jsp с erroremessage
        } else {
            String errormessage = "Данные не были изменены. Введите корректные данные.";
            request.setAttribute("errorMessage", errormessage);
            RequestDispatcher rd = request.getRequestDispatcher("/allSubscribersView.jsp");
            rd.forward(request, response);
        }
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
        processRequest(request, response);
    }

}
