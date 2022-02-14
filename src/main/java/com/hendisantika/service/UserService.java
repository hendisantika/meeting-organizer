package com.hendisantika.service;

import com.hendisantika.domain.User;
import com.hendisantika.domain.VerificationToken;
import com.hendisantika.dto.RegistrationFormDto;
import com.hendisantika.dto.profile.ProfileInfoDto;
import com.hendisantika.dto.profile.ProfileMailDto;
import com.hendisantika.dto.profile.ProfilePasswordDto;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 14/02/22
 * Time: 08.32
 * To change this template use File | Settings | File Templates.
 */
public interface UserService {

    boolean isEmailAlreadyTaken(String email);

    boolean passwordMatchesStoredPassword(String password, User user);

    User registerUser(RegistrationFormDto dto);

    void rollbackUserRegistration(User user);

    User saveUserAndFlush(User user);

    User findOne(Long id);

    User findOneByEmail(String email);

    void createVerificationToken(User user, String token);

    void updateUserProfile(User user, ProfileInfoDto dto);

    void updateUserProfile(User user, ProfileMailDto dto);

    void updateUserProfile(User user, ProfilePasswordDto dto);

    VerificationToken getVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String email);
}
