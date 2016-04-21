package ru.cdecl.pub.iota.services;

import ru.cdecl.pub.iota.models.UserProfile;


public abstract class AccountServiceTest {
    public UserProfile user;
    protected char[] password = {'r', 'o', 'o', 't'};
    public AccountService db;
}