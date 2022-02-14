package com.hendisantika.service;

import com.hendisantika.domain.Location;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 14/02/22
 * Time: 08.24
 * To change this template use File | Settings | File Templates.
 */
public interface LocationService {

    boolean existsByNameIgnoreCase(String name);

    Location addNewLocation(Location location, String creatorEmail);

    Location saveAndFlush(Location location);

    List<Location> findAll();

    Location findOneById(Long id);

    boolean canEditLocation(Location location, String userEmail);

    boolean isLocationNameAvailable(String name, Location currentLocation);
}
