package com.hendisantika.domain;

import com.hendisantika.dto.location.LocationDto;
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
 * Time: 08.11
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "LOCATION")
public class Location {

    @Id
    @Column(name = "ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Column(name = "NAME", nullable = false)
    @Getter
    @Setter
    private String name;

    @Column(name = "DESCRIPTION")
    @Getter
    @Setter
    private String description;

    @Column(name = "MAX_MEMBERS", nullable = false)
    @Getter
    @Setter
    private Integer maxMembers;

    @OneToMany
    @JoinColumn(name = "LOCATION_ID")
    @Getter
    @Setter
    private List<Reservation> reservations;

    @JoinColumn(name = "CREATED_BY", updatable = false)
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private User createdBy;

    public Location() {
        this.reservations = new LinkedList<>();
    }

    public Location(LocationDto dto) {
        this();
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.maxMembers = dto.getMaxMembers();
    }

    public void updateFromDto(LocationDto dto) {
        //TODO: create Location <-> LocationDto converter
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.maxMembers = dto.getMaxMembers();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        return id.equals(location.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", maxMembers=" + maxMembers +
                ", createdBy=" + createdBy +
                '}';
    }
}
