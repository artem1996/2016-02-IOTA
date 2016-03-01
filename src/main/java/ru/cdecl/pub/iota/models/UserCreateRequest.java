package ru.cdecl.pub.iota.models;

import org.jetbrains.annotations.NotNull;

public class UserCreateRequest extends UserProfile {

    @NotNull
    private String password;

    public UserCreateRequest() {
        password = "";
    }

    public UserCreateRequest(@NotNull String login, @NotNull String email, @NotNull String password) {
        super(login, email);
        this.password = password;
    }

    @NotNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }
}
