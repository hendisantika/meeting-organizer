package com.hendisantika.service;

import com.hendisantika.domain.User;
import com.hendisantika.domain.VerificationToken;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 14/02/22
 * Time: 08.30
 * To change this template use File | Settings | File Templates.
 */
public interface TokenService {

    VerificationToken saveAndFlush(VerificationToken token);

    VerificationToken findByUser(User user);

    VerificationToken findByToken(String token);

    void delete(VerificationToken token);
}
