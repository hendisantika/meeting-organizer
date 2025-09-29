package com.hendisantika.domain;

import com.hendisantika.dto.location.LocationDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
public class Location {

    @Id
    @Column(name = "ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "MAX_MEMBERS", nullable = false)
    private Integer maxMembers;

    @OneToMany
    @JoinColumn(name = "LOCATION_ID")
    private List<Reservation> reservations;

    @JoinColumn(name = "CREATED_BY", updatable = false)
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
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
