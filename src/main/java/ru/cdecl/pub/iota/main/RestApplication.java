package ru.cdecl.pub.iota.main;

import ru.cdecl.pub.iota.endpoints.SessionEndpoint;
import ru.cdecl.pub.iota.endpoints.UserEndpoint;
import ru.cdecl.pub.iota.services.UserProfileService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
public class RestApplication extends Application {

    @Override
    public Set<Object> getSingletons() {
        final HashSet<Object> objects = new HashSet<>();

        UserProfileService userProfileService = new UserProfileService();

        objects.add(new UserEndpoint(userProfileService));
        objects.add(new SessionEndpoint(userProfileService));

        return objects;
    }

}
