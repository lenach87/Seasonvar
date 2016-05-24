package name.valch.security;

import name.valch.SeasonvarApplication;
import name.valch.entity.SerialWithDates;
import name.valch.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Component
public class SerialPermissionEvaluator implements PermissionEvaluator {
    private static final Logger log = LoggerFactory.getLogger(SeasonvarApplication.class);
    @Override
    public boolean hasPermission(Authentication authentication,
                                 Object targetDomainObject, Object permission) {
        if(authentication == null) {

        return false;
        }
        SerialWithDates serial = (SerialWithDates) targetDomainObject;

        if(serial==null) {

            return true;
        }

        User currentUser = (User) authentication.getPrincipal();
        Set<SerialWithDates> serials = currentUser.getProfile().getSerials();
        List<Long> ids = new ArrayList<>();
        for (SerialWithDates s:serials) {
            ids.add (s.getId());
        }

        return (ids.contains(serial.getId()));
    }

    @Override
    public boolean hasPermission(Authentication authentication,
                                 Serializable targetId, String targetType, Object permission) {
        throw new UnsupportedOperationException();
    }

}


