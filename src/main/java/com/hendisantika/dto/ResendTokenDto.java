package com.hendisantika.dto;

import com.hendisantika.validation.ValidEmail;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/02/22
 * Time: 21.59
 * To change this template use File | Settings | File Templates.
 */
@Data
public class ResendTokenDto {
    @ValidEmail
    private String email;

    @Override
    public String toString() {
        return "ResendTokenDto{" +
                "email='" + email + '\'' +
                '}';
    }
}
