package Entities;

/**
 * Created by mrwah on 3/8/2018.
 */

public class Connexion {
    private String login;
    private String password;

    public Connexion(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Connexion(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
