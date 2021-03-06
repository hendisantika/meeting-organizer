package com.hendisantika.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/02/22
 * Time: 21.50
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@ComponentScan(basePackages = {"com.hendisantika"})
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private static final String[] PUBLIC_MATCHERS = {
            "/webjars/**",
            "/css/**",
            "/js/**",
            "/images/**",
            "/",
            "/register",
            "/register/confirm",
            "/register/resendToken"
    };

    private static final String SALT = "salt by Meeting Organizer :)";
}
