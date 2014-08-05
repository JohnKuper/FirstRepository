
package kuper.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Сервлет отвечающий за добавление нового абонента в базу данных
 * @author Коробейников Дмитрий
 */
@WebServlet(name = "AddNewSubscriberServlet", urlPatterns = {"/AddNewSubscriberServlet"})

public class AddNewSubscriberServlet extends HttpServlet {

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
        //Если метод вернул true, в ответе от сервлета происходит переадресация клиента на allSubscribersView.jsp
        if (validator.AddNewSubscriberFormValidation()) {
            response.sendRedirect("allSubscribersView.jsp");
        } else {
            //В случае некорректных полей, переадресация обратно на index.jsp сохраняя ранее введенные данные
            String surname = request.getParameter("Surname");
            String name = request.getParameter("Name");
            String patronymic = request.getParameter("Patronymic");
            String address = request.getParameter("Address");
            String flatnumber = request.getParameter("Flatnumber");
            String dateofRegistration = request.getParameter("DateofRegistration");
            String errormessage = "Введите корректные данные";
            request.setAttribute("Surname", surname);
            request.setAttribute("Name", name);
            request.setAttribute("Patronymic", patronymic);
            request.setAttribute("Address", address);
            request.setAttribute("Flatnumber", flatnumber);
            request.setAttribute("DateofRegistration", dateofRegistration);
            request.setAttribute("errorMessage", errormessage);
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
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
        //Передача обработки запроса в метод processRequest
        processRequest(request, response);

    }

}
