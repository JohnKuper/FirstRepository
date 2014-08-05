package kuper.controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import kuper.model.Subscriber;

/**
 *
 * Анализирует данные из запроса и проверяет их на корректность
 *
 * @author Дмитрий Коробейников
 */
public class InputDataValidator {

    private final String Surname;
    private final String Name;
    private final String Patronymic;
    private final String Address;
    private final String Flatnumber;
    private final String DateofRegistration;
    private final String Tarrif;
    private final static Pattern letterspattern = Pattern.compile("^[а-яА-я]+$");
    private final static Pattern addresspattern = Pattern.compile("^[а-яА-я0-9]+[а-яА-я0-9.,\\s'-]+[а-яА-я0-9]+$");
    private final static Pattern numberspattern = Pattern.compile("^[0-9]+$");
    private final static Pattern datepattern = Pattern.compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
    HttpServletRequest httprequest;

    /**
     * Считываются данные из запроса и заносятся в переменные класса
     *
     * @param request Запрос поступивший от клиента
     * @throws ServletException Общее исключение сервлета если произошли какие-то трудности.
     * @throws IOException Общее исключение связанное с прерванными или неудачными операциями ввода/вывода.
     */
    public InputDataValidator(HttpServletRequest request) throws IOException, ServletException {

        this.Surname = request.getParameter("Surname");
        this.Name = request.getParameter("Name");
        this.Patronymic = request.getParameter("Patronymic");
        this.Address = request.getParameter("Address");
        this.Flatnumber = request.getParameter("Flatnumber");
        this.DateofRegistration = request.getParameter("DateofRegistration");
        this.Tarrif = request.getParameter("Tarrif");
        this.httprequest = request;
    }

    /**
     * Проверяет корректность введенных данных в поля формы для добавления
     * нового абонента.
     *
     * @return Если все поля заполнены верно, возвращает true, иначе false.
     */
    public boolean AddNewSubscriberFormValidation() {
        //Проверка полей на валидность
        if (AddressValidation() && LettersOnlyValidation()
                && NumbersOnlyValidation() && DateValidation()) {
            //Создание объекта subscriber и добавление его в базу
            Subscriber subscriber = new Subscriber();
            subscriber.setSurname(Surname);
            subscriber.setName(Name);
            subscriber.setPatronymic(Patronymic);
            subscriber.setAddress(Address);
            subscriber.setFlatnumber(Flatnumber);
            subscriber.setDateofRegistration(DateofRegistration);
            subscriber.setTarrif(Tarrif);
            SubscriberController subcontroller = new SubscriberController();
            subcontroller.addNewSubscriber(subscriber);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Проверяет корректность введенных данных в поля формы для изменения
     * информации об абоненте.
     *
     * @return Если все поля заполнены верно, возвращает true, иначе false.
     */
    public boolean UpdateSubscriberFormValidation() {

        int id = Integer.parseInt(httprequest.getParameter("ID"));
        //Проверка полей на валидность
        if (DateValidation() && LettersOnlyValidation() && AddressValidation()
                && NumbersOnlyValidation()) {
            //Создание объекта subscriber и изменение информации абонента с ID=id
            Subscriber subscriber = new Subscriber();
            subscriber.setID(id);
            subscriber.setSurname(Surname);
            subscriber.setName(Name);
            subscriber.setPatronymic(Patronymic);
            subscriber.setAddress(Address);
            subscriber.setFlatnumber(Flatnumber);
            subscriber.setDateofRegistration(DateofRegistration);
            subscriber.setTarrif(Tarrif);
            SubscriberController subcontroller = new SubscriberController();
            subcontroller.updateSubscriber(subscriber);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Проверяет корректность введенных данных в поля где допустимы только буквы.
     *
     * @return Если все поля заполнены верно, возвращает true, иначе false.
     */
    public boolean LettersOnlyValidation() {
        Matcher surname = letterspattern.matcher(Surname);
        Matcher name = letterspattern.matcher(Name);
        Matcher patronymic = letterspattern.matcher(Patronymic);
        Matcher tarrif = letterspattern.matcher(Tarrif);
        return surname.matches() && name.matches() && patronymic.matches() && tarrif.matches();
    }

    /**
     * Проверяет корректность введенного адреса.
     *
     * @return Если адрес верен, возвращает true, иначе false.
     */
    public boolean AddressValidation() {
        Matcher address = addresspattern.matcher(Address);
        return address.matches();
    }

    /**
     * Проверяет корректность введенных данных в поля где допустимы только цифры.
     *
     * @return Если поле(поля) заполнены верно, возвращает true, иначе false.
     */
    public boolean NumbersOnlyValidation() {
        Matcher numbersonly = numberspattern.matcher(Flatnumber);
        return numbersonly.matches();
    }

    /**
     * Проверяет корректность введенной даты в
     * форматах(дд.мм.гггг,дд-мм-гггг,дд/мм/гггг)
     *
     * @return Если дата указана верно, возращает true, иначе false.
     */
    public boolean DateValidation() {
        Matcher date = datepattern.matcher(DateofRegistration);
        return date.matches();
    }

}
