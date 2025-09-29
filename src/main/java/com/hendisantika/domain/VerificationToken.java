package com.hendisantika.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 14/02/22
 * Time: 08.14
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "VERIFICATION_TOKEN")
@Getter
@Setter
public class VerificationToken {

    private static final int EXPIRATION_TIME_IN_HOURS = 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "TOKEN")
    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(nullable = false, name = "USER_ID")
    private User user;

    @Column(name = "EXPIRATION_TIME")
    private LocalDateTime expirationTime;

    public VerificationToken() {
    }

    public VerificationToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.expirationTime = calculateExpirationDate(EXPIRATION_TIME_IN_HOURS);
    }

    private LocalDateTime calculateExpirationDate(int expirationTimeInHours) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationTime = now.plusHours(expirationTimeInHours);
        return expirationTime;
    }

    public boolean isTokenExpired() {
        return LocalDateTime.now().isAfter(this.expirationTime);
    }

    public void updateExpirationTime() {
        this.expirationTime = calculateExpirationDate(EXPIRATION_TIME_IN_HOURS);
    }

    @Override
    public String toString() {
        return "VerificationToken{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", user.id=" + user.getId() +
                ", expirationTime=" + expirationTime +
                '}';
    }
}
