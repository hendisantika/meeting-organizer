package com.hendisantika.service;

import com.hendisantika.repository.AuthorityRepository;
import com.hendisantika.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 16/02/22
 * Time: 10.17
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UsersManagementService implements UserService {

    private final UserRepository userRepository;
    private final AuthorityService authorityService;
    private final AuthorityRepository authorityRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersManagementService(UserRepository userRepository,
                                  AuthorityService authorityService,
                                  AuthorityRepository authorityRepository,
                                  TokenService tokenService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityService = authorityService;
        this.authorityRepository = authorityRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }
}
