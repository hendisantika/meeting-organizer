package com.hendisantika.dto.location;

import com.hendisantika.validation.UniqueLocationName;

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
public class AddLocationDto extends LocationDto {

    @NotBlank
    @UniqueLocationName
    private String name;

    public AddLocationDto() {
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
        return "AddLocationDto{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}
