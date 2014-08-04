package matriculantDAO;

import models.InstructorDTO;
import models.MatriculantDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.*;

import static java.sql.DriverManager.getConnection;

/**
 * Created with IntelliJ IDEA.
 * User: VirtBox
 * Date: 25.06.14
 * Time: 21:46
 * To change this template use File | Settings | File Templates.
 */
public class MatriculantDAO {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        }
    }


    public synchronized static void addNewMatriculant(InstructorDTO login, MatriculantDTO DTO) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection(login.getHost(), login.getLogin(), login.getPassword());
            stmt = con.prepareStatement("INSERT into matriculant(faculty,name,ball,year,comment) values (?,?,?,?,?)");
            if (stmt instanceof PreparedStatement) {
                ((PreparedStatement) stmt).setString(1, DTO.getFaculty());
                ((PreparedStatement) stmt).setString(2, DTO.getName());
                ((PreparedStatement) stmt).setFloat(3, Float.parseFloat(DTO.getBall()));
                ((PreparedStatement) stmt).setInt(4, Integer.parseInt(DTO.getYear()));
                ((PreparedStatement) stmt).setString(5, DTO.getComment());
                ((PreparedStatement) stmt).execute();
            }
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {

                }
            }
        }

    }

    public synchronized static void removeMatriculant(InstructorDTO login, MatriculantDTO matriculant) throws SQLException {
        Statement stmt = null;
        Connection con = null;
        try {
            con = getConnection(login.getHost(), login.getLogin(), login.getPassword());
            stmt = con.prepareStatement("DELETE from matriculant where name=?");
            if (stmt instanceof PreparedStatement) {
                ((PreparedStatement) stmt).setString(1, matriculant.getName());
                ((PreparedStatement) stmt).execute();
            }
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {

                }
            }
        }
    }

    public synchronized static MatriculantDTO editMatriculant(InstructorDTO login, MatriculantDTO matriculant) throws SQLException {
        Statement stmt = null;
        Connection con = null;
        try {
            con = getConnection(login.getHost(), login.getLogin(), login.getPassword());
            stmt = con.prepareStatement("UPDATE matriculant set faculty=?,name=?,ball=?,year=?,comment=? where name=?");
            if (stmt instanceof PreparedStatement) {
                ((PreparedStatement) stmt).setString(1, matriculant.getFaculty());
                ((PreparedStatement) stmt).setString(2, matriculant.getName());
                ((PreparedStatement) stmt).setFloat(3, Float.parseFloat(matriculant.getBall()));
                ((PreparedStatement) stmt).setInt(4, Integer.parseInt(matriculant.getYear()));
                ((PreparedStatement) stmt).setString(5, matriculant.getComment());
                ((PreparedStatement) stmt).setString(6, matriculant.getName());
                ((PreparedStatement) stmt).execute();
            }
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {

                }
            }
        }
        return matriculant;
    }

    public synchronized static MatriculantDTO findMatriculant(InstructorDTO login, MatriculantDTO matriculant) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        MatriculantDTO matriculantDTO = null;
        try {
            con = getConnection(login.getHost(), login.getLogin(), login.getPassword());
            stmt = con.prepareStatement("SELECT faculty,name,ball,year,comment FROM matriculant where name=?");
            if (stmt instanceof PreparedStatement) {
                ((PreparedStatement) stmt).setString(1, matriculant.getName());
            }
            rs = ((PreparedStatement) stmt).executeQuery();
            while (rs.next()) {
                matriculantDTO = new MatriculantDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            }
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {

                }
            }
        }
        return matriculantDTO;

    }

    public synchronized static boolean login(HttpServletRequest request) throws ClassNotFoundException {
        Connection con = null;
        try {
            con = getConnection("jdbc:mysql://" + request.getParameter("host") + "/matriculant", request.getParameter("login"), request.getParameter("password"));
        } catch (SQLException e) {
            return false;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                }
            }
        }


        return true;
    }
}
