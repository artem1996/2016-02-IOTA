package ru.cdecl.pub.iota.servlets;

import org.json.JSONObject;
import org.json.JSONWriter;
import org.jvnet.hk2.annotations.Service;
import ru.cdecl.pub.iota.exceptions.UserAlreadyExistsException;
import ru.cdecl.pub.iota.models.UserProfile;
import ru.cdecl.pub.iota.services.AccountService;
import ru.cdecl.pub.iota.servlets.base.JsonApiServlet;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
@Singleton
@WebServlet(asyncSupported = true)
public class UserServlet extends JsonApiServlet {

    @Inject
    AccountService accountService;

    public UserServlet(AccountService accountServer) {
        this.accountService = accountServer;
    }

    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final JSONWriter jsonWriter = getJsonWriterForHttpResponse(resp);
        jsonWriter.object();

        final JSONObject jsonRequest = getJsonObjectFromHttpRequest(req);
        final UserProfile newUserProfile = new UserProfile(
                jsonRequest.getString("login"),
                jsonRequest.getString("email")
        );
        final char[] newUserPassword = jsonRequest.getString("password").toCharArray();
        // todo: validation
        try {
            final long newUserId = accountService.createUser(newUserProfile, newUserPassword);
            jsonWriter.key("id").value(newUserId);
        } catch (UserAlreadyExistsException e) {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }

        resp.getWriter().println("Win!");
        jsonWriter.endObject();
    }

}
