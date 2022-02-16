package com.hendisantika.event;

import com.hendisantika.domain.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 16/02/22
 * Time: 10.25
 * To change this template use File | Settings | File Templates.
 */
@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {

    private User user;
    private String tokenConfirmationUrl;
    private Locale locale;

    /**
     * Create new event and pass parameters needed for email message
     *
     * @param source               registered user's object
     * @param tokenConfirmationUrl url of currently working application
     * @param locale               needed for messages properties
     */
    public RegistrationCompleteEvent(Object source, String tokenConfirmationUrl, Locale locale) {
        super(source);

        this.user = (User) source;
        this.tokenConfirmationUrl = tokenConfirmationUrl;
        this.locale = locale;
    }
}
