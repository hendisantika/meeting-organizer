package com.hendisantika.service;

import com.hendisantika.domain.Authority;
import com.hendisantika.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class AuthorityManagementService implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public Authority findAuthorityByNameCreateAuthorityIfNotFound(String authorityName) {
        Authority authority = authorityRepository.findByAuthority(authorityName);
        authority = (authority == null) ? saveAuthority(authorityName) : authority;

        return authority;
    }

    @Override
    public Authority saveAuthority(String authorityName) {
        return authorityRepository.saveAndFlush(new Authority(authorityName));
    }
}
