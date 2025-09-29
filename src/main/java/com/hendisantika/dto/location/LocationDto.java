package com.hendisantika.dto.location;

import com.hendisantika.domain.Location;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
public abstract class LocationDto {

    @NotBlank
    private String description;

    @Min(value = 1)
    @Max(value = 300)
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
