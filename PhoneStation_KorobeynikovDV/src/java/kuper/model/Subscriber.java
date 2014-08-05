package kuper.model;

/**
 * Объект модели хранящий информацию о данных абонента
 * @author Дмитрий Коробейников
 */
public class Subscriber {
    private int ID;
    private String Surname;
    private String Name;
    private String Patronymic;
    private String Address;
    private String Flatnumber;
    private String DateofRegistration;
    private String Tarrif;

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        this.Surname = surname;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getPatronymic() {
        return Patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.Patronymic = patronymic;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public String getFlatnumber() {

        return Flatnumber;
    }

    public void setFlatnumber(String flatnumber) {
        this.Flatnumber = flatnumber;
    }

    public String getDateofRegistration() {
        return DateofRegistration;
    }

    public void setDateofRegistration(String dateofRegistration) {
        this.DateofRegistration = dateofRegistration;
    }

    public String getTarrif() {
        return Tarrif;
    }

    public void setTarrif(String tarrif) {
        this.Tarrif = tarrif;
    }

    public int getID() {
        return ID;
    }

    public void setID(int id) {
        this.ID = id;
    }

}
