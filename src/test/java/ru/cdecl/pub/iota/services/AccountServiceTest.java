package ru.cdecl.pub.iota.services;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.cdecl.pub.iota.models.UserProfile;
import static org.junit.Assert.*;
import ru.cdecl.pub.iota.services.AccountServiceJdbiImpl;
import ru.cdecl.pub.iota.main.DataSourceFactory;
import ru.cdecl.pub.iota.servlets.UserServlet;
import static org.mockito.Mockito.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.StringWriter;
import java.lang.String;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AccountServiceTest {
    public UserProfile user;
    protected char[] password = {'r', 'o', 'o', 't'};
    public AccountService db;
}