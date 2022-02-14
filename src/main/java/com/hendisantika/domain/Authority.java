package com.hendisantika.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 14/02/22
 * Time: 08.10
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "AUTHORITY")
public class Authority
        implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, unique = true)
    @Getter
    @Setter
    private Long id;

    @Column(name = "AUTHORITY", nullable = false, unique = true)
    @Setter // Getter is overrided
    private String authority;

    @ManyToMany(mappedBy = "authorities")
    @Getter
    @Setter
    private Set<User> users;

    public Authority() {
        this.users = new HashSet<>();
    }

    public Authority(String authority) {
        this.authority = authority;
        this.users = new HashSet<>();
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Authority authority = (Authority) o;

        return id.equals(authority.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", authority='" + authority + '\'' +
                '}';
    }
}
