package models;

/**
 * Created with IntelliJ IDEA.
 * User: VirtBox
 * Date: 26.06.14
 * Time: 1:19
 * To change this template use File | Settings | File Templates.
 */
public class InstructorDTO {
    boolean isIS;
    private String login;
    private String password;
    private String host;

    public InstructorDTO(boolean IS) {
        isIS = IS;
    }

    public InstructorDTO(boolean IS, String login, String password, String host) {
        isIS = IS;
        this.login = login;
        this.password = password;
        this.host = host;
    }

    public boolean isIS() {
        return isIS;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getHost() {
        return host;
    }
}
