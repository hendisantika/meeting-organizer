package com.hendisantika.dto.location;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 14/02/22
 * Time: 08.08
 * To change this template use File | Settings | File Templates.
 */
public abstract class LocationDto {

    @NotBlank
    @Getter
    @Setter
    private String description;

    @Min(value = 1)
    @Max(value = 300)
    @Getter
    @Setter
    private int maxMembers;

    public LocationDto() {
    }

    public LocationDto(Location location) {
        this.description = location.getDescription();
        this.maxMembers = location.getMaxMembers();
    }

    public abstract String getName();

    public abstract void setName(String name);

    @Override
    public String toString() {
        return "LocationDto{" +
                "description='" + description + '\'' +
                ", maxMembers=" + maxMembers +
                '}';
    }
}
