package com.hendisantika.config;

import com.hendisantika.domain.Authority;
import com.hendisantika.domain.User;
import com.hendisantika.repository.AuthorityRepository;
import com.hendisantika.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 10/11/25
 * Time: 06:30
 * To change this template use File | Settings | File Templates.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.email:admin@meetingorganizer.com}")
    private String adminEmail;

    @Value("${app.admin.password:admin123}")
    private String adminPassword;

    @Value("${app.admin.firstname:Admin}")
    private String adminFirstName;

    @Value("${app.admin.lastname:User}")
    private String adminLastName;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        log.info("Starting data initialization...");

        // Create authorities if they don't exist
        createAuthorityIfNotExists("ROLE_USER");
        createAuthorityIfNotExists("ROLE_ADMIN");

        // Create default admin user if it doesn't exist
        createDefaultAdminUser();

        log.info("Data initialization completed.");
    }

    private void createAuthorityIfNotExists(String authorityName) {
        Authority authority = authorityRepository.findByAuthority(authorityName);
        if (authority == null) {
            authority = new Authority(authorityName);
            authorityRepository.save(authority);
            log.info("Created authority: {}", authorityName);
        } else {
            log.info("Authority already exists: {}", authorityName);
        }
    }

    private void createDefaultAdminUser() {
        User existingAdmin = userRepository.findByEmail(adminEmail);

        if (existingAdmin == null) {
            // Fetch authorities - they are managed entities within the transaction
            Authority roleUser = authorityRepository.findByAuthority("ROLE_USER");
            Authority roleAdmin = authorityRepository.findByAuthority("ROLE_ADMIN");

            if (roleUser == null || roleAdmin == null) {
                log.error("Cannot create admin user: required authorities not found");
                return;
            }

            User admin = new User();
            admin.setFirstName(adminFirstName);
            admin.setLastName(adminLastName);
            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setPhone("+1234567890");
            admin.setEnabled(true);
            admin.setAccountNonExpired(true);
            admin.setAccountNonLocked(true);
            admin.setCredentialsNonExpired(true);

            // Assign both USER and ADMIN roles
            Set<Authority> authorities = new HashSet<>();
            authorities.add(roleUser);
            authorities.add(roleAdmin);
            admin.setAuthorities(authorities);

            userRepository.save(admin);

            log.info("========================================");
            log.info("Default Admin User Created Successfully!");
            log.info("========================================");
            log.info("Email: {}", adminEmail);
            log.info("Password: {}", adminPassword);
            log.info("========================================");
            log.info("Please change the password after first login!");
            log.info("========================================");
        } else {
            log.info("Default admin user already exists: {}", adminEmail);
        }
    }
}
