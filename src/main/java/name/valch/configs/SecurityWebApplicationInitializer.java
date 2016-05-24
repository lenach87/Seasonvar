package name.valch.configs;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
}
