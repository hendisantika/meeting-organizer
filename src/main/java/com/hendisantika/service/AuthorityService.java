package com.hendisantika.service;

import com.hendisantika.domain.Authority;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 14/02/22
 * Time: 08.21
 * To change this template use File | Settings | File Templates.
 */
public interface AuthorityService {

    Authority findAuthorityByNameCreateAuthorityIfNotFound(String authorityName);

    Authority saveAuthority(String authorityName);
}
