package org.example;

public class CreateCourier {
    private String login;
    private String password;
    private String firstName;

    public CreateCourier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }
    public CreateCourier() {

    }
    public String getLogin() {
        return login;
    }

    public CreateCourier setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CreateCourier setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CreateCourier setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
}
