package ru.diasoft.message.api.dto.sms;

import ru.diasoft.message.api.model.sms.ISenderSms;

import java.util.Objects;

public class SenderSmsDto implements ISenderSms {

    private String name;
    private String login;
    private String password;

    public SenderSmsDto() { }

    public SenderSmsDto(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SenderSms{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SenderSmsDto senderSmsDto = (SenderSmsDto) o;
        return Objects.equals(login, senderSmsDto.login) &&
                Objects.equals(password, senderSmsDto.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }
}
