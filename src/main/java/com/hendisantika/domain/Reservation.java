package com.hendisantika.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 14/02/22
 * Time: 08.11
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "RESERVATION")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, unique = true)
    @Getter
    @Setter
    private Long id;

    @Column(name = "DATE")
    @Getter
    @Setter
    private LocalDateTime reservationDate;

    @Column(name = "DESCFIPTION")
    @Getter
    @Setter
    private String description;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    @Getter
    @Setter
    private User reserver;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reservation that = (Reservation) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", reservationDate=" + reservationDate +
                ", description='" + description + '\'' +
                ", reserver.id=" + reserver.getId() +
                '}';
    }
}
