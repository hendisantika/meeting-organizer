package com.hendisantika.service;

import com.hendisantika.domain.User;
import com.hendisantika.domain.VerificationToken;
import com.hendisantika.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class TokenManagementService implements TokenService {

    private final VerificationTokenRepository tokenRepository;

    @Autowired
    public TokenManagementService(VerificationTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public VerificationToken saveAndFlush(VerificationToken token) {
        return tokenRepository.saveAndFlush(token);
    }

    @Override
    public VerificationToken findByUser(User user) {
        return tokenRepository.findByUser(user);
    }

    @Override
    public VerificationToken findByToken(String token) {
        return tokenRepository.findByToken(token);
    }
}
