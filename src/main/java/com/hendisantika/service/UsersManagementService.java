package com.hendisantika.service;

import com.hendisantika.domain.Authority;
import com.hendisantika.domain.User;
import com.hendisantika.domain.VerificationToken;
import com.hendisantika.dto.RegistrationFormDto;
import com.hendisantika.dto.profile.ProfileInfoDto;
import com.hendisantika.dto.profile.ProfileMailDto;
import com.hendisantika.repository.AuthorityRepository;
import com.hendisantika.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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

    @Override
    public boolean isEmailAlreadyTaken(String email) {
        Long usersWithEqualEmail = userRepository.countAllByEmailIgnoreCase(email);
        return usersWithEqualEmail > 0;
    }

    @Override
    public User registerUser(RegistrationFormDto dto) {
        User user = new User(dto);
        Set<Authority> authorities = new HashSet<>();
        authorities.add(authorityService.findAuthorityByNameCreateAuthorityIfNotFound("USER"));

        user.setAuthorities(authorities);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.saveAndFlush(user);
    }

    @Override
    public void rollbackUserRegistration(User userToDelete) {
        VerificationToken tokenToDelete = tokenService.findByUser(userToDelete);

        List<Authority> allAuthorities = authorityRepository.findAll();
        for (Authority auth : allAuthorities) {
            for (User userWithAuthority : auth.getUsers()) {
                if (userWithAuthority.getId().equals(userToDelete.getId())) {
                    auth.getUsers().remove(userWithAuthority);
                }
            }
        }
        authorityRepository.save(allAuthorities);
        tokenService.delete(tokenToDelete);
        userRepository.delete(userToDelete);
    }

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken verificationToken = new VerificationToken(token, user);
        tokenService.saveAndFlush(verificationToken);
    }

    @Override
    public User findOne(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public VerificationToken getVerificationToken(String token) {
        return tokenService.findByToken(token);
    }

    @Override
    public User saveUserAndFlush(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public VerificationToken generateNewVerificationToken(String email) {
        User user = userRepository.findByEmail(email);
        VerificationToken actualToken = tokenService.findByUser(user);

        actualToken.setToken(UUID.randomUUID().toString());
        actualToken.updateExpirationTime();

        actualToken = tokenService.saveAndFlush(actualToken);
        return actualToken;
    }

    @Override
    public boolean passwordMatchesStoredPassword(String password, User user) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public void updateUserProfile(User user, ProfileInfoDto dto) {
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhone(dto.getPhone());

        saveUserAndFlush(user);
    }

    @Override
    public void updateUserProfile(User user, ProfileMailDto dto) {
        user.setEmail(dto.getEmail());
        saveUserAndFlush(user);
    }
}
