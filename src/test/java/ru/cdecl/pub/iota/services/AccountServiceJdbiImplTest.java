package ru.cdecl.pub.iota.services;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
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

public class AccountServiceJdbiImplTest extends AccountServiceTest {

    public AccountServiceJdbiImpl db;

    @Before
    public void setUp() throws Exception {
        DataSourceFactory dataSourceFactory = new DataSourceFactory();
        DataSource dataSource = dataSourceFactory.provide();
        this.db = new AccountServiceJdbiImpl(dataSource);
        this.user = new UserProfile("shrek", "shrek@mail.ru");
        long id = db.createUser(this.user, this.password);
        this.user.setId(id);
    }

    @After
    public void tearDown() throws Exception {
        this.db.deleteUser(this.user.getId());
    }

    @Test
    public void testCreateUser() throws Exception {
        UserProfile newUserProfile = new UserProfile("esin88", "esin88@mail.ru");
        long id = db.createUser(newUserProfile, this.password);
        newUserProfile.setId(id);
        UserProfile userDB = db.getUserProfile("esin88");
        assertTrue("Login not equal", newUserProfile.getLogin().equals(userDB.getLogin()));
        assertTrue("E-mail not equal", newUserProfile.getEmail().equals(userDB.getEmail()));
        this.db.deleteUser(newUserProfile.getId());
    }

    @Test
    public void testEditUser() throws Exception {
        char[] newPassword = new char[]{'1', '2', '3'};
        db.editUser(this.user.getId(), new UserProfile("esin88", "esin88@mail.ru"), newPassword);
        UserProfile userProfileDB = db.getUserProfile(this.user.getId());
        assertTrue("Login not equal", userProfileDB.getLogin().equals("esin88"));
        assertTrue("E-mail not equal", userProfileDB.getEmail().equals("esin88@mail.ru"));
    }

    @Test
    public void testDeleteUser() throws Exception {
        long idUser = this.user.getId();
        db.deleteUser(idUser);
        assertTrue("User existent", !db.isUserExistent(idUser));
    }

    @Test
    public void testGetUserId() throws Exception {
        assertTrue("ID not equal", this.db.getUserId(this.user.getLogin()).equals(this.user.getId()));
    }

    @Test
    public void testGetUserProfileLogin() throws Exception {
        UserProfile user = this.db.getUserProfile(this.user.getLogin());
        assertTrue("ID not equal", user.getId().equals(this.user.getId()));
        assertTrue("Email not equal", user.getEmail().equals(this.user.getEmail()));
        assertTrue("Login not equal", user.getLogin().equals(this.user.getLogin()));
    }

    @Test
    public void testGetUserProfileId() throws Exception {
        UserProfile user = this.db.getUserProfile(this.user.getId());
        assertTrue("ID not equal", user.getId().equals(this.user.getId()));
        assertTrue("Email not equal", user.getEmail().equals(this.user.getEmail()));
        assertTrue("Login not equal", user.getLogin().equals(this.user.getLogin()));
    }

    @Test
    public void testIsUserPasswordCorrect() throws Exception {
        assertTrue("Password does not match", db.isUserPasswordCorrect(this.user.getId(), this.password));
    }

    @Test
    public void testIsUserExistentId() throws Exception {
        assertTrue("User no existent", db.isUserExistent(this.user.getId()));
    }

    @Test
    public void testIsUserExistentLogin() throws Exception {
        assertTrue("User no existent", db.isUserExistent(this.user.getLogin()));
    }
}

