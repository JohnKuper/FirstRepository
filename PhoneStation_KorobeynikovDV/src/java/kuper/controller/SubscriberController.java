package kuper.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import kuper.model.Subscriber;

/**
 * Отвечает за операции добавления, удаления, изменения информации об абонентах
 * @author Коробейников Дмитрий
 */
public class SubscriberController {

    /**
     * Добавляет нового абонента в таблицу subscribers
     * @param sub Объект класса Subscriber хранящий информацию об абоненте
     */
    public void addNewSubscriber(Subscriber sub) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            InitialContext ctx = new InitialContext();
            //Получение параметров настроек из контекста приложения
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/PhoneStationDB");
            //Подключение к базе данных
            con = ds.getConnection();
            //Создание объекта параметризованных инструкций для добавления данных в БД
            pstmt = con.prepareStatement("INSERT INTO subscribers(Surname,Name,Patronymic,Address,Flatnumber,DateofRegistration,Tarrif) VALUES(?,?,?,?,?,?,?)");
            //Занесение данных в объект инструкций
            pstmt.setString(1, sub.getSurname());
            pstmt.setString(2, sub.getName());
            pstmt.setString(3, sub.getPatronymic());
            pstmt.setString(4, sub.getAddress());
            pstmt.setString(5, sub.getFlatnumber());
            pstmt.setString(6, sub.getDateofRegistration());
            pstmt.setString(7, sub.getTarrif());
            //Выполнение инструкций и занесение данных в БД
            pstmt.execute();
        } catch (SQLException sqlex) {
            System.out.println("Не удалось выполнить операции связанные с базой данных.");
            sqlex.printStackTrace();
        } catch (NamingException nex) {
            System.out.println("Ошибка при работе с контекстом приложения. Не найдены имена или объекты.");
            nex.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqlex) {
                System.out.println("Не удалось выполнить операции связанные с базой данных.");
                sqlex.printStackTrace();
            }
        }
    }


    /**
     * Удаляет абонента с определенным ID из базы данных
     * @param id ID абонента который будет удален из базы данных
     */
        public void deleteSubscriber(int id) {
        Connection con = null;
        Statement stmt = null;
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/PhoneStationDB");
            //Подключение к базе данных
            con = ds.getConnection();
            stmt = con.createStatement();
            //Удаление абонента c ID=id из таблицы subscribers
            stmt.execute("DELETE FROM subscribers WHERE ID=" + String.valueOf(id));
        } catch (SQLException sqlex) {
            System.out.println("Не удалось выполнить операции связанные с базой данных.");
            sqlex.printStackTrace();
        } catch (NamingException nex) {
            System.out.println("Ошибка при работе с контекстом приложения. Не найдены имена или объекты.");
            nex.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqlex) {
                System.out.println("Не удалось выполнить операции связанные с базой данных.");
                sqlex.printStackTrace();
            }
        }
    }


    /**
     * Обновляет информацию об абоненте c определенным ID в таблице subscribers
     * @param sub Объект класса Subscriber чьи данные будут обновлены
     */
        public void updateSubscriber(Subscriber sub) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/PhoneStationDB");
            //Подключение к базе данных
            con = ds.getConnection();
            //Создание объекта параметризованных инструкций для изменения данных в БД по определенному ID
            pstmt = con.prepareStatement("UPDATE subscribers SET Surname=?, Name=?, Patronymic=?, Address=?, Flatnumber=?, DateofRegistration=?, Tarrif=? WHERE ID=?");
            pstmt.setString(1, sub.getSurname());
            pstmt.setString(2, sub.getName());
            pstmt.setString(3, sub.getPatronymic());
            pstmt.setString(4, sub.getAddress());
            pstmt.setString(5, sub.getFlatnumber());
            pstmt.setString(6, sub.getDateofRegistration());
            pstmt.setString(7, sub.getTarrif());
            pstmt.setInt(8, sub.getID());
            //Выполнение инструкций и занесение данных в БД
            pstmt.executeUpdate();
        } catch (SQLException sqlex) {
            System.out.println("Не удалось выполнить операции связанные с базой данных.");
            sqlex.printStackTrace();

        } catch (NamingException nex) {
            System.out.println("Ошибка при работе с контекстом приложения. Не найдены имена или объекты.");
            nex.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqlex) {
                System.out.println("Не удалось выполнить операции связанные с базой данных.");
                sqlex.printStackTrace();
            }
        }
    }


    /**
     * Считывает данные абонента с определенным ID из таблицы subscribers
     * @param id ID абонента данные которого нужно считать
     * @return Объект класса Subscriber со считанными данными
     */
        public Subscriber getSubcriber(int id) {
        Subscriber sub = null;
        Connection con = null;
        Statement stmt = null;
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/PhoneStationDB");
            //Подключение к таблице
            con = ds.getConnection();
            stmt = con.createStatement();
            //Создание курсора с данными из таблицы  по определенному ID абонента
            ResultSet rs = stmt.executeQuery("SELECT * FROM subscribers WHERE ID=" + id);
            //Установка курсора на первую строчку и чтение данных из столбцов по их индексу
            if (rs.next()) {
                sub = new Subscriber();
                sub.setID(rs.getInt(1));
                sub.setSurname(rs.getString(2));
                sub.setName(rs.getString(3));
                sub.setPatronymic(rs.getString(4));
                sub.setAddress(rs.getString(5));
                sub.setFlatnumber(rs.getString(6));
                sub.setDateofRegistration(rs.getString(7));
                sub.setTarrif(rs.getString(8));
            }
        } catch (SQLException sqlex) {
            System.out.println("Не удалось выполнить операции связанные с базой данных.");
            sqlex.printStackTrace();

        } catch (NamingException nex) {
            System.out.println("Ошибка при работе с контекстом приложения. Не найдены имена или объекты.");
            nex.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqlex) {
                System.out.println("Не удалось выполнить операции связанные с базой данных.");
                sqlex.printStackTrace();
            }
        }
        return sub;
    }


    /**
     * Считывает данные о всех абонентах в таблице subscribers
     * @return Массив ArrayList объектов типа Subscriber упорядоченых по ID.
     */
        public List getSubscribers() {
        List<Subscriber> list;
        list = new ArrayList<Subscriber>();
        Connection con = null;
        Statement stmt = null;
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/PhoneStationDB");
            //Подключение к базе данных
            con = ds.getConnection();
            stmt = con.createStatement();
            //Создание курсора с данными из таблицы, упорядоченных по ID 
            ResultSet rs = stmt.executeQuery("SELECT * FROM subscribers ORDER BY ID");
            //Установка курсора на первую строчку (далее на следующие) и чтение данных из столбцов по их индексу пока данные не закончатся
            while (rs.next()) {
                Subscriber sub = new Subscriber();
                sub.setID(rs.getInt(1));
                sub.setSurname(rs.getString(2));
                sub.setName(rs.getString(3));
                sub.setPatronymic(rs.getString(4));
                sub.setAddress(rs.getString(5));
                sub.setFlatnumber(rs.getString(6));
                sub.setDateofRegistration(rs.getString(7));
                sub.setTarrif(rs.getString(8));
                //Добавление объекта Subscriber в ArrayList<Subscriber>
                list.add(sub);
            }
        } catch (SQLException sqlex) {
            System.out.println("Не удалось выполнить операции связанные с базой данных.");
            sqlex.printStackTrace();

        } catch (NamingException nex) {
            System.out.println("Ошибка при работе с контекстом приложения. Не найдены имена или объекты.");
            nex.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqlex) {
                System.out.println("Не удалось выполнить операции связанные с базой данных.");
                sqlex.printStackTrace();

            }
        }
        return list;
    }

}
