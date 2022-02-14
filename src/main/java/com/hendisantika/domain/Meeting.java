package com.hendisantika.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 14/02/22
 * Time: 08.12
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "MEETING")
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, unique = true)
    @Getter
    @Setter
    private Long id;

    @Column(name = "DESCRIPTION")
    @Getter
    @Setter
    private String description;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "USERS_MEETINGS",
            joinColumns = {@JoinColumn(name = "MEETING_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID")})
    @Getter
    @Setter
    private List<User> users;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "LOCALIZATION_ID")
    @Getter
    @Setter
    private Location location;

    public Meeting() {
        users = new LinkedList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Meeting meeting = (Meeting) o;

        return id.equals(meeting.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", location.id=" + location.getId() +
                '}';
    }
}
