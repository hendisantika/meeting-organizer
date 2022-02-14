package com.hendisantika.domain;

import com.hendisantika.dto.RegistrationFormDto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 14/02/22
 * Time: 08.13
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "USER")
public class User
        implements UserDetails {

    @Id
    @Column(name = "ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Column(name = "FIRSTNAME", nullable = false)
    @Getter
    @Setter
    private String firstName;

    @Column(name = "LASTNAME", nullable = false)
    @Getter
    @Setter
    private String lastName;

    @Column(name = "PASSWORD", nullable = false)
    @Setter
    private String password;

    @Column(name = "EMAIL", nullable = false, unique = true)
    @Getter
    @Setter
    private String email;

    @Column(name = "PHONE")
    @Getter
    @Setter
    private String phone;

    @Column(name = "ENABLED")
    @Setter
    @Type(type = "numeric_boolean")
    private boolean enabled;

    @Column(name = "ACCOUNT_NOT_EXPIRED")
    @Setter
    @Type(type = "numeric_boolean")
    private boolean accountNonExpired;

    @Column(name = "CREDENTIALS_NOT_EXPIRED")
    @Setter
    @Type(type = "numeric_boolean")
    private boolean credentialsNonExpired;

    @Column(name = "ACCOUNT_NOT_LOCKED")
    @Setter
    @Type(type = "numeric_boolean")
    private boolean accountNonLocked;

    @Column(name = "PROFILE_PICTURE")
    @Lob
    @Getter
    @Setter
    private byte[] profilePicture;


    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "USER_AUTHORITY",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID")}
    )
    @Setter
    private Set<Authority> authorities;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
            , mappedBy = "users")
    @Getter
    @Setter
    private List<Meeting> meetings;

    public User() {
        authorities = new HashSet<>();
        meetings = new LinkedList<>();
        enabled = false;
        accountNonExpired = true;
        accountNonLocked = true;
        credentialsNonExpired = true;
    }

    public User(RegistrationFormDto dto) {
        this.authorities = new HashSet<>();
        this.meetings = new LinkedList<>();
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        enabled = false;
        accountNonExpired = true;
        accountNonLocked = true;
        credentialsNonExpired = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", enabled=" + enabled +
                ", accountNonExpired=" + accountNonExpired +
                ", credentialsNonExpired=" + credentialsNonExpired +
                ", accountNonLocked=" + accountNonLocked +
                '}';
    }
}
