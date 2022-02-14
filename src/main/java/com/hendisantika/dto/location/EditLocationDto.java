package com.hendisantika.dto.location;

import com.hendisantika.domain.Location;

import javax.validation.constraints.NotBlank;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 14/02/22
 * Time: 08.15
 * To change this template use File | Settings | File Templates.
 */
public class EditLocationDto extends LocationDto {

    @NotBlank
    private String name;

    public EditLocationDto() {
    }

    public EditLocationDto(Location location) {
        super(location);
        this.name = location.getName();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EditLocationDto{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}
